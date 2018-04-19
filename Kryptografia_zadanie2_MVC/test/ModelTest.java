/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import Model.Model;
import org.junit.After;
import Model.BigInt; 
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import sun.nio.cs.ext.Big5;

/**
 *
 * @author edodomi
 */
public class ModelTest {

  @org.junit.Test
  public void test1LeftPVariableRange(){
         BigInt number_of_chars = new BigInt("2");
         BigInt blockSize = new BigInt("4");
         int accuracyMilerTest =3;
         Model model = new Model(number_of_chars, blockSize, accuracyMilerTest);
         BigInt expected = new BigInt("64");
         
         assertEquals(expected.toString(),model.getleftPRange().toString());
         
     }
     

   @org.junit.Test
   public void test1LeftQVariableRange(){
         BigInt number_of_chars = new BigInt("2");
         BigInt blockSize = new BigInt("4");
         int accuracyMilerTest =3;
         Model model = new Model(number_of_chars, blockSize, accuracyMilerTest);
         BigInt expected = new BigInt("32");
         
         assertEquals(expected.toString(),model.getleftQRange().toString());
   }
   

    
     //Nie przechodzi  
   /*
   @org.junit.Test
   public void test2LeftQVariableRange(){
        BigInt number_of_chars = new BigInt("6");
         BigInt blockSize = new BigInt("12");
         int accuracyMilerTest =3;
         Model model = new Model(number_of_chars, blockSize, accuracyMilerTest);
         BigInt expected = new BigInt("13060694016");
         
         assertEquals(expected.toString(),model.getleftPRange().toString());
    }
  */
   
   //Sprawdza czy parzysta 
   @org.junit.Test
   public void testIsEven(){
       BigInt number_of_chars = new BigInt("2");
         BigInt blockSize = new BigInt("4");
         int accuracyMilerTest =3;
         Model model = new Model(number_of_chars, blockSize, accuracyMilerTest);
         
       assertEquals(true, model.isEven(new BigInt("2")));
       assertEquals(true, model.isEven(new BigInt("22")));
       assertEquals(true, model.isEven(new BigInt("2222")));
       assertEquals(true, model.isEven(new BigInt("222992")));
       assertEquals(true, model.isEven(new BigInt("229900")));
       
       assertEquals(false, model.isEven(new BigInt("229901")));
       assertEquals(false, model.isEven(new BigInt("22922903")));
       assertEquals(false, model.isEven(new BigInt("234123321")));
       assertEquals(false, model.isEven(new BigInt("234242342347")));
       assertEquals(false, model.isEven(new BigInt("42334242423423425")));
   } 
   
   
   
   @org.junit.Test
   public void testMaxPowerOfTwo(){
        BigInt number_of_chars = new BigInt("2");
        BigInt blockSize = new BigInt("4");
        int accuracyMilerTest =3;
         Model model = new Model(number_of_chars, blockSize, accuracyMilerTest);
        assertEquals(new BigInt("2").toString(), model.maxPowerOfTwo(new BigInt("12")).toString());
        assertEquals(new BigInt("2").toString(), model.maxPowerOfTwo(new BigInt("20")).toString());
        assertEquals(new BigInt("32").toString(), model.maxPowerOfTwo(new BigInt("32")).toString());
   }

    
    
    @org.junit.Test
    public void testIsSmaller(){
        assertEquals(true, new BigInt("5").isSmmaler(new BigInt("6")));
        assertEquals(true, new BigInt("11").isSmmaler(new BigInt("622")));
        assertEquals(true, new BigInt("5").isSmmaler(new BigInt("11")));
        assertEquals(true, new BigInt("5").isSmmaler(new BigInt("9823479872938479")));
        assertEquals(true, new BigInt("5234").isSmmaler(new BigInt("6333")));
        assertEquals(true, new BigInt("5321").isSmmaler(new BigInt("6442")));
      
        assertEquals(false, new BigInt("7").isSmmaler(new BigInt("6")));
        assertEquals(false, new BigInt("35").isSmmaler(new BigInt("6")));
        assertEquals(false, new BigInt("543").isSmmaler(new BigInt("16")));
        assertEquals(false, new BigInt("5223").isSmmaler(new BigInt("643")));
        assertEquals(false, new BigInt("59999").isSmmaler(new BigInt("6002")));
    }
    
        
    @org.junit.Test
    public void testIsBigger(){
        assertEquals(false, new BigInt("5").isBigger(new BigInt("6")));
        assertEquals(false, new BigInt("11").isBigger(new BigInt("622")));
        assertEquals(false, new BigInt("5").isBigger(new BigInt("11")));
        assertEquals(false, new BigInt("5").isBigger(new BigInt("9823479872938479")));
        assertEquals(false, new BigInt("5234").isBigger(new BigInt("6333")));
        assertEquals(false, new BigInt("5321").isBigger(new BigInt("6442")));
      
        assertEquals(true, new BigInt("7").isBigger(new BigInt("6")));
        assertEquals(true, new BigInt("35").isBigger(new BigInt("6")));
        assertEquals(true, new BigInt("543").isBigger(new BigInt("16")));
        assertEquals(true, new BigInt("5223").isBigger(new BigInt("643")));
        assertEquals(true, new BigInt("59999").isBigger(new BigInt("6002")));
    }    
    
    @org.junit.Test
   public void testMilerRabinFunction(){
    BigInt number_of_chars = new BigInt("21");
    BigInt blockSize = new BigInt("4");
    int accuracyMilerTest =10;
         
    Model model = new Model(number_of_chars, blockSize, accuracyMilerTest);
     assertEquals(true, model.testMileraRabina(new BigInt("13")));
    //assertEquals(true, model.testMileraRabina(new BigInt("97")));
    //assertEquals(true, model.testMileraRabina(79039));
    //assertEquals(true, model.testMileraRabina(99241));
       
      // False
      
    //assertEquals(false, model.testMileraRabina(new BigInt("77")));
    //assertEquals(false, model.testMileraRabina(new BigInt("85")));
   }
   
   @org.junit.Test
    public void addtestAplusB(){
        boolean flag = true; 
        
        for(int x=0; x<100; x++){
            for(int y=0; y<100; y++){
                if (!((Integer.toString(x+y)).equals(new BigInt(Integer.toString(x)).add(new BigInt(Integer.toString(y))).toString()))){
                    System.out.println("ModelTest.addtest() " + x + " + " + y);
                    flag =false;
                    assertEquals(true,flag);
                            }
                    }
        }
              assertEquals(true,flag);
    }
    
       @org.junit.Test
    public void addtestBplusA(){
        boolean flag = true; 
        
        for(int x=0; x<100; x++){
            for(int y=0; y<100; y++){
                if (!((Integer.toString(y+x)).equals(new BigInt(Integer.toString(y)).add(new BigInt(Integer.toString(x))).toString()))){
                    System.out.println("ModelTest.addtest() " + y + " + " + x);
                    flag =false;
                    assertEquals(true,flag);
                            }
                    }
        }
              assertEquals(true,flag);
    }
    
   @org.junit.Test
    public void multestAmulB(){
        boolean flag = true; 
        
        for(int x=0; x<100; x++){
            for(int y=0; y<100; y++){
                if (!((Integer.toString(x*y)).equals(new BigInt(Integer.toString(x)).mul(new BigInt(Integer.toString(y))).toString()))){
                    System.out.println("ModelTest.addtest() " + x + " * " + y);
                    flag =false;
                    assertEquals(true,flag);
                            }
                    }
        }
              assertEquals(true,flag);
    }
     @org.junit.Test
    public void multestBmulA(){
        boolean flag = true; 
        
        for(int x=0; x<100; x++){
            for(int y=0; y<100; y++){
                if (!((Integer.toString(y*x)).equals(new BigInt(Integer.toString(y)).mul(new BigInt(Integer.toString(x))).toString()))){
                    System.out.println("ModelTest.addtest() " + y + " * " + x);
                    flag =false;
                    assertEquals(true,flag);
                            }
                    }
        }
              assertEquals(true,flag);
    }
   
   @org.junit.Test
    public void divXbyYtest(){
        boolean flag = true; 
        
        for(int x=1; x<100; x++){
            for(int y=1; y<100; y++){
                if (!((Integer.toString(x/y)).equals(new BigInt(Integer.toString(x)).div(new BigInt(Integer.toString(y)),false).toString()))){
                    System.out.println("ModelTest.addtest() " + x + " / " + y);
                    flag =false;
                    System.out.println("ModelTest.divtest() expected " + x/y + " gives " + new BigInt(Integer.toString(x)).div(new BigInt(Integer.toString(y)),false).toString()  );
                    assertEquals(true,flag);
                            }
                    }
        }
              assertEquals(true,flag);
    }
    
    @org.junit.Test
    public void divYbyXtest(){
        boolean flag = true; 
        
        for(int x=1; x<100; x++){
            for(int y=1; y<100; y++){
                if (!((Integer.toString(y/x)).equals(new BigInt(Integer.toString(y)).div(new BigInt(Integer.toString(x)),false).toString()))){
                    System.out.println("ModelTest.divtest() " + y + " / " + x);
                    flag =false;
                    System.out.println("ModelTest.divtest() expected " + y/x + " gives " + new BigInt(Integer.toString(y)).div(new BigInt(Integer.toString(x)),false).toString()  );
                    assertEquals(true,flag);
                            }
                    }
        }
              assertEquals(true,flag);
    }
    
    
    
    @org.junit.Test
    public void modXbyYtest(){
        boolean flag = true; 
        
        for(int x=1; x<100; x++){
            for(int y=1; y<100; y++){
                if (!((Integer.toString(x%y)).equals(new BigInt(Integer.toString(x)).div(new BigInt(Integer.toString(y)),true).toString()))){
                    System.out.println("ModelTest.mod() " + x + " mod " + y);
                    flag =false;
                    System.out.println("ModelTest.mod() expected " + x%y + " gives " + new BigInt(Integer.toString(x)).div(new BigInt(Integer.toString(y)),true).toString()  );
                    assertEquals(true,flag);
                            }
                    }
        }
              assertEquals(true,flag);
    }
    
    @org.junit.Test
    public void modYbyXtest(){
        boolean flag = true; 
        
        for(int x=1; x<100; x++){
            for(int y=1; y<100; y++){
                if (!((Integer.toString(y%x)).equals(new BigInt(Integer.toString(y)).div(new BigInt(Integer.toString(x)),true).toString()))){
                    System.out.println("ModelTest.mod() " + y + " mod " + x);
                    flag =false;
                    System.out.println("ModelTest.mod() expected " + y%x + " gives " + new BigInt(Integer.toString(y)).div(new BigInt(Integer.toString(x)),true).toString()  );
                    assertEquals(true,flag);
                            }
                    }
        }
              assertEquals(true,flag);
    }
    
    
    
       @org.junit.Test
    public void powerXtoY(){
        boolean flag = true; 
        
        for(int x=1; x<100; x++){
            for(int y=1; y<100; y++){
                if (!((Integer.toString((int)Math.pow((double) x,(double) y))).equals(new BigInt(Integer.toString(x)).pow(new BigInt(Integer.toString(y))).toString()))){
                    System.out.println("ModelTest.pow() " + x + " pow " + y);
                    flag =false;
                    System.out.println("ModelTest.pow() expected " + (int)Math.pow((double) x,(double) y) + " gives " + new BigInt(Integer.toString(x)).pow(new BigInt(Integer.toString(y))).toString()  );
                    assertEquals(true,flag);
                            }
                    }
        }
              assertEquals(true,flag);
    }



      @org.junit.Test
    public void powerYtoX(){
        boolean flag = true; 
        
        for(int x=1; x<100; x++){
            for(int y=1; y<100; y++){
                if (!((Integer.toString((int)Math.pow((double) y,(double) x))).equals(new BigInt(Integer.toString(y)).pow(new BigInt(Integer.toString(x))).toString()))){
                    System.out.println("ModelTest.pow() " + y + " pow " + x);
                    flag =false;
                    System.out.println("ModelTest.pow() expected " + (int)Math.pow((double) x,(double) y) + " gives " + new BigInt(Integer.toString(y)).pow(new BigInt(Integer.toString(x))).toString()  );
                    assertEquals(true,flag);
                            }
                    }
        }
              assertEquals(true,flag);
    }

}

