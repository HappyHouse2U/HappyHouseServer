package com.ssafy.happyhouse.service.impl;

import com.ssafy.happyhouse.dto.NewsDto;
import com.ssafy.happyhouse.service.NewsService;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class NewsServiceImpl implements NewsService {
    @Override
    public List<NewsDto> getNewsList() {
        List<NewsDto> newsDtoList = new ArrayList<>();

        String url = "https://search.naver.com/search.naver?where=news&sm=tab_jum&query=아파트";
        Document doc = null;

        try {
            doc = Jsoup.connect(url).get();
        } catch (IOException e) {
            e.printStackTrace();
        }

        Elements element = doc.select(".news_area");
        for (Element el : element) {
            Elements elementA = el.select("a");
            newsDtoList.add(new NewsDto(elementA.attr("title"), elementA.get(0).attr("data-url")));
        }
        return newsDtoList;
    }
}
