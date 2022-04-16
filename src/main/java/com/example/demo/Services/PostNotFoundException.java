package com.example.demo.Services;

public class PostNotFoundException extends Throwable {
    public PostNotFoundException(String message) {
        super(message);
    }
}
