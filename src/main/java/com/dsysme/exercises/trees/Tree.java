package com.dsysme.exercises.trees;

import java.util.ArrayList;
import java.util.List;

public class Tree {

    private Integer value;
    private Tree root;
    private Tree left;
    private Tree right;

    /**
     *        1
     *       / \
     *      2  3
     *     / \
     *    4  5
     * Depth First Traversals:
     * (a) Inorder (Left, Root, Right) : 4 2 5 1 3
     * (b) Preorder (Root, Left, Right) : 1 2 4 5 3
     * (c) Postorder (Left, Right, Root) : 4 5 2 3 1
     */
    public enum DepthFirstTraversals {
        INORDER,
        PREORDER,
        POSTORDER;
    }

    public Tree(Integer value, Tree left, Tree right) {
        // TODO copy left and right
        this.value = value;
        if (left != null) {
            this.left = left;
            this.left.setRoot(this);
        }
        if (right != null) {
            this.right = right;
            this.right.setRoot(this);
        }
    }

    public Tree(Integer value) {
        this.value = value;
        left = null;
        right = null;
    }

    public Tree getLeft() {
        return left;
    }

    public Tree getRight() {
        return right;
    }

    public Tree getRoot() {
        return root;
    }

    public Integer getValue() {
        return value;
    }

    public void setRoot(Tree root) {
        this.root = root;
    }

    /**
     *        1
     *       / \
     *      2  3
     *     / \
     *    4  5
     * Depth First Traversals:
     * (a) Inorder (Left, Root, Right) : 4 2 5 1 3
     */


//    private void inorderTraverse(List<T> list) {
//        Tree<T> current = this;
//        if (current.left == null) {
//            list.add(current.value);
//            if (current.root != null) {
//                if (this == current.root.left) {
//                    list.add(current.root.value);
//                    if (current.root.right != null) {
//                        current.root.right.inorderTraverse(list);
//                        return;
//                    }
//                }
//                if (current.root.root != null) {
//                    list.add(current.root.root.value);
//                    current.root.root.right.inorderTraverse(list);
//                    return;
//                }
//            }
//            return;
//        } else {
//            current.left.inorderTraverse(list);
//        }
//    }

    /**
     *        1
     *       / \
     *      2  3
     *     / \
     *    4  5
     * Depth First Traversals:
     * (b) Preorder (Root, Left, Right) : 1 2 4 5 3
     */
//    private void preorderTraverse(List<T> list) {
//        list.add(this.value);
//        this.left.preorderTraverse(list);
//        this.right.preorderTraverse(list);
//    }

    /**
     *        1
     *       / \
     *      2  3
     *     / \
     *    4  5
     * Depth First Traversals:
     * (c) Postorder (Left, Right, Root) : 4 5 2 3 1
     */
//    private void postOrderTraverse(List<T> list) {
//        Tree<T> current = this;
//        if (current.left == null) {
//            list.add(current.value);
//            current.right.postOrderTraverse(list);
//            list.add(this.root.value);
//        } else {
//            current.left.postOrderTraverse(list);
//        }
//    }

    /**
     * Depth First Traversals:
     * (a) Inorder (Left, Root, Right) : 4 2 5 1 3
     * (b) Preorder (Root, Left, Right) : 1 2 4 5 3
     * (c) Postorder (Left, Right, Root) : 4 5 2 3 1
     */
    public List<Integer> traverse(DepthFirstTraversals traverseOrder) {
        List<Integer> result = new ArrayList<>();
        switch (traverseOrder) {
            case INORDER:
                TraverseAlgorithms.INSTANCE.inOrder(result, this);
                break;
            case PREORDER:
                TraverseAlgorithms.INSTANCE.preorderTraverse(result, this);
                break;
            case POSTORDER:
                TraverseAlgorithms.INSTANCE.postOrderTraverse(result, this);
                break;
        }

        return result;
    }

}

