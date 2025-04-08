package org.example.snsprojcet.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class CommentRequestDto {

    @NotBlank(message = "댓글 내용은 필수로 입력해주세요.")
    private final String content;


    public CommentRequestDto(String content) {
        this.content = content;
    }
}
