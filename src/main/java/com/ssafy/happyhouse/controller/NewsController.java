package com.ssafy.happyhouse.controller;

import com.ssafy.happyhouse.dto.NewsDto;
import com.ssafy.happyhouse.service.NewsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@RequestMapping("/api/news")
@Controller
@RequiredArgsConstructor
public class NewsController {

    private final NewsService newsService;

    @GetMapping
    public ResponseEntity<List<NewsDto>> getNewsList() {
        return ResponseEntity.ok(newsService.getNewsList());
    }
}
