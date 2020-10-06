package com.sorry.buskingbusking.controller;

import com.sorry.buskingbusking.domain.Notice;
import com.sorry.buskingbusking.domain.dto.NoticeDTO;
import com.sorry.buskingbusking.service.NoticeService;
import com.sorry.buskingbusking.util.OptionalUtil;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@Slf4j
@AllArgsConstructor
public class NoticeController {

    private final NoticeService noticeService;

    @GetMapping("/notice/list")
    public String movePageNoticeList(@PageableDefault Pageable pageable, Model model){
        Page<Notice> noticeList = noticeService.getNoticeListAll(pageable);

        log.info("총 element 수 : {}, 전체 page 수 : {}, 페이지에 표시할 element 수 : {}, 현재 페이지 index : {}, 현재 페이지의 element 수 : {}",
                noticeList.getTotalElements(), noticeList.getTotalPages(), noticeList.getSize(),
                noticeList.getNumber(), noticeList.getNumberOfElements());

        model.addAttribute("noticeList", noticeList);
        return "/notice/noticeList";
    }

    @GetMapping("/notice/view/{id}")
    public String movePageNoticeView( HttpServletRequest request, @PathVariable Long id, Model model){
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
    public String insertNotice(NoticeDTO noticeDto, @RequestParam(required = false, name = "file") MultipartFile multipartFile) throws Exception{

        OptionalUtil.addNullSafeFile(multipartFile, nullSafeFile -> noticeDto.addFile(nullSafeFile) );

        Long id = noticeService.insertNotice(noticeDto);

        return "redirect:/notice/list";
    }

}
