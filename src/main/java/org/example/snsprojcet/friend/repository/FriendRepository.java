package org.example.snsprojcet.friend.repository;

import org.example.snsprojcet.friend.entity.Friend;
import org.example.snsprojcet.friend.entity.User;
import org.example.snsprojcet.friend.enums.FriendStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface FriendRepository extends JpaRepository<Friend,Long> {
    //요청보냄 요청 수락 상태
    List<Friend> findByUserrequestOrUserreceiverAndStatus(User user1, User user2, FriendStatus status);

    //요청 수랑리스트
    List<Friend> findByUserrequest(User userrequest);

    //요청 보내는 리스트
    List<Friend> findByUserreceiver(User userreciever);

    // 로그인 유저가 친구인 관계를 모두 찾기
    List<Friend> findByStatusAndUserrequestOrUserreceiver(FriendStatus status, User user1, User user2);
    //친구 조회
    List<Friend> findAcceptedFriends(FriendStatus friendStatus, User user);
}
