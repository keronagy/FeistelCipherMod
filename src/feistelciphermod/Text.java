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
public class Text {

public long left;
public long right;

public Text(long leftHalf,long rightHalf)
{
    this.left= leftHalf;
    this.right = rightHalf;
}

public static Text Split(long fullInput)
{
    long dividor = (long)pow(2,32)-1;
    return new Text( (fullInput >> 32) & dividor ,fullInput & dividor);
}


public static Text Swap(Text splittedText)
{
  long temp = splittedText.left;
  long nLeftPart = splittedText.right;
    long nRightPart = splittedText.left; 
  
  return new Text(nLeftPart,nRightPart);
}

}
