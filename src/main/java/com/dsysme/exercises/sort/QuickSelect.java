package com.dsysme.exercises.sort;

public class QuickSelect {

    /**
     * find the correct position of element currently in arr[to - 1] (noted as pivot) in a sorted version of arr
     * and put all elements smaller then the pivot left to that position and all greater elements right to that position.
     * If we ended up with k - 1 elements smaller than the pivot, then the pivot is druid we were looking.
     * If we ended up with more than k - 1 elements smaller than the pivot, then the druid we are looking for must be
     * smaller than the pivot and therefor somewhere in the left side of the array.
     * If we ended up with less than k - 1 elements smaller than the pivot, then the druid we are looking for must be
     * greater than the pivot and therefor somewhere in the right side of the array.
     *
     * If we didn't find the answer we recursively call quickSelect to find it either on the left or on the right.
     * May the force be with us and the odds ever in our favour.
     *
     * @param arr
     * @param from
     * @param to
     * @param k
     * @return
     */
    static private int quickSelect(int[] arr, int from, int to, int k) {
        int pivot = arr[to - 1]; // pivot is the last element in search range
        int firstGreaterOrEqualIndex = from;
        int currentIndex = from;
        while (currentIndex < to - 1) {
            // we still haven't reach the pivot which is the last element
            if (arr[currentIndex] > pivot) {
                ++currentIndex;
                continue;
            }
            if (arr[currentIndex] < pivot) {
                // we found an element that is less than the pivot and we position it on the left side of firstGreaterOrEqualIndex
                int tmp = arr[firstGreaterOrEqualIndex];
                arr[firstGreaterOrEqualIndex] = arr[currentIndex];
                ++firstGreaterOrEqualIndex;
                arr[currentIndex] = tmp;
                ++currentIndex;
            }
        }
        // put pivot in its rightful place
        arr[to - 1] = arr[firstGreaterOrEqualIndex];
        arr[firstGreaterOrEqualIndex] = pivot;

        // all element left of firstGreaterOrEqualIndex are smaller then the pivot and all element right of
        // firstGreaterOrEqualIndex are greater than the index
        if (firstGreaterOrEqualIndex == k - 1)
            // the pivot is the k-th element since k - 1 elements are smaller than it
            return pivot;


        // recursively call quickSelect with the relevant part of the array
        if (firstGreaterOrEqualIndex >= k) {
            // the pivot must be a number smaller than current pivot e.g. left of current pivot position
            return quickSelect(arr, from, firstGreaterOrEqualIndex, k);
        }

        // the pivot must be a number greater than current pivot e.g. right of current pivot position
        return quickSelect(arr, firstGreaterOrEqualIndex + 1, to, k);
    }

    /**
     * find the k-th element in unsorted array e.g. find the element E in arr for which exists exactly k-1 elements
     * in arr that are smaller or equal to E.
     *
     * Time complexity is O(log n)
     * Space complexity O(log n)
     *
     * @param arr unsorted array
     * @param k int between 1 and arr.length
     * @return the value sorted_arr[k -1] where sorted_arr is the sorted version of arr
     */
    static public int quickSelect(int[] arr, int k) {
        return quickSelect(arr, 0, arr.length, k);
    }

}
