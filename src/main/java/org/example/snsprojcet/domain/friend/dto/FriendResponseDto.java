package org.example.snsprojcet.domain.friend.dto;

import lombok.Getter;
import org.example.snsprojcet.domain.friend.entity.Friend;

import org.example.snsprojcet.domain.user.entity.User;

@Getter
public class FriendResponseDto {
    private Long id;
    private Long requesterId;
    private Long receiverId;
    private String status;

    public FriendResponseDto(Friend friend) {
        this.id = friend.getId();
        this.requesterId = friend.getUserrequest().getId();
        this.receiverId = friend.getUserreceiver().getId();
        this.status = friend.getStatus().name();
    }

    public FriendResponseDto() {}

    public static FriendResponseDto fromUser(User user) {
    FriendResponseDto dto = new FriendResponseDto();
    dto.id = user.getId();
    return dto;
    }

}