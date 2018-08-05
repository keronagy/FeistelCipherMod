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
  long[] keys = {};
  
  fFunction f = new fFunction();
  
    FiestelCipherMod(long[] keys)
  {
      this.keys=keys;
  }
       
public long Encrypt(long fullInput)
{
    Text newSplittedText;

    newSplittedText = Text.Split(fullInput);

     for(int i =0; i<8; i++)
     {
    newSplittedText = new Text(newSplittedText.right & ((long)pow(2,32)-1),
            (newSplittedText.left ^  f.run(newSplittedText.right,keys[i])) & ((long)pow(2,32)-1) );
     
     if(i==7)
     {
     newSplittedText = Text.Swap(newSplittedText);
     
     }
     
     }
     return (newSplittedText.left << 32) + newSplittedText.right;
}

public long Decrypt(long fullInput)
{
    Text newSplittedText;

    newSplittedText = Text.Split(fullInput);
    
     for(int i =0; i<8; i++)
     {
            newSplittedText = new Text(newSplittedText.right & ((long)pow(2,32)-1), 
                    (newSplittedText.left ^  f.run(newSplittedText.right,keys[7-i])) & ((long)pow(2,32)-1) );
           
            if(i==7)
     {
    
     newSplittedText = Text.Swap(newSplittedText);
  
     }
     }  
     return (newSplittedText.left << 32) + newSplittedText.right;
}

}
