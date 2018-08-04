/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package feistelciphermod;

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
        String padding = "0000000000000000000000000000000000000000000000000000000000000000";
        
        FiestelCipherMod  f = new FiestelCipherMod();
  
        long E = f.Encrypt(test);
        
   
//        
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