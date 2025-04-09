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
        return findUserByEmail(email).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "존재하지 않는 email 입니다."));
    }

    // userId로 회원 정보 찾기
    Optional<User> findUserById (Long id);

    default User findUserByIdOrElseThrow(Long id) {
        return findUserById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "존재하지 않는 id입니다."));
    }

    boolean existsByEmail (String email);
    default User findByIdOrElseThrow(Long id) {
        return findById(id)
                .orElseThrow(() ->
                        new ResponseStatusException(
                                HttpStatus.NOT_FOUND,
                                "비밀번호를 조회할 수 없습니다 = " + id
                        )
                );
    }
}
