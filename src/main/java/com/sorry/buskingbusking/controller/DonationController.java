package com.sorry.buskingbusking.controller;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@AllArgsConstructor
@Slf4j
public class DonationController {

    @GetMapping("/donation/list")
    public String movePageDonationList(){
        return "/donation/list";
    }
}
