package org.example.snsprojcet.friend.repository;

import org.example.snsprojcet.friend.entity.Friend;
import org.example.snsprojcet.friend.entity.User;
import org.example.snsprojcet.friend.enums.FriendStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FriendRepository extends JpaRepository<Friend,Long> {

    //요청 보내는 리스트
    List<Friend> findByUserrequest(User userrequest);

    //요청 받는 리스트
    List<Friend> findByUserreceiver(User userreciever);

    //친구 조회
    List<Friend> findAcceptedFriends(FriendStatus friendStatus, User user);
}
