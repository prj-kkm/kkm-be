package com.example.kkm.user.auth.service;

import com.example.kkm.user.auth.exception.PasswordNotMatchException;
import com.example.kkm.user.auth.exception.UserNotFoundException;
import com.example.kkm.user.auth.model.SignInForm;
import com.example.kkm.user.auth.model.SignUpForm;
import com.example.kkm.user.auth.repository.PasswordRepository;
import com.example.kkm.user.auth.repository.UserRepository;
import com.example.kkm.user.auth.security.TokenProvider;
import com.example.kkm.user.domain.entity.Password;
import com.example.kkm.user.domain.entity.User;
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
     *
     * @param signUpForm 회원가입 시 입력하는 정보
     * @return
     */
    @Transactional
    public ResponseEntity<?> signUp(SignUpForm signUpForm) {
        if (isEmailAlreadyUsed(signUpForm.getEmail())) {
            return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body("이미 가입된 이메일이 존재합니다.");
        }

        User user = createUser(signUpForm);
        createPasswordForUser(user, signUpForm.getPassword());

        return ResponseEntity.ok().build();
    }

    private boolean isEmailAlreadyUsed(String email) {
        return userRepository.existsByEmail(email);
    }

    private User createUser(SignUpForm signUpForm) {
        User user = User.builder()
            .email(signUpForm.getEmail())
            .build();
        return userRepository.save(user);
    }

    private void createPasswordForUser(User user, String rawPassword) {
        Password password = Password.builder()
            .user(user)
            .password(passwordEncoder.encode(rawPassword))
            .salt("") // 추후 salt 사용 시 로직 추가 필요
            .build();
        passwordRepository.save(password);
    }


    /**
     * 회원 정보 인증
     *
     * @param signInForm 로그인 시 입력하는 정보
     * @return 결과
     */
    public User authenticate(SignInForm signInForm) {
        User user = findUserByEmail(signInForm.getEmail());
        validatePassword(signInForm.getPassword(), user);
        return user;
    }

    private User findUserByEmail(String email) {
        return userRepository.findByEmail(email)
            .orElseThrow(() -> new UserNotFoundException("가입되지 않은 이메일입니다."));
    }

    private void validatePassword(String rawPassword, User user) {
        Password password = findPasswordByUser(user);
        if (!passwordEncoder.matches(rawPassword, password.getPassword())) {
            throw new PasswordNotMatchException("비밀번호가 일치하지 않습니다.");
        }
    }

    private Password findPasswordByUser(User user) {
        return passwordRepository.findByUser(user)
            .orElseThrow(() -> new IllegalStateException("비밀번호가 존재하지 않습니다."));
    }

    public String generateJwtToken(User user) {
        return tokenProvider.generateJwtToken(user);
    }
}
