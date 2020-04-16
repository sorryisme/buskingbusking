package com.sorry.buskingbusking.controller;

import com.sorry.buskingbusking.domain.Member;
import com.sorry.buskingbusking.service.MemberService;
import com.sorry.buskingbusking.service.NoticeService;
import jdk.vm.ci.meta.Local;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@RunWith(SpringRunner.class)
@WebMvcTest(NoticeController.class)
public class NoticeControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private NoticeService noticeService;

    Member writer;

    @Before
    public void 작성자_생성(){
        writer = Member.builder()
                        .email("writer@admin.com")
                        .nickName("writer")
                        .password("1234")
                        .delYn("N")
                        .regDt(LocalDateTime.now())
                        .build();
    }

    @Test
    public void 페이지_이동 () throws Exception{
        mockMvc.perform(MockMvcRequestBuilders.get("/notice/list"))
                .andExpect(status().isOk())
                .andExpect(view().name("/notice/noticeList"));

    }





}