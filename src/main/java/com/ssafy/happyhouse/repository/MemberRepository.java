package com.ssafy.happyhouse.repository;

import com.ssafy.happyhouse.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, String> {
    Member findByIdAndPw(String id, String pw);
}
