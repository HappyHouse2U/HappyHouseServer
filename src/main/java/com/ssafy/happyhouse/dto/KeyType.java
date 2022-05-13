package com.ssafy.happyhouse.dto;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum KeyType {
    TITLE("제목"),
    WRITER("작성자"),
    ID("글 ID");

    private final String key;
}
