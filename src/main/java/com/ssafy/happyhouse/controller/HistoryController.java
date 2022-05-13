package com.ssafy.happyhouse.controller;

import com.ssafy.happyhouse.domain.History;
import com.ssafy.happyhouse.service.HistoryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Slf4j
@RequestMapping("/history")
@Controller
@RequiredArgsConstructor
public class HistoryController {
    private final HistoryService historyService;

    @GetMapping("/regList.do")
    public String historyListByAddressId(@RequestParam String sido,
                                         @RequestParam String sigugun,
                                         @RequestParam String dong,
                                         Model model) {
        List<History> historyList = historyService.getHistoryListByAddressId(sido + sigugun + dong + "00");
        model.addAttribute("historyList", historyList);
        return "/history/regList";
    }

    @GetMapping("/aptList.do")
    public String historyListByAptName(@RequestParam String aptName, Model model) {
        List<History> historyList = historyService.getHistoryListByAptName(aptName);
        model.addAttribute("historyList", historyList);
        return "/history/aptList";
    }

    @GetMapping("/detail.do")
    public String getHistory(@RequestParam Long no, Model model) {
        History history = historyService.getHistory(no);
        model.addAttribute("history", history);
        return "/history/detail";
    }
}
