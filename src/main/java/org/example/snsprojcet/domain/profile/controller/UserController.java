package org.example.snsprojcet.domain.profile.controller;

import lombok.RequiredArgsConstructor;
import org.example.snsprojcet.domain.profile.dto.FriendResponseDto;
import org.example.snsprojcet.domain.profile.dto.UserResponseDto;
import org.example.snsprojcet.domain.profile.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


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

}