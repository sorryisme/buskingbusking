package com.sorry.buskingbusking.repository;

import com.sorry.buskingbusking.Repository.MemberRepository;
import com.sorry.buskingbusking.domain.Member;
import jdk.internal.jline.internal.Log;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import javax.validation.constraints.Null;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasSize;

@RunWith(SpringRunner.class)
@DataJpaTest
public class MemberRepositoryTest {

    Logger LOG = LoggerFactory.getLogger(getClass());

    @Autowired
    private TestEntityManager testEntityManager;

    @Autowired
    private MemberRepository memberRepository;

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
        Long savedId = savedMember.getId();
        Member updateMember = Member.builder()
                .id(savedId)
                .email("updated@0naver.com")
                .password("9999")
                .nickName("jisungkim")
                .msgId("N")
                .msgId(null)
                .delYn("N")
                .updDt(LocalDateTime.now())
                .build();
        memberRepository.save(updateMember);
        Optional<Member> optionalMember = memberRepository.findById(savedId);
        Member updatedMember = optionalMember.get();

        assertThat(updatedMember).isNotNull();
        assertThat(updatedMember.getEmail()).isEqualTo("updated@naver.com");

    }


}

