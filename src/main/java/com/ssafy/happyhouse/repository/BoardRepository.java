package com.ssafy.happyhouse.repository;

import com.ssafy.happyhouse.domain.Board;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BoardRepository extends JpaRepository<Board, Long> {
    List<Board> findByTitle(String title);

    List<Board> findByWriter(String writer);

    List<Board> findByNo(Long no);
}
