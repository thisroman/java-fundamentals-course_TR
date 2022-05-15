package com.bobocode;

import java.util.Arrays;

public class HashTable<K, V> {

    @SuppressWarnings({"unchecked", "rawtype"})
    private Node<K, V>[] index = new Node[0];

    private int size = 0;

    // unique
    private boolean add(K key, V value) {
        String rawHash = String.valueOf(key);
        int numericValue = Character.getNumericValue(rawHash.charAt(0)) - 10;//'a' starts from 10
        resize(numericValue + 1);
        if (index[numericValue] != null) {
            Node<K, V> curr = index[numericValue];
            if (curr.hasKeyElement(key)) {
                return false;
            }
            index[numericValue] = new Node<>(key, value);
            index[numericValue].next = curr;
        }else {
            index[numericValue] = new Node<>(key, value);
        }
        return true;
    }

    public void printTable() {
        int i = 0;
        for (Node<K, V> el : index) {
            System.out.print(i++);
            Node.print(el);
            System.out.println("");
        }
    }

    public void resize(int newSize) {
        if (index.length < newSize) {
            index = Arrays.copyOf(index, newSize);
        }
    }

    public void put(K key, V value) {
        if (add(key, value)) {
            size++;
        }
    }

    public void clear() {
        index = new Node[0];
        size = 0;
    }

    public boolean containsValue(V value) {
        for (Node<K, V> el : index) {
            if (el.hasValueElement(value)) {
                return true;
            }
        }
        return false;
    }

    public boolean containsKey(K key) {
        for (Node<K, V> el : index) {
            if (el.hasKeyElement(key)) {
                return true;
            }
        }
        return false;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public void rehash() {

    }

    public V get(K key) {
        String rawHash = String.valueOf(key);
        int numericValue = Character.getNumericValue(rawHash.charAt(0)) - 10;//'a' starts from 10
        if (index[numericValue] != null) {
            Node<K, V> curr = index[numericValue];
            return curr.get(key);
        }
        return null;
    }

    V remove(K key) {
        String rawHash = String.valueOf(key);
        int numericValue = Character.getNumericValue(rawHash.charAt(0)) - 10;//'a' starts from 10
        if (index[numericValue] != null) {
            Node<K, V> curr = index[numericValue];

            // case for head removing
            if (curr.key.equals(key)) {
                V result = curr.value;
                index[numericValue] = curr.next;
                curr = null;
                size--;
                return result;
            } else {
                size--;
                return curr.remove(key);
            }
        }
        return null;
    }

    public int size() {
        return size;
    }
}
