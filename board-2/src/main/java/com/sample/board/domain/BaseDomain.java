package com.sample.board.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.time.Instant;

@SuperBuilder
@NoArgsConstructor
@Getter
public abstract class BaseDomain {

    private String createId;
    protected String updateId;
    private Instant createDate;
    protected Instant updateDate;

    public void initDomain(String userId) {
        Instant now = Instant.now();
        this.createId = userId;
        this.updateId = userId;
        this.createDate = now;
        this.updateDate = now;
    }

    public void updateDomain(String userId) {
        this.updateId = userId;
        this.updateDate = Instant.now();
    }
}
