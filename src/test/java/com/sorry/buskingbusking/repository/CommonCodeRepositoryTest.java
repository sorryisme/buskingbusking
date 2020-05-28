package com.sorry.buskingbusking.repository;

import com.sorry.buskingbusking.Repository.CommonCodeRepository;
import com.sorry.buskingbusking.domain.CommonCode;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;


@DataJpaTest
@RunWith(SpringRunner.class)
public class CommonCodeRepositoryTest {

    @Autowired
    private TestEntityManager testEntityManager;

    @Autowired
    private CommonCodeRepository commonCodeRepository;

    @Test
    public void 객체리스트_조회(){
        CommonCode commonCode1 = CommonCode.builder()
                                            .codeName("codeName1")
                                            .codeDesc("codeDesc1")
                                            .regDt(LocalDateTime.now())
                                            .build();

        testEntityManager.persist(commonCode1);
        CommonCode commonCode2 = CommonCode.builder()
                                            .codeName("codeName2")
                                            .codeDesc("codeDesc2")
                                            .regDt(LocalDateTime.now())
                                            .build();
        testEntityManager.persist(commonCode2);
        CommonCode commonCode3 = CommonCode.builder()
                                            .codeName("codeName3")
                                            .codeDesc("codeDesc3")
                                            .regDt(LocalDateTime.now())
                                            .build();
        testEntityManager.persist(commonCode3);
        List<CommonCode> codeList = commonCodeRepository.findAll();
        assertThat(codeList).isNotNull();
        assertThat(codeList).hasSize(3);
        assertThat(codeList).contains(commonCode1, commonCode2, commonCode3);

    }



}
