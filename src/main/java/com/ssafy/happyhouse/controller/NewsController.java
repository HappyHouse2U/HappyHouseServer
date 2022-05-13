package com.ssafy.happyhouse.controller;

import com.example.happyhouse5.dto.NewsDto;
import com.example.happyhouse5.service.NewsService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@RequestMapping("/news")
@Controller
@RequiredArgsConstructor
public class NewsController {

    private final NewsService newsService;

    @GetMapping("/newsList.do")
    public String getNewsList(Model model) {
        List<NewsDto> newsList = newsService.getNewsList();
        model.addAttribute("newsList", newsList);
        return "/news";
    }
}
