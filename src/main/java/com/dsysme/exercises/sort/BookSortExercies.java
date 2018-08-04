package com.dsysme.exercises.sort;

import java.util.*;

public class BookSortExercies {

    // 10.1 from cracking the code interview I had to read the hint :-(
    static public int[] mergeSorted(int[] a, int countA, int[] b) {
        int aIndex = countA - 1;
        int bIndex = b.length - 1;
        int mergeCount = countA + b.length;
        int mergeIndex = mergeCount - 1;
        // we put the elements in a from end to start, we take elements in descending order
        while (bIndex >= 0 && aIndex >= 0)        {
            if (a[aIndex] > b[bIndex] && mergeIndex != aIndex) {
                // take the biggest element left in a
                a[mergeIndex] = a[aIndex];
                --aIndex;
                --mergeIndex;
            } else {
                // take the biggest element left in b
                a[mergeIndex] = b[bIndex];
                --bIndex;
                --mergeIndex;
            }
        }
        // copy b reminder
        while (bIndex >= 0) {
           a[mergeIndex] = b[bIndex];
           --mergeIndex;
           --bIndex;
        }
        return a;
    }

    static private String[] sortByCharCount(String[] arr, char ch) {
        Map<Long,List<String>> helper = new HashMap<>();
        for (String s : arr) {
            long key = s.chars().filter(c -> c == ch).count();
            if (helper.containsKey(key)) {
                helper.get(key).add(s);
            } else {
                List<String> list = new ArrayList<>();
                list.add(s);
                helper.put(key,list);
            }
        }
        Long[] sortedKeys = new Long[helper.size()];
        helper.keySet().toArray(sortedKeys);
        Arrays.sort(sortedKeys);
        int i = 0;
        for (Long key : sortedKeys) {
            for (String s : helper.get(key)) {
                arr[i] = s;
                ++i;
            }
        }
        return arr;
    }


    // 10.2 from cracking the code interview This is different than the solution in the book
    static public String[] sortAnagrams(String[] arr) {
        char[] alphabet = "abcdefghijklmnopqrstuvwxyz".toCharArray();
        for (char ch : alphabet) {
            sortByCharCount(arr, ch);
        }
        return arr;
    }


    static public int binarySearchWithOffset(int[] arr, int from, int to, int element, int offset) {
        int size = to - from;

        if (size <= 1) {
            int transformedIndex = (from + offset) % arr.length;
            if (arr[transformedIndex] == element)
                // we found te druid we were looking for
                return transformedIndex;
            else
                // element not found
                return -1;
        }

        int logicIndex = from + size/2;
        int transformedIndex = ((from + size/2) + offset) % arr.length;
        if (arr[transformedIndex] < element) {
            // search right side of range
            return binarySearchWithOffset(arr, logicIndex, to, element, offset);
        } else if (arr[transformedIndex] > element) {
            // search left side of range
            return binarySearchWithOffset(arr, from, logicIndex, element, offset);
        }

        // (arr[index] == element) we found the druid we were looking for
        return transformedIndex;

    }

    static public int findOffset(int[] arr, int from, int to) {
        int index = from + (to - from)/2;
        if (arr[index] > arr[index+1])
            // we found the offset
            return index + 1;
        if (arr[index] > arr[to]) {
            return findOffset(arr, index, to);
        } else if (arr[index] < arr[to]) {
            return findOffset(arr, from, index);
        }
        return 0; // no offset
    }

    // 10.3 my solution is different form the solution of the book, though I think the
    // complexity is the same (mine is 2 * log n which is still log n)
    // my implementation is not as clean it could be. With the help of passing functions
    // it could have been cleaner, but the green tests suggests it works :-)
    static public int searchRotatedArray(int[] arr, int element) {
        int offset = findOffset(arr, 0, arr.length -1);
        return binarySearchWithOffset(arr, 0, arr.length, element, offset);
    }

    static class ArrayNoSize {
        int[] arr;

        public ArrayNoSize(int[] arr) {
            this.arr = arr;
        }

        int get(int i) {
            if (i >= arr.length)
                return -1;
            else
                return arr[i];
        }

    }

    static public int findSizeInRange(ArrayNoSize arr, int from, int to) {
        int index = from + (to - from) / 2;
        if (arr.get(index) != -1 && arr.get(index + 1) == -1) {
            // we found the size
            return index + 1;
        }
        if (arr.get(index) == -1) {
            // size is less then index
            return findSizeInRange(arr, from, index);
        }
        // size is greater than index
        return findSizeInRange(arr, index, to);
    }

    static public int findSize(ArrayNoSize arr) {
        int initialGuess = 100;
        if (arr.get(initialGuess) > 0 && arr.get(initialGuess + 1) == -1) {
            // we guessed the size
            return initialGuess + 1;
        }
        int lowerBound = initialGuess;
        int upperBound = initialGuess;
        // find range for the array size
        if (arr.get(initialGuess) != -1) {
            while (arr.get(upperBound) != -1) {
                lowerBound = upperBound;
                upperBound = upperBound * 2;
            }
        } else {
            while (arr.get(lowerBound) == -1) {
                upperBound = lowerBound;
                lowerBound = lowerBound / 2;
            }
        }

        return findSizeInRange(arr, lowerBound, upperBound);



    }

    // 10.4
    static public int findElement(ArrayNoSize arr, int element) {
        int size = findSize(arr);
        return binarySearchWithOffset(arr.arr, 0, size, element, 0);
    }

    // The book's solution searches left and right simultaneously and return the first found
    static private int findNonEmptyMiddle(String[] arr, int from, int to, String element) {
        int mid = (from + to)/2;
        // try to find a non empty cell left to given index
        while (arr[mid].isEmpty() && mid > from) {
            mid = mid - 1;
        }
        if (mid <= from && (mid < 0 || arr[mid].intern() != element || arr[mid].isEmpty())) {
            // try to find a non empty cell right to given index
            mid = (from + to)/2;
            while (arr[mid].isEmpty() && mid <= to) {
                mid = mid + 1;
            }
        }
        return mid;
    }

    // 10.5
    static public int findStringInSparseArray(String[] arr, int from, int to, String element) {
        int mid = (from + to)/2;
        int lowerBound = from;
        int upperBound = to;
        while (arr[mid].intern() != element.intern()) {
            // we didn't yet found the element
            if (upperBound - lowerBound <= 1) {
                // element not found
                return -1;
            }
            if (arr[mid].isEmpty()) {
                mid = findNonEmptyMiddle(arr, lowerBound, upperBound, element);
            }
            if (arr[mid].compareTo(element) < 0) {
                // element is on the right side of mid
                lowerBound = mid;
                mid = (lowerBound + upperBound)/2;
            } else if (arr[mid].compareTo(element) > 0) {
                // element is on the left side of mid
                upperBound = mid;
                mid = (lowerBound + upperBound)/2;
            } else {
                // we found the element
                return mid;
            }
        }
        return mid;
    }
}
