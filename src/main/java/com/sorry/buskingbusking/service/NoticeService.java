package com.sorry.buskingbusking.service;

import com.sorry.buskingbusking.domain.Notice;
import com.sorry.buskingbusking.domain.dto.NoticeDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface NoticeService {

    Page<Notice> getNoticeListAll(Pageable pageable);

    Notice findNoticeById(Long noticeId);

    Long insertNotice(NoticeDTO noticeDTO);


}
