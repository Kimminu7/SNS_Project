package org.example.snsprojcet.domain.board.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.example.snsprojcet.domain.board.entity.Board;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class BoardResponseDto {

    private final Long id;

    private final String title;

    private final String nickname;

    private final String contents;

    private final LocalDateTime createdAt;

    private final LocalDateTime updatedAt;
    // 좋아요 수
    private long likeCounts;

    public BoardResponseDto(Long id, String title, String nickname, String contents, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        this.title = title;
        this.nickname = nickname;
        this.contents = contents;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public static BoardResponseDto from(Board board, long likeCounts) {
        return new BoardResponseDto(board.getId(), board.getTitle(), board.getUser().getNickname(), board.getContents(), board.getCreatedAt(), board.getUpdatedAt(), likeCounts);
    }
}
