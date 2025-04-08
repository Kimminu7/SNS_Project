package org.example.snsprojcet.domain.auth.service;

import lombok.RequiredArgsConstructor;
import org.example.snsprojcet.common.config.PasswordEncoder;
import org.example.snsprojcet.domain.auth.dto.LoginResponseDto;
import org.example.snsprojcet.domain.user.entity.User;
import org.example.snsprojcet.domain.user.repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    // 로그인
    public LoginResponseDto login(String email, String password) {
        // email로 user 조회
        User userByEmailOrElseThrow = userRepository.findUserByEmailOrElseThrow(email);
        // 회원 탈퇴시 email 사용 불가.
        if (!userByEmailOrElseThrow.getActivated()) {
            throw new RuntimeException("탈퇴한 email입니다.");
        }
        // 저장된 password가 입력받은 password가 맞는지 검증
        boolean matchedPassword = passwordEncoder.match(password, userByEmailOrElseThrow.getPassword());
        // 일치하지 않을 때
        if (!matchedPassword) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "비밀번호가 일치하지 않습니다.");
        }
        return new LoginResponseDto(userByEmailOrElseThrow.getId(), userByEmailOrElseThrow.getEmail(), userByEmailOrElseThrow.getNickname());
    }
}
