package com.bobocode;

import java.util.Objects;
import java.util.Stack;

public class DemoApp2 {

    public static void main(String[] args) {
        var head = createLinkedList(4, 3, 9, 1);
        printReversed(head);
    }

    /**
     * Creates a list of linked {@link Node} objects based on the given array of elements and returns a head of the list.
     *
     * @param elements an array of elements that should be added to the list
     * @param <T>      elements type
     * @return head of the list
     */
    public static <T> Node<T> createLinkedList(T... elements) {
        if (elements == null || elements.length == 0) {
            return null;
        }
        var head = new Node<>(elements[0]);
        var current = head;
        for (int i = 0; i < elements.length; i++) {
            current.next = new Node<>(elements[i]);
            current = current.next;
        }
        return head;
    }

    /**
     * Prints a list in a reserved order using a recursion technique. Please note that it should not change the list,
     * just print its elements.
     * <p>
     * Imagine you have a list of elements 4,3,9,1 and the current head is 4. Then the outcome should be the following:
     * 1 -> 9 -> 3 -> 4
     *
     * @param head the first node of the list
     * @param <T>  elements type
     */
    public static <T> void printReversed(Node<T> head) {
        Objects.requireNonNull(head);
        printRecursivly(head.next);
        System.out.println(head.element);
    }

    private static <T> void printRecursivly(Node<T> node) {
        var stack = new Stack<T>();

        if (node != null){
            printRecursivly(node.next);
            System.out.print(node.element + "->");
        }
    }

    /**
     * A generic node that holds an element of any type and a reference to the next element.
     *
     * @param <T> element type
     */
    public static class Node<T> {
        T element;
        Node<T> next;

        public Node(T element) {
            this.element = element;
        }
    }
}
