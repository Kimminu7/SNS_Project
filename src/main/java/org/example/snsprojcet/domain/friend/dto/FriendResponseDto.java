package org.example.snsprojcet.domain.friend.dto;

import lombok.Getter;
import org.example.snsprojcet.domain.friend.entity.Friend;

@Getter
public class FriendResponseDto {
    private Long id;
    private String requesterNickname;
    private String receiverNickname;
    private String status;

    public FriendResponseDto(Friend friend) {
        this.id = friend.getId();
        this.requesterNickname = friend.getUserrequest().getNickname();
        this.receiverNickname = friend.getUserreceiver().getNickname();
        this.status = friend.getStatus().name();
    }
}