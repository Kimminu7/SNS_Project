package org.example.snsprojcet.domain.board.service;

import org.example.snsprojcet.domain.board.dto.BoardDetailReponseDto;
import org.example.snsprojcet.domain.board.dto.BoardResponseDto;
import org.example.snsprojcet.domain.user.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


public interface BoardService {

    // 게시판 생성
    BoardResponseDto createBoard(Long userId, String title, String contents);
    // 게시판 전체 조회
    Page<BoardResponseDto> findAll(Pageable pageable);
    // 게시판 상세 조회
    BoardDetailReponseDto findById(Long id);
    // 게시판 수정
    String updateBoard(User loginUser, Long id, String title, String contents);
    // 게시판 삭제
    String deleteBoard(User loginUser, Long id);
}
