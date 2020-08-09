package com.sorry.buskingbusking.repository;

import com.sorry.buskingbusking.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long> {

    public Member findMemberByEmailAndPassword(String email, String password);
}
