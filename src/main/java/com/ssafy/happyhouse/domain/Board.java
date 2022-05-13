package com.ssafy.happyhouse.domain;

import com.ssafy.happyhouse.dto.BoardRegistRequestDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Board {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long no;

    String date;

    String title;

    String content;

    String writer;

    public static Board regist(BoardRegistRequestDto requestDto) {
        Board board = new Board();
        board.setTitle(requestDto.getTitle());
        board.setContent(requestDto.getContent());
        board.setDate(LocalDate.now().toString());
        board.setWriter(requestDto.getMemberId());
        return board;
    }

    public static void modify(Board board, String title, String content) {
        board.setTitle(title);
        board.setContent(content);
        board.setDate(LocalDate.now().toString());
    }
}
