package com.example.study;

import com.example.study.domain.DummyUser;
import com.example.study.support.CsvLoader;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Component
@RequiredArgsConstructor
public class MigrationRunner implements CommandLineRunner {
    private final CsvLoader csvLoader;

    private LocalDateTime stringToLocalDateTime(String date) {
        if (date == null || date.isBlank()) {
            return null;
        }

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return LocalDateTime.parse(date, formatter);
    }

    @Override
    public void run(String... args) throws Exception {
        List<DummyUser> users = csvLoader.load("data.csv", record -> new DummyUser(
                Long.parseLong(record.get(0)),
                record.get(1),
                record.get(2),
                record.get(3),
                record.get(4),
                record.get(5),
                stringToLocalDateTime(record.get(6))
        ));

        users.forEach(dummyUser -> {
            System.out.println(dummyUser.getName());
        });
    }
}
