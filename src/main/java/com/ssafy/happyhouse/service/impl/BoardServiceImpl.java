package com.ssafy.happyhouse.service.impl;

import com.ssafy.happyhouse.domain.Board;
import com.ssafy.happyhouse.repository.BoardRepository;
import com.ssafy.happyhouse.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BoardServiceImpl implements BoardService {

    private final BoardRepository boardRepository;

    @Override
    public List<Board> getBoardList() {
        return boardRepository.findAll();
    }

    @Override
    public void registBoard(Board board) {
        boardRepository.save(board);
    }

    @Override
    public Board getBoard(Long no) {
        return boardRepository.getById(no);
    }

    @Override
    public Board modifyBoard(Board board) {
        Board old = getBoard(board.getNo());
        if (old != null) {
            old.setTitle(board.getTitle());
            old.setContent(board.getContent());
            old.setDate(LocalDate.now().toString());
            boardRepository.save(old);
        }
        return old;
    }

    @Override
    public void deleteBoard(Long no) {
        boardRepository.deleteById(no);
    }
}
