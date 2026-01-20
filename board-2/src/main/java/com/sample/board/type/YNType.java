package com.sample.board.type;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum YNType {
    Y("Y"),
    N("N");

    private final String value;
}
