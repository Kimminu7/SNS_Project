package org.example.snsprojcet.domain.user.dto;

import lombok.Getter;

@Getter
public class UserResponseDto {

    private final String name;

    private final Long age;

    private final String nickname;

    private final String introduction;

    public UserResponseDto(String name, Long age, String nickname, String introduction) {
        this.name = name;
        this.age = age;
        this.nickname = nickname;
        this.introduction = introduction;
    }
}
