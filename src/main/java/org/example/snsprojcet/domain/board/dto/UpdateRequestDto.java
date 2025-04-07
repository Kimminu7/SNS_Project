package org.example.snsprojcet.domain.board.dto;

import lombok.Getter;

@Getter
public class UpdateRequestDto {

    private final String title;

    private final String contents;

    public UpdateRequestDto(String title, String contents) {
        this.title = title;
        this.contents = contents;
    }
}
