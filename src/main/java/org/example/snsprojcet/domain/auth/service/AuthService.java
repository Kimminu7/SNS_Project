package org.example.snsprojcet.domain.auth.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.example.snsprojcet.common.config.PasswordEncoder;
import org.example.snsprojcet.domain.auth.repository.AuthRepository;
import org.example.snsprojcet.domain.user.entity.User;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final AuthRepository authRepository;
    private final PasswordEncoder passwordEncoder;

    // 로그인
    @Transactional
    public void login(String email, String password) {
        // email로 password 조회
        User userByEmailOrElseThrow = authRepository.findUserByEmailOrElseThrow(email);
        // 저장된 password가 입력받은 password가 맞는지 검증
        boolean matchedPassword = passwordEncoder.matche(password, userByEmailOrElseThrow.getPassword());
        // 일치하지 않을 때
        if (!matchedPassword) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "비밀번호가 일치하지 않습니다.");
        }
    }
}
