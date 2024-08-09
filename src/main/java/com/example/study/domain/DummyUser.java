package com.example.study.domain;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Getter
@EqualsAndHashCode(of = "id")
@Entity
@Table(name = "dummy_users")
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
            Long id,
            String email,
            String name,
            String address,
            String phone,
            String gender,
            LocalDateTime createdAt
    ) {
        this.id = id;
        this.email = email;
        this.name = name;
        this.address = address;
        this.phone = phone;
        this.gender = gender;
        this.createdAt = createdAt;
    }
}
