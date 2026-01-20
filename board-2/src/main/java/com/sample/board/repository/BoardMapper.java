package com.sample.board.repository;

import com.sample.board.domain.Board;
import com.sample.board.dto.board.BoardListResponseDto;
import com.sample.board.dto.common.PageInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Optional;

@Mapper
public interface BoardMapper {

    // C
    void insertBoard(Board board);

    // R
    Optional<Board> findByBoardId(@Param("boardId") Long boardId);

    List<BoardListResponseDto> findBoardList(@Param("title") String title,
                                              @Param("pageInfo") PageInfo pageInfo);

    long countBoard(@Param("title") String title);

    // U
    void updateBoard(Board board);

    // D
    int deleteByBoardId(@Param("boardId") Long boardId, @Param("updateId") String updateId);
}
