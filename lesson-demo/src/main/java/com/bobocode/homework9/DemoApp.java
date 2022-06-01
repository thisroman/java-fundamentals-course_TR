package com.bobocode.homework9;

import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;

public class DemoApp {
    public static void main(String[] args) {
        var list = new ArrayList<>(ThreadLocalRandom.current().ints(100, 0, 200).boxed().collect(Collectors.toSet()));
        System.out.println(list);
        var mergeSort = new MergeSort<>(list);
        mergeSort.compute();
        System.out.println(list);
    }
}
