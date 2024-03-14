package com.example.kkm.user.auth.repository;

import com.example.kkm.user.domain.entity.Password;
import com.example.kkm.user.domain.entity.User;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PasswordRepository extends JpaRepository<Password, Long> {
    Optional<Password> findByUser(User user);

}
