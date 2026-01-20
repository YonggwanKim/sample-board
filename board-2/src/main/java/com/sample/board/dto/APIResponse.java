package com.sample.board.dto;

import com.sample.board.type.ResponseCode;

public record APIResponse<T>(String code, String message, T result) {

    public APIResponse(T result) {
        this(ResponseCode.OK.getCode(), ResponseCode.OK.getMessage(), result);
    }
}
