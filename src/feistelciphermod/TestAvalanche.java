/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package feistelciphermod;

import java.util.Random;

/**
 *
 * @author koko_
 */
public class TestAvalanche {
    
    public static void Test()
    {
        double k=0;
        for (int i = 0; i < 1000; i++) {
            
        
        Random  r= new Random();
        Random r2 = new Random();
        long test = r.nextLong();
    
      long randomKey = r.nextLong();
     int toggel = r.nextInt(65);
//        System.out.println("toggel = " + toggel);
      KeyGenerator kg = new KeyGenerator(8);
      long dgf[] = kg.generateRoundKeys(randomKey);
      
      long dgf2[] = kg.generateRoundKeys(randomKey ^ (1<< toggel));
      FiestelCipherMod  f = new FiestelCipherMod(dgf);
      FiestelCipherMod  f2 = new FiestelCipherMod(dgf2);
      long E = f.Encrypt(8982431342848L);
      long E2 = f2.Encrypt(8982431342848L);
      
      long D = f.Decrypt(E);
      long D2 = f2.Decrypt(E2);
      
       k+= countSetBits(E^E2)/64.0;
            //System.out.println("k = " + k);
//        System.out.println("k = " + k);
//      System.out.println("Initial     " +PrintWithPadding(8982431342848L)); 
//      System.out.println("Initial     " +PrintWithPadding(8982431342848L));
//      System.out.println("Encrypted   " + PrintWithPadding(E));
//      System.out.println("Encrypted2  " + PrintWithPadding(E2));
//      
//      System.out.println("Decrypted   " + PrintWithPadding(D));
//      System.out.println("Deccrypted   " + PrintWithPadding(D2));
    }
        double avg = k/1000;
        System.out.println("avg = " + avg);
    }
    public static int countSetBits(long c)
    {
        int count=0;
  
        while(c!=0){
         c = c &(c-1);
         count++;
        }
        return count;
       }
    
    
        public static String PrintWithPadding (long val)
    {
        String padding = "0000000000000000000000000000000000000000000000000000000000000000";
        String result = padding + Long.toBinaryString(val);
        result = result.substring(result.length() - 64, result.length());
        return result;
    }
    
}
