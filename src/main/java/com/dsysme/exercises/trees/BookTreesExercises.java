package com.dsysme.exercises.trees;

import java.util.Arrays;

public enum  BookTreesExercises {
    INSTANCE;

    public int computeTreeHeight(Tree tree) {
        if (tree == null) return 0;
        if (tree.getLeft() == null) return computeTreeHeight(tree.getLeft()) + 1;
        if (tree.getRight() == null) return computeTreeHeight(tree.getRight()) + 1;
        return Math.max(computeTreeHeight(tree.getLeft()), computeTreeHeight(tree.getRight())) + 1;
    }

    // 4.2
    public Tree minimalTree(int[] arr) {
        if (arr.length == 0) {
            return null;
        }
        if (arr.length == 1) {
            return new Tree(arr[0],null, null);
        }
        int pivot = arr[arr.length/2];
        int[] smallerElements = Arrays.copyOfRange(arr, 0, arr.length /2);
        int[] biggerElements =  Arrays.copyOfRange(arr, arr.length/2 + 1, arr.length);
        return new Tree(pivot, minimalTree(smallerElements), minimalTree(biggerElements));
    }
}
