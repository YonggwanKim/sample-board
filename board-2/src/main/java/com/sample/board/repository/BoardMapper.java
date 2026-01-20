package com.sample.board.repository;

import com.sample.board.domain.Board;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Optional;

@Mapper
public interface BoardMapper {

    // C
    void insertBoard(Board board);

    // R
    Optional<Board> findByBoardId(@Param("boardId") Long boardId);
}
