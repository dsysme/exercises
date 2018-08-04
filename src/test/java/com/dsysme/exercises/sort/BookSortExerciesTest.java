package com.dsysme.exercises.sort;

import com.dsysme.exercises.common.Tools;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.RandomUtils;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class BookSortExerciesTest {



    @Test
    void mergeSorted() {
        int[] t = Tools.generateRandomIntArray(20);
        Arrays.sort(t);
        int[] b = Tools.generateRandomIntArray(20);
        Arrays.sort(b);
        int[] a = new int[t.length + b.length];
        int[] expected = new int[t.length + b.length];
        for (int i = 0; i < t.length; ++i) {
            a[i] = t[i];
            expected[i] = t[i];
        }
        for (int i = 0; i < b.length; ++i) {
            expected[t.length+i] = b[i];
        }

        Arrays.sort(expected);

        System.out.println("a:" + Arrays.toString(a));
        System.out.println("b:" + Arrays.toString(b));
        System.out.println("expected:" + Arrays.toString(expected));
        int[] result = BookSortExercies.mergeSorted(a, t.length, b);
        System.out.println("result:" + Arrays.toString(result));

        assertArrayEquals(result, expected);
    }

    @Test
    void mergeSortedF() {
        int[] a = {20, 23, 32, 51, 77, 0, 0, 0, 0, 0, 0, 0};
        int[] b = {8, 16, 29, 40, 78, 86, 87};
        int[] expected = {8, 16, 20, 23, 29, 32, 40, 51, 77, 78, 86, 87};
        int[] result = BookSortExercies.mergeSorted(a, 5, b);
        System.out.println("a:" + Arrays.toString(a));
        System.out.println("b:" + Arrays.toString(b));
        System.out.println("expected:" + Arrays.toString(expected));
        System.out.println("result:" + Arrays.toString(result));
        assertArrayEquals(result, expected);
    }

    @Test
    void sortAnagrams() {
        String[] arr = {"abc", "plf", "goo", "cbc", "oog", "cba"};
        String[] result = BookSortExercies.sortAnagrams(arr);
        System.out.println(Arrays.toString(result));
    }

    @Test
    void binarySearch() {
        for (int i = 0; i < 100; ++i) {
            int[] arr = Tools.generateRandomIntArray(20);
            Arrays.sort(arr);
            int expected = RandomUtils.nextInt(0, arr.length);
            int element = arr[expected];
            System.out.println("Search for:" + element + " in: " + Arrays.toString(arr));
            int actual = BookSortExercies.binarySearchWithOffset(arr, 0, arr.length, element, 0);
            System.out.println("element's index is:" + actual);
            assertTrue(expected == actual || arr[expected] == arr[actual]);
        }
    }

    @Test
    void binarySearchWithOffset() {
        int[] arr = { 15,16,19,20,25,1,3,4,5,7,10,14 };
        int actual = BookSortExercies.binarySearchWithOffset(arr, 0, arr.length, 5, 5);
        assertEquals( 8, actual);
    }
/*
rotate [10, 16, 37, 56, 71, 75] by 0: [10, 16, 37, 56, 71, 75]
Search for:75 in: [10, 16, 37, 56, 71, 75]
element's index is:-1

 */
    @Test
    void binarySearchWithOffsetF() {
        int[] arr = { 10, 16, 37, 56, 71, 75 };
        int actual = BookSortExercies.searchRotatedArray(arr, 75);
        assertEquals( 5, actual);
    }

    @Test
    void findOffset() {
        int[] arr = { 16,19,20,25,1,3,4,5,7,10,14,15 };
        int actual = BookSortExercies.findOffset(arr, 0, arr.length -1);
        assertEquals( 4, actual);
    }


    @Test
    void findRotated() {
        for (int i = 0; i < 1000; ++i) {
            int[] arr = Tools.generateRandomIntArray(20);
            int offset = RandomUtils.nextInt(0, arr.length);
            Arrays.sort(arr);
            int[] rotated = Tools.rotate(arr, offset);
            System.out.println("rotate " + Arrays.toString(arr) + " by " + offset + ": " + Arrays.toString(rotated));
            int randomIndex = RandomUtils.nextInt(0, arr.length);
            int element = arr[randomIndex];
            System.out.println("Search for:" + element + " in: " + Arrays.toString(rotated));
            int actual = BookSortExercies.searchRotatedArray(rotated, element);
            System.out.println("element's index is:" + actual);
            assertTrue(rotated[actual] == element);
        }
    }


    @Test
    void findRotatedF() {
        int[] rotated = { 89, 0, 34, 38, 45, 76 };
        int actual = BookSortExercies.binarySearchWithOffset(rotated, 0, rotated.length, 0, 1);
        assertEquals(1, actual);
    }

    @Test
    void findRotatedF2() {
        int[] rotated = { 91, 0, 1, 3, 23, 42, 44, 46, 52, 52, 56, 65, 76, 78, 80 };
        int actual = BookSortExercies.searchRotatedArray(rotated, 0);
        assertEquals(1, actual);
    }

    @Test
    void findElementNoSize() {
        for (int i = 0; i < 10000; ++i) {
            int[] arr = Tools.generateRandomIntArray(1000);
            Arrays.sort(arr);
            int randomIndex = RandomUtils.nextInt(0, arr.length);
            int element = arr[randomIndex];
            BookSortExercies.ArrayNoSize ans = new BookSortExercies.ArrayNoSize(arr);
            int index = BookSortExercies.findElement(ans, element);
            System.out.println("found element "+element+" at "+ index +" in:"+ Arrays.toString(arr));
            assertEquals(arr[index], element);
        }
    }

    @Test
    void findElementWhenElementNotInArray() {
        for (int i = 0; i < 10000; ++i) {
            int[] arr = Tools.generateRandomIntArray(1000);
            Arrays.sort(arr);
            int element = arr[arr.length - 1] + 1;
            BookSortExercies.ArrayNoSize ans = new BookSortExercies.ArrayNoSize(arr);
            int index = BookSortExercies.findElement(ans, element);
            System.out.println("found element "+element+" at "+ index +" in:"+ Arrays.toString(arr));
            assertEquals(index, -1);
        }
    }

    @Test
    void findStringInSparseArray() {
        for (int i = 0; i < 1000; ++i) {
            System.out.println("ROUND: "+i);
            String[] arr = Tools.generateRandomStringSortedSparseArray(20);
            if (Arrays.asList(arr).stream().allMatch(s -> s.isEmpty())) {
                // skip all empty array
                continue;
            }
            int randomIndex = RandomUtils.nextInt(0, arr.length -1);
            while (arr[randomIndex].isEmpty()) {
                // we need to find a non empty string
                randomIndex = RandomUtils.nextInt(0, arr.length);
            }
            String element = arr[randomIndex];
            System.out.println("find element " + element + " in:" + Arrays.toString(arr));
            int actual = BookSortExercies.findStringInSparseArray(arr, 0, arr.length, element);
            System.out.println("found element " + element + " at " + actual + " in:" + Arrays.toString(arr));
            assertEquals(element, arr[actual]);
        }
    }

    @Test
    void searchNonExistElement() {
        String[] arr = {"", "", "EQrbEGkMd", "", "", "", "FLqBsKBPcb", "Fes", "ISdsOHYaQCw", "ImrqObKlTzz", "dCyrjPHFiC", "sKkKGcbDpGTWlJFveX", "sQXGPTADf", "vVjWLkeQirs", "yOaFTTSnTp", "zGMeXaNISDkHcp"};
        String expected = "Haskell";
        int actual = BookSortExercies.findStringInSparseArray(arr, 0, arr.length, expected);
        assertEquals(-1, actual);
    }

    @Test
    void searchFirstNotEmptyElement() {
        String[] arr = {"", "", "", "", "", "GQLkOXXCkuUrMikED", "HFFzITahhUlN", "OIgcgJ", "QDC", "RBExdqcOSNwKfZcfqaQ", "RbCtw", "VJQXpzWfGTaaE", "VwNILYaeXD", "XmnVCWjwy", "ZuGpMEnsbkgppAMik", "exRvWQU", "kJWaIJmY", "xQealnWKneg"};
        String expected = "GQLkOXXCkuUrMikED";
        int actual = BookSortExercies.findStringInSparseArray(arr, 0, arr.length, expected);
        assertEquals(5, actual);
    }
}