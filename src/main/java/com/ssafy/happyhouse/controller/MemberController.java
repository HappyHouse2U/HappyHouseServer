package com.ssafy.happyhouse.controller;

import com.ssafy.happyhouse.domain.Member;
import com.ssafy.happyhouse.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@Slf4j
@RequestMapping("/api/member")
@RequiredArgsConstructor
@Controller
public class MemberController {

    private final MemberService memberService; // 왜 private final 붙여야 하는가!!!!!!

    @PostMapping("/login")
    private ResponseEntity<String> login(@RequestParam String id, @RequestParam String pw, HttpSession session) {
        Member member = memberService.login(id, pw);
        String name = member.getName();
        session.setAttribute("id", id);
        session.setAttribute("name", name);
        session.setAttribute("pw", pw);

        session.setAttribute("phone", member.getPhone());
        session.setAttribute("address", member.getAddress());
        session.setAttribute("detailAddress", member.getDetailAddress());
        return ResponseEntity.ok().build();
    }

    @GetMapping("/logout")
    private ResponseEntity<String> logout(HttpSession session) {
        session.invalidate();
        return ResponseEntity.ok().build();
    }

    @PostMapping("/join")
    private ResponseEntity<String> join(@RequestParam String id, @RequestParam String pw,
                                        @RequestParam String name, @RequestParam String phone,
                                        @RequestParam String address, @RequestParam String detailAddress,
                                        HttpSession session) {

        Member member = new Member(id, pw, name, phone, address, detailAddress);
        memberService.regist(member);
        session.setAttribute("id", id);
        session.setAttribute("pw", pw);
        session.setAttribute("name", name);
        session.setAttribute("phone", phone);
        session.setAttribute("address", address);
        session.setAttribute("detailAddress", detailAddress);
        return ResponseEntity.ok().build();
    }

    @PutMapping
    private ResponseEntity<String> modify(HttpSession session, @RequestParam String pw,
                                          @RequestParam String name, @RequestParam String phone,
                                          @RequestParam String address, @RequestParam String detailAddress) {
        String id = (String) session.getAttribute("id");
        Member member = new Member(id, pw, name, phone, address, detailAddress);
        Member result = memberService.modify(member);
        if (result != null) {    // 수정 성공시
            session.setAttribute("name", name);
            session.setAttribute("pw", pw);

            session.setAttribute("phone", phone);
            session.setAttribute("address", address);
            session.setAttribute("detailAddress", detailAddress);
        }
        return ResponseEntity.ok().build();
    }

    @DeleteMapping
    private ResponseEntity<String> delete(HttpSession session) {
        String id = (String) session.getAttribute("id");
        if (id != null) {
            memberService.deleteMember(id);
            session.invalidate();
        }
        return ResponseEntity.ok().build();
    }
}
