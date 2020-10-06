package com.sorry.buskingbusking.controller;

import com.sorry.buskingbusking.repository.PerformanceRepository;
import com.sorry.buskingbusking.domain.Member;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.time.LocalDateTime;

@RunWith(SpringRunner.class)
@WebMvcTest(PerformanceController.class)
public class PerformanceControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PerformanceRepository performanceRepository;

    Member writer;

    @Before
    public void 공연정보_작성자(){
        writer = Member.builder()
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
    public void 퍼포먼스_리스트_이동() throws Exception{
        mockMvc.perform(get("/performance/list"))
                .andExpect(status().isOk())
                .andExpect(view().name("performanceList.html"));
    }




}