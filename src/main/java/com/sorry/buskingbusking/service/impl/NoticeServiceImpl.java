package com.sorry.buskingbusking.service.impl;


import com.sorry.buskingbusking.domain.CommonFile;
import com.sorry.buskingbusking.repository.CommonFileRepository;
import com.sorry.buskingbusking.repository.MemberRepository;
import com.sorry.buskingbusking.repository.NoticeRepository;
import com.sorry.buskingbusking.domain.Member;
import com.sorry.buskingbusking.domain.Notice;
import com.sorry.buskingbusking.domain.dto.NoticeDTO;
import com.sorry.buskingbusking.service.NoticeService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
@Transactional
public class NoticeServiceImpl implements NoticeService {

    private final NoticeRepository noticeRepository;

    private final CommonFileRepository commonFileRepository;

    private final MemberRepository memberRepository;
    @Override
    @Transactional(readOnly = true)
    public Page<Notice> getNoticeListAll(Pageable pageable) {

        int page = (pageable.getPageNumber() == 0) ? 0 : (pageable.getPageNumber() - 1);
        pageable = PageRequest.of(page, 10);

        return noticeRepository.findAll(pageable);
    }

    @Override
    @Transactional(readOnly = true)
    public Notice findNoticeById(Long noticeId) {
        Optional<Notice> optionalNotice = noticeRepository.findById(noticeId);
        Notice findNotice = optionalNotice.get();
        findNotice.addViewCnt();

        return findNotice;
    }

    @Override
    public Long insertNotice(NoticeDTO noticeDTO) throws Exception {
        Notice notice = noticeDTO.toEntity();

        List<MultipartFile> fileList = noticeDTO.getNoticeFileList();
        for( MultipartFile file : fileList ){
            CommonFile commonFile = new CommonFile();
            commonFile.saveFileList(file);
            commonFileRepository.save(commonFile);
            notice.getFileList().add(commonFile);
        }

        Notice savedNotice = noticeRepository.save(notice);
        return savedNotice.getId();
    }

    @Override
    public void deleteNotice(NoticeDTO noticeDTO) throws Exception {

        Notice notice = noticeRepository.getOne(noticeDTO.getId());
        List<CommonFile> files = notice.getFileList();

        for(CommonFile commonFile : files) {
            commonFile.deleteFile();
        }

        noticeRepository.delete(noticeDTO.toEntity());
    }
}
