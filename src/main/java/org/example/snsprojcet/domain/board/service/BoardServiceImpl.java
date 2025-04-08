package org.example.snsprojcet.domain.board.service;

import lombok.RequiredArgsConstructor;
import org.example.snsprojcet.domain.board.dto.BoardResponseDto;
import org.example.snsprojcet.domain.board.entity.Board;
import org.example.snsprojcet.domain.board.repository.BoardRepository;
import org.example.snsprojcet.domain.user.entity.User;
import org.example.snsprojcet.domain.user.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BoardServiceImpl implements BoardService{

    private final BoardRepository boardRepository;
    private final UserRepository userRepository;

    // 게시판 생성
    @Override
    public BoardResponseDto createBoard(Long userId, String title, String contents) {

        User findUser = userRepository.findByIdOrElseThorw(userId);

        Board board = new Board(title, contents, findUser);

        Board savedBoard = boardRepository.save(board);

        return new BoardResponseDto(savedBoard.getId(), savedBoard.getTitle(), savedBoard.getUser().getNickname(), savedBoard.getUser().getNickname(), savedBoard.getCreatedAt(), savedBoard.getUpdatedAt());
    }

    // 게시판 전체 조회
    @Override
    public List<BoardResponseDto> findAll() {
        return boardRepository.findAll().stream().map(BoardResponseDto::toDto).toList();
    }

    // 게시판 단건 조회
    @Override
    public BoardResponseDto findById(Long id) {

        Board findBoard = boardRepository.findByIdOrElseThrow(id);

        return new BoardResponseDto(findBoard.getId(), findBoard.getTitle(), findBoard.getUser().getNickname(), findBoard.getContents(), findBoard.getCreatedAt(), findBoard.getUpdatedAt());
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
    @Override
    public String deleteBoard(Long id) {

        Board findMember = boardRepository.findByIdOrElseThrow(id);

        boardRepository.delete(findMember);

        return "게시물이 정상적으로 삭제 되었습니다.";
    }
}
