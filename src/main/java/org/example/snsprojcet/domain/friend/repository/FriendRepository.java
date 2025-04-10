package org.example.snsprojcet.domain.friend.repository;

import org.example.snsprojcet.domain.friend.entity.Friend;
import org.example.snsprojcet.domain.friend.enums.FriendStatus;
import org.example.snsprojcet.domain.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface FriendRepository extends JpaRepository<Friend,Long> {

    //요청 보내는 리스트
    List<Friend> findByUserrequest(User userrequest);

    //요청 받는 리스트
    List<Friend> findByUserreceiver(User userreciever);

    //친구 조회
    @Query("SELECT f FROM Friend f WHERE (f.userrequest = :user OR f.userreceiver = :user) AND f.status = :status")
    List<Friend> findAcceptedFriends(FriendStatus friendStatus, User user);

    // userrequest는 친구 요청을 보내는 사람이고, userreceiver는 친구 요청을 받는 사람
    Friend findByUserrequestAndUserreceiver(User userrequest, User userreceiver);

    // 반대편 관계도 찾기 위해 userreceiver와 userrequest를 반대로 사용
    Friend findByUserreceiverAndUserrequest(User userreceiver, User userrequest);
}
