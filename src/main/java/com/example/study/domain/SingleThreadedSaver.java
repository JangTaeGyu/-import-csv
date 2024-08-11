package com.example.study.domain;

import com.example.study.support.CsvLoader;
import com.example.study.support.TimeTracker;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class SingleThreadedSaver {
    private final CsvLoader csvLoader;
    private final DummyUserRepository dummyUserRepository;

    public void save(String file) {
        List<DummyUser> users = csvLoader.load(file, DummyUserMapper::toEntity);

        TimeTracker.track(() -> {
            users.forEach(dummyUserRepository::save);
        });
    }

    @Transactional
    public void saveTransaction(String file) {
        List<DummyUser> users = csvLoader.load(file, DummyUserMapper::toEntity);

        TimeTracker.track(() -> {
            users.forEach(dummyUserRepository::save);
        });
    }
}
