package com.dsysme.exercises.sort;

import java.util.ArrayList;
import java.util.List;

public class QuickSortExercises {

    static public class Helper {
        private List<Integer> smaller;
        private List<Integer> bigger;
        private int value;

        public Helper(List<Integer> smaller, List<Integer> bigger, int value) {
            this.smaller = smaller;
            this.bigger = bigger;
            this.value = value;
        }
    }

    /**
     * Traverse input arrays and return 2 arrays one holding all elements smaller than splitter and another holding all
     * element strictly bigger than the splitter
     * @param input
     * @param splitter
     * @return
     */
    public static Helper helper(List<Integer>  input, int splitter) {
        List<Integer> smaller = new ArrayList<>();
        List<Integer> bigger = new ArrayList<>();
        for (int i : input) {
            if (i <= splitter) {
                smaller.add(i);
            } else {
                bigger.add(i);
            }
        }
        return new Helper(smaller, bigger, splitter);
    }

    public static Integer findElement(List<Integer> elements, int k) {
        if (elements.size() == 1 && k == 1) {
            //  we found the druid we where looking for!
            return elements.get(0);
        }

        // chose a pivot and split the input array into smaller/bigger bags compared to the pivot
        int pivot = elements.get( elements.size() - 1); // get last element as pivot
        List<Integer> list = elements.subList(0, elements.size() - 1); // get all element except the last (the pivot)
        Helper helper = helper(list, pivot);
        if (helper.smaller.size() == k - 1) {
            return helper.value; // we found the druid we where looking for!
        } else {
            if (helper.smaller.size() < k ) {
                // the element we are looking for is bigger than our pivot
                return findElement(helper.bigger,k - (helper.smaller.size() + 1)/*counting the pivot here*/);
            }
            // the element we are looking for is smaller than our pivot
            return findElement(helper.smaller, k - 1);
        }
    }
}
