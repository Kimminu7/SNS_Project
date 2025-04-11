package org.example.snsprojcet.domain.friend.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.example.snsprojcet.domain.friend.enums.FriendStatus;
import org.example.snsprojcet.domain.user.entity.User;

@NoArgsConstructor
@Getter
@Entity
@Table(name = "friend")
public class Friend {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //친구 요청 보내는 사람
    @ManyToOne
    @JoinColumn(name = "userrequest_id",nullable = false)
    private User userrequest;

    //받는사람
    @ManyToOne
    @JoinColumn(name = "userreceiver_id",nullable = false)
    private User userreceiver;

    public Friend(User userrequest, User userreceiver, FriendStatus status) {
        this.userrequest = userrequest;
        this.userreceiver = userreceiver;
        this.status = status;
    }

    public Friend(User userrequest, User userreceiver) {
        this.userrequest = userrequest;
        this.userreceiver = userreceiver;
        this.status = FriendStatus.PENDING;
    }

    @Enumerated(EnumType.STRING)
    private FriendStatus status;

    //친구수락
    public void accept() {
        this.status = FriendStatus.ACCEPTED;
    }

    //친구 거절
    public void reject() {
        this.status = FriendStatus.REJECTED;
    }
}
