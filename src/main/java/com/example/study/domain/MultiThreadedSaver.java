package com.example.study.domain;

import com.example.study.support.CsvLoader;
import com.example.study.support.TimeTracker;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

@Slf4j
@Component
@RequiredArgsConstructor
public class MultiThreadedSaver {
    private final CsvLoader csvLoader;
    private final DummyUserRepository dummyUserRepository;

    private void processUsersInBatches(List<DummyUser> users, int batchSize) {
        ExecutorService executorService = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());

        int batchCount = (int) Math.ceil((double) users.size() / batchSize);

        for (int i = 0; i < batchCount; i++) {
            int start = i * batchSize;
            int end = Math.min(start + batchSize, users.size());
            List<DummyUser> batch = users.subList(start, end);

            executorService.submit(() -> processBatch(batch));
        }

        executorService.shutdown();

        try {
            boolean terminated = executorService.awaitTermination(Long.MAX_VALUE, TimeUnit.NANOSECONDS);
            if (!terminated) {
                System.out.println("Time out waiting for users to finish");
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    private void processBatch(List<DummyUser> users) {
        log.info("processBatch");
        users.forEach(dummyUserRepository::save);
    }

    public void save(String file, int batchSize) {
        List<DummyUser> users = csvLoader.load(file, DummyUserMapper::toEntity);

        TimeTracker.track(() -> {
            processUsersInBatches(users, batchSize);
        });
    }

    @Transactional
    public void saveTransaction(String file, int batchSize) {

    }
}
