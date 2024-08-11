package com.example.study.domain;

import com.example.study.support.CsvLoader;
import com.example.study.support.TimeTracker;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class SingleThreadedSaver implements Saver {
    private final CsvLoader csvLoader;
    private final DummyUserRepository dummyUserRepository;

    @Override
    public void save(String file) {
        List<DummyUser> users = csvLoader.load("data.csv", DummyUserMapper::toEntity);

        TimeTracker.track(() -> {
            users.forEach(dummyUserRepository::save);
        });
    }

    @Transactional
    @Override
    public void saveTransaction(String file) {
        List<DummyUser> users = csvLoader.load("data.csv", DummyUserMapper::toEntity);

        TimeTracker.track(() -> {
            users.forEach(dummyUserRepository::save);
        });
    }
}
