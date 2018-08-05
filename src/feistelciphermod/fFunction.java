/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package feistelciphermod;

import java.math.BigInteger;
import java.util.Arrays;


/**
 *
 * @author koko_
 */
public class fFunction {
    final int [] expantionArray = {2, 3, 6, 21, 29, 17, 31, 4, 23, 14, 25, 0, 12, 24, 28, 17, 2, 5, 16, 7, 6, 1, 15, 16, 9, 8, 31, 0, 24, 26, 23, 27, 20, 21, 10, 15, 25, 4, 10, 19, 14, 28, 18, 22, 30, 2, 11, 13};
    String expanded = "";
    String key;
    String afterKeyXor ;
    String afterSBoxes = "";
    String afterPerm = "";
    final int [] permutationArray = { 20, 8, 16, 27, 24, 15, 23, 0, 29, 4, 10, 7, 12, 31, 17, 30, 5, 9, 28, 19, 6, 3, 11, 14, 25, 18, 2, 1, 21, 13, 22, 26};
    
    public  void Expand(long e){
        
        String temp = Long.toBinaryString(e);
//        System.out.println("before 32 append");
//        System.out.println(temp);
        
        while (temp.length() < 32)
            temp = "0" + temp;
//        System.out.println("after 32 append");
//        System.out.println(temp);
        for (int i = 0; i < 48; i++) {
            expanded+= temp.charAt(expantionArray[i]);
        }
//        System.out.println("expanded 32->48 ");
//        System.out.println(expanded);
    }
    public void KeyXor(long keyLong){
        key = Long.toBinaryString(keyLong);
        while (key.length() < 48)
            key = "0" + key;
//        System.out.println(key);
        StringBuilder sb = new StringBuilder();

        for(int i = 0; i < key.length(); i++)
            sb.append((key.charAt(i) ^ expanded.charAt(i)));

        afterKeyXor = sb.toString();
//        System.out.println("result = \n" + afterKeyXor);    
    }
    
    
    public void Sboxes(){
        String[] pairs = afterKeyXor.split("(?<=\\G\\d{12})");
       
        
        for (int i = 0; i < pairs.length; i++) {
//            System.out.println(pairs[i]);
            afterSBoxes+= pairs[i].substring(2, 10);
//            System.out.println("afterSBoxes = " + afterSBoxes);
        }
        
        
        

    }
    public void Perm()
    {
        System.out.println(afterSBoxes.length());
        System.out.println(permutationArray.length);
        for (int i = 0; i < afterSBoxes.length(); i++) {
            System.out.println("i = " + i);
            System.out.println(permutationArray[i]);
            afterPerm += afterSBoxes.charAt(permutationArray[i]);
        }
    }
    
    
    public long run(long msg , long key)
    {
        afterPerm="";
        afterSBoxes="";
        Expand(msg);
        KeyXor(key);
        Sboxes();
        Perm();
        return new BigInteger(afterPerm, 2).longValue() ;
        
    }

}
