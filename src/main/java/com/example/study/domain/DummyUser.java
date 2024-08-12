package com.example.study.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Getter @NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity @Table(name = "dummy_users")
public class DummyUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String email;

    private String name;

    private String address;

    private String phone;

    private String gender;

    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;

    public DummyUser(
            String email,
            String name,
            String address,
            String phone,
            String gender,
            LocalDateTime createdAt
    ) {
        this.email = email;
        this.name = name;
        this.address = address;
        this.phone = phone;
        this.gender = gender;
        this.createdAt = createdAt;
    }
}
