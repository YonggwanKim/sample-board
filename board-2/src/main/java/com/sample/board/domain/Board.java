package com.sample.board.domain;

import com.sample.board.dto.board.BoardCreateRequestDto;
import com.sample.board.type.YNType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class Board extends BaseDomain {

    private Long boardId;
    private String title;
    private String content;
    private YNType useYN;

    public static Board of(BoardCreateRequestDto dto, String userId) {
        Board board = Board.builder()
                .title(dto.getTitle())
                .content(dto.getContent())
                .useYN(YNType.Y)
                .build();
        board.initDomain(userId);
        return board;
    }
}
