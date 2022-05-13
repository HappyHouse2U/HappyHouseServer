package com.ssafy.happyhouse.service;

import com.ssafy.happyhouse.domain.History;

import java.net.MalformedURLException;
import java.util.List;

public interface HistoryService {
    boolean addHistory(History history);

    List<History> getHistoryListByAddressId(String addressId);

    List<History> getHistoryListByAptName(String aptName);

    History getHistory(Long no);

    int getDistToSubWay(double lat, double lng) throws MalformedURLException;

    void getCoordinates(History history, int count);

    void sortList(List<History> list);
}
