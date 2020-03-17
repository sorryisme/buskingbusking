package com.sorry.buskingbusking.controller;

import com.sorry.buskingbusking.domain.Member;
import com.sorry.buskingbusking.service.MemberService;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;


import java.util.Collections;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

@RunWith(SpringRunner.class)
@WebMvcTest(MemberController.class)
public class MemberControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private MemberService memberService;

    @Test
    @DisplayName("회원가입_페이지이동")
    public void member_sign_Up() throws Exception{
        mvc.perform(get("/member/signUp"))
                                            .andExpect(status().isOk())
                                            .andExpect(view().name("/member/signUp"));
    }

    @Test
    @DisplayName("회원조회_전체조회")
    public void member_get_List() throws Exception {
        Member member = new Member();
        given(memberService.getMemberList()).willReturn(Collections.singletonList(member));

        mvc.perform(get("/member/getList"))
                                            .andExpect(status().isOk())
                                            .andExpect(view().name("/member/memberList"))
                                            .andExpect(model().attributeExists("memberList"))
                                            .andExpect(model().attribute("memberList",Collections.singletonList(member)));


    }




}
