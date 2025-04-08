package org.example.snsprojcet.domain.user.service;

import lombok.RequiredArgsConstructor;
// import org.example.snsprojcet.common.config.PasswordEncoder;
// import org.example.snsprojcet.domain.user.dto.UserSignUpRequestDto;
import org.example.snsprojcet.domain.user.dto.UserSignUpResponseDto;
import org.example.snsprojcet.domain.user.entity.User;
import org.example.snsprojcet.domain.user.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    // repository 접근
    private final UserRepository userRepository;
    // encoder 접근
    // private final PasswordEncoder passwordEncoder;


    public UserSignUpResponseDto signUp(String name, Long age, String nickname, String email, String password, String introduction) {
        // 비밀번호 암호화 실시
        // String encodedPassword = passwordEncoder.encode(password);
        // User 객체 생성
        User user = new User(name, age, nickname, email, password, introduction);
        // user 객체 저장
        User userSignUp = userRepository.save(user);
        return new UserSignUpResponseDto(userSignUp.getId(), userSignUp.getName(), userSignUp.getAge(), userSignUp.getNickname(), userSignUp.getEmail(), userSignUp.getIntroduction());
    }
}