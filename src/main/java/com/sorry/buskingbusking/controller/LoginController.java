package com.sorry.buskingbusking.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {

    @GetMapping("/auth/loginForm")
    public String movePageLogin(){
        return "auth/loginForm";
    }

    @GetMapping("/auth/loginFail")
    public String movePageLoginFail(){
        return "auth/loginFail";
    }
}
