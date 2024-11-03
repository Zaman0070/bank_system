package com.socialmedia;

import java.util.List;
import java.util.concurrent.locks.ReentrantLock;

public class User {
    private String username;
    private List<Post> timeline;
    private ReentrantLock lock;

    public User(String username, List<Post> timeline, ReentrantLock lock) {
        this.username = username;
        this.timeline = timeline;
        this.lock = lock;
    }

    public void post(String content) {
        Post newPost = new Post(this.username, content);
        addPost(newPost);
    }

    // Method to safely add posts to the shared timeline
    private void addPost(Post post) {
        lock.lock();
        try {
            timeline.add(post);
            System.out.println("New post added by " + post.getAuthor() + ": " + post.getContent());
        } finally {
            lock.unlock();
        }
    }
}
