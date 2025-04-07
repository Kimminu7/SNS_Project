package org.example.snsprojcet.domain.board.service;

import org.example.snsprojcet.domain.board.dto.BoardResponseDto;

import java.util.List;

public interface BoardService {

    // 일정 생성
    BoardResponseDto createBoard(String title, String nickName, String contents);

    List<BoardResponseDto> findAll();

    BoardResponseDto findById(Long id);

    String updateBoard(Long id, String title, String contents);

    String deleteBoard(Long id);
}
