package com.sorry.buskingbusking.service.impl;

import com.sorry.buskingbusking.Repository.MemberRepository;
import com.sorry.buskingbusking.domain.Member;
import com.sorry.buskingbusking.domain.dto.MemberDTO;
import com.sorry.buskingbusking.service.MemberService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@AllArgsConstructor
@Transactional
public class MemberServiceImpl implements MemberService {

    private final MemberRepository memberRepository;

    @Override
    @Transactional(readOnly = true)
    public List<Member> getMemberList() {
        return memberRepository.findAll();
    }

    @Override
    public Long saveMember(MemberDTO memberDTO) throws Exception {
        Member saveMember = memberRepository.save(memberDTO.toEntity());
        return saveMember.getId();
    }
}
