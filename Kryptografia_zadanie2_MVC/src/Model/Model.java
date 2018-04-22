package Model;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.SecureRandom;
import java.util.Random;


public class Model {
    
    private BigInt HowManyBytes;// = new BigInt("2");  //dlugosc wiadomosci czyli jak wynosi 8 to bedzie tam mozna umiescic 8 bitow
    private BigInt HowManyBytesWithRepeatedBytes;// = new BigInt("4"); // dodatkowe bity
    private int accuracyMilerTest ;//Dokladonsc testu MIlera  opisana wzorem 1-(1/4)^n  <-- n == accuracyMilerTest
   
    public BigInt p;  //klucz 1 czesc
    public BigInt q;  //klucz 2 czesc
    
    private BigInt leftPRange;   
    private BigInt leftQRange;
    private BigInt d;
    private BigInt publickey;
   
    public BigInt yp = new BigInt("0");
    public BigInt yq = new BigInt("0");
    final private String AddedWord ="15" ;
    final private int AddedWordLength = this.AddedWord.length();


    
//******************************************************************************
    private void fill_P_KeyVariableRange(){
        this.leftPRange  = new BigInt("2").pow(this.HowManyBytesWithRepeatedBytes.mul(new BigInt("8")).add(new BigInt("2")));
               this.leftPRange = new BigInt("200");

    }
    
//******************************************************************************
   private void fill_Q_KeyVariableRange(){
       this.leftQRange = new BigInt("2").pow(this.HowManyBytesWithRepeatedBytes.mul(new BigInt("8")).add(new BigInt("1")));
       this.leftQRange = new BigInt("100");
   }
   
//******************************************************************************
   public boolean isEven(BigInt x) {
       if (x.div(new BigInt("2"), true).toString().equals(new BigInt("0").toString()))
           return true;
       else
           return false;
   }
   
//******************************************************************************
   public BigInt maxPowerOfTwo(BigInt x){
       BigInt result;
       BigInt breakVariable = new BigInt("0");
       BigInt two = new BigInt("2");
       BigInt counter = new BigInt("1");
       BigInt temp = new BigInt("1");
        
       boolean flag = true;
       while(flag){
            result =x.div(two.pow(counter), true);
                       //System.out.println("Model " + counter.toString() + " " +result.toString() );

           if(result.toString().equals(breakVariable.toString()))
             counter = counter.add(temp);
           else
                flag = false;
       }
       return counter.sub(temp); // return s
       
   }
   
//******************************************************************************
   public BigInt a_pow_d_mod_n(BigInt a, BigInt x){
       return (a.pow(this.d)).div(x,true);
   }

//******************************************************************************
    public boolean testMileraRabina(BigInt p){
        BigInt temp = new BigInt("1");
        BigInt two = new BigInt("2");
        BigInt one = new BigInt("1");
        BigInt randomDigit;
        BigInt x;
        
// sprawdzamy czy liczba nie jest parzysta, jezeli jest nie jest ona liczbą pierwszą        
        if (isEven(p))
            return false;
// Obliczamy Maksymalną potęgę 2 dzieląc x-1
        BigInt s = maxPowerOfTwo(p.sub(one));

// Obliczamy wartosć d = 
        this.d = p.div(new BigInt("2").pow(s), false);
        
        boolean flag = true;
        for(int i=0; i<this.accuracyMilerTest; i++){
                randomDigit =randomDigit(two,p.sub(two)) ;
                x = a_pow_d_mod_n(randomDigit,p);
                if( x.equals(one) || x.equals(p.sub(one)))
                    continue;
                
            for(BigInt j = new BigInt("1"); (j.isSmmaler(s))&& (x.notequals(p.sub(one))); j=j.add(one)){
                x = (x.pow(two)).div(p,true);
                if (x.equals(one)){
                    flag = false;
                        break;
                    }
                }
                if(!flag)
                    break;
                if(x.notequals(p.sub(one))){
                    flag = false;
                    break;
                }
        }
     return flag;    
    }
    
//******************************************************************************
    public void choose_P_AND_Q(){
        BigInt one = new BigInt("1");
        BigInt two = new BigInt("2");
        BigInt three = new BigInt("3");
        BigInt four = new BigInt("4");

        this.p = randomDigit(this.leftPRange, this.leftPRange.add(this.leftPRange.div(two, false)));
        while(!(!this.p.div(four,true).notequals(three) && testMileraRabina(this.p))){
           this.p=this.p.add(one); 
        }
       
        this.q = randomDigit(this.leftQRange, this.leftQRange.add(this.leftQRange.div(two, false)));
        while(!(!this.q.div(four,true).notequals(three) && testMileraRabina(this.q))){
          this.q=this.q.add(one);
        }
     this.publickey = this.p.mul(this.q);
    }
    
    
//******************************************************************************
    public BigInt randomDigit(BigInt left, BigInt right){
     String valueLeftAsString = left.toString();
     String valueRightAsString = right.toString();
     int valueLeftLength = valueLeftAsString.length();
     int valueRightLength = valueRightAsString.length();
     StringBuilder value = new StringBuilder();
     Random rand = new Random();
     int digit;
     String ndigit;

     int range = rand.nextInt(valueRightLength - valueLeftLength +1)+(valueLeftLength);
     int[] result = new int[range];

     //Jezeli wylosowany zakres jest mniejszy of cyfry 
     if(range < valueRightLength && range > valueLeftLength){
        
        for (int i=0; i<range; i++ ){
            if(i==0){
                result[i] = rand.nextInt(9)+1;
            }
            else
                result[i] = rand.nextInt(10);
        }
     }
     
     else if(range < valueRightLength && range == valueLeftLength){
         
         for (int i=0; i<range; i++ ){
             ndigit = valueLeftAsString.substring(i, i+1);
             if(i==0)
                 result[i]=rand.nextInt(10 - Integer.parseInt(ndigit))+Integer.parseInt(ndigit);
             else
                 result[i] = rand.nextInt(10);
     
            }
     }
   
     else if(range == valueRightLength && range > valueLeftLength) {
         boolean flag = false;
         for (int i=0; i<range; i++ ){
                  ndigit = valueRightAsString.substring(i, i+1);
             if(i==0){
                 result[i]=rand.nextInt(Integer.parseInt(ndigit))+1;
             }
             else{
                  if (!flag){
                    for(int z=0; z<i; z++){
                        if (Integer.parseInt(valueRightAsString.substring(z, z+1)) > result[z] )
                            flag=true;
                       }
                    }
                    if(flag)
                        result[i] =  result[i] = rand.nextInt(10);
                    else
                        result[i] = rand.nextInt(Integer.parseInt(ndigit)+1);
                }
            }
        }
     else
          for(int z=0; z<range; z++){
              int rndigit = Integer.parseInt(valueRightAsString.substring(z, z+1));
              int lndigit =  Integer.parseInt(valueLeftAsString.substring(z, z+1));
             //zamiana miejscami jezeli zakres prawostronny jest wiekszy niz lewo stronny 
              if(rndigit < lndigit){
                  rndigit+= lndigit;
                  lndigit = rndigit - lndigit;
                  rndigit -= lndigit;
              }
              result[z] = rand.nextInt(rndigit - lndigit +1 )+ lndigit;
          
          }
     for (int t=0; t<range; t++)
          value.append(result[t]);
    return new BigInt(value.toString());
  }

//******************************************************************************
//**************************    
   private byte[] plainText;
   private byte[] encodedText;
   private String decodedText;
   private byte[] key;
   private byte[] plainText2;
   
   //Mnozymy razy 8 bo bedziemy szyfrowac po jednym bajcie minimalnie
   
      public Model(BigInt number_of_chars,int accuracyMilerTest){
        this.HowManyBytes = number_of_chars; // ile bajtow ma byc 
        this.HowManyBytesWithRepeatedBytes = this.HowManyBytes.mul(new BigInt("2")); // ma byc zawsze dwa razy wiecej bajtow;
        this.accuracyMilerTest = accuracyMilerTest;
        this.fill_P_KeyVariableRange();
        this.fill_Q_KeyVariableRange();

    }
//******************************************************************************
    public void readFileAsBinary(String filepath) throws IOException{        
        FileInputStream input = new FileInputStream(filepath);
        this.plainText = Files.readAllBytes(Paths.get(filepath));
        input.close();
    }
    
//******************************************************************************
      public void readKeyAsBinary(String privatekey, String publickey) throws IOException{        
        FileInputStream input = new FileInputStream(privatekey);
        this.key = Files.readAllBytes(Paths.get(privatekey));
        input.close();
    }
      
//******************************************************************************
      public void saveFileAsBinary(byte[] dataprivatekey, byte[] datapublickey,String privatekey, String publickey)throws IOException{
        FileOutputStream output = new FileOutputStream(privatekey);
        output.write(dataprivatekey);
        output.close();
        
        FileOutputStream output2 = new FileOutputStream(publickey);
        output.write(datapublickey);
        output.close();
    }
    
//******************************************************************************
      public void wypisz(byte[] text){
        for (int i=0; i< text.length; i++)
            System.out.println( String.format("bajt nr%8s :%8s", i, Integer.toBinaryString(text[i] & 0xFF)).replace(' ', '0'));
    }

//******************************************************************************
 public void encode(byte[] var) {
     BigInt varLengthnew = new BigInt(Integer.toString(var.length));
     BigInt one = new BigInt("1");
     BigInt eight = new BigInt("8");
     BigInt two = new BigInt("2");
     BigInt message;

     StringBuilder coded = new StringBuilder();
      byte [] result = new byte[var.length*2];
      
      for(BigInt i=new BigInt("0"); i.isSmmaler(varLengthnew); i=i.add(one)){
          
          String s1 =  Integer.toString(var[Integer.parseInt(i.toString())]);
          StringBuilder value = new StringBuilder();
          value.append(s1); 
          value.append(this.AddedWord);

            message =new BigInt(value.toString());
            System.out.println("WIADOMOSC  czesc " + message.toString());
            coded.append(
                   (message.pow(two)).div(this.publickey,true).toString());
           
            coded.append(" "); //kazdy blok oddzielamy spacja
      }
            this.encodedText = 
                coded.toString().getBytes();
 }
 
 
 
 
 public void decode(String codedText){
   
    
    BigInt coded, mp, mq, r1, r2, s1, s2;
    BigInt one = new BigInt("1");
    BigInt four = new BigInt("4");
    String[] splited = codedText.split("\\s+");
    StringBuilder ResultOfDecode = new StringBuilder();
    
  

    resetValues(); // zerujemy wartosci dla Rozszerzonego algorytmu euklidesaa
    extendedAlghoritmEuklidesa(this.p , this.q);
    
    String s11 = this.yp.toString();
    String s22 = this.yq.toString();
    if (!this.yp.getSign())
        s11 = "-"+s11;
    if (!this.yq.getSign())
        s22 = "-"+s22;

    for (int i =0; i<splited.length;i++){
      coded = new BigInt(splited[i]);
      System.out.println("odszyfrujemy liczbe " + splited[i]); 
     
    //Obliczamy mp oraz mq dla naszej zaszyfrowanje wiadomosci 
     mp = ((coded.pow((this.p.add(one)).div(four, false))).div(this.p,true));
     mq = ((coded.pow((this.q.add(one)).div(four, false))).div(this.q,true));
   
   //Olbliczamy nasze pierwiastki dla naszej wiadomosci 
     r1 = ((this.yp.mul(this.p).mul(mq)).add(this.yq.mul(this.q).mul(mp))).div(this.publickey,true);
     r2 = (((this.yp.mul(new BigInt("1",false))).mul(this.p).mul(mq)).sub(this.yq.mul(this.q).mul(mp))).div(this.publickey,true);
     s1 =((this.yp.mul(this.p).mul(mq)).sub(this.yq.mul(this.q).mul(mp))).div(this.publickey,true);
     s2 = (((this.yp.mul(new BigInt("1",false))).mul(this.p).mul(mq)).add(this.yq.mul(this.q).mul(mp))).div(this.publickey,true);
     
   
      System.out.println("mp " + mp.toString()); 
      System.out.println("mq " + mq.toString()); 
      System.out.println("Wyniki Yp oraz Yq " + s11  + " " + s22 );
      System.out.println("Znalezione pierwiastki to: " + r1.toString() + " " + 
                 r2.toString() + " " + s1.toString() + " " + s2.toString()  + " " );

       
     //System.out.println("PEIRWIASTKIIII " + r1.toString()+ "  p1 "+ (this.yp.mul(this.p).mul(mq)).add(this.yq.mul(this.q).mul(mp)).toString()); 
    // System.out.println("PEIRWIASTKIIII " + r2.toString() + "  p2 " +(((this.yp.mul(new BigInt("1",false))).mul(this.p).mul(mq)).sub(this.yq.mul(this.q).mul(mp))).toString()) ; 
     //System.out.println("PEIRWIASTKIIII " + s1.toString()+ "  p3 "+ ((this.yp.mul(this.p).mul(mq)).sub(this.yq.mul(this.q).mul(mp))).toString()); 
     //System.out.println("PEIRWIASTKIIII " + s2.toString() + " p4 "+ (((this.yp.mul(new BigInt("1",false))).mul(this.p).mul(mq)).add(this.yq.mul(this.q).mul(mp))).toString()); 

     if (r1.toString().substring(r1.toString().length() - this.AddedWord.length(), r1.toString().length() ).contains(this.AddedWord))
         ResultOfDecode.append(r1.toString()).append(" ");
     else if (r2.toString().substring(r2.toString().length() - this.AddedWord.length() ,r2.toString().length()).contains(this.AddedWord))
         ResultOfDecode.append(r2.toString()).append(" ");
     else if (s1.toString().substring(s1.toString().length() - this.AddedWord.length(), s1.toString().length()).contains(this.AddedWord))
         ResultOfDecode.append(s1.toString()).append(" ");
     else if(s2.toString().substring(s2.toString().length()- this.AddedWord.length(), s2.toString().length()).contains(this.AddedWord))
         ResultOfDecode.append(s2.toString()).append(" ");
     else
       ;  //System.out.println("Nie znaleziono pierwiastka. Znalezione pierwiastki to: " + r1.toString() + " " + 
           //      r2.toString() + " " + s1.toString() + " " + s2.toString() );
    }
   this.decodedText = ResultOfDecode.toString();
 }
 
 private BigInt m=new BigInt("0");
 private BigInt mp=new BigInt("0");
 private BigInt r=new BigInt("0");
 private BigInt rp=new BigInt("1");
 private BigInt nwdw=new BigInt("0");
 private BigInt zero = new BigInt("0");
 private BigInt zero1 = new BigInt("10");
 
 public void extendedAlghoritmEuklidesa(BigInt a, BigInt b){

    if(b.equals(zero)){
        this.nwdw=a;
         this.r=a;
        return;
    }
    else
        extendedAlghoritmEuklidesa(b,a.div(b,true));
    BigInt t=this.mp;
    this.mp=(this.rp.sub((a.div(b,false)).mul( this.mp)));
    this.m=b;
    this.rp=t;
    this.r=a;
    this.yp = this.rp;
    this.yq =  this.mp;
 }
 
    private void resetValues(){
    this.m=new BigInt("0");
    this.mp=new BigInt("0");
    this.r=new BigInt("0");
    this.rp=new BigInt("1");
    this.nwdw=new BigInt("0");
    this.zero = new BigInt("0");
    }
         
    

//******************************************************************************
//**************************** GETTERY *****************************************
//******************************************************************************
    
    public byte[] getPlainText2(){
        return this.plainText2;
    }
    
 //**************************  
    public byte[] getKey(){
        return this.key;
    }
    
 //**************************   
    public void setKey(byte[] key){
        this.key = key;
    }
    
//**************************  
    public byte[] getPlainText(){
        return this.plainText;
    }
    
//**************************
    public byte[] getEncodedText(){
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
    public String getPrivateKey(){
        return new String("p: " + this.p.toString() + "\n" + "q: " + this.q.toString() );
    }
    
//**************************
    public String getPublicKey(){
        return this.publickey.toString();
    }
    
//**************************
    public BigInt getQ() {
        return this.q;
    }

   public String getDecodedText(){
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