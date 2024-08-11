package com.example.study.domain;

import org.springframework.data.repository.Repository;

public interface DummyUserRepository extends Repository<DummyUser, Long> {
    void save(DummyUser dummyUser);
}
