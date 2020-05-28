package com.sorry.buskingbusking.controller;

import com.sorry.buskingbusking.domain.CommonCode;
import com.sorry.buskingbusking.service.CommonCodeService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Collections;

import static org.junit.Assert.*;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@WebMvcTest(CommonCodeController.class)
public class CommonCodeControllerTest {

    @Autowired
    MockMvc mvc;

    @MockBean
    CommonCodeService commonCodeService;

    CommonCode commonCode;

    @Before
    public void 공통코드_생성(){
        commonCode = CommonCode.builder()
                                .id(1L)
                                .codeName("CodeName")
                                .codeDesc("CodeDesc")
                                .regDt(LocalDateTime.now())
                                .updDt(LocalDateTime.now())
                                .build();
        given(commonCodeService.getCodeList()).willReturn(Collections.singletonList(commonCode));

    }

    @Test
    public void 공통코드_리스트_이동() throws Exception{
        mvc.perform(get("/admin/code/list"))
                .andExpect(status().isOk())
                .andExpect(view().name("/admin/code/codeList"))
                .andExpect(model().attributeExists("codeList"))
                .andExpect(model().attribute("codeList",Collections.singletonList(commonCode)));
    }
}