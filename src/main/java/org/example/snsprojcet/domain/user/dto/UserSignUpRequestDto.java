package org.example.snsprojcet.domain.user.dto;

import lombok.Getter;

@Getter
public class UserSignUpRequestDto {
    // 이름
    private final String name;

    // 나이
    private final Long age;

    // 닉네임
    private final String nickname;

    // 이메일
    private final String email;

    // 비밀번호
    private final String password;

    // 소개글
    private final String introduction;


    public UserSignUpRequestDto(String name, Long age, String nickname, String email, String password, String introduction) {
        this.name = name;
        this.age = age;
        this.nickname = nickname;
        this.email = email;
        this.password = password;
        this.introduction = introduction;
    }
}
