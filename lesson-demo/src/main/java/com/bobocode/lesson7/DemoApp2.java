package com.bobocode.lesson7;

import java.util.function.DoubleBinaryOperator;

public class DemoApp2 {

    public static void main(String[] args) {
        //DoubleBinaryOperator
        double a = 2.;
        double b = 2.;

        DoubleBinaryOperator operator = (x , y) -> (x * y);
        DoubleBinaryOperator operatorSum = Double::sum;
        System.out.println(operator.applyAsDouble(a, b));
        System.out.println(operatorSum.applyAsDouble(a, b));
    }
}
