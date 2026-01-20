package com.sample.board.service;

import com.sample.board.domain.Board;
import com.sample.board.dto.board.BoardCreateRequestDto;
import com.sample.board.dto.board.BoardDetailResponseDto;
import com.sample.board.dto.board.BoardListResponseDto;
import com.sample.board.dto.common.PageInfo;
import com.sample.board.dto.common.PageResponse;
import com.sample.board.exception.CNXException;
import com.sample.board.repository.BoardMapper;
import com.sample.board.type.ResponseCode;

import java.util.List;
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

    public PageResponse<BoardListResponseDto> getBoardList(String title, Integer page, Integer size) {
        PageInfo pageInfo = PageInfo.of(page, size);
        long totalSize = boardMapper.countBoard(title);
        if(totalSize == 0) {
            return PageResponse.of(page, size, 0, List.of());
        }
        List<BoardListResponseDto> dataList = boardMapper.findBoardList(title, pageInfo);
        return PageResponse.of(page, size, totalSize, dataList);
    }
}
