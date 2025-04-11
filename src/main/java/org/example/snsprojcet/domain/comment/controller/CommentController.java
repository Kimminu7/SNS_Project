package org.example.snsprojcet.domain.comment.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.snsprojcet.domain.auth.filter.LoginUser;
import org.example.snsprojcet.domain.comment.dto.CommentRequestDto;
import org.example.snsprojcet.domain.comment.dto.CommentResponseDto;
import org.example.snsprojcet.domain.comment.service.CommentService;
import org.example.snsprojcet.domain.user.entity.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/boards/{boardId}/comments")
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;

    // 댓글 생성
    @PostMapping
    public ResponseEntity<CommentResponseDto> saveComment (@PathVariable Long boardId, @LoginUser User loginUser, @Valid @RequestBody CommentRequestDto requestDto) {

        CommentResponseDto commentResponseDto = commentService.save(boardId, loginUser.getId(), requestDto.getContent());

        return new ResponseEntity<>(commentResponseDto, HttpStatus.CREATED);
    }

    // 특정 게시글 댓글 전체 조회
    @GetMapping()
    public ResponseEntity<List<CommentResponseDto>> findAll() {
        List<CommentResponseDto> commentResponseDtos = commentService.findAll();

        return new ResponseEntity<>(commentResponseDtos, HttpStatus.OK);
    }

    // 특정 게시글의 특정 댓글 조회
    @GetMapping("/{commentId}")
    public ResponseEntity<CommentResponseDto> findById (@PathVariable Long commentId){
        CommentResponseDto responseDto = commentService.findById(commentId);

        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }

    // 특정 게시글의 댓글 수정
    @PutMapping("/{commentId}")
    public ResponseEntity<String> update(@PathVariable Long commentId, @LoginUser User loginUser, @Valid @RequestBody CommentRequestDto requestDto) {
        // 수정
        String responseDto = commentService.update(commentId, loginUser.getId(), requestDto.getContent());
        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }

    // 댓글 삭제
    @DeleteMapping("/{commentId}")
    public ResponseEntity<CommentResponseDto> delete( @PathVariable Long commentId, @LoginUser User loginUser) {

        commentService.delete(commentId, loginUser.getId());
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
