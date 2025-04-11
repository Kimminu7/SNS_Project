package org.example.snsprojcet.domain.board.repository;

import org.example.snsprojcet.domain.board.entity.Board;
import org.example.snsprojcet.domain.user.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Repository;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Repository
public interface BoardRepository extends JpaRepository<Board, Long> {

    // Optional 클래스를 이용하지 않는 예외처리 메소드
    default Board findByIdOrElseThrow(Long id) {
        return findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "존재 하지 않는 ID = " + id));
    }

    // 이미 정렬된 pageable 객체를 파라미터로 사용하면 findAll로 전체 데이터를 가져올때 정렬된 데이터를 얻을 수 있음.
    Page<Board> findAll(Pageable pageable);

    List<Board> findByUser(User user);
}
