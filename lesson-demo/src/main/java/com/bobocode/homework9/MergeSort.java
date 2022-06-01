package com.bobocode.homework9;


import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.RecursiveAction;

public class MergeSort<T extends Comparable<? super T>> extends RecursiveAction {

    private final List<T> elements;

    public MergeSort(List<T> elements) {
        verifyModifiable(elements);
        this.elements = elements;
    }

    @Override
    public void compute() {
        if (elements.size() > 1) {
            int middle = elements.size() / 2;
            var leftSubList = new ArrayList<>(this.elements.subList(0, middle));
            var rightSublist = new ArrayList<>(this.elements.subList(middle, this.elements.size()));

            var leftPart = new MergeSort<>(leftSubList);
            var rightPart = new MergeSort<>(rightSublist);

            rightPart.fork();
            leftPart.compute();
            rightPart.join();

            merge(elements, leftSubList, rightSublist);
        }
    }

    private static <T extends Comparable<? super T>> void merge(List<T> list, List<T> leftPart, List<T> rightPart) {
        int leftIndex = 0;
        int rightIndex = 0;
        int index = 0;

        while (leftIndex < leftPart.size() && rightIndex < rightPart.size()) {
            if (leftPart.get(leftIndex).compareTo(rightPart.get(rightIndex)) <= 0) {
                list.set(index++, leftPart.get(leftIndex++));
            } else {
                list.set(index++, rightPart.get(rightIndex++));
            }
        }

        if (leftIndex < leftPart.size()) {
            list.set(index, leftPart.get(leftIndex));
        } else if (rightIndex < rightPart.size()) {
            list.set(index, rightPart.get(rightIndex));
        }
    }

    private static void verifyModifiable(List<?> list) {
        Objects.requireNonNull(list);
        try {
            list.add(null);
            list.remove(list.size() - 1);
        } catch (Exception e) {
            throw new IllegalArgumentException("Argument is illegal");
        }
    }
}
