package org.example.snsprojcet.domain.user.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.snsprojcet.domain.user.dto.UserDeleteRequestDto;
import org.example.snsprojcet.domain.user.dto.UserSignUpRequestDto;
import org.example.snsprojcet.domain.user.dto.UserSignUpResponseDto;
import org.example.snsprojcet.domain.user.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    // service 접근
    private final UserService userService;

    // 회원 가입
    @PostMapping("/signup")
    public ResponseEntity<UserSignUpResponseDto> signUp (@Valid @RequestBody UserSignUpRequestDto requestDto) {
        UserSignUpResponseDto userSignUpResponseDto = userService.signUp(requestDto.getName(), requestDto.getAge(), requestDto.getNickname(), requestDto.getEmail(), requestDto.getPassword(), requestDto.getIntroduction());
        return new ResponseEntity<>(userSignUpResponseDto, HttpStatus.CREATED);
    }


    // 회원 탈퇴
    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete (@PathVariable Long id, @RequestBody UserDeleteRequestDto requestDto) {
        userService.deleteUser(id, requestDto.getPassword());
        return ResponseEntity.ok("회원 탈퇴 성공!");
    }
}
