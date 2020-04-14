package com.sorry.buskingbusking.service;

import com.sorry.buskingbusking.domain.Member;
import com.sorry.buskingbusking.domain.dto.MemberDTO;

import java.util.List;

public interface MemberService {

    List<Member> getMemberList();

    Long saveMember(MemberDTO memberDTO) throws Exception;
}
