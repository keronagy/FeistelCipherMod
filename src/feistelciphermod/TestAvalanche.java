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
        Random  r= new Random();
        Random r2 = new Random();
        long test = 8982431342848L;
    
      long randomKey = r.nextLong();
     int toggel = r.nextInt(65);
        System.out.println("toggel = " + toggel);
      KeyGenerator kg = new KeyGenerator(8);
      long dgf[] = kg.generateRoundKeys(randomKey);
      FiestelCipherMod  f = new FiestelCipherMod(dgf);
      long E = f.Encrypt(8982431342848L);
      long E2 = f.Encrypt(8982431342849L);
      
      long D = f.Decrypt(E);
      int k = countSetBits(E^E2);
        System.out.println("k = " + k);
      System.out.println("Initial     " +PrintWithPadding(test)); 
      System.out.println("Initial     " +PrintWithPadding(test+1));
      System.out.println("Encrypted   " + PrintWithPadding(E));
      System.out.println("Encrypted   " + PrintWithPadding(E2));
    }
    public static int countSetBits(long n)
    {
        int count = 0;
        while (n != 0) {
            count += n & 1;
            n >>= 1;
            
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
