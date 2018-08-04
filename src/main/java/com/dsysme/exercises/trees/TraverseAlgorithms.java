package com.dsysme.exercises.trees;

import java.util.List;

public enum TraverseAlgorithms {

    INSTANCE;

    public void inOrder(List<Integer> accumulator, Tree node)  {
        if (node != null) {
            inOrder(accumulator, node.getLeft());
            accumulator.add(node.getValue());
            inOrder(accumulator, node.getRight());
        }
    }

    public void preorderTraverse(List<Integer> accumulator, Tree node) {
        if (node != null) {
            accumulator.add(node.getValue());
            preorderTraverse(accumulator, node.getLeft());
            preorderTraverse(accumulator, node.getRight());
        }
    }

    public void postOrderTraverse(List<Integer> accumulator, Tree node) {
        if (node != null) {
            postOrderTraverse(accumulator, node.getLeft());
            postOrderTraverse(accumulator, node.getRight());
            accumulator.add(node.getValue());
        }
    }
}
