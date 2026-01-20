package com.sample.board.exception;

import com.sample.board.type.ResponseCode;
import lombok.Getter;

@Getter
public class CNXException extends RuntimeException {

    private final ResponseCode responseCode;

    public CNXException(ResponseCode responseCode) {
        super(responseCode.getMessage());
        this.responseCode = responseCode;
    }

    public CNXException(ResponseCode responseCode, String message) {
        super(message);
        this.responseCode = responseCode;
    }
}
