package org.example.snsprojcet.domain.user.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.snsprojcet.domain.user.dto.FriendResponseDto;
import org.example.snsprojcet.domain.user.dto.UpdatePasswordRequestDto;
import org.example.snsprojcet.domain.user.dto.UserResponseDto;
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
    // 유저 프로필 단 건 조회
    @GetMapping("/{userId}/profile")
    public ResponseEntity<UserResponseDto> findUserById(@PathVariable Long userId) {
        UserResponseDto userResponseDto = userService.findUserById(userId);

        return new ResponseEntity<>(userResponseDto, HttpStatus.OK);
    }

    // 친구 프로필 단 건 조회
    @GetMapping("/{friendId}/profile")
    public ResponseEntity<FriendResponseDto> findfriendById(@PathVariable Long userId) {
        FriendResponseDto friendResponseDto = userService.findFriendById(userId);

        return new ResponseEntity<>(friendResponseDto, HttpStatus.OK);
    }


    // 유저 프로필(비밀번호) 수정
    @PutMapping("/{userId}")
    public ResponseEntity<Void> updatePassword(
            @PathVariable Long userId,
            @RequestBody UpdatePasswordRequestDto requestDto
    ) {

        userService.updatePassword(userId, requestDto.getOldPassword(), requestDto.getNewPassword());

        return new ResponseEntity<>(HttpStatus.OK);
    }

    // 회원 탈퇴
    @DeleteMapping("/{userId}")
    public ResponseEntity<String> delete (@PathVariable Long userId, @RequestBody UserDeleteRequestDto requestDto) {
        userService.deleteUser(userId, requestDto.getPassword());
        return ResponseEntity.ok("회원 탈퇴 성공!");
    }
}
