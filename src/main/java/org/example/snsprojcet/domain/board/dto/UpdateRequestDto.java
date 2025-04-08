package org.example.snsprojcet.domain.board.dto;

import lombok.Getter;

@Getter
public class UpdateRequestDto {

    private final String title;

    private final String contents;

    private final String email;

    public UpdateRequestDto(String title, String contents, String email) {
        this.title = title;
        this.contents = contents;
        this.email = email;
    }
}
