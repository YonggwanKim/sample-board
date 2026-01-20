package com.sample.board.service;

import com.sample.board.domain.Board;
import com.sample.board.dto.board.BoardCreateRequestDto;
import com.sample.board.dto.board.BoardDetailResponseDto;
import com.sample.board.exception.CNXException;
import com.sample.board.repository.BoardMapper;
import com.sample.board.type.ResponseCode;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional(readOnly = true)
public class BoardService {

    private final BoardMapper boardMapper;

    @Transactional
    public BoardDetailResponseDto createBoard(BoardCreateRequestDto requestDto, String userId) {
        Board board = Board.of(requestDto, userId);
        boardMapper.insertBoard(board);
        return BoardDetailResponseDto.from(board);
    }

    public BoardDetailResponseDto getBoard(Long boardId) {
        Board board = boardMapper.findByBoardId(boardId)
                .orElseThrow(() -> new CNXException(ResponseCode.DATA_NOT_FOUND,
                        "게시글을 찾을 수 없습니다. ID: " + boardId));
        return BoardDetailResponseDto.from(board);
    }
}
