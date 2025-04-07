package org.example.snsprojcet.domain.board.controller;

import lombok.RequiredArgsConstructor;
import org.example.snsprojcet.domain.board.dto.BoardResponseDto;
import org.example.snsprojcet.domain.board.dto.CreateBoardRequestDto;
import org.example.snsprojcet.domain.board.dto.UpdateRequestDto;
import org.example.snsprojcet.domain.board.service.BoardService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/boards")
@RequiredArgsConstructor
public class BoardController {

    private final BoardService boardService;

    // 게시판 생성
    @PostMapping
    public ResponseEntity<BoardResponseDto> createBoard(@RequestBody CreateBoardRequestDto requestDto) {

        BoardResponseDto responseDto = boardService.createBoard(requestDto.getTitle(), requestDto.getNickname(), requestDto.getContents());

        return new ResponseEntity<>(responseDto, HttpStatus.CREATED);
    }

    // 게시판 전체 조회
    @GetMapping
    public ResponseEntity<List<BoardResponseDto>> findAll() {

        List<BoardResponseDto> responseDtoList = boardService.findAll();

        return new ResponseEntity<>(responseDtoList, HttpStatus.OK);
    }

    // 게시판 단건 조회
    @GetMapping("/{id}")
    public ResponseEntity<BoardResponseDto> findById(@PathVariable Long id) {

        BoardResponseDto responseDto = boardService.findById(id);

        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }

    // 게시물 수정
    @PutMapping("/{id}")
    public ResponseEntity<String> updateBoard(
            @PathVariable Long id,
            @RequestBody UpdateRequestDto requestDto
    ) {

        String responseDto = boardService.updateBoard(id, requestDto.getTitle(), requestDto.getContents());

        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }

    // 게시글 삭제
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteBoard(@PathVariable Long id) {

        String responseDto = boardService.deleteBoard(id);

        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }
}
