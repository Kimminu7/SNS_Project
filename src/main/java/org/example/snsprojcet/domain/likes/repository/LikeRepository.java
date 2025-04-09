package org.example.snsprojcet.domain.likes.repository;

import org.example.snsprojcet.domain.board.entity.Board;
import org.example.snsprojcet.domain.likes.entity.Likes;
import org.example.snsprojcet.domain.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LikeRepository extends JpaRepository<Likes, Integer> {

    Optional<Likes> findByBoardAndUser(Board board, User user);

    long countByBoardId(Long boardId);
}
