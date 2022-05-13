package com.ssafy.happyhouse.service;


import com.ssafy.happyhouse.domain.Board;

import java.util.List;

public interface BoardService {
    List<Board> getBoardList();

    void registBoard(Board board);

    Board getBoard(Long no);

    Board modifyBoard(Board board);

    void deleteBoard(Long no);
}
