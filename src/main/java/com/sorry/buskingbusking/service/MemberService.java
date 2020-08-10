package com.sorry.buskingbusking.service;

import com.sorry.buskingbusking.domain.Member;
import com.sorry.buskingbusking.domain.dto.MemberDTO;

import java.util.List;

public interface MemberService {

    List<Member> getMemberList();

    Long saveMember(MemberDTO memberDTO) throws Exception;

    Long updateMember(MemberDTO memberDTO) throws Exception;

    Member findMemberById(Long id) throws Exception;

    MemberDTO findMemberByIdAndPwd(MemberDTO memberDTO) throws Exception;

}
