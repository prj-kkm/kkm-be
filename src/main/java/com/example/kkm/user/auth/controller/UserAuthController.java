package com.example.kkm.user.auth.controller;

import com.example.kkm.user.auth.entity.User;
import com.example.kkm.user.auth.exception.PasswordNotMatchException;
import com.example.kkm.user.auth.exception.UserNotFoundException;
import com.example.kkm.user.auth.model.SignInform;
import com.example.kkm.user.auth.model.SignUpForm;
import com.example.kkm.user.auth.model.UserAuthResponseError;
import com.example.kkm.user.auth.security.TokenProvider;
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

    /**
     * 회원가입을 위한 API
     * @param signUpForm 회원가입 시 입력하는 내용
     * @param errors
     * @return
     */
    @PostMapping("/api/signup")
    public ResponseEntity<?> signUp(@RequestBody @Valid SignUpForm signUpForm, Errors errors) {

        if (errors.hasErrors()) {
            return sendUserAuthResponseErrors(errors);
        }

        return userAuthService.signUp(signUpForm);
    }

    /**
     * 로그인을 위한 API
     * @param signInform 로그인 시 입력하는 내용
     * @param errors
     * @return
     */
    @PostMapping("/api/login")
    public ResponseEntity<?> signIn(@RequestBody @Valid SignInform signInform, Errors errors) {

        if (errors.hasErrors()) {
            return sendUserAuthResponseErrors(errors);
        }

        try {
            User user = userAuthService.authenticate(signInform);   // 유저 인증
            String token = userAuthService.generateJwtToken(user);  // 토큰 발행
            return ResponseEntity.ok().body(token);

        } catch (UserNotFoundException userNotFoundException) {
            return new ResponseEntity<>(userNotFoundException.getMessage(), HttpStatus.NOT_FOUND);

        } catch (PasswordNotMatchException passwordNotMatchException) {
            return new ResponseEntity<>(passwordNotMatchException.getMessage(), HttpStatus.BAD_REQUEST);

        } catch (Exception exception) {
            return new ResponseEntity<>(exception.getMessage(), HttpStatus.BAD_REQUEST);

        }
    }

    private ResponseEntity<?> sendUserAuthResponseErrors(Errors errors) {
        var userAuthResponseErrors = new ArrayList<UserAuthResponseError>();

        errors.getAllErrors().forEach(error -> {
            userAuthResponseErrors.add(UserAuthResponseError.of((FieldError) error));
        });

        return new ResponseEntity<>(userAuthResponseErrors, HttpStatus.BAD_REQUEST);
    }
}
