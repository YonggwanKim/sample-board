package com.sample.board.dto.common;

import com.sample.board.exception.CNXException;
import com.sample.board.type.ResponseCode;
import lombok.AllArgsConstructor;
import lombok.ToString;

/**
 * 페이징 처리 데이터.
 */
@AllArgsConstructor
@ToString
public class PageInfo {
    private int offset;
    private int size;

    private String data;
    private String sort;


    private PageInfo(int offset, int size) {
        this.offset = offset;
        this.size = size;
    }

    static public PageInfo of(int page, int size) {
        int offset = 0;
        if(page != 1) offset = (page - 1) * size;

        return new PageInfo(offset, size);
    }

    static public PageInfo of(int page, int size, String sortData) {
        int offset = 0;
        if(page != 1) offset = (page - 1) * size;

        String[] sortDataSplit = sortData.trim().split(","); //0: data, 1: sort

        if(sortDataSplit.length != 2) {
            throw new CNXException(ResponseCode.BAD_REQUEST, "invalid sort-data: " + sortData);
        }

        if(!sortDataSplit[1].matches("(?i)asc|desc")) {
            throw new CNXException(ResponseCode.BAD_REQUEST, "invalid sort: " + sortDataSplit[1]);
        }

        return new PageInfo(offset, size, sortDataSplit[0], sortDataSplit[1]);
    }
}

