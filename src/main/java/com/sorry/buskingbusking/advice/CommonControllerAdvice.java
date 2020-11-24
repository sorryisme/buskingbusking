package com.sorry.buskingbusking.advice;

import com.sorry.buskingbusking.domain.dto.LoginDTO;
import com.sorry.buskingbusking.domain.dto.MemberDTO;
import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.NoHandlerFoundException;

import javax.servlet.http.HttpSession;

@ControllerAdvice("com.sorry.buskingbusking.controller")
public class CommonControllerAdvice {

    @ExceptionHandler(RuntimeException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public String handlerRuntime500Exception(){
        return "error/commonException";
    }


    @ExceptionHandler(NoHandlerFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String handlerRuntime404Exception(){
        return "error/commonException";
    }

    @ModelAttribute
    public void getSessionUserInfo(HttpSession session, Model model){
        MemberDTO loginInfo = (MemberDTO)session.getAttribute("login");
        if(loginInfo != null && !loginInfo.getEmail().isEmpty()){
            LoginDTO loginDTO = LoginDTO.builder()
                    .email(loginInfo.getEmail())
                    .id(loginInfo.getId())
                    .mobile(loginInfo.getMobile())
                    .nickName(loginInfo.getNickName())
                    .build();
            model.addAttribute("loginInfo", loginDTO);
        }
    }
}
