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
public class main {

    /**
     * @param args the command line arguments
     */
  
    
    public static void main(String[] args) {
        long test = 898242749842849L;

       
      Random  r= new Random();

    
      long randomKey = r.nextLong();
     
      KeyGenerator kg = new KeyGenerator(8);
      long dgf[] = kg.generateRoundKeys(test);
      FiestelCipherMod  f = new FiestelCipherMod(dgf);
   
     

      long E = f.Encrypt(test);
    
      long D = f.Decrypt(E);
      System.out.println("Initial     " +PrintWithPadding(test)); 
      System.out.println("Encrypted   " + PrintWithPadding(E));
      System.out.println("Decrypted   " +(PrintWithPadding(D)));

     
        
        
        
        
       
        
    }
    public static String PrintWithPadding (long val)
    {
        String padding = "0000000000000000000000000000000000000000000000000000000000000000";
        String result = padding + Long.toBinaryString(val);
        result = result.substring(result.length() - 64, result.length());
        return result;
    }
    
}
