package com.sorry.buskingbusking.service;

import com.sorry.buskingbusking.domain.Notice;
import com.sorry.buskingbusking.domain.dto.NoticeDTO;

import java.util.List;

public interface NoticeService {

    List<Notice> getNoticeListAll();

    Notice findNoticeById(Long noticeId);

    Long insertNotice(NoticeDTO noticeDTO);


}
