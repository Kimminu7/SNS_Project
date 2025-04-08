package org.example.snsprojcet.domain.auth.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.example.snsprojcet.domain.auth.dto.LoginRequestDto;
import org.example.snsprojcet.domain.auth.dto.LoginResponseDto;
import org.example.snsprojcet.domain.auth.service.AuthService;
import org.example.snsprojcet.domain.user.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/authors")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    // 로그인
    @PostMapping("/login")
    public ResponseEntity<LoginResponseDto> login (@RequestBody LoginRequestDto requestDto, HttpServletRequest servletRequest) {
        LoginResponseDto login = authService.login(requestDto.getEmail(), requestDto.getPassword());
        // userID 변수에 저장
        Long id = login.getId();
        // null 값일 시 예외처리
        if (id == null) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
        // 로그인 성공 로직
        // 세션이 존재하면 기존 session 반환, 세션이 없으면 새로운 session 생성
        HttpSession session = servletRequest.getSession();
        // session에 로그인 회원 정보 저장
        session.setAttribute("userId", login);
        return new ResponseEntity<>(login, HttpStatus.OK);
    }

    // 로그아웃
    @PostMapping("/logout")
    public ResponseEntity<String> logout (HttpServletRequest servletRequest) {
        // 로그인이 아닐 시 HttpSession이 null 값 반환
        HttpSession session = servletRequest.getSession(false);
        // 세션이 있으면 로그인 된 경우
        if (session != null) {
            session.invalidate(); // 세션 삭제
        }
        return ResponseEntity.ok("로그아웃에 성공!");
    }
}
