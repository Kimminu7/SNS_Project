package org.example.snsprojcet.domain.board.controller;

import lombok.RequiredArgsConstructor;
import org.example.snsprojcet.domain.auth.filter.LoginUser;
import org.example.snsprojcet.domain.board.dto.BoardDetailReponseDto;
import org.example.snsprojcet.domain.board.dto.BoardResponseDto;
import org.example.snsprojcet.domain.board.dto.CreateBoardRequestDto;
import org.example.snsprojcet.domain.board.dto.UpdateRequestDto;
import org.example.snsprojcet.domain.board.service.BoardService;
import org.example.snsprojcet.domain.user.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/boards")
@RequiredArgsConstructor
public class BoardController {

    private final BoardService boardService;

    // 게시판 생성
    @PostMapping
    public ResponseEntity<BoardResponseDto> createBoard(
            @LoginUser User loginUser,
            @RequestBody CreateBoardRequestDto requestDto
    ) {

        BoardResponseDto responseDto = boardService.createBoard(loginUser.getId(), requestDto.getTitle(), requestDto.getContents());

        return new ResponseEntity<>(responseDto, HttpStatus.CREATED);
    }

    // 게시판 전체 조회
    @GetMapping
    public ResponseEntity<Page<BoardResponseDto>> findAll(
            @PageableDefault(direction = Sort.Direction.DESC, sort = "createdAt") Pageable pageable
    ) {

        Page<BoardResponseDto> responseDtoList = boardService.findAll(pageable);

        return new ResponseEntity<>(responseDtoList, HttpStatus.OK);
    }

    // 게시판 상세 조회
    @GetMapping("/{id}")
    public ResponseEntity<BoardDetailReponseDto> findById(@PathVariable Long id) {

        BoardDetailReponseDto responseDto = boardService.findById(id);

        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }

    // 게시물 수정
    @PutMapping("/{id}")
    public ResponseEntity<String> updateBoard(
            @PathVariable Long id,
            @LoginUser User loginUser,
            @RequestBody UpdateRequestDto requestDto
    ) {

        String responseDto = boardService.updateBoard(loginUser, id, requestDto.getTitle(), requestDto.getContents());

        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }

    // 게시글 삭제
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteBoard(@PathVariable Long id, @LoginUser User loginUser) {

        String responseDto = boardService.deleteBoard(loginUser, id);

        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }
}
