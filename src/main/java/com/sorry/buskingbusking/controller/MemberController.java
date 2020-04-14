package com.sorry.buskingbusking.controller;

import com.sorry.buskingbusking.domain.dto.MemberDTO;
import com.sorry.buskingbusking.service.MemberService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;


@Controller
@Slf4j
@AllArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @GetMapping("/member/signUp")
    public String movePageSignUp(){
        return "/member/signUp";
    }

    @GetMapping("/admin/member/getList")
    public String memberGetList(Model model){
        model.addAttribute("memberList", memberService.getMemberList());
        return "/admin/member/memberList";
    }

    @PostMapping("/member/saveMember")
    public String saveMember(MemberDTO memberDTO) throws Exception{
        Long id = memberService.saveMember(memberDTO);
        log.info("{}",id);
        return "/member/signUp";
    }


}
