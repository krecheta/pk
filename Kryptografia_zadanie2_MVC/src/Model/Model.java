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
       System.out.println("Model.Model.maxPowerOfTwo()" +counter.sub(temp).toString() );
       return counter.sub(temp); // return s
       
   }
   
//******************************************************************************
   public BigInt a_pow_d_mod_n(BigInt a, BigInt x){
       return (a.pow(this.d)).div(x,true);
   }

//******************************************************************************
    public boolean testMileraRabina(BigInt p){
        System.out.println("");
        System.out.println("Model.Model.aaaaaaaaaaaaaaaa()");

        BigInt temp = new BigInt("1");
        BigInt two = new BigInt("2");
        BigInt one = new BigInt("1");
        BigInt randomDigit;
        BigInt x;
        
// sprawdzamy czy liczba nie jest parzysta, jezeli jest nie jest ona liczbą pierwszą        
        if (isEven(p))
            return false;
// Obliczamy Maksymalną potęgę 2 dzieląc x-1
                    System.out.println("p -1 " + p.sub(one));

        BigInt s = maxPowerOfTwo(p.sub(one));
                  System.out.println("s " + s);

// Obliczamy wartosć d = 
        this.d = p.div(new BigInt("2").pow(s), false);
        
              boolean flag = true;
        for(int i=0; i<this.accuracyMilerTest; i++){
            System.out.println("Zaczynamy petle " );
                    System.out.println("d " + d);
                    System.out.println("p " + p);
                    System.out.println("");


           
                         

                randomDigit =randomDigit(two,p.sub(two)) ;
                x = a_pow_d_mod_n(randomDigit,p);
                System.out.println("radnom " + randomDigit );
                System.out.println("x 1 " + x);
                
                if( x.equals(one) || x.equals(p.sub(one)))
                    continue;
                
                for(BigInt j = new BigInt("1"); (j.isSmmaler(s))&& (x.notequals(p.sub(one))); j=j.add(one)){
                    System.out.println("JJJJJ " + j);
                    x = (x.pow(two)).div(p,true);
                    if (x.equals(one)){
                        flag = false;
                        System.out.println("WYCHODZIMY 1");
                        break;
                    }
                }
                if(!flag)
                    break;
                if(x.notequals(p.sub(one))){
                                            System.out.println("WYCHODZIMY 2");
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
        //random P digit
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
        System.out.println("XXXX " + left.toString() + " " + right.toString());
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
        System.out.println("RANGE  " + range);

     //Jezeli wylosowany zakres jest mniejszy of cyfry 
     if(range < valueRightLength && range > valueLeftLength){
        
        for (int i=0; i<range; i++ ){
            
            if(i==0){
                result[i] = rand.nextInt(9)+1;
                System.out.println("ModelCZEMU TU JESTEM "  + result[i]);}
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
                  
              System.out.println("Model.Model.randomDigit()       " +rndigit + "    " + lndigit );
              result[z] = rand.nextInt(rndigit - lndigit +1 )+ lndigit;
          
          }
     
     
     for (int t=0; t<range; t++)
          value.append(result[t]);
        System.out.println("WYLOSOWANO " + value.toString());
    return new BigInt(value.toString());
          
  }

//******************************************************************************
//**************************    
   private byte[] plainText;
   private byte[] encodedText;
   private byte[] decodedText;
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
            System.out.println("WIADOMOSC 1 czesc " + message.toString());
            coded.append(
                   (message.pow(two)).div(this.publickey,true).toString());
           
            coded.append(" "); //kazdy blok oddzielamy spacja
      }
            this.encodedText = 
                coded.toString().getBytes();
                         System.out.println("encodedText");
                                     //System.out.println("WIADOMOSC 1 czesc " + message.toString());

 }
 
 
 
 
 public void decode(String codedText){
    this.m=new BigInt("0");
    this.mp=new BigInt("0");
    this.r=new BigInt("0");
    this.rp=new BigInt("1");
    this.nwdw=new BigInt("0");
    this.zero = new BigInt("0");
  
     BigInt coded = new BigInt(codedText);
     BigInt one = new BigInt("1");
      BigInt four = new BigInt("4");
     BigInt mp,mq,r1,r2,s1,s2;
     System.out.println("klucze " + this.p +  " "+ this.q);
     System.out.println("codedtext " + coded.toString() +  " "+ codedText);
     System.out.println("div(four, false) " + this.p.add(one).div(four, false).toString());


     mp = ((coded.pow((this.p.add(one)).div(four, false))).div(this.p,true));
     mq = ((coded.pow((this.q.add(one)).div(four, false))).div(this.q,true));
     System.out.println("klucze2 " + this.p +  " "+ this.q);
     
     extendedAlghoritmEuklidesa(this.p , this.q);
           
     
     String s11 = this.yp.toString();
    String s22 = this.yq.toString();
    if (!this.yp.getSign())
        s11 = "-"+s11;
     if (!this.yq.getSign())
        s22 = "-"+s22;
     System.out.println("WYNICZEK S1 oraz s2" + s11  + " " + s22 );
     

     r1 = ((this.yp.mul(this.p).
             mul(mq)).
             add(this.yq.mul(this.q).
                     mul(mp))).
             div(this.publickey,true);
     r2 = (((this.yp.mul(new BigInt("1",false))).mul(this.p).mul(mq)).sub(this.yq.mul(this.q).mul(mp))).div(this.publickey,true);
     s1 =((this.yp.mul(this.p).mul(mq)).sub(this.yq.mul(this.q).mul(mp))).div(this.publickey,true);
     s2 = (((this.yp.mul(new BigInt("1",false))).mul(this.p).mul(mq)).add(this.yq.mul(this.q).mul(mp))).div(this.publickey,true);
     
      System.out.println("mp " + mp.toString()); 
       System.out.println("mq " + mq.toString()); 
            System.out.println("this.yq " + this.yq.toString()); 
       System.out.println("this.yp " + this.yp.toString());
       
       
     //System.out.println("PEIRWIASTKIIII " + r1.toString()+ "  p1 "+ (this.yp.mul(this.p).mul(mq)).add(this.yq.mul(this.q).mul(mp)).toString()); 
    // System.out.println("PEIRWIASTKIIII " + r2.toString() + "  p2 " +(((this.yp.mul(new BigInt("1",false))).mul(this.p).mul(mq)).sub(this.yq.mul(this.q).mul(mp))).toString()) ; 
     //System.out.println("PEIRWIASTKIIII " + s1.toString()+ "  p3 "+ ((this.yp.mul(this.p).mul(mq)).sub(this.yq.mul(this.q).mul(mp))).toString()); 
     //System.out.println("PEIRWIASTKIIII " + s2.toString() + " p4 "+ (((this.yp.mul(new BigInt("1",false))).mul(this.p).mul(mq)).add(this.yq.mul(this.q).mul(mp))).toString()); 

     if (r1.toString().substring(r1.toString().length() - this.AddedWord.length(), r1.toString().length() ).contains(this.AddedWord))
         this.decodedText = r1.toString().getBytes();
     else if (r2.toString().substring(r2.toString().length() - this.AddedWord.length() ,r2.toString().length()).contains(this.AddedWord))
        this.decodedText = r2.toString().getBytes();
     else if (s1.toString().substring(s1.toString().length() - this.AddedWord.length(), s1.toString().length()).contains(this.AddedWord))
        this.decodedText = s1.toString().getBytes();
     else if(s2.toString().substring(s2.toString().length()- this.AddedWord.length(), s2.toString().length()).contains(this.AddedWord))
        this.decodedText = s2.toString().getBytes(); 
     else
         System.out.println("Nie znaleziono pierwiastka. Znalezione pierwiastki to: " + r1.toString() + "\n" + 
                 r2.toString() + "\n" + s1.toString() + "\n" + s2.toString()  + " " +r1.toString().substring(r1.toString().length() - this.AddedWord.length(), r1.toString().length() - 1));
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
     System.out.println("mp " + this.mp + " rp " + this.rp + " a " +  a + " b " + b + " mp " + mp);
     this.mp=(this.rp.sub((a.div(b,false)).mul( this.mp)));
    System.out.println("MP  "+  this.mp.toString() +"  this.rp.sub(a.div(b,false)))"+this.rp.sub(a.div(b,false))+" " +a.div(b,false).toString() );
    
           System.out.println("xxxx" + this.m.toString());

     this.m=b;
      System.out.println("xxxx" + this.m.toString());
 
     this.rp=t;
     this.r=a;


    System.out.println("przed konwersja an biginta "+ mp+ " * "+ m+ " + "+ rp+ " * "+r);
    this.yp = this.rp;
    this.yq =  this.mp;
    
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

   public byte[] getDecodedText(){
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