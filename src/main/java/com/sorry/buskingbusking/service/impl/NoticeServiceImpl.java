package com.sorry.buskingbusking.service.impl;


import com.sorry.buskingbusking.Repository.NoticeRepository;
import com.sorry.buskingbusking.domain.Notice;
import com.sorry.buskingbusking.service.NoticeService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
@Transactional
public class NoticeServiceImpl implements NoticeService {

    private final NoticeRepository noticeRepository;

    @Override
    @Transactional(readOnly = true)
    public List<Notice> getNoticeListAll() {
        return noticeRepository.findAll();
    }

    @Override
    public Notice findNoticeById(Long noticeId) {
        Optional<Notice> optionalNotice = noticeRepository.findById(noticeId);
        Notice findNotice = optionalNotice.get();
        findNotice.addViewCnt();

        return findNotice;
    }

    @Override
    public Long insertNotice(Notice notice) {
        return null;
    }
}
