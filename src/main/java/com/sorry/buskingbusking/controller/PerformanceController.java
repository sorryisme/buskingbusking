package com.sorry.buskingbusking.controller;

import com.sorry.buskingbusking.domain.Notice;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@Slf4j
@AllArgsConstructor
public class PerformanceController {


    @GetMapping("/performance/list")
    public String movePagePerformance(Model model){
        return "/performance/performanceList";
    }


}
