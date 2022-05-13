package com.ssafy.happyhouse.controller;

import com.ssafy.happyhouse.domain.History;
import com.ssafy.happyhouse.service.HistoryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Slf4j
@RequestMapping("/api/history")
@Controller
@RequiredArgsConstructor
public class HistoryController {
    private final HistoryService historyService;

    @GetMapping("/address")
    public ResponseEntity<List<History>> historyListByAddressId(@RequestParam String sido,
                                                                @RequestParam String sigugun,
                                                                @RequestParam String dong) {
        List<History> historyList = historyService.getHistoryListByAddressId(sido + sigugun + dong + "00");
        return ResponseEntity.ok(historyList);
    }

    @GetMapping("/apt")
    public ResponseEntity<List<History>> historyListByAptName(@RequestParam String aptName) {
        List<History> historyList = historyService.getHistoryListByAptName(aptName);
        return ResponseEntity.ok(historyList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<History> getHistory(@PathVariable Long id) {
        return ResponseEntity.ok(historyService.getHistory(id));
    }
}
