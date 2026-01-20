package com.sample.board.dto.board;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BoardListResponseDto {

    private Long boardId;
    private String title;
    private String createId;
    private Instant createDate;
}
