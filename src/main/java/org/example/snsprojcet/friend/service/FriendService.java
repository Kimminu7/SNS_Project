package org.example.snsprojcet.friend.service;

import lombok.RequiredArgsConstructor;
import org.example.snsprojcet.friend.entity.Friend;
import org.example.snsprojcet.friend.entity.User;
import org.example.snsprojcet.friend.enums.FriendStatus;
import org.example.snsprojcet.friend.repository.FriendRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;
import java.util.stream.Collectors;

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
    public List<Friend> getSentRequests(User user) {
        return friendRepository.findByUserrequest(user);
    }

    // 내가 받은 친구 요청 목록
    public List<Friend> getReceivedRequests(User user) {
        return friendRepository.findByUserreceiver(user);
    }
    // 친구 목록 조회
    public List<User> getFriendList(User user) {
        List<Friend> friends = friendRepository.findAcceptedFriends(FriendStatus.ACCEPTED, user);

        return friends.stream()
                .map(f -> f.getUserrequest().equals(user) ? f.getUserreceiver() : f.getUserrequest())
                .collect(Collectors.toList());
    }

    //친구삭제 및 확인
    public void deleteFriend(Long friendId, User currentUser) {
        Friend friend = friendRepository.findById(friendId)
                .orElseThrow(() -> new RuntimeException("친구 관계를 찾을 수 없습니다."));

        if (!friend.getUserrequest().equals(currentUser) && !friend.getUserreceiver().equals(currentUser)) {
            throw new RuntimeException("이 친구 관계에 없는 사람입니다.");
        }

        if (friend.getStatus() != FriendStatus.ACCEPTED) {
            throw new RuntimeException("수락된 친구만 삭제할 수 있습니다.");
        }

        friendRepository.delete(friend);
    }
}
