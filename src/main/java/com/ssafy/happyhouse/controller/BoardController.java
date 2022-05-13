package com.ssafy.happyhouse.controller;

import com.ssafy.happyhouse.domain.Board;
import com.ssafy.happyhouse.service.BoardService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.time.LocalDate;
import java.util.List;

@Slf4j
@RequestMapping("/board")
@Controller
@RequiredArgsConstructor
public class BoardController {

    private final BoardService boardService;

    @RequestMapping("/list.do")
    public String getBoardList(String key, String word, Model model) {
        List<Board> boardList;
        if(word == null) boardList = boardService.getBoardList();
        else boardList = boardService.getBoardList(key, word);
        model.addAttribute("boards", boardList);
        return "/board/notice";
    }

    @PostMapping("/regist.do")
    public String registBoard(@RequestParam String title, @RequestParam String content, HttpSession httpSession) {
        Board board = new Board();
        board.setTitle(title);
        board.setContent(content);
        board.setDate(LocalDate.now().toString());
        board.setWriter((String)httpSession.getAttribute("id"));
        boardService.registBoard(board);
        return "redirect:/board/list.do";
    }

    @GetMapping("/regist_form.do")
    public String registForm() {
        return "/board/regist";
    }

    @GetMapping("select.do")
    public String getBoard(@RequestParam Long no, Model model) {
        model.addAttribute("board", boardService.getBoard(no));
        return "/board/select";
    }

    @GetMapping("modify_form.do")
    public String modifyForm(@RequestParam Long no, Model model) {
        model.addAttribute("board", boardService.getBoard(no));
        return "/board/modify";
    }

    @PostMapping("modify.do")
    public String modifyBoard(@RequestParam Long no, @RequestParam String title, @RequestParam String content, HttpSession httpSession) {
        Board board = new Board();
        board.setNo(no);
        board.setTitle(title);
        board.setContent(content);
        board.setDate(LocalDate.now().toString());
        board.setWriter((String)httpSession.getAttribute("id"));
        boardService.modifyBoard(board);
        return "redirect:/board/select.do?no="+no;
    }

    @GetMapping("delete.do")
    public String deleteBoard(@RequestParam Long no) {
        boardService.deleteBoard(no);
        return "redirect:/board/list.do";
    }
}
