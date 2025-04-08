package org.example.snsprojcet.domain.board.service;

import org.example.snsprojcet.domain.board.dto.BoardResponseDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


public interface BoardService {

    // 게시판 생성
    BoardResponseDto createBoard(Long userId, String title, String contents);
    // 게시판 전체 조회
    Page<BoardResponseDto> findAll(Pageable pageable);
    // 게시판 단건 조회
    BoardResponseDto findById(Long id);
    // 게시판 수정
    String updateBoard(Long id, String title, String contents);
    // 게시판 삭제
    String deleteBoard(Long id);
}
