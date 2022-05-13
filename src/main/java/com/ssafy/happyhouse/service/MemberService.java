package com.ssafy.happyhouse.service;

import com.ssafy.happyhouse.domain.Member;

public interface MemberService {

    boolean idCheck(String id);

    Member login(String id, String pw);

    Member regist(Member member);

    Member modify(Member member);

    Member getMember(String id);

    void deleteMember(String id);
}
