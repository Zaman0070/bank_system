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
                .map(n -> n * n).sorted((a,b)->(b-a)).collect(Collectors.toList());
        System.out.println(collect);

        List<Integer> collect1 = Stream.iterate(0, n -> n + 1)
                .limit(101).skip(1).collect(Collectors.toList());
        System.out.println(collect1);


        int[] numbers1 = {1, 2, 3, 4, 5,1,6,7,8,9,10,2};
        List<Integer> collect2 = Arrays.stream(numbers1).distinct().boxed().toList();
        System.out.println(collect2);


        List<Integer> vale = Arrays.asList(1, 2, 3, 4, 5,12,26,56,78,98,100);
        List<Integer> collect3 = vale.parallelStream().map(x->x*2).collect(Collectors.toList());
        System.out.println(collect3);


    }
}
