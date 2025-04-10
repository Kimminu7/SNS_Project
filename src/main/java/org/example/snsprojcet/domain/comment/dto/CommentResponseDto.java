package org.example.snsprojcet.domain.comment.dto;

import lombok.Getter;
import org.example.snsprojcet.domain.comment.entity.Comment;

import java.time.LocalDateTime;

@Getter
public class CommentResponseDto {

    private final Long id;
    private final String nickname;
    private final String content;
    private final LocalDateTime createdAt;

    public CommentResponseDto(Long id, String nickname, String content, LocalDateTime createdAt) {
        this.id = id;
        this.nickname = nickname;
        this.content = content;
        this.createdAt = createdAt;
    }

    public static CommentResponseDto toDto(Comment comment) {
        return new CommentResponseDto(
                comment.getId(),
                comment.getUser().getNickname(),
                comment.getContent(),
                comment.getCreatedAt()  // 실제 DB에 저장된 시간
        );
    }
}
