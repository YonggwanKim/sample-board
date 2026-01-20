package com.sample.board.dto.common;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PageResponse<T> {

    private int page;
    private int size;
    private long totalSize;
    private List<T> data;

    public static <T> PageResponse<T> of(int page, int size, long totalSize, List<T> data) {
        return PageResponse.<T>builder()
                .page(page)
                .size(size)
                .totalSize(totalSize)
                .data(data)
                .build();
    }
}
