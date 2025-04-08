package org.example.snsprojcet.domain.profile.controller;

import lombok.RequiredArgsConstructor;
import org.example.snsprojcet.domain.profile.dto.FriendResponseDto;
import org.example.snsprojcet.domain.profile.dto.UpdatePasswordRequestDto;
import org.example.snsprojcet.domain.profile.dto.UserResponseDto;
import org.example.snsprojcet.domain.profile.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    // 유저 프로필 단 건 조회
    @GetMapping("/{id}")
    public ResponseEntity<UserResponseDto> findUserById(@PathVariable Long id) {
        UserResponseDto userResponseDto = userService.findUserById(id);

        return new ResponseEntity<>(userResponseDto, HttpStatus.OK);
    }

    // 친구 프로필 단 건 조회
    @GetMapping("/{id}")
    public ResponseEntity<FriendResponseDto> findfriendById(@PathVariable Long id) {
        FriendResponseDto friendResponseDto = userService.findFriendById(id);

        return new ResponseEntity<>(friendResponseDto, HttpStatus.OK);
    }

    // 유저 프로필(비밀번호) 수정
    @PutMapping("/{id}")
    public ResponseEntity<Void> updatePassword(
            @PathVariable Long id,
            @RequestBody UpdatePasswordRequestDto requestDto
    ) {

        userService.updatePassword(id, requestDto.getOldPassword(), requestDto.getNewPassword());

        return new ResponseEntity<>(HttpStatus.OK);
    }

}