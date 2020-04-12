package com.sorry.buskingbusking.controller;

import com.sorry.buskingbusking.domain.Member;
import com.sorry.buskingbusking.service.MemberService;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;


import java.time.LocalDateTime;
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

    public Member member;

    @Before
    public void 회원정보_생성(){
        member = Member.builder()
                    .email("wivipp39@naver.com")
                    .password("1234")
                    .nickName("sorryisme")
                    .mobile("010-7146-7182")
                    .msgYn("Y")
                    .msgId("wivipp39")
                    .delYn("N")
                    .regDt(LocalDateTime.now())
                    .updDt(LocalDateTime.now()).build();
        given(memberService.getMemberList()).willReturn(Collections.singletonList(member));
    }

    @Test
    public void 회원가입_페이지_이동() throws Exception{
        mvc.perform(get("/member/signUp"))
                                            .andExpect(status().isOk())
                                            .andExpect(view().name("/member/signUp"));
    }


    @Test
    public void 어드민_회원정보_리스트() throws Exception {

        mvc.perform(get("/admin/member/getList"))
                                            .andExpect(status().isOk())
                                            .andExpect(view().name("admin/member/memberList"))
                                            .andExpect(model().attributeExists("memberList"))
                                            .andExpect(model().attribute("memberList",Collections.singletonList(member)));

    }




}
