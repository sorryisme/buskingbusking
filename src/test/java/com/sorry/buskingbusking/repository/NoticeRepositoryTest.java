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
        //작성자 등록
        testEntityManager.persist(writer);
        //공지사항 10개 등록
        IntStream.rangeClosed(1,10).forEach(args->{
            testEntityManager.persist(
                    Notice.builder()
                            .noticeTitle("공지사항" + args )
                            .noticeContents("공지사항 테스트" + args)
                            .viewCnt(0)
                            .member(writer)
                            .regDt(LocalDateTime.now())
                            .build()
            );
        });

        List<Notice> findNoticeList = noticeRepository.findAll();


        assertThat(findNoticeList).hasSize(10);
        assertThat(findNoticeList.get(0).getNoticeTitle()).isEqualTo("공지사항1");
        assertThat(findNoticeList.get(0).getMember()).isEqualTo(writer);
    }

    @Test
    public void 공지사항_작성자_조회(){

        //작성자 등록
        testEntityManager.persist(writer);
        //공지사항 10개 등록
        IntStream.rangeClosed(1,10).forEach(args->{
            testEntityManager.persist(
                    Notice.builder()
                            .noticeTitle("공지사항" + args )
                            .noticeContents("공지사항 테스트" + args)
                            .viewCnt(0)
                            .member(writer)
                            .regDt(LocalDateTime.now())
                            .build()
            );
        });

        List<Notice> list = noticeRepository.findByMember(writer);

        assertThat(list.get(0).getNoticeTitle()).isEqualTo("공지사항1");
        assertThat(list.get(0).getMember()).isEqualTo(writer);
        assertThat(list).hasSize(10);

    }

}
