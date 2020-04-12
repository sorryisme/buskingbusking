package com.sorry.buskingbusking.service.impl;

import com.sorry.buskingbusking.Repository.MemberRepository;
import com.sorry.buskingbusking.domain.Member;
import com.sorry.buskingbusking.service.MemberService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class MemberServiceImpl implements MemberService {

    private final MemberRepository memberRepository;


    @Override
    public List<Member> getMemberList() {
        return memberRepository.findAll();
    }
}
