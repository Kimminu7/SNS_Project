package org.example.snsprojcet.domain.user.controller;

import lombok.RequiredArgsConstructor;
import org.example.snsprojcet.domain.user.dto.UserSignUpRequestDto;
import org.example.snsprojcet.domain.user.dto.UserSignUpResponseDto;
import org.example.snsprojcet.domain.user.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {
    // service 접근
    private final UserService userService;

    // 회원 가입
    @PostMapping("/signup")
    public ResponseEntity<UserSignUpResponseDto> signUp (@RequestBody UserSignUpRequestDto requestDto) {
        UserSignUpResponseDto userSignUpResponseDto = userService.signUp(requestDto.getName(), requestDto.getAge(), requestDto.getNickname(), requestDto.getEmail(), requestDto.getPassword(), requestDto.getIntroduction());
        return new ResponseEntity<>(userSignUpResponseDto, HttpStatus.CREATED);
    }
    // 조회
    // 수정
    // 회원 탈퇴
}
