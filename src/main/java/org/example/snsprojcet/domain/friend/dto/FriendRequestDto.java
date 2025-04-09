package org.example.snsprojcet.domain.friend.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class FriendRequestDto {
    private Long requestId;
    private Long receiverId;
}