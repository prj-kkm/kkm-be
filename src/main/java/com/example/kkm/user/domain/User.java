package com.example.kkm.user.domain;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class User {

    @Id
    Long id;
    @Column(unique = true)
    private String email;

}
