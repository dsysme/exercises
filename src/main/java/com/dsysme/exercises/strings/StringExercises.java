package com.dsysme.exercises.strings;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class StringExercises {
    // 1.1 implement an algorithm to determine if a string has all unique characters.
    // What if you cannot use additional data structure?
    static public Boolean isUnique(String str)  {
        char[] chars = str.toCharArray();
        Arrays.sort(chars);
        for (int i = 1; i < chars.length; ++i) {
            if (chars[i-1] == chars[i]) {
                return false;
            }
        }
        return true;
    }


    // 1.2 given 2 strings, write a method to decide if one is a permutation of the other
    static public boolean checkPermutation(String first, String second) {
        if (first.length() != second.length())
            return false;
        Map<Integer, Integer> firstCount = new HashMap<>();
        countChars(first, firstCount);
        Map<Integer, Integer> secondCount = new HashMap<>();
        countChars(first, secondCount);
        return firstCount.equals(secondCount);
    }

    private static void countChars(String first, Map<Integer, Integer> charToCount) {
        first.chars().forEach(ch -> {
            int count = 1;
            if (charToCount.containsKey(ch)) {
                count += charToCount.get(ch);
            }
            charToCount.put(ch, count);
        });
    }

    static public boolean checkPermutationWithSorting(String first, String second) {
        if (first.length() != second.length())
            return false;

        char[] firstArray = first.toCharArray();
        Arrays.sort(firstArray); //[C@11dc3715
        char[] secondArray = second.toCharArray();
        Arrays.sort(secondArray); //[C@a4102b8

        String sortedFirst = new String(first);
        String sortedSecond = new String(second);

        return sortedFirst.intern() == sortedSecond.intern();
    }


}

