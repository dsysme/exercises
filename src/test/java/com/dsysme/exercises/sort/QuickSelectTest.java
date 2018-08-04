package com.dsysme.exercises.sort;

import com.dsysme.exercises.common.Tools;
import org.apache.commons.lang3.RandomUtils;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class QuickSelectTest {

    @Test
    void quickSelect() {
        for (int i = 0; i < 1000; ++i) {
            int[] arr = Tools.generateRandomIntArray(100);
            int[] arrCopy = Arrays.copyOf(arr, arr.length);
            int k = RandomUtils.nextInt(1, arr.length);
            System.out.println("Find " + k + " element is " + " in: " + Arrays.toString(arrCopy));
            int actual = QuickSelect.quickSelect(arr, k);
            Arrays.sort(arrCopy);
            int expected = arrCopy[k - 1];
            System.out.println("The "+k +" element is " + expected + " in: "+ Arrays.toString(arrCopy));
            assertEquals(expected, actual);
            System.out.println("The "+k +" element found was " + actual + " in: "+ Arrays.toString(arrCopy));
        }
    }

//    Find 6 element is  in: [943772, 482951, 933151, 230977, 321603, 324961, 956536, 295539, 654158, 593915, 409773, 798031, 366049, 615767, 518237]
//The 6 element is 409773 in: [230977, 295539, 321603, 324961, 366049, 409773, 482951, 518237, 593915, 615767, 654158, 798031, 933151, 943772, 956536]

    @Test
    void quickSelectF() {
        int[] arr = { 943772, 482951, 933151, 230977, 321603, 324961, 956536, 295539, 654158, 593915, 409773, 798031, 366049, 615767, 518237 };
        int[] arrCopy = { 943772, 482951, 933151, 230977, 321603, 324961, 956536, 295539, 654158, 593915, 409773, 798031, 366049, 615767, 518237 };
        Arrays.sort(arrCopy);
        int k = 6;
        int expected = arrCopy[k - 1];
        System.out.println("The " + k + " element is: " + expected + " in "+ Arrays.toString(arrCopy));
        int actual = QuickSelect.quickSelect(arr, k);
        assertEquals(expected, actual);
    }
}