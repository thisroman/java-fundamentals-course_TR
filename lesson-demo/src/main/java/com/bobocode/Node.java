package com.bobocode;

public class Node<K, V> {
    K key;
    V value;
    Node<K, V> next;

    public Node(K key, V value) {
        this.key = key;
        this.value = value;
    }

    public Node<K, V> add(K key, V value) {
        this.next = new Node<>(key, value);
        return next;
    }


    public Node<K, V> printAndRemove(Node<K, V> head) {
        Node<K, V> prev = head;
        System.out.print(prev.toString());
        prev = null;
        return head.next;
    }

    public static <K,V> void print(Node<K,V> head) {
        while (head != null) {
            head = head.printAndRemove(head);
            if (head != null) {
                System.out.print("->");
            }
        }
    }

    @Override
    public String toString() {
        return " " + key + ": " + value + " ";
    }

    public boolean hasKeyElement(K key) {
        if (this.key.equals(key)){
            return true;
        }
        Node<K,V> iter = this.next;
        while (iter != null) {
            if(iter.key.equals(key)){
                return true;
            }
            iter = iter.next;
        }
        return false;
    }

    public boolean hasValueElement(V value) {
        if (this.value.equals(value)){
            return true;
        }
        Node<K,V> iter = this.next;
        while (iter != null) {
            if(iter.value.equals(value)){
                return true;
            }
            iter = iter.next;
        }
        return false;
    }

    public V get(K key) {
        if (this.key.equals(key)){
            return value;
        }
        Node<K,V> iter = this.next;
        while (iter != null) {
            if(iter.key.equals(key)){
                return iter.value;
            }
            iter = iter.next;
        }
        return null;
    }

    // not for first
    public V remove(K key) {
        V result = get(key);

        Node<K,V> prev = this;
        Node<K,V> iter = this.next;
        while (iter != null) {
            if(iter.key.equals(key)){
                prev.next = iter.next;
                break;
            }
            prev = iter;
            iter = iter.next;
        }
        return result;
    }
}
