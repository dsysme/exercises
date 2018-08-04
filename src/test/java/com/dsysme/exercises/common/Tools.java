package com.dsysme.exercises.common;

import com.dsysme.exercises.trees.Tree;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.RandomUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Tools {

    static public int[] generateRandomIntArray(int max) {
        int size = RandomUtils.nextInt(5, max);
        int[] a = new int[size];
        for (int i = 0; i < size; ++i) {
            int value = RandomUtils.nextInt(0, 1000000);
            a[i] = value;
        }
        return a;
    }

    static public List<Integer> asList(int[] array) {
        List<Integer> intList = new ArrayList<>();
        for (int i : array)
        {
            intList.add(i);
        }
        return intList;
    }

    static public String[] generateRandomStringSortedSparseArray(int max) {
        int size = RandomUtils.nextInt(max / 2, max);
        String[] result = new String[size];
        for (int i = 0; i < size; ++i) {
            result[i] = RandomStringUtils.randomAlphabetic(2, 20);
        }
        Arrays.sort(result);
        int sparseCount = RandomUtils.nextInt(size / 4, (size * 3) / 4);
        int resultIndex = 0;
        String[] sparseResult = new String[size + sparseCount];
        for (int i = 0; i < sparseResult.length; ++i) {
            if (sparseCount > 0 && RandomUtils.nextBoolean()) {
                --sparseCount;
                sparseResult[i] = "";
            } else if (resultIndex < result.length) {
                sparseResult[i] = result[resultIndex++];
            } else {
                --sparseCount;
                sparseResult[i] = "";
            }
        }

        return sparseResult;
    }

    static public int[] rotate(int[] arr, int offset) {
        int[] result = new int[arr.length];
        for (int i = 0; i < arr.length; ++i) {
            int transformedIndex = (i + offset) % arr.length;
            result[transformedIndex] = arr[i];
        }
        return result;
    }

    public static Tree buildTree() {
        Tree leftTree = new Tree(2, new Tree(4, null, null), new Tree(5, null, null));
        Tree rightTree = new Tree(3, null, null);
        return new Tree(1, leftTree, rightTree);
    }

}
