package com.sample.board.type;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum ResponseCode {
    OK("00200", "success", HttpStatus.OK),
    BAD_REQUEST("00400", "bad request", HttpStatus.BAD_REQUEST),
    UNAUTHORIZED("00401", "unauthorized", HttpStatus.UNAUTHORIZED),
    DATA_NOT_FOUND("00404", "not found data", HttpStatus.NOT_FOUND),
    CONFLICT("00409", "conflict data", HttpStatus.CONFLICT),
    INTERNAL_SERVER_ERROR("00500", "server error", HttpStatus.INTERNAL_SERVER_ERROR);

    private final String code;
    private final String message;
    private final HttpStatus httpStatus;
}
