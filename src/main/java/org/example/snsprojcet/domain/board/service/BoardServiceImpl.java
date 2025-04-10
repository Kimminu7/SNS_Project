package org.example.snsprojcet.domain.board.service;

import lombok.RequiredArgsConstructor;
import org.example.snsprojcet.domain.board.dto.BoardDetailReponseDto;
import org.example.snsprojcet.domain.board.dto.BoardResponseDto;
import org.example.snsprojcet.domain.board.entity.Board;
import org.example.snsprojcet.domain.board.repository.BoardRepository;
import org.example.snsprojcet.domain.comment.dto.CommentResponseDto;
import org.example.snsprojcet.domain.comment.entity.Comment;
import org.example.snsprojcet.domain.comment.repository.CommentRepository;
import org.example.snsprojcet.domain.likes.repository.LikeRepository;
import org.example.snsprojcet.domain.user.entity.User;
import org.example.snsprojcet.domain.user.repository.UserRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BoardServiceImpl implements BoardService{

    private final BoardRepository boardRepository;
    // 가입된 user를 조회하기 위함. ( 회원인 유저만 게시판 기능 이용 가능 )
    private final UserRepository userRepository;
    private final LikeRepository likeRepository;
    private final CommentRepository commentRepository;

    // 게시판 생성
    @Override
    public BoardResponseDto createBoard(Long userId, String title, String contents) {

        User findUser = userRepository.findUserByIdOrElseThrow(userId);

        Board board = new Board(title, contents, findUser);

        Board savedBoard = boardRepository.save(board);

        return new BoardResponseDto(savedBoard.getId(), savedBoard.getTitle(), savedBoard.getUser().getNickname(), savedBoard.getContents(), savedBoard.getCreatedAt(), savedBoard.getUpdatedAt());
    }

    // 게시판 전체 조회
    @Override
    public Page<BoardResponseDto> findAll(Pageable pageable) {
        Page<Board> boards = boardRepository.findAll(pageable);

        return boards.map(
                board -> {
                    Long boardId = board.getId();
                    // 해당 게시글(boardId)의 좋아요 수를 조회
                    long likeCount = likeRepository.countByBoardId(boardId);
                    return BoardResponseDto.from(board, likeCount); // 댓글 포함 x
                }
        );
    }

    // 게시판 상세 조회
    @Override
    public BoardDetailReponseDto findById(Long id) {

        Board findBoard = boardRepository.findByIdOrElseThrow(id);

        long likeCount = likeRepository.countByBoardId(id);

        // 댓글 조회 (생성일 순서)
        List<Comment> comments = commentRepository.findByBoardIdOrderByCreatedAtDesc(id);

        List<CommentResponseDto> commentsList = comments.stream().map(CommentResponseDto::toDto).toList();

        return BoardDetailReponseDto.from(findBoard, likeCount, commentsList);
    }

    // 게시판 수정
    @Override
    public String updateBoard(Long id, String title, String contents) {

        Board findBoard = boardRepository.findByIdOrElseThrow(id);

        findBoard.update(title, contents);

        Board savedBoard = boardRepository.save(findBoard);

        return "게시물이 정상적으로 수정 되었습니다.";
    }

    // 게시판 삭제
    @Transactional
    @Override
    public String deleteBoard(Long id) {

        Board findMember = boardRepository.findByIdOrElseThrow(id);

        // 댓글을 먼저 삭제
        commentRepository.deleteByBoardId(id);

        // 다음 게시판을 완전히 삭제
        boardRepository.delete(findMember);

        return "게시물이 정상적으로 삭제 되었습니다.";
    }
}
