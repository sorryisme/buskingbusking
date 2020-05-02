package com.sorry.buskingbusking.controller;

import com.sorry.buskingbusking.domain.Notice;
import com.sorry.buskingbusking.domain.Performance;
import com.sorry.buskingbusking.domain.dto.PerformanceDTO;
import com.sorry.buskingbusking.service.PerformanceService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

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

    @GetMapping("/performance/view/{id}")
    public String movePagePerformanceView(@PathVariable Long id, Model model){
        Performance performance = performanceService.findPerformanceById(id);
        model.addAttribute("performance",performance);
        return "/performance/performanceView";
    }

    @PostMapping("/performance/insert")
    public String insertPerformance(@RequestParam(required = false) MultipartFile file,PerformanceDTO performanceDTO) throws Exception{
        log.info(file.getOriginalFilename());
        log.info(file.getName());
        log.info("{}",file.getSize());

        if(file != null) {
            performanceDTO.addFile(file);
        }

        performanceService.insertPerformance(performanceDTO);


        return "redirect:/performance/list";
    }




}
