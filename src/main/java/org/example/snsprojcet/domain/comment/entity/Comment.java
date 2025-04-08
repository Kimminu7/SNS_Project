package org.example.snsprojcet.domain.comment.entity;

import lombok.Getter;
import jakarta.persistence.*;
import org.example.snsprojcet.domain.board.entity.Board;
import org.example.snsprojcet.domain.user.entity.User;

@Getter
@Entity
@Table(name = "comment")
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String content;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "board_id", nullable = false)
    private Board board;

    public Comment() {

    }

    public Comment(String content, User user, Board board) {
        this.content = content;
        this.user = user;
        this.board = board;
    }

    public void update(String content) {
        this.content = content;
    }

}
