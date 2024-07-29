package com.example.async.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

@RestController
@Slf4j
@RequiredArgsConstructor
public class TestController {

    private final AsyncService asyncService;

    @GetMapping("/test")
    public String testCtrl() throws InterruptedException {
        List<String> strings = new ArrayList<>();
        strings.add("t1");
        strings.add("t2");
        strings.add("t3");
        strings.add("t4");

        CompletableFuture
                .allOf(strings
                        .stream()
                        .map(email ->
                                asyncService.asyncMethod(email)
                                        .thenAccept(s -> {
                                            log.info(s);
                                        }))
                        .toArray(CompletableFuture[]::new)
                )
                .join();


        asyncService.asyncMethod("email")
                .thenAccept(s -> {
                    log.info(s);
                });

//        CompletableFuture<Void> allThread = CompletableFuture.allOf(void1,void2,void3);
//        allThread.join();

        return "success";
    }

}
