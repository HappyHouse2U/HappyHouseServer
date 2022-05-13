package com.ssafy.happyhouse.controller;

import com.ssafy.happyhouse.domain.Board;
import com.ssafy.happyhouse.dto.BoardModifyRequestDto;
import com.ssafy.happyhouse.dto.BoardRegistRequestDto;
import com.ssafy.happyhouse.dto.KeyType;
import com.ssafy.happyhouse.service.BoardService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RequestMapping("/api/board")
@Controller
@RequiredArgsConstructor
public class BoardController {

    private final BoardService boardService;

    @GetMapping
    public ResponseEntity<List<Board>> getBoardList(@RequestParam KeyType key, @RequestParam String word) {
        List<Board> boardList;
        if (word == null) boardList = boardService.getBoardList();
        else boardList = boardService.getBoardList(key, word);
        return ResponseEntity.ok(boardList);
    }

    @PostMapping
    public ResponseEntity<Board> registBoard(@RequestBody BoardRegistRequestDto requestDto) {
        return ResponseEntity.ok(
                boardService.registBoard(Board.regist(requestDto))
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<Board> getBoard(@PathVariable Long id) {
        return ResponseEntity.ok(boardService.getBoard(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Board> modifyBoard(@PathVariable Long id, @RequestBody BoardModifyRequestDto requestDto) {
        return ResponseEntity.ok(
                boardService.modifyBoard(id, requestDto.getTitle(), requestDto.getContent())
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteBoard(@PathVariable Long id) {
        boardService.deleteBoard(id);
        return ResponseEntity.ok().build();
    }
}
