package com.sorry.buskingbusking.controller;

import com.sorry.buskingbusking.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class MemberController {

    @Autowired
    private MemberService memberService;

    @GetMapping("/member/signUp")
    public String movePageSignUp(){
        return "/member/signUp";
    }

    @GetMapping("/admin/member/getList")
    public String memberGetList(Model model){
        model.addAttribute("memberList", memberService.getMemberList());
        return "/admin/member/memberList";
    }


}
