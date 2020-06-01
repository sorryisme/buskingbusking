package com.sorry.buskingbusking.controller;

import com.sorry.buskingbusking.domain.Member;
import com.sorry.buskingbusking.domain.dto.MemberDTO;
import com.sorry.buskingbusking.service.MemberService;
import com.sorry.buskingbusking.setting.FileSetting;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.web.multipart.MultipartFile;


import java.io.File;
import java.io.FileInputStream;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.stream.Collectors;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

@RunWith(SpringRunner.class)
@WebMvcTest(MemberController.class)
public class MemberControllerTest {

    private static final Logger log = LoggerFactory.getLogger(MemberControllerTest.class);

    @Autowired
    private MockMvc mvc;

    @MockBean
    private MemberService memberService;

    public Member member;

    public MemberDTO publicMember;

    public MockHttpServletRequestBuilder rb;

    @Before
    public void 일반회원_DTO_생성(){
        publicMember = MemberDTO.builder()
                                .email("public@naver.com")
                                .password("1234")
                                .nickName("publicNick")
                                .mobile("01071467182")
                                .msgYn("Y")
                                .msgId("publicMsg")
                                .delYn("N")
                                .regDt(LocalDateTime.now())
                                .build();


         rb  = post("/member/saveMember").param("email",publicMember.getEmail());


    }

    @Before
    public void 회원정보_Entity_생성(){
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
                                            .andExpect(view().name("/admin/member/memberList"))
                                            .andExpect(model().attributeExists("memberList"))
                                            .andExpect(model().attribute("memberList",Collections.singletonList(member)
                                                                                                        .stream()
                                                                                                        .map(member -> member.toDTO())
                                                                                                        .collect(Collectors.toList())));
    }

    @Test
    public void 회원가입_일반회원() throws Exception{

        mvc.perform(post("/member/saveMember"))
                    .andExpect(status().isOk());

    }



}
