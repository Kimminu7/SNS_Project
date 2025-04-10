package org.example.snsprojcet.domain.comment.repository;

import org.example.snsprojcet.domain.comment.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {

    default Comment findByIdOrElseThrow(Long id) {
        return findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "맞는 아이디가 없습니다."));
    }

    // 댓글 생성일자 기준 내림차순 정렬
    List<Comment> findByBoardIdOrderByCreatedAtDesc(Long boardId);

}
