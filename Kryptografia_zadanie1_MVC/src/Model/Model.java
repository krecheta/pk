package Model;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.SecureRandom;


public class Model {
   private byte[] plainText;
   private byte[] encodedText;
   private byte[] key;
   private byte[] plainText2;
   
      
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
    
    public void xorData(byte[] data, byte[] key, int k){  
        byte [] result = new byte[data.length];
        for(int i=0; i< data.length; i++)
        result[i] = (byte) (data[i]^key[i]);
       if(k == 0)
            this.encodedText = result;
       else
           this.plainText2 = result;
    }

    public byte[] getPlainText2(){
        return this.plainText2;
    }
    public byte[] getKey(){
        return this.key;
    }
    
    public void setKey(byte[] key){
        this.key = key;
    }
    
    public byte[] getPlainText(){
        return this.plainText;
    }
    
    public byte[] getEncodedText(){
        return encodedText;
    }
    
    public void wypisz(byte[] text){
        for (int i=0; i< text.length; i++)
            System.out.println( String.format("bajt nr%8s :%8s", i, Integer.toBinaryString(text[i] & 0xFF)).replace(' ', '0'));

    }
    
    
    
    
}
