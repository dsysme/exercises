package com.dsysme.interviews;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AnyclipTest {

    @Test
    void customRandom() {
        int countZeros = 0;
        int countOnes = 0;
        int countTwos = 0;
        for (int i = 0; i < 100000; ++i) {
            int result = Anyclip.INSTANCE.customRandom();
            if (result == 0) {
                ++countZeros;
            } else if (result == 1) {
                ++countOnes;
            } else ++countTwos;
        }
        System.out.println(String.format("0: %d, 1: %d, 2:%d", countZeros/1000, countOnes/1000, countTwos/1000));
        assertTrue(countZeros/1000 > 58 && countZeros/1000 < 62);
        assertTrue(countOnes/1000 > 28 && countOnes/1000 < 32);
        assertTrue(countTwos/1000 > 8 && countTwos/1000 < 12);
    }


}