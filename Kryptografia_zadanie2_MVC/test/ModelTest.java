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
/*
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
        assertEquals(new BigInt("5").toString(), model.maxPowerOfTwo(new BigInt("32")).toString());
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
   */
    @org.junit.Test
   public void testMilerRabinFunction(){
    BigInt number_of_chars = new BigInt("21");
    BigInt blockSize = new BigInt("4");
    int accuracyMilerTest =20;
     boolean flag=true;
    int[] list = {17,19,23,29,31,37,41,43,47,53,59,61,67,71,73,79,83,89,97,101,
                103,107,109,113,127,131,137,139,149,151,157,163,167,173,179,181,191,193,197,
                199,211,223,227,229,233,239,241,251,257,263,269,271,277,281,283,293,307,311,
                313,317,331,337,347,349,353,359,367,373,379,383,389,397,401,409,419,421,431,
                433,439,443,449,457,461,463,467,479,487,491,499,503,509,521,523,541,547,557,
                563,569,571,577,587,593,599,601,607,613,617,619,631,641,643,647,653,659,661,
                673,677,683,691,701,709,719,727,733,739,743,751,757,761,769,773,787,797,809,
                811,821,823,827,829,839,853,857,859,863,877,881,883,887,907,911,919,929,937,
                941,947,953,967,971,977,983,991,997,1009,1013,1019,1021,1031,1033,1039,1049,
                1051,1061,1063,1069,1087,1091,1093,1097,1103,1109,1117,1123,1129,1151,1153,1163,
                1171,1181,1187,1193,1201,1213,1217,1223,1229,1231,1237,1249,1259,1277,1279,1283,
                1289,1291,1297,1301,1303,1307,1319,1321,1327,1361,1367,1373,1381,1399,1409,1423,
                1427,1429,1433,1439,1447,1451,1453,1459,1471,1481,1483,1487,1489,1493,1499,1511,
                1523,1531,1543,1549,1553,1559,1567,1571,1579,1583,1597,1601,1607,1609,1613,1619,
                1621,1627,1637,1657,1663,1667,1669,1693,1697,1699,1709,1721,1723,1733,1741,1747,
                1753,1759,1777,1783,1787,1789,1801,1811,1823,1831,1847,1861,1867,1871,1873,1877,
                1879,1889,1901,1907,1913,1931,1933,1949,1951,1973,1979,1987,1993,1997,1999,2003,
                2011,2017,2027,2029,2039,2053,2063,2069,2081,2083,2087,2089,2099,2111,2113,2129,
                2131,2137,2141,2143,2153,2161,2179,2203,2207,2213,2221,2237,2239,2243,2251,2267,
                2269,2273,2281,2287,2293,2297,2309,2311,2333,2339,2341,2347,2351,2357,2371,2377,
                2381,2383,2389,2393,2399,2411,2417,2423,2437,2441,2447,2459,2467,2473,2477,2503,
                2521,2531,2539,2543,2549,2551,2557,2579,2591,2593,2609,2617,2621,2633,2647,2657,
                2659,2663,2671,2677,2683,2687,2689,2693,2699,2707,2711,2713,2719,2729,2731,2741,
                2749,2753,2767,2777,2789,2791,2797,2801,2803,2819,2833,2837,2843,2851,2857,2861};

    Model model = new Model(number_of_chars, blockSize, accuracyMilerTest);
   
        System.out.println("ModelTest.zaczynam ()");
   for (int i=0;i<list.length; i++){
       if (!(model.testMileraRabina(new BigInt(Integer.toString(list[i])))))
       {
           flag = false;
           System.out.println("ModelTest.testMilerRabinFunction() " + list[i] );
           assertEquals(true, flag);
       }
       assertEquals(true, flag);
   }
   /*
   // Test milera dla wyniku liczby zlozonej 
   flag = false;
   for (int i=0;i<list.length; i++){
       
       if (model.testMileraRabina(new BigInt(Integer.toString((list[i] +((list[i+1]-list[i])/2))   ))))
       {
           flag = true;
           System.out.println("ModelTest.testMilerRabinFunction() false " + i + " " +Integer.toString((list[i] +((list[i+1]-list[i])/2) )));
           assertEquals(false, flag);
       }
      
   }
    assertEquals(true, flag);
       */
    //assertEquals(true, model.testMileraRabina(79039));
    //assertEquals(true, model.testMileraRabina(99241));
       
      // False
      
    //assertEquals(false, model.testMileraRabina(new BigInt("77")));
    //assertEquals(false, model.testMileraRabina(new BigInt("85")));
   } 
   /*
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
    
   */ 
}

