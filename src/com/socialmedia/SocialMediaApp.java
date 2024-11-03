package com.socialmedia;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.ReentrantLock;

public class SocialMediaApp {

    private static final List<Post> timeline = new ArrayList<>();
    private static final ReentrantLock lock = new ReentrantLock();
    private static volatile boolean running = true;

    public static void main(String[] args) {
        // Start the background worker thread
        Thread workerThread = new Thread(new BackgroundWorker(timeline, lock));
        workerThread.start();

        // Create users and simulate posting
        User user1 = new User("Alice", timeline, lock);
        User user2 = new User("Bob", timeline, lock);

        user1.post("Hello, world!");
        user2.post("Good morning!");
        user1.post("Beautiful day for coding.");

        // Simulate main thread running
        try {
            Thread.sleep(5000); // Main app continues running
        } catch (InterruptedException e) {
            e.printStackTrace();
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

    // Method to safely stop the background worker
    public static boolean isRunning() {
        return running;
    }

    public static void stop() {
        running = false;
    }
}
