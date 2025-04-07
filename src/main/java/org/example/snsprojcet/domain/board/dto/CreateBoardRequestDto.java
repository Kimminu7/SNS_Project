package org.example.snsprojcet.domain.board.dto;

import lombok.Getter;

@Getter
public class CreateBoardRequestDto {

    private final String title;

    private final String nickName;

    private final String contents;

    public CreateBoardRequestDto(String title, String nickName, String contents) {
        this.title = title;
        this.nickName = nickName;
        this.contents = contents;
    }
}
