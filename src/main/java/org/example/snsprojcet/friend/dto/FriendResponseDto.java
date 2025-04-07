package org.example.snsprojcet.friend.dto;

import org.example.snsprojcet.friend.entity.Friend;

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
}