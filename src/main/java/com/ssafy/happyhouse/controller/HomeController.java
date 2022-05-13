package com.ssafy.happyhouse.controller;

import com.example.happyhouse5.util.parse.ParseUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.sql.SQLException;

@Controller
@RequiredArgsConstructor
public class HomeController {
    private final ParseUtil parseUtil;
    @RequestMapping("/")
    public String index(){
        return "index";
    }

    @RequestMapping("/save")
    public String saveData() throws SQLException {
        parseUtil.run();
        return "save";
    }
}
