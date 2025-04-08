package org.example.snsprojcet.domain.profile.service;

import lombok.RequiredArgsConstructor;
import org.example.snsprojcet.domain.profile.dto.FriendResponseDto;
import org.example.snsprojcet.domain.profile.dto.UserResponseDto;
import org.example.snsprojcet.domain.profile.entity.User;
import org.example.snsprojcet.domain.profile.repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public UserResponseDto findUserById(Long id) {
        User findUser = userRepository.findById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "유저 프로필을 조회할 수 없습니다. :" + id)
        );

        return new UserResponseDto(findUser.getName(), findUser.getAge(), findUser.getNickname(), findUser.getIntroduction());
    }

    public FriendResponseDto findFriendById(Long id) {
        User findFriend = userRepository.findById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "친구 프로필을 조회할 수 없습니다. :" + id)
        );

        return new FriendResponseDto(findFriend.getNickname(), findFriend.getIntroduction());
    }

    @Transactional
    public void updatePassword(Long id, String oldPassword, String newPassword) {

        User findUser = userRepository.findByIdOrElseThrow(id);

        // 1. 현재 비밀번호 일치하지 않음

//        if (!passwordEncoder.matches(oldPassword, findUser.getPassword())) {         // 비밀번호가 해시 처리돼 있다면 PasswordEncoder.matches()를 사용해야 한다.
//            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "비밀번호가 일치하지 않습니다.");
//        }

        if (!findUser.getPassword().equals(oldPassword)) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "비밀번호가 일치하지 않습니다.");
        }

        // 2. 새 비밀번호 형식이 잘못된 경우
        if (!isValidPasswordFormat(newPassword)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "비밀번호 형식이 올바르지 않습니다.");
        }

        // 3. 새 비밀번호가 기존과 같은 경우
        if (oldPassword.equals(newPassword)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "현재 비밀번호와 다른 비밀번호를 입력해주세요.");
        }

        findUser.updatePassword(newPassword);
    }

    // 비밀번호 형식 유효성 검사
    private boolean isValidPasswordFormat(String password) {
        String regex = "^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{8,}$";   // 최소 8자, 하나 이상의 숫자, 하나 이상의 영문자 포함
        return password != null && password.matches(regex);

    }
}
