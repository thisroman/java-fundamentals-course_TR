package com.bobocode.lesson10;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.RecursiveAction;

public class MergeSort<T> extends RecursiveAction {

    private final List<T> ar;

    public MergeSort(List<T> ar) {
        Objects.requireNonNull(ar);
        validateOnModify(ar);
        this.ar = ar;
    }

    @Override
    protected void compute() {
        if (ar.size() == 1){
            return;
        }

        var left = new ArrayList<T>(ar.subList(0, ar.size()/2));
        var right = new ArrayList<T>(ar.subList(ar.size()/2, ar.size()));
        var msLeft = new MergeSort<>(left);
        var msRight = new MergeSort<>(right);

        msLeft.fork();
        msRight.compute();
        msLeft.join();

        merge(left, right);
    }

    private void merge(ArrayList<T> left, ArrayList<T> right) {
        int leftIndex = 0;
        int rightIndex = 0;
        int resIndex = 0;
        while (left.size() > leftIndex && right.size() > rightIndex){

        }
    }

    private void validateOnModify(List<T> ar) {
        try{
            ar.add(ar.size(), null);
            ar.remove(ar.size() - 1);
        }catch (Exception ex){
            throw new IllegalArgumentException("Argument Illegal");
        }
    }
}
