package Model;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Random;

public class Model {

    final private BigInt HowManyBytes;
    final private int accuracyMilerTest;
    final private String AddedWord = "159";
    final private int AddedWordLength = this.AddedWord.length();
    private BigInt leftPRange, leftQRange, d, publickey;
    private byte[] plainText,encodedText, decodedText, key, plainText2;
   

    public BigInt p,q;
    public BigInt yp = new BigInt("0");
    public BigInt yq = new BigInt("0");
   
//******************************************************************************
    private void fill_P_KeyVariableRange() {
        this.leftPRange = new BigInt("10").pow(new BigInt(Integer.toString(3 + this.AddedWordLength -2)));//.pow(new BigInt(Integer.toString(this.AddedWord.length())));
    }

//******************************************************************************
    private void fill_Q_KeyVariableRange() {
        this.leftQRange = new BigInt("10").pow(new BigInt(Integer.toString(2 + this.AddedWordLength -2)));//.pow(new BigInt(Integer.toString(this.AddedWord.length())));
    }

//******************************************************************************
    public boolean isEven(BigInt x) {
        return x.div(new BigInt("2"), true).toString().equals(new BigInt("0").toString());
    }

//******************************************************************************
    public BigInt maxPowerOfTwo(BigInt x) {
        BigInt result;
        BigInt breakVariable = new BigInt("0");
        BigInt two = new BigInt("2");
        BigInt counter = new BigInt("1");
        BigInt temp = new BigInt("1");

        boolean flag = true;
        while (flag) {
            result = x.div(two.pow(counter), true);
            if (result.toString().equals(breakVariable.toString())) {
                counter = counter.add(temp);
            } else {
                flag = false;
            }
        }
        return counter.sub(temp); // return s
    }

//******************************************************************************
    public boolean testMileraRabina(BigInt p) {
        BigInt temp = new BigInt("1");
        BigInt two = new BigInt("2");
        BigInt one = new BigInt("1");
        BigInt randomDigit;
        BigInt x;

// sprawdzamy czy liczba nie jest parzysta, jezeli jest nie jest ona liczbą pierwszą        
        if (isEven(p)) {
            return false;
        }
// Obliczamy Maksymalną potęgę 2 dzieląc x-1
        BigInt s = maxPowerOfTwo(p.sub(one));

// Obliczamy wartosć d = 
        this.d = p.div(new BigInt("2").pow(s), false);

        boolean flag = true;
        for (int i = 0; i < this.accuracyMilerTest; i++) {
            randomDigit = randomDigit(two, p.sub(two));
            x = randomDigit.power_modulo_fast(this.d, p);
            if (x.equals(one) || x.equals(p.sub(one))) {
                continue;
            }

            for (BigInt j = new BigInt("1"); (j.isSmmaler(s)) && (x.notequals(p.sub(one))); j = j.add(one)) {
                x = x.power_modulo_fast(two, p);
                if (x.equals(one)) {
                    flag = false;
                    break;
                }
            }
            if (!flag) {
                break;
            }
            if (x.notequals(p.sub(one))) {
                flag = false;
                break;
            }
        }
        return flag;
    }

//******************************************************************************
    public void choose_P_AND_Q() {
        System.err.println("Choosing P and Q");
        BigInt one = new BigInt("1");
        BigInt two = new BigInt("2");
        BigInt three = new BigInt("3");
        BigInt four = new BigInt("4");

        this.p = randomDigit(this.leftPRange, this.leftPRange.add(this.leftPRange.div(two, false)));
        while (!(!this.p.div(four, true).notequals(three) && testMileraRabina(this.p))) {
            this.p = this.p.add(one);
        }
        System.out.println("P was choosen: " + this.p.toString());

        this.q = randomDigit(this.leftQRange, this.leftQRange.add(this.leftQRange.div(two, false)));
        while (!(!this.q.div(four, true).notequals(three) && testMileraRabina(this.q)) && !this.p.equals(this.q)) {
            this.q = this.q.add(one);
        }
        System.out.println("Q was choosen: " + this.q.toString());
        this.publickey = this.p.mul(this.q);
    }

//******************************************************************************
    public BigInt randomDigit(BigInt left, BigInt right) {
        String valueLeftAsString = left.toString();
        String valueRightAsString = right.toString();
        int valueLeftLength = valueLeftAsString.length();
        int valueRightLength = valueRightAsString.length();
        StringBuilder value = new StringBuilder();
        Random rand = new Random();
        int digit;
        String ndigit;

        int range = rand.nextInt(valueRightLength - valueLeftLength + 1) + (valueLeftLength);
        int[] result = new int[range];

        //Jezeli wylosowany zakres jest mniejszy of cyfry 
        if (range < valueRightLength && range > valueLeftLength) {

            for (int i = 0; i < range; i++) {
                if (i == 0) {
                    result[i] = rand.nextInt(9) + 1;
                } else {
                    result[i] = rand.nextInt(10);
                }
            }
        } else if (range < valueRightLength && range == valueLeftLength) {

            for (int i = 0; i < range; i++) {
                ndigit = valueLeftAsString.substring(i, i + 1);
                if (i == 0) {
                    result[i] = rand.nextInt(10 - Integer.parseInt(ndigit)+1) + Integer.parseInt(ndigit);
                } else {
                    result[i] = rand.nextInt(10);
                }

            }
        } else if (range == valueRightLength && range > valueLeftLength) {
            boolean flag = false;
            for (int i = 0; i < range; i++) {
                ndigit = valueRightAsString.substring(i, i + 1);
                if (i == 0) {
                    result[i] = rand.nextInt(Integer.parseInt(ndigit)) + 1;
                } else {
                    if (!flag) {
                        for (int z = 0; z < i; z++) {
                            if (Integer.parseInt(valueRightAsString.substring(z, z + 1)) > result[z]) {
                                flag = true;
                            }
                        }
                    }
                    if (flag) {
                        result[i] = result[i] = rand.nextInt(10);
                    } else {
                        result[i] = rand.nextInt(Integer.parseInt(ndigit) + 1);
                    }
                }
            }
        } else {
            for (int z = 0; z < range; z++) {
                int rndigit = Integer.parseInt(valueRightAsString.substring(z, z + 1));
                int lndigit = Integer.parseInt(valueLeftAsString.substring(z, z + 1));
                //zamiana miejscami jezeli zakres prawostronny jest wiekszy niz lewo stronny 
                if (rndigit < lndigit) {
                    rndigit += lndigit;
                    lndigit = rndigit - lndigit;
                    rndigit -= lndigit;
                }
                result[z] = rand.nextInt(rndigit - lndigit + 1) + lndigit;

            }
        }
        for (int t = 0; t < range; t++) {
            value.append(result[t]);
        }
        return new BigInt(value.toString());
    }
    
//******************************************************************************    
    public Model(BigInt number_of_chars, int accuracyMilerTest) {
        this.HowManyBytes = number_of_chars; // ile bajtow ma byc 
        this.accuracyMilerTest = accuracyMilerTest;
        this.fill_P_KeyVariableRange();
        this.fill_Q_KeyVariableRange();

    }

//******************************************************************************
    public void readFileAsBinary(String filepath) throws IOException {
        FileInputStream input = new FileInputStream(filepath);
        this.plainText = Files.readAllBytes(Paths.get(filepath));
        input.close();
    }


//******************************************************************************
    public void saveFileAsBinary(byte[] data, String fileName) throws IOException {
        FileOutputStream output = new FileOutputStream(fileName);
        output.write(data);
        output.close();
        System.out.println("Plik zapisano");
    }

//******************************************************************************
    public void wypisz(byte[] text) {
        for (int i = 0; i < text.length; i++) {
            System.out.println(String.format("bajt nr%8s :%8s", i, Integer.toBinaryString(text[i] & 0xFF)).replace(' ', '0'));
        }
    }

//******************************************************************************
    //kodowanie naszej wiadomosci
    public void encode(byte[] var) {
        BigInt message;
        String ReturnedString;
        BigInt two = new BigInt("2");
        StringBuilder partOfMessage;
        StringBuilder coded = new StringBuilder();

        //ustawiamy rozmiar tablicy na nowa wartosc 
        byte[] result = new byte[1 + this.AddedWordLength];
        for (int i = 0; i < var.length; i++) {
            partOfMessage = new StringBuilder();
            partOfMessage.append(Integer.toString(var[i] & 0xff)); // bierzemy 1 bajt i zamieniamy na lcizbę z zakresu od 0-255
            partOfMessage.append(this.AddedWord); //dodajemy do niego naszą dodatkową wiadomosc 
            message = new BigInt(partOfMessage.toString());

            coded.append(
                    message.power_modulo_fast(two, this.publickey).toString()); // kodujemy naszą wiadomosc

            coded.append(" "); //kazdy blok oddzielamy spacja
        }
        this.encodedText
                = coded.toString().getBytes();
    }
        private byte[] ResultOfDecode = "x".getBytes();

    //dekodowanie naszej wiadomosci 
    public void decode(String codedText) {
        BigInt coded, mp, mq;
        String r1, r2, s1, s2;
        BigInt one = new BigInt("1");
        BigInt four = new BigInt("4");
        String[] splited = codedText.split("\\s+"); // ucinamyz  naszego tekstu spacje 
        resetValues(); // zerujemy wartosci dla Rozszerzonego algorytmu euklidesaa
        extendedAlghoritmEuklidesa(this.p, this.q);
        String s11 = this.yp.toString();
        String s22 = this.yq.toString();
        if (!this.yp.getSign()) {
            s11 = "-" + s11;
        }
        if (!this.yq.getSign()) {
            s22 = "-" + s22;
        }

        byte[] tableOfBytes = new byte[splited.length];

        for (int i = 0; i < splited.length; i++) {
            System.out.println("iteration " + i);

            coded = new BigInt(splited[i]);
            //Obliczamy mp oraz mq dla naszej zaszyfrowanje wiadomosci 
            mp = coded.power_modulo_fast(((this.p.add(one)).div(four, false)), this.p);
            mq = coded.power_modulo_fast(((this.q.add(one)).div(four, false)), this.q);
   
            //Olbliczamy nasze pierwiastki dla naszej wiadomosci 
           r1 = countR1(mp,mq);
           if (checkIsResultIsCorrect(r1)){
               appendToResultOfDecode(tableOfBytes, i);
               continue;
           }
           r2 = countR2(mp,mq);
           if (checkIsResultIsCorrect(r2)){
               appendToResultOfDecode(tableOfBytes, i);
               continue;
           }
            s1 = countS1(mp,mq);
           if (checkIsResultIsCorrect(s1)){
               appendToResultOfDecode(tableOfBytes, i);
               continue;
           }
            s2 = countS2(mp,mq);
           if (checkIsResultIsCorrect(s2)){
               appendToResultOfDecode(tableOfBytes, i);
               continue;
           }
               
                System.out.println("mp " + mp.toString());
                System.out.println("mq " + mq.toString());
                System.out.println("Wyniki Yp oraz Yq " + s11 + " " + s22);
                System.out.println("Znalezione pierwiastki to: " + r1 + " "
                        + r2 + " " + s1 + " " + s2 + " ");
                break;
        }
     this.decodedText = tableOfBytes;
    }
    private String countR1(BigInt mp, BigInt mq){
        return ((this.yp.mul(this.p).mul(mq)).add(this.yq.mul(this.q).mul(mp))).div(this.publickey, true).toString();
    }
    
    private String countR2(BigInt mp, BigInt mq){
        return (((this.yp.mul(new BigInt("1", false))).mul(this.p).mul(mq)).sub(this.yq.mul(this.q).mul(mp))).div(this.publickey, true).toString();
    }
    
    private String countS1(BigInt mp, BigInt mq){
       return ((this.yp.mul(this.p).mul(mq)).sub(this.yq.mul(this.q).mul(mp))).div(this.publickey, true).toString();
    }
    
    private String countS2(BigInt mp, BigInt mq){
       return (((this.yp.mul(new BigInt("1", false))).mul(this.p).mul(mq)).add(this.yq.mul(this.q).mul(mp))).div(this.publickey, true).toString();
    }
    
    //Sprawdza czy pierwiastek rozwiazania jest prawidlowy 
    private boolean checkIsResultIsCorrect(String value){
        if (value.length() >= this.AddedWordLength && value.substring(value.length() - this.AddedWord.length(), value.length()).contains(this.AddedWord)) {
            if (value.length() == this.AddedWordLength) {
                this.ResultOfDecode = new byte[]{(byte) (0)};
                return true;
            } 
            else {
                this.ResultOfDecode = (value.substring(0, (value.length() - this.AddedWord.length())).getBytes());
                return true;
            }
        }
     return false;
    }
    private void appendToResultOfDecode(byte[] tableOfBytes , int i){
        if (ResultOfDecode[0] == new byte[]{(byte) (0)}[0]) {
                tableOfBytes[i] = (new byte[]{(byte) (0)})[0];
            } else {
                tableOfBytes[i] = (byte) Integer.parseInt(new String(ResultOfDecode));
            }
    }
    private BigInt m = new BigInt("0");
    private BigInt mpp = new BigInt("0");
    private BigInt r = new BigInt("0");
    private BigInt rp = new BigInt("1");
    private BigInt nwdw = new BigInt("0");
    private BigInt zero = new BigInt("0");
    private BigInt zero1 = new BigInt("10");

    public void extendedAlghoritmEuklidesa(BigInt a, BigInt b) {

        if (b.equals(zero)) {
            this.nwdw = a;
            this.r = a;
            return;
        } else {
            extendedAlghoritmEuklidesa(b, a.div(b, true));
        }
        BigInt t = this.mpp;
        this.mpp = (this.rp.sub((a.div(b, false)).mul(this.mpp)));
        this.m = b;
        this.rp = t;
        this.r = a;
        this.yp = this.rp;
        this.yq = this.mpp;
    }

    private void resetValues() {
        this.m = new BigInt("0");
        this.mpp = new BigInt("0");
        this.r = new BigInt("0");
        this.rp = new BigInt("1");
        this.nwdw = new BigInt("0");
        this.zero = new BigInt("0");
    }

//******************************************************************************
//**************************** GETTERY *****************************************
//******************************************************************************
    public byte[] getPlainText2() {
        return this.plainText2;
    }

    //**************************  
    public byte[] getKey() {
        return this.key;
    }

    //**************************   
    public void setKey(byte[] key) {
        this.key = key;
    }

//**************************  
    public byte[] getPlainText() {
        return this.plainText;
    }

//**************************
    public byte[] getEncodedText() {
        return encodedText;
    }

//**************************
    public BigInt getleftPRange() {
        return this.leftPRange;
    }

//**************************
    public BigInt getleftQRange() {
        return this.leftQRange;
    }

//**************************
    public BigInt getP() {
        return this.p;
    }

//**************************    
    public String getPrivateKey() {
        return new String("p: " + this.p.toString() + "\n" + "q: " + this.q.toString());
    }

//**************************
    public String getPublicKey() {
        return this.publickey.toString();
    }

//**************************
    public BigInt getQ() {
        return this.q;
    }

    public byte[] getDecodedText() {
        return this.decodedText;
    }
}

/*
Zakres wybieramy tak: mamy x1 oraz x2 gdzie x1 oznacza ilosc bitow ile bedziemy 
kodowac  a x2 jak duzy blok potrzebujemy aby zakodowac + dodatkowe bity
np mamy x1=1 x2=16

to wtedy bedziemy kodowac 8 bitow a wiadomosc bedzie miala dlugosc 16 bity 
wiec aby miec miejsce na cala wiadomosc musimy miec liczbę większą niz 2^16
ale zaby p i q byly oddalone od siebie wybieramy zakres minimalny dla p  o 2 wiekszy niz dla q a dla q o jeden wikszy niz x2
czyli gdy x1=1 x2=2 to 
zakres dla p lewostronny wynosi  2^16+2 czyli 2^18
zakres dla q lewostronny wynosi  2^16+1 czyli 2^17



p losujemy wiec z zakresu lewostronnego2^18 
    a prawostronny to lewostronny +1/2lewostronny  

q losujemy wiec z zakresu lewostronnego 2^17 
    a prawostronny to lewostronny +1/3lewostronny    

*/
