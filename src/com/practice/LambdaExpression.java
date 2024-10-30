package com.practice;

import java.util.concurrent.Callable;

public class LambdaExpression {
    
  
    public static void main(String[] args) {
        
        // write a lambda expression to add two numbers
        Callable<Integer> integerThrowableCallableOp = () -> {
            return 10 + 20;
        };


        // write a lambda expression to multiply two numbers
        Callable<Integer> integerThrowableCallableOp1 = () -> {
            return 10 * 20;
        };

        // write a lambda expression to find the length of a string
        Callable<Integer> integerThrowableCallableOp2 = "Hello"::length;


    }
    
    
    
    
    
}
