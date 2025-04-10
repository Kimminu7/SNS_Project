package org.example.snsprojcet.domain.comment.service;

import lombok.RequiredArgsConstructor;
import org.example.snsprojcet.domain.board.entity.Board;
import org.example.snsprojcet.domain.board.repository.BoardRepository;
import org.example.snsprojcet.domain.comment.dto.CommentRequestDto;
import org.example.snsprojcet.domain.comment.dto.CommentResponseDto;
import org.example.snsprojcet.domain.user.entity.User;
import org.example.snsprojcet.domain.user.repository.UserRepository;
import org.example.snsprojcet.domain.comment.entity.Comment;
import org.example.snsprojcet.domain.comment.repository.CommentRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;
    private final BoardRepository boardRepository;
    private final UserRepository userRepository;

    // 로직 방향: 회원가입 생성 -> 로그인 (유저id 보유) -> 일정 생성 (보드 id 보유) -> 일정 조회 (보드 id 받음) -> 댓글달기(유저 id, 보드 id 확인)

    // 댓글 작성 기능
    public CommentResponseDto save(Long boardId, Long userId, String content) {

        // 보드, 유저 id 조회
        Board board = boardRepository.findByIdOrElseThrow(boardId);
        User user = userRepository.findUserByIdOrElseThrow(userId);
        // 댓글 생성
        Comment comment = new Comment(content, user, board);
        Comment saved = commentRepository.save(comment);

        return new CommentResponseDto(comment.getId(),comment.getContent());
    }

    // 특정 게시글 댓글 전체 조회
    public List<CommentResponseDto> findAll() {
        return commentRepository.findAll().stream().map(CommentResponseDto :: toDto).toList();
    }

    // 특정 게시글 특정 댓글 조회 기능
    public CommentResponseDto findById(Long id) {
        Comment findComment = commentRepository.findByIdOrElseThrow(id);
        User writer = findComment.getUser();

        return new CommentResponseDto(findComment.getId(), findComment.getContent());
    }

    // 특정 게시글 특정 댓글 수정 ( 내용만 수정, 작성자 or 게시글 작성자만 가능 )
    @Transactional
    public CommentResponseDto update(Long commentId, Long userId, String content) {
        Comment comment = commentRepository.findByIdOrElseThrow(commentId);

        // 게시글 작성자, 댓글 작성자 확인여부 기능
        validatePermission(comment, userId);

        comment.update(content);

        return new CommentResponseDto(comment.getId(), comment.getContent());
    }

    // 특정 게시글 특정 댓글 삭제(작성자 or 게시글 작성자만 가능)
    @Transactional
    public void delete(Long commentId, Long userId) {
        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new IllegalArgumentException("해당 댓글이 존재하지 않습니다."));

        // 게시글 작성자, 댓글 작성자 확인여부 기능
        validatePermission(comment, userId);

        commentRepository.delete(comment);
    }

    // 권한 체크 메서드
    private void validatePermission(Comment comment, Long userId) {
        // 로그인한 유저가 댓글 작성자인지 확인
        boolean isCommentAuthor = comment.getUser().getId().equals(userId);
        // 로그인한 유저가 게시글 작성자인지 확인
        boolean isBoardAuthor = comment.getBoard().getUser().getId().equals(userId);

        if (!isCommentAuthor && !isBoardAuthor) {
            throw new IllegalArgumentException("해당 댓글에 대한 수정/삭제 권한이 없습니다.");
        }
    }





}
