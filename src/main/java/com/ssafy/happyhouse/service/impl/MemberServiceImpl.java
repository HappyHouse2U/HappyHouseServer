package com.ssafy.happyhouse.service.impl;

import com.ssafy.happyhouse.domain.Member;
import com.ssafy.happyhouse.repository.MemberRepository;
import com.ssafy.happyhouse.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {

    private final MemberRepository memberRepository;

    @Override
    public boolean idCheck(String id) {
        return memberRepository.existsById(id);
    }

    @Override
    public Member login(String id, String pw) {
        return memberRepository.findByIdAndPw(id, pw);
    }

    @Override
    public Member regist(Member member) {
        return memberRepository.save(member);
    }

    @Override
    public Member modify(Member member) {
        Member old = getMember(member.getId());
        if (old != null) {
            old.setName(member.getName());
            old.setAddress(member.getAddress());
            old.setDetailAddress(member.getDetailAddress());
            old.setPhone(member.getPhone());
            old.setPw(member.getPw());
            memberRepository.save(old);
        }
        return old;
    }

    @Override
    public Member getMember(String id) {
        return memberRepository.getById(id);
    }

    @Override
    public void deleteMember(String id) {
        memberRepository.deleteById(id);
    }
}
