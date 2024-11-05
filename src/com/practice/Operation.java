package com.practice;

import java.util.function.BinaryOperator;
import java.util.function.UnaryOperator;

public class Operation {

    // unary operation
    // binary operation

    // unary operator : if the operation is performed on a single operand and the result is also a single operand
    // (means:: operation type and result time are same)

    // binary operator : if the operation is performed on two operands and the result is also a single operand

    UnaryOperator<Integer> square = x-> x * x;
    UnaryOperator<String> upperCase = String::toUpperCase;


    BinaryOperator<String> concat = (a,b) -> a + b;

    BinaryOperator<Integer> sum = Integer::sum;


    public static void main(String[] args) {

        Operation op = new Operation();
        System.out.println(op.square.apply(2));
        System.out.println(op.upperCase.apply("hello"));

        System.out.println(op.concat.apply("hello", "world"));
        System.out.println(op.sum.apply(2,3));


    }

}
