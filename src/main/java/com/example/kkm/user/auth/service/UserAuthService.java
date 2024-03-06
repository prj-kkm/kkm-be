package com.example.kkm.user.auth.service;

import com.example.kkm.user.auth.entity.Password;
import com.example.kkm.user.auth.entity.User;
import com.example.kkm.user.auth.exception.ExistsEmailException;
import com.example.kkm.user.auth.model.SignUpForm;
import com.example.kkm.user.auth.repository.PasswordRepository;
import com.example.kkm.user.auth.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserAuthService {

    private final UserRepository userRepository;
    private final PasswordRepository passwordRepository;

    /**
     * 회원가입
     * @param signUpForm
     * @return
     */
    public ResponseEntity<?> signUp(SignUpForm signUpForm) {
        // 이메일 중복 확인
        boolean isEmailUsed = userRepository.existsByEmail(signUpForm.getEmail());
        if (isEmailUsed) {
            return new ResponseEntity<>("이미 가입된 이메일이 존재합니다.", HttpStatus.BAD_REQUEST);
        }

        User user = userRepository.save(new User(signUpForm));
        String encryptedPassword = new BCryptPasswordEncoder().encode(signUpForm.getPassword());

        passwordRepository.save(Password.builder()
                                        .user_id(user.getUser_id())
                                        .password(encryptedPassword)
                                        .salt("")
                                        .build());

        return ResponseEntity.ok().build();
    }
}
