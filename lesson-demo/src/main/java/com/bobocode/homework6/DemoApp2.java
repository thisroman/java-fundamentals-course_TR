package com.bobocode.homework6;

import java.util.*;

public class DemoApp2 {


    public static void main(String[] args) {

        List<Integer> input = Arrays.asList(1111, 11, 34, 6, 77, 32, 8, 0, 76, 5, 3, 3, 43, 45, 66, -6);
        mergeSort(input).stream().forEach(el -> {
            System.out.print(el.toString() + " ");
            System.out.println();
        });
    }

    public static <T> void sort(List<T> input) {

    }

    private static <T extends Comparable<T>> List<T> mergeSort(List<T> input) {
        Objects.requireNonNull(input);
        if (input.getClass().isInstance(Collections.unmodifiableCollection(Collections.emptyList()).getClass())) {
            throw new UnsupportedOperationException();
        }

        if (input.size() == 1) {
            return input;
        }
        List<T> aPart = mergeSort(input.subList(0, input.size() / 2));
        List<T> bPart = mergeSort(input.subList(input.size() / 2, input.size()));
        return merge(aPart, bPart);
    }

    private static <T extends Comparable<T>> List<T> merge(List<T> aPart, List<T> bPart) {
        List<T> result = new ArrayList<>(aPart.size() + bPart.size());
        int posA = 0;
        int posB = 0;
        T lastResult;
        if (aPart.get(0).compareTo(bPart.get(0)) > 0) {
            lastResult = aPart.get(0);
            posA++;
        } else {
            lastResult = bPart.get(0);
            posB++;
        }
        int i = 1;
        result.set(0, lastResult);
        while (posA < aPart.size() && posB < bPart.size()) {
            T aEl = aPart.get(posA);
            T bEl = bPart.get(posB);
            if (aEl.compareTo(bEl) > 0) {
                result.set(i, aEl);
                posA++;
            } else {
                result.set(i, bEl);
                posB++;
            }
            i++;
        }
        while (posA < aPart.size() -1) {
            result.set(i, aPart.get(posA));
            i++;
            posA++;
        }
        while (posB < bPart.size() - 1) {
            result.set(i, bPart.get(posB));
            i++;
            posB++;
        }

        return result;
    }
}
