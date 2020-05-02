package com.sorry.buskingbusking.controller;

import com.sorry.buskingbusking.domain.CommonCode;
import com.sorry.buskingbusking.service.CommonCodeService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
@Slf4j
@AllArgsConstructor
public class CommonCodeController {

    private final CommonCodeService commonCodeService;

    @GetMapping("/admin/code/list")
    public String movePageCodeList(Model model){
        List<CommonCode> codeList = commonCodeService.getCodeList();
        model.addAttribute("codeList", codeList);
        return "/admin/code/codeList";
    }
}
