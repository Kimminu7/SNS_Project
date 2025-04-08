package org.example.snsprojcet.domain.profile.repository;

import org.example.snsprojcet.domain.profile.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public interface UserRepository extends JpaRepository<User, Long> {

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