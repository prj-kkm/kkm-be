package com.example.kkm.user.auth.controller;

import com.example.kkm.user.auth.model.SignInForm;
import com.example.kkm.user.auth.model.SignUpForm;
import com.example.kkm.user.auth.service.UserAuthService;
import com.example.kkm.user.domain.entity.User;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class UserAuthController {

    private final UserAuthService userAuthService;

    /**
     * 회원가입을 위한 API
     *
     * @param signUpForm 회원가입 시 입력하는 내용
     * @return ResponseEntity<?> 회원가입 처리 결과
     */
    @PostMapping("/api/signup")
    public ResponseEntity<?> signUp(@RequestBody @Valid SignUpForm signUpForm) {

        return userAuthService.signUp(signUpForm);
    }

    /**
     * 로그인을 위한 API
     *
     * @param signInForm 로그인 시 입력하는 내용
     * @return ResponseEntity<?> 로그인 처리 결과
     */
    @PostMapping("/api/login")
    public ResponseEntity<?> signIn(@RequestBody @Valid SignInForm signInForm) {
        User user = userAuthService.authenticate(signInForm);
        String token = userAuthService.generateJwtToken(user);

        return ResponseEntity.ok(token);
    }
}
