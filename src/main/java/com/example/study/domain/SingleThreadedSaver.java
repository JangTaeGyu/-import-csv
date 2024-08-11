package com.example.study.domain;

import com.example.study.support.CsvLoader;
import com.example.study.support.TimeTracker;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Component
@RequiredArgsConstructor
public class SingleThreadedSaver {
    private final CsvLoader csvLoader;
    private final DummyUserRepository dummyUserRepository;

    private LocalDateTime stringToLocalDateTime(String date) {
        if (date == null || date.isBlank()) {
            return null;
        }

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return LocalDateTime.parse(date, formatter);
    }


    @Transactional
    public void save(String file) {
        List<DummyUser> users = csvLoader.load("data.csv", record -> new DummyUser(
                Long.parseLong(record.get(0)),
                record.get(1),
                record.get(2),
                record.get(3),
                record.get(4),
                record.get(5),
                stringToLocalDateTime(record.get(6))
        ));


        TimeTracker.track(() -> {
            users.forEach(dummyUserRepository::save);
        });
    }
}
