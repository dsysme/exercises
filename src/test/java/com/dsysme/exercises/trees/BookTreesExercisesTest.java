package com.dsysme.exercises.trees;

import com.dsysme.exercises.common.Tools;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class BookTreesExercisesTest {

    @Test
    void minimalTree() {
        for (int i = 0; i < 1000; ++i) {
            int[] arr = Tools.generateRandomIntArray(300);
            Arrays.sort(arr);
            Tree actualTree = BookTreesExercises.INSTANCE.minimalTree(arr);

            // make sure tree height does not exceeds log(n)
            int actualHeight = BookTreesExercises.INSTANCE.computeTreeHeight(actualTree);
            double maxHeight = Math.round(Math.log(arr.length) / Math.log(2));
            System.out.println("actualHeight: " + actualHeight + " expected maximum height: " + maxHeight + " array: " + Arrays.toString(arr));
            assertTrue(actualHeight <= maxHeight);
            List<Integer> actualAsList = new ArrayList<>();
            List<Integer> expectedAsList = Tools.asList(arr);

            // make sure the tree produced didn't loose any elements or didn't maintained the order, by dumping it to
            // a list and comparing to the original array
            TraverseAlgorithms.INSTANCE.inOrder(actualAsList, actualTree);
            assertEquals(expectedAsList, actualAsList);
        }
    }

    @Test
    void computeTreeHeight() {
        Tree tree = Tools.buildTree();
        assertEquals(3, BookTreesExercises.INSTANCE.computeTreeHeight(tree));
    }
}