package com.sorry.buskingbusking.repository;

import com.sorry.buskingbusking.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {

    public Optional<Member> findMemberByEmailAndPassword(String email, String password);
}
