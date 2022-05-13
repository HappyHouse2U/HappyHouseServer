package com.ssafy.happyhouse.repository;

import com.ssafy.happyhouse.domain.Board;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BoardRepository extends JpaRepository<Board, Long> {
//    List<Board> findAll();
}
