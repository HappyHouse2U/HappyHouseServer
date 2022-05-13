package com.ssafy.happyhouse.service.impl;

import com.ssafy.happyhouse.domain.Board;
import com.ssafy.happyhouse.exception.BoardNotFoundException;
import com.ssafy.happyhouse.exception.MemberNotFoundException;
import com.ssafy.happyhouse.repository.BoardRepository;
import com.ssafy.happyhouse.service.BoardService;
import com.ssafy.happyhouse.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BoardServiceImpl implements BoardService {

    private final BoardRepository boardRepository;
    private final MemberService memberService;

    @Override
    public List<Board> getBoardList() {
        return boardRepository.findAll();
    }

    @Override
    public List<Board> getBoardList(String key, String word) {
        if (word == null) return boardRepository.findAll();
        else {
            if (key.equals("title")) return boardRepository.findByTitle(word);
            else if (key.equals("writer")) return boardRepository.findByWriter(word);
            else return boardRepository.findByNo(Long.parseLong(word));
        }
    }

    @Override
    public Board registBoard(Board board) {
        if (!memberService.idCheck(board.getWriter())) throw new MemberNotFoundException();
        boardRepository.save(board);
        return board;
    }

    @Override
    public Board getBoard(Long no) {
        return boardRepository.findById(no).orElseThrow(BoardNotFoundException::new);
    }

    @Override
    public Board modifyBoard(Long no, String title, String content) {
        Board old = getBoard(no);
        Board.modify(old, title, content);
        boardRepository.save(old);
        return old;
    }

    @Override
    public void deleteBoard(Long no) {
        boardRepository.deleteById(no);
    }
}
