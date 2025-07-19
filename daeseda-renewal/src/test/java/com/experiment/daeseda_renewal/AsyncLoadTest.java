package com.experiment.daeseda_renewal;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

@SpringBootTest
public class AsyncLoadTest {

    private static final Logger logger = LoggerFactory.getLogger(AsyncLoadTest.class);

    private static final int THREAD_COUNT = 210000; // 동시 요청 수
    private static final String TEST_URL = "http://localhost:8080/";

    private final WebClient webClient = WebClient.create();

    @Test
    void testAsyncLoad() throws InterruptedException {
        ExecutorService executor = Executors.newFixedThreadPool(THREAD_COUNT);
        CountDownLatch latch = new CountDownLatch(THREAD_COUNT); // 모든 요청이 끝날 때까지 기다리도록 설정

        for (int i = 0; i < THREAD_COUNT; i++) {
            executor.submit(() -> {
                long start = System.currentTimeMillis();

                webClient.get()
                        .uri(TEST_URL)
                        .retrieve()
                        .bodyToMono(String.class)
                        .doOnSuccess(response -> {
                            long end = System.currentTimeMillis();
                            logger.info("Response time: {} ms", (end - start));
                            latch.countDown(); // 요청 완료 후 카운트 감소
                        })
                        .doOnError(error -> {
                            logger.error("Request failed: {}", error.getMessage());
                            latch.countDown();
                        })
                        .subscribe(); // 🚀 비동기 실행!
            });
        }

        latch.await(1, TimeUnit.MINUTES); // 모든 요청이 끝날 때까지 대기
        executor.shutdown();
    }
}
