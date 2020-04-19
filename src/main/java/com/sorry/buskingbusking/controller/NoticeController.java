package com.sorry.buskingbusking.controller;

import com.sorry.buskingbusking.Repository.NoticeRepository;
import com.sorry.buskingbusking.domain.Notice;
import com.sorry.buskingbusking.domain.dto.NoticeDTO;
import com.sorry.buskingbusking.service.NoticeService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
@Slf4j
@AllArgsConstructor
public class NoticeController {

    private final NoticeService noticeService;

    @GetMapping("/notice/list")
    public String movePageNoticeList(Model model){
        List<Notice> noticeList = noticeService.getNoticeListAll();
        model.addAttribute("noticeList", noticeList);
        return "/notice/noticeList";
    }

    @GetMapping("/notice/view/{id}")
    public String movePageNoticeView(@PathVariable Long id, Model model){
        log.info("** id = {}", id);
        Notice notice = noticeService.findNoticeById(id);
        model.addAttribute("notice", notice);

        return "/notice/noticeView";
    }

    @GetMapping("/notice/form")
    public String movePageNoticeForm(){
        return "/notice/noticeForm";
    }

    @PostMapping("/notice/insert")
    public String insertNotice(NoticeDTO noticeDto){

        Long id = noticeService.insertNotice(noticeDto);
        return "redirect:/notice/list";
    }

}
