package com.sorry.buskingbusking.controller;

import com.sorry.buskingbusking.domain.Member;
import com.sorry.buskingbusking.service.NoticeService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.internal.stubbing.answers.ThrowsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.time.LocalDateTime;

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
                        .build();
    }

    @Test
    public void 공지사항_리스트_이동 () throws Exception{
        mockMvc.perform(MockMvcRequestBuilders.get("/notice/list"))
                .andExpect(status().isOk())
                .andExpect(view().name("/notice/noticeList"));
    }

    @Test
    public void 공지사항_상세보기_이동 () throws Exception {

        mockMvc.perform(MockMvcRequestBuilders.get("/notice/view/{id}","1"))
                .andExpect(status().isOk())
                .andExpect(view().name("/notice/noticeView"));

    }









}