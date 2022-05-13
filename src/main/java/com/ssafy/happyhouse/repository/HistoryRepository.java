package com.ssafy.happyhouse.repository;

import com.ssafy.happyhouse.domain.History;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface HistoryRepository extends JpaRepository<History, Long> {
    List<History> findAllByAddressId(String addressId);
    List<History> findByAptNameContains(@Param("APT_NAME") String aptName);
}
