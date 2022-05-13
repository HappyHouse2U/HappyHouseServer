package com.ssafy.happyhouse.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ErrorResponseDto {
    private ErrorDto error;

    public static ErrorResponseDto from(ErrorDto errorDto) {
        return ErrorResponseDto.builder()
                .error(errorDto)
                .build();
    }
}