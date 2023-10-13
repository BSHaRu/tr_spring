package com.example.security1.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@Entity
@Table(name = "User2")
public class User2Entity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int uno;
    private String uid;
    private String pass;
    private String email;
    private String role;

    private String provider;
    private String providerId;
    @CreationTimestamp
    private LocalDateTime regDate;
}
