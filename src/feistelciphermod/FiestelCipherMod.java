/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package feistelciphermod;

import static java.lang.Math.pow;

/**
 *
 * @author ASUS
 */
public class FiestelCipherMod {
  long[] keys = {273409523799523L,94197096021383L,114827730219342L,46625876281884L,
                    280391729122195L,214218025129539L,251859736875900L,213159350773165L};
  
  fFunction f = new fFunction();
          
       
public long Encrypt(long fullInput)
{
    Text newSplittedText;
            
//    System.out.println((Long.toBinaryString((long)fullInput)));
    newSplittedText = Text.Split(fullInput);
//    System.out.println((Long.toBinaryString((long)newSplittedText.left) + "    " + Long.toBinaryString((long)newSplittedText.right)));
     for(int i =0; i<8; i++)
     {
    newSplittedText = new Text(newSplittedText.right & ((long)pow(2,32)-1), (newSplittedText.left ^  f.run(newSplittedText.right,keys[i])) & ((long)pow(2,32)-1) );
    System.out.println(i+ "   " + (Long.toBinaryString((long)newSplittedText.left) + "    " + Long.toBinaryString((long)newSplittedText.right)));
//     System.out.println(Long.toBinaryString((long)newSplittedText.left));
//     System.out.println(Long.toBinaryString((long)newSplittedText.right));
     
     if(i==7)
     {
     newSplittedText = Text.Swap(newSplittedText);
     System.out.println(i+ "   " + (Long.toBinaryString((long)newSplittedText.left) + "    " + Long.toBinaryString((long)newSplittedText.right)));
     }
     
     }
     return (newSplittedText.left << 32) + newSplittedText.right;
}

public long Decrypt(long fullInput)
{
    Text newSplittedText;
//     System.out.println("d"+(Long.toBinaryString((long)fullInput)));
    newSplittedText = Text.Split(fullInput);
    
     for(int i =0; i<8; i++)
     {
            newSplittedText = new Text(newSplittedText.right & ((long)pow(2,32)-1), (newSplittedText.left ^  f.run(newSplittedText.right,keys[7-i])) & ((long)pow(2,32)-1) );
            System.out.println("d"+i+ "   " + (Long.toBinaryString((long)newSplittedText.left) + "    " + Long.toBinaryString((long)newSplittedText.right)));
            if(i==7)
     {
     newSplittedText = Text.Swap(newSplittedText);
     }
     }
     return (newSplittedText.left << 32) + newSplittedText.right;
}

}