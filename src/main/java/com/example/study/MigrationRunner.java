package com.example.study;

import com.example.study.domain.MultiThreadedSaver;
import com.example.study.domain.SingleThreadedSaver;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class MigrationRunner implements CommandLineRunner {
    private final SingleThreadedSaver singleThreadedSaver;
    private final MultiThreadedSaver multiThreadedSaver;

    @Override
    public void run(String... args) throws Exception {
        // singleThreadedSaver.save("data.csv");
        // singleThreadedSaver.saveTransaction("data.csv");
        multiThreadedSaver.save("data.csv", 1000);
    }
}
