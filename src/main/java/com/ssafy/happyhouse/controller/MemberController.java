package com.ssafy.happyhouse.controller;

import com.ssafy.happyhouse.domain.Member;
import com.ssafy.happyhouse.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@Slf4j
@RequestMapping("/member")
@RequiredArgsConstructor
@Controller
public class MemberController {

    private final MemberService memberService; // 왜 private final 붙여야 하는가!!!!!!

    @GetMapping("/login_form.do")
    private String loginForm() {
        return "member/login";
    }

    @PostMapping("/login.do")
    private String login(@RequestParam String id, @RequestParam String pw, Model model, HttpSession session) {
        Member member = memberService.login(id, pw);

        if (member != null) {
            String name = member.getName();
            session.setAttribute("id", id);
            session.setAttribute("name", name);
            session.setAttribute("pw", pw);

            session.setAttribute("phone", member.getPhone());
            session.setAttribute("address", member.getAddress());
            session.setAttribute("detailAddress", member.getDetailAddress());

            return "index";
        }
        model.addAttribute("errorMsg", "아이디 비밀번호 확인");
        return "member/login";
    }

    @GetMapping("/logout.do")
    private String logout(HttpSession session) {
        session.invalidate();
        return "index";
    }

    @GetMapping("/join_form.do")
    private String joinForm() {
        return "member/join";
    }

    @PostMapping("/join.do")
    private String join(@RequestParam String id, @RequestParam String pw,
                        @RequestParam String name, @RequestParam String phone,
                        @RequestParam String address, @RequestParam String detailAddress,
                        Model model, HttpSession session) {

        Member member = new Member(id, pw, name, phone, address, detailAddress);
        if (memberService.idCheck(id)) {
            model.addAttribute("errorMsg", "이미 존재하는 아이디입니다.");
            return "member/join";
        }
        try {
            Member result = memberService.regist(member);
            if (result != null) {    // 회원가입 성공시
                session.setAttribute("id", id);
                session.setAttribute("pw", pw);
                session.setAttribute("name", name);
                session.setAttribute("phone", phone);
                session.setAttribute("address", address);
                session.setAttribute("detailAddress", detailAddress);
                return "index";
            } else {    // 회원가입 실패시
                model.addAttribute("errorMsg", "이미 존재하는 아이디입니다.");
                return "member/join";
            }
        } catch (Exception e) {
            model.addAttribute("errorMsg", "회원가입 중 문제가 발생하였습니다.");
            throw e;
        }
    }

    @GetMapping("modify_form.do")
    private String modifyForm() {
        return "member/modify";
    }

    @PostMapping("modify.do")
    private String modify(Model model, HttpSession session, @RequestParam String pw,
                          @RequestParam String name, @RequestParam String phone,
                          @RequestParam String address, @RequestParam String detailAddress) {
        String id = (String) session.getAttribute("id");
        Member member = new Member(id, pw, name, phone, address, detailAddress);
        try {
            Member result = memberService.modify(member);
            if (result != null) {    // 수정 성공시
                session.setAttribute("name", name);
                session.setAttribute("pw", pw);

                session.setAttribute("phone", phone);
                session.setAttribute("address", address);
                session.setAttribute("detailAddress", detailAddress);
                return "member/modify";
            } else {    // 수정 실패시
                model.addAttribute("errorMsg", "회원 정보 수정 중 문제가 발생하였습니다.");
                return "member/modify";
            }
        } catch (Exception e) {
            model.addAttribute("errorMsg", "회원 정보 수정 중 문제가 발생하였습니다.");
            throw e;
        }
    }

    @GetMapping("/delete.do")
    private String delete(Model model, HttpSession session) {
        String id = (String) session.getAttribute("id");
        if (id != null) {
            memberService.deleteMember(id);
            session.invalidate();
        } else {
            model.addAttribute("errorMsg", "삭제오류");
        }
        return "index";
    }
}
