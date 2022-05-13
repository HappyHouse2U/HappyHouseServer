package com.ssafy.happyhouse.service;

import com.ssafy.happyhouse.dto.NewsDto;

import java.util.List;

public interface NewsService {
    List<NewsDto> getNewsList();
}
