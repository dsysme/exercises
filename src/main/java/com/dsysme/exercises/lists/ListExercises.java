package com.dsysme.exercises.lists;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ListExercises {
    static class Node {
        Node next = null;
        int data;

        public Node(int data) {
            this.data = data;
        }

        public void appendToTail(int d) {
            Node end = new Node(d);
            Node current = this;
            while (current.next != null)
                current = current.next;
            current.next = end;
        }

        static public Node fromList(List<Integer> list) {
            if (list.isEmpty())
                return null;
            int value = list.get(0);
            Node result = new Node(value);
            Node current = result;
            for (Integer i : list.subList(1, list.size())) {
                current.next = new Node(i);
                current = current.next;
            }
            return result;
        }

        public String toString() {
            StringBuilder sb = new StringBuilder();
            Node current = this;
            while (current != null) {
                sb.append("[").append(current.data).append("]->");
                current = current.next;
            }
            sb.append("[]");
            return sb.toString();
        }
    }

//    public Node deleteNode(Node head, int value) {
//
//        if (head.data == value)
//            return head.next;
//
//        Node n = head;
//        while (n.next != null) {
//           if (n.next.data == value) {
//               n.next = n.next.next;
//               break;
//           }
//        }
//        return head;
//    }

    static public Node removeDups(Node node) {
        if (node == null)
            return null;
        Node prev = node;
        Node current = node.next;
        Set<Integer> elements = new HashSet<>();
        while (current != null) {
            if (elements.contains(current.data)) {
                prev.next = current.next;
            }
            elements.add(current.data);
            prev = current;
            current = current.next;
        }
        return node;
    }
}
