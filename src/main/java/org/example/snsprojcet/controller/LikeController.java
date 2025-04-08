package org.example.snsprojcet.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.example.snsprojcet.service.LikeService;
import org.springframework.context.annotation.Bean;
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
    public ResponseEntity<Boolean> likes(@PathVariable Long boardId, HttpServletRequest request){
        // 로그인 id 받아오기
        HttpSession session = request.getSession();
        Long userId = (Long) session.getAttribute("userId");

        // 로그인 확인
        if (userId == null) {
            return new ResponseEntity<>(false, HttpStatus.UNAUTHORIZED);
        }

        // 좋아요 눌렀는지 유무 확인
        Boolean liked = likeService.toggleLike(boardId, userId);

        return new ResponseEntity<>(liked, HttpStatus.OK);
    }


}
