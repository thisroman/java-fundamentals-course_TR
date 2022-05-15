package com.bobocode;

import com.bobocode.data.Accounts;

import java.time.LocalDate;
import java.util.Arrays;

public class DemoApp {

    public static void main(String[] args) {
        var accounts = Accounts.generateAccountList(10);
        var emailToBirthdayTable = new HashTable<String, LocalDate>();
        accounts.forEach(a -> emailToBirthdayTable.put(a.getEmail(), a.getBirthday()));

        System.out.println("***" + accounts.get(0));
        System.out.println("----" + emailToBirthdayTable.remove(accounts.get(0).getEmail()));

        emailToBirthdayTable.printTable();
    }

//    public static void main2(String[] args) {
//        Node<Integer> h = createLinkedList(5,7,1,4);
//        printLinkedList(h);
//    }
//
//    public static <T> Node<T> createLinkedList(T... elements){
//        Node<T> h = null;
//        Node<T> curr = null;
//       for(T el : elements){
//           if (h == null) {
//               h = new Node<>(el);
//               curr = h;
//           } else{
//               curr = curr.add(el);
//           }
//       }
//       return h;
//    }
//
//    public static void printLinkedList(Node<?> head){
//        Node.print(head);
//    }
}
