package com.bobocode.lesson8;

import com.bobocode.data.Accounts;
import com.bobocode.model.Account;

import java.util.Comparator;
import java.util.function.Function;

public class DemoApp2 {

    public static void main(String[] args) {
        var accountWithMaxBalance = Accounts.generateAccountList(10)
                .stream()
                .peek(System.out::println)
                .max(createComparatorComparing(Account::getBalance))
                .orElseThrow();

        System.out.println("----");
        System.out.println(accountWithMaxBalance);
        System.out.println("----");
        Accounts.generateAccountList(10)
                .stream()
                .sorted(
                        composeComparatorThenComparing(createComparatorComparing(Account::getSex), Account::getEmail)
                )
                .forEach(System.out::println);
    }

    private static <T, R extends Comparable<? super R>> Comparator<T> createComparatorComparing(
            Function<? super T, ? extends R> extractor) {

        return (o1, o2) -> extractor.apply(o1).compareTo(extractor.apply(o2));
    }

    private static <T, R extends Comparable<? super R>> Comparator<T> composeComparatorThenComparing(
            Comparator<? super T> comparator, Function<? super T, ? extends R> function) {
        return (o1, o2) -> comparator.compare(o1, o2) != 0 ? comparator.compare(o1, o2) :
                function.apply(o1).compareTo(function.apply(o2));
    }
}
