package com.ssafy.happyhouse.service;

import com.ssafy.happyhouse.domain.Board;

import java.util.List;

public interface BoardService {
    List<Board> getBoardList();

    List<Board> getBoardList(String key, String word);

    Board registBoard(Board board);

    Board getBoard(Long no);

    Board modifyBoard(Long no, String title, String content);

    void deleteBoard(Long no);
}
