package com.ssafy.happyhouse.service.impl;

import com.ssafy.happyhouse.domain.History;
import com.ssafy.happyhouse.exception.HistoryNotFoundException;
import com.ssafy.happyhouse.repository.HistoryRepository;
import com.ssafy.happyhouse.service.HistoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class HistoryServiceImpl implements HistoryService {
    private final HistoryRepository historyRepository;

    @Override
    @Transactional
    public boolean addHistory(History history) {
        History save = historyRepository.save(history);
        return save.getAptName().equals(history.getAptName());
    }

    @Override
    @Transactional(readOnly = true)
    public List<History> getHistoryListByAddressId(String addressId) {
        return historyRepository.findAllByAddressId(addressId);
    }

    @Override
    @Transactional(readOnly = true)
    public List<History> getHistoryListByAptName(String aptName) {
        return historyRepository.findByAptNameContains(aptName);
    }

    @Override
    @Transactional(readOnly = true)
    public History getHistory(Long no) {
        return historyRepository.findById(no).orElseThrow(HistoryNotFoundException::new);
    }
}
