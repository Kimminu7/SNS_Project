package org.example.snsprojcet.domain.profile.entity;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@Table(name = "user")
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    // 기본키
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

    // 기본 생성자


    public User (String name, Long age, String nickname, String email, String password, String introduction) {
        this.name = name;
        this.age = age;
        this.nickname = nickname;
        this.email = email;
        this.password = password;
        this.introduction = introduction;
    }
}
