package com.practice;
import java.util.*;
import java.util.stream.*;


public class StreamTest {

    public static void main(String[] args) {
        // imerative style
        int[] numbers = {1, 2, 3, 4, 5};
        int sum = 0;
        for (int number : numbers) {
            if (number % 2 == 0) {
                sum += number;
            }
        }

        // declarative style
        sum = 0;
        sum = Arrays.stream(numbers)
            .filter(number -> number % 2 == 0)
            .sum();
//        System.out.println(sum);

        List<String> names = Arrays.asList("John", "Jane", "Adam", "Tom");
        Stream<String> stream = names.stream();
//        stream.forEach(System.out::print);
//        System.out.println();
//        Stream.of("John", "Jane", "Adam", "Tom")
//            .forEach(System.out::print);
//        Stream.iterate(1, n -> n + 1)
//            .limit(10)
//            .forEach(System.out::print);
        Stream<String> limit = Stream.generate(() -> "Hello")
                .limit(3);

         List<Integer> list = Arrays.asList(1, 2, 3, 4, 5);
        List<Integer> collect = list.stream()
                .map(n -> n * n).collect(Collectors.toList());
        System.out.println(collect);


    }
}
