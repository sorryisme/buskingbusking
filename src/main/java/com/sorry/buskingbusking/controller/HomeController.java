package com.sorry.buskingbusking.controller;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/")
    public String movePageSignUp(){
        return "index";
    }

    @GetMapping("/admin")
    public String movePageAdmin(){
        return "admin/adminIndex";
    }
}
