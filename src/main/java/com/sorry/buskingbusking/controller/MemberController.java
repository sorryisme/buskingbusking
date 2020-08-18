package com.sorry.buskingbusking.controller;

import com.sorry.buskingbusking.domain.Member;
import com.sorry.buskingbusking.domain.dto.MemberDTO;
import com.sorry.buskingbusking.service.CommonCodeService;
import com.sorry.buskingbusking.service.MemberService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;


@Controller
@Slf4j
@AllArgsConstructor
public class MemberController {

    private final MemberService memberService;

    private final CommonCodeService commonCodeService;


    @GetMapping("/member/signUp")
    public String movePageSignUp(Model model){
        model.addAttribute("LocCodeList", commonCodeService.findByCodeName("LocCode"));
        model.addAttribute("GenreCodeList", commonCodeService.findByCodeName("GenreCode"));
        return "/member/signUp";
    }

    @GetMapping("/admin/member/getList")
    public String memberGetList(Model model){
        List<Member> memberList =  memberService.getMemberList();
        List<MemberDTO> memberDTOList = memberList.stream()
                                                    .map(member -> member.toDTO())
                                                    .collect(Collectors.toList());

        model.addAttribute("memberList", memberDTOList);
        return "/admin/member/memberList";
    }

    @PostMapping("/member/saveMember")
    public String saveMember(@RequestParam(name ="file") MultipartFile file, @Valid MemberDTO memberDTO) throws Exception{
        Long id = memberService.saveMember(memberDTO);
        log.info("{}",id);
        return "/member/signUp";
    }


    @GetMapping("/member/updateMember/{id}")
    public String updateMember(@PathVariable(name = "id") Long id, Model model) throws Exception{
        memberService.findMemberById(id);
        return "/member/memberView";
    }

    @PutMapping("/member/updateMember")
    public String updateMember(MemberDTO memberDTO) throws Exception{
        memberService.updateMember(memberDTO);
        return "/member/memberView";
    }


}
