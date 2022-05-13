package com.ssafy.happyhouse.repository;

import com.ssafy.happyhouse.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, String> {
    Optional<Member> findByIdAndPw(String id, String pw);

}
