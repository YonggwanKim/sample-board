package com.sample.board.service;

import com.sample.board.domain.Board;
import com.sample.board.dto.board.BoardCreateRequestDto;
import com.sample.board.dto.board.BoardDetailResponseDto;
import com.sample.board.dto.board.BoardListResponseDto;
import com.sample.board.dto.board.BoardUpdateRequestDto;
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

    /**
     * Creates a new board post.
     *
     * @param requestDto the board creation request containing title and content
     * @param userId the ID of the user creating the post
     * @return the created board details
     */
    @Transactional
    public BoardDetailResponseDto createBoard(BoardCreateRequestDto requestDto, String userId) {
        Board board = Board.of(requestDto, userId);
        boardMapper.insertBoard(board);
        return BoardDetailResponseDto.from(board);
    }

    /**
     * Retrieves a board post by its ID.
     *
     * @param boardId the ID of the board to retrieve
     * @return the board details
     * @throws CNXException if the board is not found
     */
    public BoardDetailResponseDto getBoard(Long boardId) {
        Board board = boardMapper.findByBoardId(boardId)
                .orElseThrow(() -> new CNXException(ResponseCode.DATA_NOT_FOUND,
                        "Board not found. ID: " + boardId));
        return BoardDetailResponseDto.from(board);
    }

    /**
     * Retrieves a paginated list of board posts.
     *
     * @param title the title keyword to search (optional)
     * @param page the page number
     * @param size the number of items per page
     * @return the paginated board list
     */
    public PageResponse<BoardListResponseDto> getBoardList(String title, Integer page, Integer size) {
        PageInfo pageInfo = PageInfo.of(page, size);
        long totalSize = boardMapper.countBoard(title);
        if (totalSize == 0) {
            return PageResponse.of(page, size, 0, List.of());
        }
        List<BoardListResponseDto> dataList = boardMapper.findBoardList(title, pageInfo);
        return PageResponse.of(page, size, totalSize, dataList);
    }

    /**
     * Updates an existing board post.
     *
     * @param boardId the ID of the board to update
     * @param requestDto the board update request containing title and content
     * @param userId the ID of the user updating the post
     * @return the updated board details
     * @throws CNXException if the board is not found
     */
    @Transactional
    public BoardDetailResponseDto updateBoard(Long boardId, BoardUpdateRequestDto requestDto, String userId) {
        Board board = boardMapper.findByBoardId(boardId)
                .orElseThrow(() -> new CNXException(ResponseCode.DATA_NOT_FOUND,
                        "Board not found. ID: " + boardId));
        board.update(requestDto, userId);
        boardMapper.updateBoard(board);
        return BoardDetailResponseDto.from(board);
    }

    /**
     * Deletes a board post (soft delete).
     *
     * @param boardId the ID of the board to delete
     * @param userId the ID of the user deleting the post
     * @throws CNXException if the board is not found
     */
    @Transactional
    public void deleteBoard(Long boardId, String userId) {
        int deletedCount = boardMapper.deleteByBoardId(boardId, userId);
        if (deletedCount == 0) {
            throw new CNXException(ResponseCode.DATA_NOT_FOUND,
                    "Board not found. ID: " + boardId);
        }
    }
}
