package com.experiment.daeseda_renewal;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.reactive.function.client.ClientResponse;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

@SpringBootTest
public class LoadTest {

    private static final Logger logger = LoggerFactory.getLogger(LoadTest.class);

    private static final int THREAD_COUNT = 1000;
    private static final String TEST_URL = "http://localhost:8080/";

    private final WebClient webClient = WebClient.create();

    @Test
    void testLoad() throws InterruptedException {
        ExecutorService executor = Executors.newFixedThreadPool(THREAD_COUNT);

        for (int i = 0; i < THREAD_COUNT; i++) {
            executor.submit(() -> {
                long start = System.currentTimeMillis();

                try {
                    String response = webClient.get()
                            .uri(TEST_URL)
                            .retrieve()
                            .onStatus(status -> status.isError(), ClientResponse::createException) // 오류 로그 확인
                            .bodyToMono(String.class)
                            .block();

                    long end = System.currentTimeMillis();
                    logger.info("Response time: {} ms | Response: {}", (end - start), response);
                } catch (Exception e) {
                    logger.error("Request failed: {}", e.getMessage());
                }
            });
        }

        executor.shutdown();
        executor.awaitTermination(1, TimeUnit.MINUTES);
    }
}
