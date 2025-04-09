package org.example.snsprojcet.domain.user.dto;

public class FriendResponseDto {

    private final String nickname;

    private final String introduction;

    public FriendResponseDto (String nickname, String introduction) {
        this.nickname = nickname;
        this.introduction = introduction;
    }
}
