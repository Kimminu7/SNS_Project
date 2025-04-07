package org.example.snsprojcet.domain.board.repository;

import org.example.snsprojcet.domain.board.entity.Board;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BoardRepository extends JpaRepository<Board, Long> {

}
