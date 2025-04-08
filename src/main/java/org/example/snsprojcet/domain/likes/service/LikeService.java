package org.example.snsprojcet.domain.likes.service;

import lombok.RequiredArgsConstructor;
import org.example.snsprojcet.domain.board.entity.Board;
import org.example.snsprojcet.domain.board.repository.BoardRepository;
import org.example.snsprojcet.domain.user.entity.User;
import org.example.snsprojcet.domain.user.repository.UserRepository;
import org.example.snsprojcet.domain.likes.entity.Likes;
import org.example.snsprojcet.domain.likes.repository.LikeRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class LikeService {

    private final LikeRepository likeRepository;
    private final BoardRepository boardRepository;
    private final UserRepository userRepository;

    @Transactional
    public boolean toggleLike (Long boardId, Long userId) {
        // boardId로 게시글 조회
        Board board = boardRepository.findByIdOrElseThrow(boardId);
        // userId로 로그인 유저가 맞는지 확인
        User user = userRepository.findUserByIdOrElseThrow(userId);

        // 사용자가 해당 게시글에 이미 좋아요 눌렀는지 확인하기
        // Optional 사용이유 : null을 안전하게 사용할 수 있어서
        Optional<Likes> existingLike = likeRepository.findByBoardAndUser(board, user);

        if(existingLike.isPresent()) {
            // 이미 좋아요 했으면 삭제 (좋아요 취소)
            likeRepository.delete(existingLike.get());
            // 좋아요 취소 반환
            return false;
        } else {
                                           // 게시글 정보  //사용자 정보// Likes 객체 생성
            Likes newLike = Likes.builder().board(board).user(user).build();
            // DB에 저장
            likeRepository.save(newLike);
            return true;
        }
    }


}
