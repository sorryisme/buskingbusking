package com.sorry.buskingbusking.repository;

import com.sorry.buskingbusking.Repository.NoticeRepository;
import com.sorry.buskingbusking.domain.Member;
import com.sorry.buskingbusking.domain.Notice;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

import static org.assertj.core.api.Assertions.assertThat;


@RunWith(SpringRunner.class)
@DataJpaTest
public class NoticeRepositoryTest {

    Logger LOG = LoggerFactory.getLogger(getClass());

    @Autowired
    TestEntityManager testEntityManager;

    @Autowired
    NoticeRepository noticeRepository;

    Member writer;

    List<Notice> noticeList = new ArrayList<>();

    @Before
    public void 작성자_생성(){
        writer = Member.builder()
                .email("wivipp39@naver.com")
                .password("1234")
                .nickName("sorryisme")
                .mobile("010-7146-7182")
                .msgYn("Y")
                .msgId("wivipp39")
                .delYn("N")
                .regDt(LocalDateTime.now())
                .updDt(LocalDateTime.now()).build();
    }

    @Before
    public void 공지사항_리스트_준비(){
        IntStream.rangeClosed(1,10).forEach(args->{
            noticeList.add(
                    Notice.builder()
                            .noticeTitle("공지사항" + args )
                            .noticeContents("공지사항 테스트" + args)
                            .viewCnt(0)
                            .member(writer)
                            .regDt(LocalDateTime.now())
                            .build()
            );
        });
    }


    @Test
    public void 공지사항_추가(){
        Notice notice = Notice.builder()
                                .noticeTitle("공지사항")
                                .noticeContents("공지사항 내용")
                                .viewCnt(0)
                                .member(writer)
                                .regDt(LocalDateTime.now())
                                .build();


        testEntityManager.persist(notice);

        Notice findNotice = noticeRepository.getOne(notice.getId());
        assertThat(findNotice).isEqualTo(notice);
        assertThat(findNotice.getMember()).isEqualTo(writer);
        assertThat(findNotice.getNoticeTitle()).isEqualTo("공지사항");
    }

    @Test
    public void 공지사항_리스트(){
        noticeList.forEach(notice -> {
            testEntityManager.persist(notice);
        });

        List<Notice> findNoticeList = noticeRepository.findAll();

        assertThat(findNoticeList).isEqualTo(noticeList);
        assertThat(findNoticeList.size()).isEqualTo(noticeList.size());
        assertThat(findNoticeList).hasSize(10);
        assertThat(findNoticeList.get(0).getNoticeTitle()).isEqualTo("공지사항1");

    }

}
