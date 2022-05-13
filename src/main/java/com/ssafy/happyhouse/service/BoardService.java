package com.ssafy.happyhouse.service;

import com.ssafy.happyhouse.domain.Board;
import com.ssafy.happyhouse.dto.KeyType;

import java.util.List;

public interface BoardService {
    List<Board> getBoardList();

    List<Board> getBoardList(KeyType key, String word);

    Board registBoard(Board board);

    Board getBoard(Long no);

    Board modifyBoard(Long no, String title, String content);

    void deleteBoard(Long no);
}
