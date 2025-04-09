package org.example.snsprojcet.domain.user.dto;

import lombok.Getter;

@Getter
public class AnotherUserResponseDto {

    private final String nickname;

    private final String introduction;

    public AnotherUserResponseDto(String nickname, String introduction) {
        this.nickname = nickname;
        this.introduction = introduction;
    }
}
