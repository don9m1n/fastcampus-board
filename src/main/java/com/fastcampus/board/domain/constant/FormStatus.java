package com.fastcampus.board.domain.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum FormStatus {

    CREATE("저장", false),
    UPDATE("수정", true);

    private final String description;
    private final Boolean update;
}
