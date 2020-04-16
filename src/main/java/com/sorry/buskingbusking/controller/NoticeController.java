package com.sorry.buskingbusking.controller;

import com.sorry.buskingbusking.Repository.NoticeRepository;
import com.sorry.buskingbusking.service.NoticeService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
@Slf4j
@AllArgsConstructor
public class NoticeController {

    private final NoticeService noticeService;

    @GetMapping("/notice/list")
    public String movePageNoticeList(){
        return "/notice/noticeList";
    }

    @GetMapping("/notice/view/{id}")
    public String movePageNotice(@PathVariable String id){
        return "/notice/noticeView";
    }

}
