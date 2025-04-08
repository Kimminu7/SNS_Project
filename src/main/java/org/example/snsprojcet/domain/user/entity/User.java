package org.example.snsprojcet.domain.user.entity;


import jakarta.persistence.*;
import lombok.Getter;
import org.hibernate.annotations.SQLDelete;

@Entity
@Table(name = "user")
@Getter
// 소프트 delete
@SQLDelete(sql ="UPDATE user SET activated = false WHERE id = ?")
public class User {

    // 기본키
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // 이름
    @Column(nullable = false)
    private String name;

    // 나이
    @Column(nullable = false)
    private Long age;

    // 닉네임
    @Column(nullable = false)
    private String nickname;

    // email
    @Column(nullable = false, unique = true)
    private String email;

    // 비밀번호
    @Column(nullable = false)
    private String password;

    // 소개글
    @Column(nullable = false)
    private String introduction;

    // 활동 상태(false -> 탈퇴O, true -> 탈퇴X)
    @Column
    private Boolean activated;

    // 기본 생성자
    public User(){}

    public User (String name, Long age, String nickname, String email, String password, String introduction) {
        this.name = name;
        this.age = age;
        this.nickname = nickname;
        this.email = email;
        this.password = password;
        this.introduction = introduction;
        this.activated = true;
    }
}
