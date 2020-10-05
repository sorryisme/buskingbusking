package com.sorry.buskingbusking.repository;

import com.sorry.buskingbusking.domain.Member;
import com.sorry.buskingbusking.domain.Performance;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;
import static org.assertj.core.api.Assertions.assertThat;


import java.time.LocalDateTime;
import java.util.Optional;

@RunWith(SpringRunner.class)
@DataJpaTest
public class PerformanceRepositoryTest {

    @Autowired
    TestEntityManager testEntityManager;

    @Autowired
    PerformanceRepository performanceRepository;

    Member writer;

    @Before
    public void 공연정보_작성자(){
        writer = Member.builder()
                .email("wivipp39@naver.com")
                .password("1234")
                .nickName("sorryisme")
                .mobile("010-7146-7182")
                .msgYn("Y")
                .msgId("wivipp39")
                .delYn("N")
                .build();
    }


    @Test
    public void 공연정보_작성(){
        Performance performance = Performance.builder()
                                            .performanceName("공연정보")
                                            .performanceGenre("장르")
                                            .performanceDate("10월")
                                            .performanceDesc("공연설명")
                                            .performanceLocation("공연장소")
                                            .viewCnt(0)
                                            .regDt(LocalDateTime.now())
                                            .build();


        testEntityManager.persist(writer);
        performance.addMember(writer);
        testEntityManager.persist(performance);

       Optional<Performance> OptionalPerformance =  performanceRepository.findById(performance.getId());
       Performance findPerformance = OptionalPerformance.get();

       assertThat(findPerformance).isEqualTo(performance);
       assertThat(findPerformance.getMember()).isEqualTo(writer);
       assertThat(findPerformance.getPerformanceName()).isEqualTo(performance.getPerformanceName());


    }
}
