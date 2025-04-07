package org.example.snsprojcet.friend.repository;

import org.example.snsprojcet.friend.entity.Friend;
import org.example.snsprojcet.friend.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface FriendRepository extends JpaRepository<Friend,Long> {
    //요청보냄 요청 수락
    Optional<Friend> findByUserrequestAndUserreceiver(User userrequest,User userreceiver);
    //요청 수랑리스트
    List<Friend> findByUserrequest(User userrequest);

    List<Friend> findByUserreceiver(User userreciever);
    //요철 보내는 리스트
}
