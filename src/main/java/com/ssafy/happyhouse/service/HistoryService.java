package com.ssafy.happyhouse.service;

import com.ssafy.happyhouse.domain.History;

import java.util.List;

public interface HistoryService {
    boolean addHistory(History history);

    List<History> getHistoryListByAddressId(String addressId);

    List<History> getHistoryListByAptName(String aptName);

    History getHistory(Long no);
}
