/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package feistelciphermod;

import java.util.Random;

/**
 *
 * @author kord
 */
public class KeyGenerator {
    
    private Random r;
    private long[] keys;
    private int numberOfRounds;
    public static final byte[] t1 = {
        57, 49, 41, 33, 25, 17, 9,
        1, 58, 50, 42, 34, 26, 18,
        10, 2, 59, 51, 43, 35, 27,
        19, 11, 3, 60, 52, 44, 36,
        63, 55, 47, 39, 31, 23, 15,
        7, 62, 54, 46, 38, 30, 22,
        14, 6, 61, 53, 45, 37, 29,
        21, 13, 5, 28, 20, 12, 4,
    };
    public static final byte[] t2 = {
        14, 17, 11, 24, 1, 5,
        3, 28, 15, 6, 21, 10,
        23, 19, 12, 4, 26, 8,
        16, 7, 27, 20, 13, 2,
        41, 52, 31, 37, 47, 55,
        30, 40, 51, 45, 33, 48,
        44, 49, 39, 56, 34, 53,
        46, 42, 50, 36, 29, 32
    };

    public KeyGenerator(int numberOfRounds) {
        this.r = new Random();
        this.numberOfRounds = numberOfRounds;
        this.keys = new long[numberOfRounds];
    }
    
    

    private static long permute(byte[] table, int srcWidth, long src) {
        long dst = 0;
        for (int i = 0; i < table.length; i++) {
            int srcPos = srcWidth - table[i];
            dst = (dst << 1) | (src >> srcPos & 0x01);
        }
        return dst;
    }
    public int lcs(int bits, int k) {
        return (bits << k) | (bits >> (Integer.SIZE - k));

    }
    
      private long generateKey(long k, int round) {

        k = permute(t1, 64, k);

        int lsbs = (int) (((0xFFFF0000) & (k)) >> 16);
        int msbs = (int) ((0x0000FFFF) & (k));
        int s = (round == 0 || round == 2 || round == 6) ? 1 : 2;

        lsbs = lcs(lsbs, s);
        msbs = lcs(msbs, s);
        long nk = (((long) lsbs) << 32) | (msbs & 0xffffffffL);
        long mask = r.nextLong()*(1 << 8 | 1 << 16 | 1 << 24 | 1 << 32 | 1 << 40 | 1 << 48 | 1 << 56);
        nk^=mask;
        nk = permute(t2, 64, nk);
        return nk;
    }
      

      public long[] generateRoundKeys(long key) {
        keys = new long[numberOfRounds];

        for (int i = 0; i < numberOfRounds; i++) {
            this.keys[i] = generateKey(key, i);
            key = this.keys[i];
        }
        return keys;
    }

}
