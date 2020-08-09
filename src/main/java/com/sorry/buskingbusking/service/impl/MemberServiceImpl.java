package com.sorry.buskingbusking.service.impl;

import com.sorry.buskingbusking.repository.MemberRepository;
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

    @Override
    public Long updateMember(MemberDTO memberDTO) throws Exception {
        Member savedMember = memberRepository.getOne(memberDTO.getId());
        savedMember.updateMember(memberDTO.toEntity());
        return savedMember.getId();
    }

    @Override
    @Transactional(readOnly = true)
    public Member findMemberById(Long id) throws Exception {
        return memberRepository.getOne(id);
    }

    @Override
    @Transactional(readOnly = true)
    public Member findMemberByIdAndPwd(MemberDTO memberDTO) throws Exception {
        String email = memberDTO.getEmail();
        String pwd = memberDTO.getPassword();

        return memberRepository.findMemberByEmailAndPassword(email,pwd);
    }
}
