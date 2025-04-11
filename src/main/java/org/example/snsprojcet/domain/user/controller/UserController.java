package org.example.snsprojcet.domain.user.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.snsprojcet.domain.auth.filter.LoginUser;
import org.example.snsprojcet.domain.user.dto.*;
import org.example.snsprojcet.domain.user.entity.User;
import org.example.snsprojcet.domain.user.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    // 내 프로필 조회
    @GetMapping("/profile/{userId}")
    public ResponseEntity<UserResponseDto> findUserById(@PathVariable Long userId, @LoginUser User loginUser) {

        UserResponseDto userResponseDto = userService.findUserById(loginUser.getId());
        return new ResponseEntity<>(userResponseDto, HttpStatus.OK);
    }

    // 전체 유저 목록 조회
    @GetMapping("/List")
    public ResponseEntity<List<AllUserResponseDto>> findAllUser () {
        List<AllUserResponseDto> allUser = userService.findAllUser();
        return new ResponseEntity<>(allUser, HttpStatus.OK);
    }

    // 다른 유저 프로필 단 건 조회
    @GetMapping("/profile")
    public ResponseEntity<AnotherUserResponseDto> findUserByNickname(@RequestParam String nickname) {
        AnotherUserResponseDto anotherUserResponseDto = userService.findUserByNickname(nickname);

        return new ResponseEntity<>(anotherUserResponseDto, HttpStatus.OK);
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
    public ResponseEntity<String> delete (@PathVariable Long userId, @RequestBody UserDeleteRequestDto requestDto, HttpServletRequest servletRequest) {
        userService.deleteUser(userId, requestDto.getPassword());
        // 회원 탈퇴 시 세션 삭제
        HttpSession session = servletRequest.getSession(false);
        if (session != null) {
            session.invalidate();
        }
        return ResponseEntity.ok("회원 탈퇴 성공!");
    }
}
