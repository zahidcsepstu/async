package com.example.async.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

@Service
@Slf4j
public class AsyncService {

    @Async
    public CompletableFuture<String> asyncMethod(String s)  {
        log.info("Thread name {}", Thread.currentThread().getName());
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        log.info("Completed {}", Thread.currentThread().getName());

        return CompletableFuture.completedFuture(s);
    }


    public CompletableFuture<Void> syncMethod() throws InterruptedException {
        log.info("Thread name {}", Thread.currentThread().getName());
        Thread.sleep(5000);
        log.info("Completed {}", Thread.currentThread().getName());

        return CompletableFuture.completedFuture(null);
    }
}
