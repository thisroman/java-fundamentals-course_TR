package com.bobocode;

import com.bobocode.data.Accounts;
import com.bobocode.model.Account;

import java.util.Objects;
import java.util.Stack;

public class DemoApp3 {

    public static void main(String[] args) {
//        var accounts = Accounts.generateAccountList(10);
//        var randomComparator = new RandomFieldCOmparator(Account.class);
//        accounts.stream()
//                .sorted(Account::getLastName)
//                .forEach(System.out::println);
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
