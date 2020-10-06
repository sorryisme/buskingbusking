package com.sorry.buskingbusking.controller;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


@AllArgsConstructor
@Controller
@Slf4j
public class GrapeController {

    @GetMapping("/grape/list")
    public String movePageGrapeList(){
        return "/grape/grapeList";
    }
}
