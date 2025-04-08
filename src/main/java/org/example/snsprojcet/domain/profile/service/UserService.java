package org.example.snsprojcet.domain.profile.service;

import lombok.RequiredArgsConstructor;
import org.example.snsprojcet.domain.profile.dto.FriendResponseDto;
import org.example.snsprojcet.domain.profile.dto.UserResponseDto;
import org.example.snsprojcet.domain.profile.entity.User;
import org.example.snsprojcet.domain.profile.repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
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
}
