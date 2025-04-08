package org.example.snsprojcet.domain.user.dto;

import lombok.Getter;

@Getter
public class UserFindResponseDto {
    // id
    private final Long id;

    // 이름
    private final String name;

    // 나이
    private final Long age;

    // 닉네임
    private final String nickname;

    // 이메일
    private final String email;

    // 소개글
    private final String introduction;

    public UserFindResponseDto(Long id, String name, Long age, String nickname, String email, String introduction) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.nickname = nickname;
        this.email = email;
        this.introduction = introduction;
    }
}
