package org.example.snsprojcet.domain.board.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.example.snsprojcet.domain.board.entity.Board;
import org.example.snsprojcet.domain.comment.dto.CommentResponseDto;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@AllArgsConstructor
public class BoardDetailReponseDto {

    private final Long id;

    private final String title;

    private final String nickname;

    private final String contents;

    private final LocalDateTime createdAt;

    private final LocalDateTime updatedAt;
    // 좋아요 수
    private long likeCounts;

    private final List<CommentResponseDto> comments;

    public static BoardDetailReponseDto from(Board board, long likeCounts, List<CommentResponseDto> comments) {
        return new BoardDetailReponseDto(
                board.getId(),
                board.getTitle(),
                board.getUser().getNickname(),
                board.getContents(),
                board.getCreatedAt(),
                board.getUpdatedAt(),
                likeCounts,
                comments
        );
    }
}
