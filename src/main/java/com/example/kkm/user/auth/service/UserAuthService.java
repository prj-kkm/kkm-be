package com.example.kkm.user.auth.service;

import com.example.kkm.user.auth.entity.Password;
import com.example.kkm.user.auth.entity.User;
import com.example.kkm.user.auth.exception.PasswordNotMatchException;
import com.example.kkm.user.auth.exception.UserNotFoundException;
import com.example.kkm.user.auth.model.SignInform;
import com.example.kkm.user.auth.model.SignUpForm;
import com.example.kkm.user.auth.repository.PasswordRepository;
import com.example.kkm.user.auth.repository.UserRepository;
import com.example.kkm.user.auth.security.TokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserAuthService {

    private final PasswordEncoder passwordEncoder;
    private final TokenProvider tokenProvider;

    private final UserRepository userRepository;
    private final PasswordRepository passwordRepository;

    /**
     * 회원가입
     * @param signUpForm 회원가입 시 입력하는 정보
     * @return
     */
    @Transactional
    public ResponseEntity<?> signUp(SignUpForm signUpForm) {
        // 이메일 중복 확인
        boolean isEmailUsed = userRepository.existsByEmail(signUpForm.getEmail());
        if (isEmailUsed) {
            return new ResponseEntity<>("이미 가입된 이메일이 존재합니다.", HttpStatus.BAD_REQUEST);
        }

        User user = User.builder()
                        .email(signUpForm.getEmail())
                        .build();

        userRepository.save(user);

        Password password = Password.builder()
                                    .user(user)
                                    .password(passwordEncoder.encode(signUpForm.getPassword()))
                                    .salt("")
                                    .build();

        passwordRepository.save(password);

        return ResponseEntity.ok().build();
    }

    /**
     * 회원 정보 인증
     * @param signInform 로그인 시 입력하는 정보
     * @return 결과
     */
    public User authenticate(SignInform signInform) {

        User user = userRepository.findByEmail(signInform.getEmail())
            .orElseThrow(() -> new UserNotFoundException("가입되지 않은 이메일입니다."));

        Password password = passwordRepository.findByUser(user);

        if (!passwordEncoder.matches(signInform.getPassword(), password.getPassword())) {
            throw new PasswordNotMatchException("비밀번호가 일치하지 않습니다.");
        }

        return user;
    }

    public String generateJwtToken(User user) {
        return tokenProvider.generateJwtToken(user);
    }
}
