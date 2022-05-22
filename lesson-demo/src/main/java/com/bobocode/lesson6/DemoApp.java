package com.bobocode.lesson6;

import java.util.List;

public class DemoApp {

    public static void main(String[] args) {
        List<String> input = List.of("1","2","5","-1","10","0");

        MergeSort.sort(input);
    }
}
