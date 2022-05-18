package com.bobocode.homework5;

import com.bobocode.data.Accounts;
import com.bobocode.model.Account;

public class DemoApp2 {
    public static void main(String[] args) {
        var accountComparator = new RandomFieldComparator<>(Account.class, true);
        System.out.println(accountComparator);
        Accounts.generateAccountList(10)
                .stream()
                .sorted(accountComparator)
                .forEach(System.out::println);
    }
}
