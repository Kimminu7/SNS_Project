package org.example.snsprojcet.domain.auth.repository;

import org.example.snsprojcet.domain.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

public interface AuthRepository extends JpaRepository<User, Long> {
    // 유저 email로 유저 찾기
    Optional<User> findUserByEmail (String email);

    default User findUserByEmailOrElseThrow(String email) {
        return findUserByEmail(email).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Does not exits email = " + email));
    }
}
