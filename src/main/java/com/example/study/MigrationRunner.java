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
        // singleThreadedSaver.save("fake_data_50k.csv");
        singleThreadedSaver.saveTransaction("fake_data_50k.csv");
        // multiThreadedSaver.save("fake_data_50k.csv", 1000);
    }
}
