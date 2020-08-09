package com.sorry.buskingbusking.controller;

import com.sorry.buskingbusking.domain.Member;
import com.sorry.buskingbusking.domain.dto.MemberDTO;
import com.sorry.buskingbusking.service.MemberService;
import com.sun.media.jfxmedia.logging.Logger;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpSession;

@Controller
@Slf4j
@AllArgsConstructor
public class LoginController {

    private final MemberService memberService;

    @GetMapping("/auth/loginForm")
    public String movePageLogin(){
        return "auth/loginForm";
    }

    @GetMapping("/auth/loginFail")
    public String movePageLoginFail(){
        return "auth/loginFail";
    }

    @PostMapping("/auth/login")
    public String login(MemberDTO memberDTO, HttpSession session, Model model) throws Exception{
        Member loginMember = memberService.findMemberByIdAndPwd(memberDTO);
        log.info(loginMember.getEmail());

        return "index";
    }

}
