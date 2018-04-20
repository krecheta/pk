package Model;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.SecureRandom;
import java.util.Random;


public class Model {
    private BigInt number_of_chars;// = new BigInt("2");  //dlugosc wiadomosci
    private BigInt blockSize;// = new BigInt("4"); // Dlugosc bloku czly wiadomosc + dodatkowe bity
    private int accuracyMilerTest ;//Dokladonsc testu MIlera  opisana wzorem 1-(1/4)^n  <-- n == accuracyMilerTest
    private int p;  //klucz 1 czesc
    private int q;  //klucz 2 czesc
    private BigInt leftPRange;   
    private BigInt leftQRange;
    private BigInt d;
    
//******************************************************************************
    private void fill_P_KeyVariableRange(){
       this.leftPRange  = this.number_of_chars.pow(
               this.blockSize.add(
                          new BigInt("2").mul(
                                  this.blockSize.div(new BigInt("3"), false)))); //p bedzie wieksze od q o 2/3b
   }
    
//******************************************************************************
   private void fill_Q_KeyVariableRange(){
       this.leftQRange = this.number_of_chars.pow( this.blockSize.add(new BigInt("1")));
   }
   
//******************************************************************************
   public boolean isEven(BigInt x) {
       if (x.div(new BigInt("2"), true).toString().equals(new BigInt("0").toString()))
           return true;
       else
           return false;
   }
   
   public BigInt maxPowerOfTwo(BigInt x){
       BigInt result = new BigInt("0");
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
   public BigInt a_pow_d_mod_n(BigInt a, BigInt x){
       return (a.pow(this.d)).div(x,true);
   }
     
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


           
                         

                randomDigit =randomDigit(p.sub(two)) ;
                x = a_pow_d_mod_n(randomDigit,p);
                System.out.println("radnom " + randomDigit );
                System.out.println("x 1" + x);
                
                if( x.equals(one) || x.equals(p.sub(one)))
                    continue;
                
                for(BigInt j = new BigInt("1"); (j.isSmmaler(s))&& (x.notequals(p.sub(one))); j.add(one)){
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
                    
         /*           
                      System.out.println("Random digit " + randomDigit);
                      System.out.println("d " + d);
                          System.out.println("x " + x);

          BigInt r = new BigInt("0");
          do{
              /*breakResult = (breakResult.pow(two)).div(x, true);
              if(breakResult.equals(one) || breakResult.notequals(x.sub(one)))
                  return false;
              r = r.add(temp);
              
              
                if((randomDigit.pow(this.d.mul(two.pow(r)))).equals(x.sub(one)))
                    return false;
             
                       r = r.add(temp);
                System.out.println("Model.Model.testMileraRabina()" + r.toString()+ "\\n");
                                System.out.println();

            } while (r.isSmmaler(maxPow2.sub(one)));
//while (r.isSmmaler(maxPow2) && breakResult.notequals(x.sub(one)));
          }
              
         return true;*/
    }
    
    
    public BigInt randomDigit(BigInt x){
     String ndigit;
     String valueAsString = x.toString();
     int valueLength = valueAsString.length();
     StringBuilder value = new StringBuilder();
     Random rand = new Random();
     int digit;
     
    
     
     int range = rand.nextInt(x.toString().length())+1;
     int[] result = new int[range];
        System.out.println("RANGE  " + range);

     //Jezeli wylosowany zakres jest mniejszy of cyfry 
     if(range < valueLength){
        
        for (int i=0; i<range; i++ ){
            
            if(i==0){
                result[i] = rand.nextInt(9)+1;
                System.out.println("ModelCZEMU TU JESTEM "  + result[i]);}
            else
                result[i] = rand.nextInt(10);
        }
     }
     
     else{
         boolean flag = false;
         for (int i=0; i<range; i++ ){
                  ndigit = valueAsString.substring(i, i+1);

             if(i==0){
                 result[i]=rand.nextInt(Integer.parseInt(ndigit))+1;
             }
             else{
                    if (!flag){
                     for(int z=0; z<i; z++){
                        if (Integer.parseInt(valueAsString.substring(z, z+1)) > result[z] )
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
     for (int t=0; t<range; t++)
          value.append(result[t]);
        System.out.println("WYLOSOWANO " + value.toString());
    return new BigInt(value.toString());
          
  }
     
     
     
     
     
 
        
        
        
    

    
    
    
   
  // **************************************/
   private byte[] plainText;
   private byte[] encodedText;
   private byte[] key;
   private byte[] plainText2;
   
   

   
   
   
   
   
   
   
   
   
   
   
   
   
   
   
   
   
   
      public Model(BigInt number_of_chars, BigInt blockSize,int accuracyMilerTest){
        this.number_of_chars = number_of_chars;
        this.blockSize = blockSize;
        this.accuracyMilerTest = accuracyMilerTest;
        this.fill_P_KeyVariableRange();
        this.fill_Q_KeyVariableRange();

    }
   
   
      
      public void readFileAsBinary(String filepath) throws IOException{        
        FileInputStream input = new FileInputStream(filepath);
        this.plainText = Files.readAllBytes(Paths.get(filepath));
        input.close();
    }
      
      public void readKeyAsBinary(String filepath) throws IOException{        
        FileInputStream input = new FileInputStream(filepath);
        this.key = Files.readAllBytes(Paths.get(filepath));
        input.close();
    }
      
      public void saveFileAsBinary(byte[] data, String filepath)throws IOException{
        FileOutputStream output = new FileOutputStream(filepath);
        output.write(data);
        output.close();
    }

    public void generateKey(int dataSize){
        SecureRandom random = new SecureRandom();
        byte [] bytes = new byte[dataSize]; // 128 bits are converted to 16 bytes;
        random.nextBytes(bytes);
        this.key = bytes;
    }
      public void wypisz(byte[] text){
        for (int i=0; i< text.length; i++)
            System.out.println( String.format("bajt nr%8s :%8s", i, Integer.toBinaryString(text[i] & 0xFF)).replace(' ', '0'));

    }

    public int intPow(int blockSize, int wordLen) {
        int result=blockSize;
        for (int i=0; i<wordLen; i++ )
            result = result * blockSize;
        return result;
    }
    
    public void xorData(byte[] data, byte[] key, int k){  
        byte [] result = new byte[data.length];
        for(int i=0; i< data.length; i++)
        result[i] = (byte) (data[i]^key[i]);
       if(k == 0)
            this.encodedText = result;
       else
           this.plainText2 = result;
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
}
