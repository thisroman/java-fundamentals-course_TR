package com.bobocode.lesson10;

import java.util.Comparator;
import java.util.function.Function;

public class DemoApp2 {

    public static void main(String[] args) {
        System.out.println("test");
    }

//    public static <T, ? super Comparable<R>> Comparator<T> getComparatore(Function<? super T, ? extends R> extractor) {
//        return (o1, o2) -> {
//            return extractor.apply(o1).compareTo(extractor.apply(o2));
//        };
//    }
}
