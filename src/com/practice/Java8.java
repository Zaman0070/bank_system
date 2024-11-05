package com.practice;

import java.util.List;
import java.util.function.*;
import java.util.stream.Collectors;

public class Java8 {

    // Predicate is a functional interface that takes an argument and returns a boolean
    // Function is a functional interface that takes an argument and returns a value
    // Consumer is a functional interface that takes an argument and returns nothing
    // Supplier is a functional interface that takes no argument and returns a value


    Predicate<Integer> isEven = n -> n % 2 == 0;
    Predicate<Integer> isOdd = n -> n % 2 != 0;
    Predicate<String> isShortWord = s -> s.length() <= 5;
    Predicate<String> isStartWithVip = s -> s.toLowerCase().startsWith("vip");

    // BiPredicate

    BiPredicate<Integer, Integer> isGreater = (a,b) -> a > b;

    BiPredicate<String,Integer> isLength = (s,n) -> s.length() == n;

    BiPredicate<String,String> isSame = String::equals;
    // Lambda expression

    // Predicate chaining
    // and, or, negate
    // and returns a composed predicate that represents a short-circuiting logical AND of this predicate and another


    // Function

    Function<Integer, Integer> square = n -> n * n;

    Function<Integer,String> convert = String::valueOf;

    Function<String,Integer> length = String::length;

    Function<List<String>, List<String>> sort = list -> list.stream().sorted(
            (a,b) -> b.compareTo(a)
    ).collect(Collectors.toList());

    // BiFunction

    BiFunction<String,String,Integer> lengthOfStrings = (a,b) -> a.length() + b.length();

    BiFunction<Integer,Integer,Integer> sum = Integer::sum;

    // Consumer

    Consumer<String> print = System.out::println;

    Consumer<Integer> val = System.out::println;

    // BiConsumer

    BiConsumer<Integer,Integer> add = (a,b) -> System.out.println(a+b);

    // Supplier

    Supplier<String> supplier = () -> "JAVA 8";








    public static void main(String[] args) {
        Java8 java8 = new Java8();
//        System.out.println(java8.isEven.test(4));
//        boolean vipAlex = java8.isShortWord.and(java8.isStartWithVip).test("vipAla");
//        boolean vipAlex = java8.isShortWord.or(java8.isStartWithVip).test("vipAlas");
//        System.out.println(java8.isEven.negate().test(3));

        // BiPredicate
//        System.out.println(java8.isGreater.test(5, 3));
//        System.out.println(java8.isLength.test("Hello", 5));
//        System.out.println(java8.isSame.test("Hello", "Hello"));

        // Function


//        System.out.println(java8.square.apply(5));
//        System.out.println(java8.convert.apply(5));
//        System.out.println(java8.length.apply("Hello"));
//        System.out.println(java8.sort.apply(List.of("John", "Jane", "Adam", "Tom")));

        // BiFunction
//        System.out.println(java8.lengthOfStrings.apply("Hello", "World"));
//        System.out.println(java8.sum.apply(5, 3));

        // Consumer

//        java8.print.accept("Hello");

        // Supplier

//        System.out.println(java8.supplier.get());
    }
}
