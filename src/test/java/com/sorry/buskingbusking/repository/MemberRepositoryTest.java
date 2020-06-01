package com.sorry.buskingbusking.repository;

import com.sorry.buskingbusking.Repository.MemberRepository;
import com.sorry.buskingbusking.domain.Address;
import com.sorry.buskingbusking.domain.Member;
import com.sorry.buskingbusking.setting.FileSetting;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.File;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
public class MemberRepositoryTest {

    Logger LOG = LoggerFactory.getLogger(getClass());

    @Autowired
    private TestEntityManager testEntityManager;

    @Autowired
    private MemberRepository memberRepository;

    File file;

    Address address;

    @Before
    public void 파일준비(){
        String fileDir = FileSetting.MOCK_FILE_PATH.getValue();
        String fileName = "test_file.PNG";
        String fileFullPath  = fileDir + "/" + fileName;
        file = new File(fileFullPath);
    }

    @Before
    public void 주소준비(){
        address = Address.builder()
                        .zipcode("04967")
                        .address1("서울특별시 광진구")
                        .address2("광장로7길 24")
                        .addressExt("1층")
                        .build();
    }


    @Test
    public void 회원가입_저장(){

        Member member = Member.builder()
                .email("wivipp39@naver.com")
                .password("1234")
                .nickName("sorryisme")
                .mobile("010-7146-7182")
                .msgYn("Y")
                .msgId("wivipp39")
                .delYn("N")
                .regDt(LocalDateTime.now())
                .updDt(LocalDateTime.now()).build();

        testEntityManager.persist(member);
        assertThat(memberRepository.getOne(member.getId())).isEqualTo(member);
    }

    @Test
    public void 회원저장_조회(){
        Member member1 =  Member.builder()
                .email("test@naver.com")
                .password("1234")
                .nickName("nick1")
                .mobile("010-7146-7182")
                .msgYn("Y")
                .msgId("wivipp39")
                .delYn("N")
                .regDt(LocalDateTime.now())
                .updDt(LocalDateTime.now()).build();
        testEntityManager.persist(member1);
        Member member2 = Member.builder()
                .email("test2@naver.com")
                .password("1234")
                .nickName("test2")
                .mobile("010-7146-7182")
                .msgYn("Y")
                .msgId("wivipp39")
                .delYn("N")
                .regDt(LocalDateTime.now())
                .updDt(LocalDateTime.now()).build();
        testEntityManager.persist(member2);
        Member member3 = Member.builder()
                .email("test3@naver.com")
                .password("1234")
                .nickName("test2")
                .mobile("010-6647-7182")
                .msgYn("Y")
                .msgId("apple")
                .delYn("N")
                .regDt(LocalDateTime.now())
                .updDt(LocalDateTime.now()).build();
        testEntityManager.persist(member3);

        List<Member> memberList = memberRepository.findAll();
        assertThat(memberList).hasSize(3);
        assertThat(memberList).contains(member1,member2,member3);
    }

    @Test
    public void 회원_업데이트(){

        //given
        Member savedMember = Member.builder()
                .email("wivipp39@naver.com")
                .password("1234")
                .nickName("sorryisme")
                .mobile("010-7146-7182")
                .msgYn("Y")
                .msgId("wivipp39")
                .delYn("N")
                .regDt(LocalDateTime.now())
                .updDt(LocalDateTime.now()).build();

        testEntityManager.persist(savedMember);

        Member updateMember = Member.builder()
                .id(savedMember.getId())
                .email("updated@0naver.com")
                .password("9999")
                .nickName("jisungkim")
                .msgId("N")
                .msgId(null)
                .delYn("N")
                .updDt(LocalDateTime.now())
                .build();

        savedMember.updateMember(updateMember);
        testEntityManager.flush();


        Optional<Member> optionalMember = memberRepository.findById(savedMember.getId());
        Member updatedMember = optionalMember.get();

        assertThat(updatedMember).isNotNull();
        assertThat(updatedMember.getEmail()).isEqualTo("updated@0naver.com");
        assertThat(updatedMember.getPassword()).isEqualTo("9999");
        assertThat(updatedMember.getNickName()).isEqualTo("jisungkim");
    }


}

