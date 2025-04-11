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

    /**
     * 친구 추가 요청
     * @param requester
     * @param receiver
     * @return
     */
    public Friend sendFriendRequest(User requester, User receiver) {
        if(friendRepository.existsByUserrequestAndUserreceiver(requester, receiver)){
            throw new IllegalArgumentException("이미 요청을 보냈습니다.");
        }
        return friendRepository.save(new Friend(requester, receiver, FriendStatus.PENDING));
    }

    // 친구 수락
    @Transactional
    public void acceptFriendRequest(Long friendId, User loginUser) {
        Friend friend = friendRepository.findById(friendId)
                .orElseThrow(() -> new IllegalArgumentException("친구 요청이 존재하지 않습니다."));
        if(!friend.getUserreceiver().getId().equals(loginUser.getId())){
            throw new IllegalArgumentException("친구 요청 수락 권한이 없습니다.");
        }
        friend.accept();

        Friend reverse = new Friend(friend.getUserreceiver(), friend.getUserrequest());
        reverse.accept();
        friendRepository.save(reverse);
    }

    // 친구 거절
    @Transactional
    public void rejectFriendRequest(Long friendId, User loginUser) {
        Friend friend = friendRepository.findById(friendId)
                .orElseThrow(() -> new IllegalArgumentException("친구 요청이 존재하지 않습니다."));

        if(!friend.getUserreceiver().getId().equals(loginUser.getId())){
            throw new IllegalArgumentException("친구 요청을 거절할 권한이 없습니다.");
        }
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
    @Transactional
    public boolean deleteFriend(User user1, User user2) {
        Friend f1 = friendRepository.findByUserrequestAndUserreceiver(user1, user2);
        Friend f2 = friendRepository.findByUserrequestAndUserreceiver(user2, user1);

        if (f1 != null) friendRepository.delete(f1);
        if (f2 != null) friendRepository.delete(f2);

        return f1 != null || f2 != null;
    }

}
