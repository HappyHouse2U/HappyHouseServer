package com.ssafy.happyhouse.controller;

import com.ssafy.happyhouse.util.parse.ParseUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.sql.SQLException;

@Controller
@RequiredArgsConstructor
public class HomeController {
    private final ParseUtil parseUtil;

    @GetMapping("/api/save")
    public ResponseEntity<String> saveData() throws SQLException {
        parseUtil.run();
        return new ResponseEntity<>("저장 성공", HttpStatus.OK);
    }
}
