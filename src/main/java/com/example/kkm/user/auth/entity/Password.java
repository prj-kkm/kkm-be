package com.example.kkm.user.auth.entity;

import com.example.kkm.user.auth.model.SignUpForm;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "passwords")
public class Password {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int password_id;

    @Column
    private int user_id;

    @Column
    private String password;

    @Column
    private String salt;

    public Password(User user, SignUpForm signUpForm, String salt) {
        this.user_id = user.getUser_id();
        this.password = signUpForm.getPassword();
        this.salt = salt;
    }

}
