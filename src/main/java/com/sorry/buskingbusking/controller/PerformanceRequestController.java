package com.sorry.buskingbusking.controller;

import com.sorry.buskingbusking.domain.Performance;
import com.sorry.buskingbusking.service.PerformanceRequestService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;

@Controller
@Slf4j
@AllArgsConstructor
public class PerformanceRequestController {

    private final PerformanceRequestService performanceRequestService;



}
