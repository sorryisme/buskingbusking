package com.sorry.buskingbusking.controller;

import com.sorry.buskingbusking.domain.Member;
import com.sorry.buskingbusking.domain.dto.MemberDTO;
import com.sorry.buskingbusking.service.CommonCodeService;
import com.sorry.buskingbusking.service.MemberService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Collections;
import java.util.stream.Collectors;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@WebMvcTest(MemberController.class)
public class MemberControllerTest {

    private static final Logger log = LoggerFactory.getLogger(MemberControllerTest.class);

    @Autowired
    private MockMvc mvc;

    @MockBean
    private MemberService memberService;

    @MockBean
    private CommonCodeService commonCodeService;

    public Member member;

    public MemberDTO publicMember;

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
                                .build();
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
                    .build();
    }

    @Test
    public void 일반회원_회원가입_페이지이동() throws Exception{
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
                                            .andExpect(model().attribute("memberList",Collections.singletonList(member)));
    }

    @Test
    public void 회원가입_일반회원_회원등록() throws Exception{

        mvc.perform(post("/member/saveMember"))
            .andExpect(status().isOk())
            .andExpect(view().name("/member/signUp"));


    }

    @Test
    public void 회원가입_일반회원_회원수정() throws Exception{

        mvc.perform(post("/member/updateMember"))
                .andExpect(status().isOk());

    }



}
