package org.example.snsprojcet.repository;

import org.example.snsprojcet.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public interface CommentRepository extends JpaRepository<Comment, Long> {

    default Comment findByIdOrElseThorw(Long id) {
        return findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "맞는 아이디가 없습니다."));
    }

}
