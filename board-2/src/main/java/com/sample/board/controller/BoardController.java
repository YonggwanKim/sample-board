package com.sample.board.controller;

import com.sample.board.dto.APIResponse;
import com.sample.board.dto.board.BoardCreateRequestDto;
import com.sample.board.dto.board.BoardDetailResponseDto;
import com.sample.board.dto.board.BoardListResponseDto;
import com.sample.board.dto.common.PageResponse;
import com.sample.board.service.BoardService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/board")
@RequiredArgsConstructor
@Slf4j
@Validated
public class BoardController {

    private final BoardService boardService;

    @PostMapping
    public APIResponse<BoardDetailResponseDto> createBoard(
            @Valid @RequestBody BoardCreateRequestDto requestDto
    ) {
        log.info("[게시글 생성] 제목: {}", requestDto.getTitle());
        // TODO: 실제 인증 구현 시 userId를 토큰에서 추출
        String userId = "admin";
        return new APIResponse<>(boardService.createBoard(requestDto, userId));
    }

    @GetMapping
    public APIResponse<PageResponse<BoardListResponseDto>> getBoardList(
            @RequestParam(value = "title", required = false) String title,
            @RequestParam(value = "page", defaultValue = "1") Integer page,
            @RequestParam(value = "size", defaultValue = "20") Integer size
    ) {
        log.info("[게시글 목록 조회] 제목: {}, 페이지: {}, 사이즈: {}", title, page, size);
        return new APIResponse<>(boardService.getBoardList(title, page, size));
    }

    @GetMapping("/{board-id}")
    public APIResponse<BoardDetailResponseDto> getBoard(
            @PathVariable("board-id") Long boardId
    ) {
        log.info("[게시글 조회] ID: {}", boardId);
        return new APIResponse<>(boardService.getBoard(boardId));
    }
}
