package com.ssafy.happyhouse.dto;

import lombok.Data;

@Data
public class BoardRegistRequestDto {
    private String title;
    private String content;
    private String memberId;
}
