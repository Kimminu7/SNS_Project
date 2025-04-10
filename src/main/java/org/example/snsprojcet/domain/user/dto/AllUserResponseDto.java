package org.example.snsprojcet.domain.user.dto;

import lombok.Getter;

@Getter
public class AllUserResponseDto {
    // id
    private final Long id;

    // 이름
    private final String name;

    // 닉네임
    private final String nickname;

    // 소개글
    private final String introduction;

    public AllUserResponseDto(Long id, String name, String nickname, String introduction) {
        this.id = id;
        this.name = name;
        this.nickname = nickname;
        this.introduction = introduction;
    }
}
