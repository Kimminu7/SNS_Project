package org.example.snsprojcet.domain.user.repository;

import org.example.snsprojcet.domain.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Repository;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    // 유저 email로 유저 찾기
    Optional<User> findUserByEmail (String email);

    default User findUserByEmailOrElseThrow(String email) {
        return findUserByEmail(email).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Does not exits email = " + email));
    }

    // userId로 회원 정보 찾기
    Optional<User> findUserById (Long id);

    default User findUserByIdOrElseThrow(Long id) {
        return findUserById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Does not exits id = " + id));
    }
}
