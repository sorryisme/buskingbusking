package com.sorry.buskingbusking.batch;

import com.sorry.buskingbusking.domain.UserStatus;
import com.sorry.buskingbusking.repository.MemberRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.test.JobLauncherTestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.batch.runtime.BatchStatus;

import java.time.LocalDateTime;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class InactiveUserJobTest {

    @Autowired
    private JobLauncherTestUtils jobLauncherTestUtils;

    @Autowired
    private MemberRepository memberRepository;




    @Test
    public void 휴면계정_테스트 () throws Exception {
        JobExecution jobExecution = jobLauncherTestUtils.launchJob();
        assertEquals(BatchStatus.COMPLETED, jobExecution.getStatus());
        assertEquals(0, memberRepository.findByModifiedDateBeforeAndStatusEquals(LocalDateTime.now().minusYears(1), UserStatus.UNUSED).size());
    }

}
