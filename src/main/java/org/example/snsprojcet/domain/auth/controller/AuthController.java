package org.example.snsprojcet.domain.auth.controller;

import lombok.RequiredArgsConstructor;
import org.example.snsprojcet.domain.auth.dto.LoginRequestDto;
import org.example.snsprojcet.domain.auth.service.AuthService;
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
    public ResponseEntity<Void> login (@RequestBody LoginRequestDto requestDto) {
        authService.login(requestDto.getEmail(), requestDto.getPassword());
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
