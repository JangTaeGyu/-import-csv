package com.example.study;

import com.example.study.domain.DummyUser;
import com.example.study.support.CsvLoader;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class MigrationRunner implements CommandLineRunner {
    private final CsvLoader csvLoader;

    @Override
    public void run(String... args) throws Exception {
        List<DummyUser> users = csvLoader.load("data.csv", record -> {

        });
    }
}
