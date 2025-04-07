package org.example.snsprojcet.domain.board.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDateTime;

@Getter
@Entity
@NoArgsConstructor
@Table(name = "board")
public class Board {

    // 게시물 ID
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // 유저 ID
//    @ManyToOne
//    @JoinColumn(name = "user_id")
//    private User user;
    @Column(nullable = false)
    private String nickname;

    // 제목
    @Column(nullable = false)
    private String title;

    // 내용
    @Column(nullable = false)
    private String contents;

    // 작성일
    @CreatedDate
    @Column(updatable = false)
    private LocalDateTime createdAt;

    // 수정일
    @LastModifiedDate
    private LocalDateTime updatedAt;

    public Board(String title, String nickname, String contents) {
        this.title = title;
        this.nickname = nickname;
        this.contents = contents;
        createdAt = LocalDateTime.now();
    }

    public void update(String title, String contents) {
        this.title = title;
        this.contents = contents;
        updatedAt = LocalDateTime.now();
    }
}
