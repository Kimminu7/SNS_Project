package org.example.snsprojcet.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Entity
@Table(uniqueConstraints = { @UniqueConstraint(name = "likes_uk", columnNames = {"board_id", "user_id"})})
public class Likes {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OnDelete(action = OnDeleteAction.CASCADE)
    @ManyToOne
    @JoinColumn(name = "board_id", nullable = false)
    private Board board;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    // BaseEntity사용? 수정일 생성일?

}
