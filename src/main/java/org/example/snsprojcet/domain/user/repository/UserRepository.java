package org.example.snsprojcet.domain.user.repository;

import org.example.snsprojcet.domain.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;


public interface UserRepository extends JpaRepository<User, Long> {

    default User findByIdOrElseThorw(Long id) {
        return findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "존재하지 않는 ID = " + id));
    }
}