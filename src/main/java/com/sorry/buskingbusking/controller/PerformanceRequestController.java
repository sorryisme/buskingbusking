package com.sorry.buskingbusking.controller;

import com.sorry.buskingbusking.domain.Performance;
import com.sorry.buskingbusking.domain.PerformanceRequest;
import com.sorry.buskingbusking.service.PerformanceRequestService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
@Slf4j
@AllArgsConstructor
public class PerformanceRequestController {

    private final PerformanceRequestService performanceRequestService;

    @GetMapping("peformanceRequest/list")
    public String movePagePerformanceRequest(Model model){

        List<PerformanceRequest> performanceRequestList = performanceRequestService.getPerformanceRequestList();
        model.addAttribute("performanceRequestList", performanceRequestList);
        return "performanceRequest/peformanceRequestList";
    }

}
