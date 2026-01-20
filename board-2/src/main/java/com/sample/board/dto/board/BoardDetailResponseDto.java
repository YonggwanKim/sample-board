package com.sample.board.dto.board;

import com.sample.board.domain.Board;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BoardDetailResponseDto {

    private Long boardId;
    private String title;
    private String content;
    private String createId;
    private Instant createDate;
    private String updateId;
    private Instant updateDate;

    public static BoardDetailResponseDto from(Board board) {
        return BoardDetailResponseDto.builder()
                .boardId(board.getBoardId())
                .title(board.getTitle())
                .content(board.getContent())
                .createId(board.getCreateId())
                .createDate(board.getCreateDate())
                .updateId(board.getUpdateId())
                .updateDate(board.getUpdateDate())
                .build();
    }
}
