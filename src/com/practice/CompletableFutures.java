package com.practice;

import java.util.concurrent.CompletableFuture;

public class CompletableFutures {
    public static void main(String[] args) {

        CompletableFuture<Integer> future = CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(1000);
                System.out.println("Hello");
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            return 10;
        });

    }
}
