package com.example.kkm.user.auth.controller;

import com.example.kkm.user.auth.model.SignUpForm;
import com.example.kkm.user.auth.model.UserAuthResponseError;
import com.example.kkm.user.auth.service.UserAuthService;
import jakarta.validation.Valid;
import java.util.ArrayList;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class UserAuthController {

    private final UserAuthService userAuthService;

    @PostMapping("/api/signup")
    public ResponseEntity<?> signUp(@RequestBody @Valid SignUpForm signUpForm, Errors errors) {

        // 예외 처리
        if (errors.hasErrors()) {
            var userAuthResponseErrors = new ArrayList<UserAuthResponseError>();

            errors.getAllErrors().forEach(error -> {
                userAuthResponseErrors.add(UserAuthResponseError.of((FieldError) error));
            });

            return new ResponseEntity<>(userAuthResponseErrors, HttpStatus.BAD_REQUEST);
        }

        return userAuthService.signUp(signUpForm);
    }
}
