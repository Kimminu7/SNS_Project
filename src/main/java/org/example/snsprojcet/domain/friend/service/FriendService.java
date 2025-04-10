package org.example.snsprojcet.domain.friend.service;

import lombok.RequiredArgsConstructor;
import org.example.snsprojcet.domain.friend.dto.FriendResponseDto;
import org.example.snsprojcet.domain.friend.entity.Friend;
import org.example.snsprojcet.domain.friend.enums.FriendStatus;
import org.example.snsprojcet.domain.friend.repository.FriendRepository;
import org.example.snsprojcet.domain.user.entity.User;
import org.example.snsprojcet.domain.user.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.example.snsprojcet.domain.friend.enums.FriendStatus.ACCEPTED;

@Service
@RequiredArgsConstructor
public class FriendService {

    private final FriendRepository friendRepository;
    private final UserRepository userRepository;

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
    public List<String> findAllFriendList (String nickname) {
        // 유저 조회
        User userByNicknameOrElseThrow = userRepository.findUserByNicknameOrElseThrow(nickname);
        // 친구 요청 리스트 불러오기(친구 요청 목록에서 nickname 뽑아오기위해)
        // 수락 한 친구 상태만 보여주기
        List<FriendResponseDto> list =
                friendRepository.findByUserrequest(userByNicknameOrElseThrow)
                        .stream()
                        .filter(friend -> friend.getStatus().equals(ACCEPTED)) // 친구 요청을 수락했는지 확인
                        .map(friend -> new FriendResponseDto(friend))
                        .toList();
        // nickname 뽑아와 새로운 리스트 생성
        List<String> nicknames = list.stream()
                .map(friendResponseDto -> friendResponseDto.getReceiverNickname()) // FriendResponseDto에서 nickname 가져오기
                .toList();
        return nicknames;
    }

    //친구삭제 및 확인
    public void deleteFriend(User currentUser, String nickname) {
        User togetUser = userRepository.findUserByNicknameOrElseThrow(nickname);

        Friend friend = friendRepository.findByUserrequestAndUserreceiver(currentUser, togetUser)
                .orElseGet(() -> friendRepository.findByUserrequestAndUserreceiver(togetUser, currentUser)
                        .orElseThrow(() -> new RuntimeException("친구 관계를 찾을 수 없습니다.")));

        if (!friend.getUserrequest().equals(currentUser) && !friend.getUserreceiver().equals(currentUser)) {
            throw new RuntimeException("이 친구 관계에 없는 사람입니다.");
        }

        if (friend.getStatus() != FriendStatus.ACCEPTED) {
            throw new RuntimeException("수락된 친구만 삭제할 수 있습니다.");
        }

        friendRepository.delete(friend);
    }
}
