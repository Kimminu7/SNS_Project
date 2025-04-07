package org.example.snsprojcet.entity;


import jakarta.persistence.*;
import lombok.Getter;

@Entity
@Table(name = "user")
@Getter
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
    @Column(nullable = false)
    private String email;

    // 비밀번호
    @Column(nullable = false)
    private String password;

    // 기본 생성자
    public User(){}

    public User (String name, Long age, String nickname, String email, String password) {
        this.name = name;
        this.age = age;
        this.nickname = nickname;
        this.email = email;
        this.password = password;
    }
}
