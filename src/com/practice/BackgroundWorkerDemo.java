package com.practice;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.ReentrantLock;

public class BackgroundWorkerDemo {

    private static List<String> sharedData = new ArrayList<>();
    private static ReentrantLock lock = new ReentrantLock();
    private static volatile boolean running = true;

    public static void main(String[] args) {
        // Start the background worker thread
        Thread workerThread = new Thread(new DataSaver());
        workerThread.start();

        // Simulate main thread changes
        for (int i = 1; i <= 5; i++) {
            modifyData("Data " + i);
            try {
                Thread.sleep(1000); // Simulate delay for next modification
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        // Stop the worker thread
        running = false;
        try {
            workerThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Application has finished.");
    }

    // Method to modify data
    public static void modifyData(String newData) {
        lock.lock();
        try {
            System.out.println("Adding: " + newData);
            sharedData.add(newData);
        } finally {
            lock.unlock();
        }
    }

    // Worker class to periodically save data
    public static class DataSaver implements Runnable {
        @Override
        public void run() {
            while (running) {
                lock.lock();
                try {
                    if (!sharedData.isEmpty()) {
                        // Simulate saving data (e.g., writing to file or database)
                        System.out.println("Saving data: " + sharedData);
                        sharedData.clear();
                    }
                } finally {
                    lock.unlock();
                }

                // Wait before the next save
                try {
                    Thread.sleep(2000); // Adjust interval as needed
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
