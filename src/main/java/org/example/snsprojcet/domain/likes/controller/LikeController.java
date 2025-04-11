package org.example.snsprojcet.domain.likes.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.example.snsprojcet.domain.auth.filter.LoginUser;
import org.example.snsprojcet.domain.likes.service.LikeService;
import org.example.snsprojcet.domain.user.entity.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class LikeController {

    private final LikeService likeService;

    // 게시글 좋아요 토글
    @PostMapping("/api/boards/{boardId}/likes")
    public ResponseEntity<Boolean> likes(@PathVariable Long boardId, @LoginUser User loginUser){

        // 좋아요 눌렀는지 유무 확인
        Boolean liked = likeService.toggleLike(boardId, loginUser.getId());

        return new ResponseEntity<>(liked, HttpStatus.OK);
    }


}
