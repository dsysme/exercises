package com.dsysme.exercises.sort;

import com.dsysme.exercises.sort.QuickSortExercises;
import org.apache.commons.lang3.RandomUtils;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class QuickSortExercisesTest {

    @Test
    void findElement() {
        int size = RandomUtils.nextInt(5, 20);
        List<Integer> myList = new ArrayList<>();
        List<Integer> myListCopy = new ArrayList<>();
        for (int i = 0; i < size; ++i) {
            int value = RandomUtils.nextInt(0, 100);
            myList.add(value);
            myListCopy.add(value);
        }

        System.out.println(myList.toString());
        System.out.println("\nThe array sorted:");
        Integer[] expected = new Integer[size];
        Arrays.sort(myListCopy.toArray(expected));
        for (int i = 0; i < size; ++i) {
            System.out.print(expected[i] + ",");
        }

        int k = RandomUtils.nextInt(1, size);
        System.out.println(String.format("Lets find the %s element. It should be %s", k, expected[k - 1].intValue()));
        int result = QuickSortExercises.findElement(myList, k);
        assertEquals(expected[k - 1].intValue(), result);
    }

    /*
    The array sorted:
        5,10,10,19,19,23,34,35,49,64,67,80,88,90,95,97,98,Lets find the 16 element.
    It should be 97
     */
    @Test
    void findElemenetF1() {
        Integer[] arr = {98, 90, 88, 10, 67, 97, 5, 19, 80, 10, 34, 49, 23, 35, 95, 64, 19};
        int k = 16;
        Integer expected = 97;
        Integer result = QuickSortExercises.findElement(Arrays.asList(arr), k);
        assertEquals(expected, result);
    }

    @Test
    void findElemenetF2() {
        Integer[] arr = {62, 42, 31, 1, 17, 91, 24, 59, 75, 32, 42, 19, 82, 0};
        int k = 6;
        Integer expected = 31;
        Integer result = QuickSortExercises.findElement(Arrays.asList(arr), k);
        assertEquals(expected, result);
    }
}