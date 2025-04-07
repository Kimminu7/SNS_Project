package org.example.snsprojcet.dto.boarddto;

import lombok.Getter;

@Getter
public class CreateBoardRequestDto {

    private final String title;

    private final String userName;

    private final String contents;

    public CreateBoardRequestDto(String title, String userName, String contents) {
        this.title = title;
        this.userName = userName;
        this.contents = contents;
    }
}
