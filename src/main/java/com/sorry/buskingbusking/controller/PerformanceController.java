package com.sorry.buskingbusking.controller;

import com.sorry.buskingbusking.domain.Notice;
import com.sorry.buskingbusking.domain.Performance;
import com.sorry.buskingbusking.service.PerformanceService;
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

    private final PerformanceService performanceService;

    @GetMapping("/performance/list")
    public String movePagePerformance(Model model){
        List<Performance> performanceList = performanceService.getPerformanceListAll();
        model.addAttribute("performanceList", performanceList);
        return "/performance/performanceList";
    }

    @GetMapping("/performance/form")
    public String movePagePerformanceForm(){
        return "/performance/performanceForm";
    }




}
