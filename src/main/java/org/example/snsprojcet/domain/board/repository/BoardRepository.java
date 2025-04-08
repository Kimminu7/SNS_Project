package org.example.snsprojcet.domain.board.repository;

import org.example.snsprojcet.domain.board.entity.Board;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

public interface BoardRepository extends JpaRepository<Board, Long> {

    // Optional 클래스를 이용하지 않는 예외처리 메소드
    default Board findByIdOrElseThrow(Long id) {
        return findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "존재 하지 않는 ID = " + id));
    }

    // Spring Data JPA가 지원해주는 쿼리 메소드, 생성일자 기준으로 내림차순 정렬.
    List<Board> findAllByOrderByCreatedAtDesc();
}
