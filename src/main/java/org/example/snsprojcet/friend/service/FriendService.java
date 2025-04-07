package org.example.snsprojcet.friend.service;

import lombok.RequiredArgsConstructor;
import org.example.snsprojcet.friend.entity.Friend;
import org.example.snsprojcet.friend.entity.User;
import org.example.snsprojcet.friend.enums.FriendStatus;
import org.example.snsprojcet.friend.repository.FriendRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FriendService {

    private final FriendRepository friendRepository;

    // 친구 요청 보내기
    public Friend sendFriendRequest(User requester, User receiver) {
        return friendRepository.save(new Friend(requester, receiver, FriendStatus.PENDING));
    }

    // 친구 수락
    @Transactional
    public void acceptFriendRequest(Long friendId) {
        Friend friend = friendRepository.findById(friendId)
                .orElseThrow(() -> new IllegalArgumentException("친구 요청이 존재하지 않습니다."));
        friend.accept();
    }

    // 친구 거절
    @Transactional
    public void rejectFriendRequest(Long friendId) {
        Friend friend = friendRepository.findById(friendId)
                .orElseThrow(() -> new IllegalArgumentException("친구 요청이 존재하지 않습니다."));
        friend.reject();
    }

    // 내가 요청한 친구 목록
    public List<Friend> getSentRequests(User me) {
        return friendRepository.findByUserrequest(me);
    }

    // 내가 받은 친구 요청 목록
    public List<Friend> getReceivedRequests(User me) {
        return friendRepository.findByUserreceiver(me);
    }
}
