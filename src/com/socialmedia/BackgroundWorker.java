package com.socialmedia;

import java.util.List;
import java.util.concurrent.locks.ReentrantLock;

public class BackgroundWorker implements Runnable {
    private final List<Post> timeline;
    private final ReentrantLock lock;

    public BackgroundWorker(List<Post> timeline, ReentrantLock lock) {
        this.timeline = timeline;
        this.lock = lock;
    }

    @Override
    public void run() {
        while (SocialMediaApp.isRunning()) {
            lock.lock();
            try {
                if (!timeline.isEmpty()) {
                    System.out.println("Saving timeline...");
                    for (Post post : timeline) {
                        System.out.println("Saved post by " + post.getAuthor() + ": " + post.getContent());
                    }
                    timeline.clear(); // Clear after saving
                }
            } finally {
                lock.unlock();
            }

            // Wait before the next save
            try {
                Thread.sleep(3000); // Save interval
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
