package com.dsysme.exercises.trees;

import com.dsysme.exercises.common.Tools;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TreeTest {

    // TODO add tests with generating random trees

    /**
     *        1
     *       / \
     *      2  3
     *     / \
     *    4  5
     * Depth First Traversals:
     * (a) Inorder (Left, Root, Right) : 4 2 5 1 3
     */
    @Test
    void traverseInorder() {

        Tree tree = Tools.buildTree();

        Integer[] array = { 4, 2, 5, 1, 3 };
        List<Integer> expect = Arrays.asList(array);
        List<Integer> actual = tree.traverse(Tree.DepthFirstTraversals.INORDER);
        System.out.println(actual);
        assertEquals(expect, actual);

    }


    /**
     *        1
     *       / \
     *      2  3
     *     / \
     *    4  5
     * Depth First Traversals:
     * (b) Preorder (Root, Left, Right) : 1 2 4 5 3
     */
    @Test
    void traversePreorder() {
        Tree tree = Tools.buildTree();
        Integer[] array = {1, 2, 4, 5, 3};
        List<Integer> expect = Arrays.asList(array);
        List<Integer> actual = tree.traverse(Tree.DepthFirstTraversals.PREORDER);
        System.out.println(actual);
        assertEquals(expect, actual);
    }

    /**
     *        1
     *       / \
     *      2  3
     *     / \
     *    4  5
     * Depth First Traversals:
     * (c) Postorder (Left, Right, Root) : 4 5 2 3 1
     */
    @Test
    void traversePostorder() {

        Tree tree = Tools.buildTree();

        Integer[] array = { 4, 5, 2, 3, 1 };
        List<Integer> expect = Arrays.asList(array);
        List<Integer> actual = tree.traverse(Tree.DepthFirstTraversals.POSTORDER);
        System.out.println(actual);
        assertEquals(expect, actual);

    }

    @Test
    void traverseRootOnlyTree() {
        Tree tree = new Tree(1, null, null);
        Integer[] expected = {1};
        List<Integer> actual = tree.traverse(Tree.DepthFirstTraversals.INORDER);
        assertEquals(Arrays.asList(expected), actual);
        actual = tree.traverse(Tree.DepthFirstTraversals.PREORDER);
        assertEquals(Arrays.asList(expected), actual);
        actual = tree.traverse(Tree.DepthFirstTraversals.POSTORDER);
        assertEquals(Arrays.asList(expected), actual);
    }
}