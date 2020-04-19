package com.sorry.buskingbusking.service.impl;


import com.sorry.buskingbusking.Repository.MemberRepository;
import com.sorry.buskingbusking.Repository.NoticeRepository;
import com.sorry.buskingbusking.domain.Member;
import com.sorry.buskingbusking.domain.Notice;
import com.sorry.buskingbusking.domain.dto.NoticeDTO;
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

    private final MemberRepository memberRepository;
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
    public Long insertNotice(NoticeDTO noticeDTO) {
        noticeDTO.setInsert();
        //추후 삭제 예정
        Optional<Member> writer = memberRepository.findById(1L);
        noticeDTO.setMember(writer.get());
        //삭제예정 끝
        Notice savedNotice = noticeRepository.save(noticeDTO.toEntity());
        return savedNotice.getId();
    }
}
