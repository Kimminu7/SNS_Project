package org.example.snsprojcet.domain.board.dto;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class BoardResponseDto {

    private final Long id;

    private final String title;

    private final String nickName;

    private final String contents;

    private final LocalDateTime createdAt;

    private final LocalDateTime updatedAt;

    public BoardResponseDto(Long id, String title, String nickName, String contents, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        this.title = title;
        this.nickName = nickName;
        this.contents = contents;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }
}
