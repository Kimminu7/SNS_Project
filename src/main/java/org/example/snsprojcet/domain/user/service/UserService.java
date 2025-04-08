package org.example.snsprojcet.domain.user.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.example.snsprojcet.common.config.PasswordEncoder;
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
    private final PasswordEncoder passwordEncoder;

    // 회원 가입
    public UserSignUpResponseDto signUp(String name, Long age, String nickname, String email, String password, String introduction) {
        // 중복된 email인지 검증
        if (userRepository.existsByEmail(email)) {
            throw new RuntimeException("이미 가입된 email입니다.");
        }
        // 비밀번호 암호화 실시
        String encodedPassword = passwordEncoder.encode(password);
        // User 객체 생성
        User user = new User(name, age, nickname, email, encodedPassword, introduction);
        // user 객체 저장
        User userSignUp = userRepository.save(user);
        return new UserSignUpResponseDto(userSignUp.getId(), userSignUp.getName(), userSignUp.getAge(), userSignUp.getNickname(), userSignUp.getEmail(), userSignUp.getIntroduction());

    }

    // 회원 탈퇴
    @Transactional
    public void deleteUser(Long id, String password) {
        // id로 유저 조회
        User userByIdOrElseThrow = userRepository.findUserByIdOrElseThrow(id);
        // 비밀번호 검증
        if (!passwordEncoder.match(password, userByIdOrElseThrow.getPassword())) {
            throw new RuntimeException("비밀번호가 일치하지 않습니다.");

        }
        if (!userByIdOrElseThrow.getActivated()) {
            throw new RuntimeException("이미 가입된 적이 있는 email입니다.");
        }
        userRepository.delete(userByIdOrElseThrow);
    }

}
