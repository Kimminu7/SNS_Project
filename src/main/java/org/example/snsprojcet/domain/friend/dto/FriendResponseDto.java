package org.example.snsprojcet.domain.friend.dto;

import org.example.snsprojcet.domain.friend.entity.Friend;

import org.example.snsprojcet.domain.user.entity.User;

public class FriendResponseDto {
    private Long id;
    private Long requesterId;
    private Long receiverId;
    private String status;
    private String nickname;

    public FriendResponseDto(Friend friend) {
        this.id = friend.getId();
        this.requesterId = friend.getUserrequest().getId();
        this.receiverId = friend.getUserreceiver().getId();
        this.status = friend.getStatus().name();
    }
    public FriendResponseDto(Long id, String nickname) {
        this.id = id;
        this.nickname = nickname;
    }

    public static FriendResponseDto fromUser(User user) {
        return new FriendResponseDto(user.getId(), user.getNickname());
    }
}