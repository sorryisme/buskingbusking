package com.sorry.buskingbusking.repository;

import com.sorry.buskingbusking.domain.Member;
import com.sorry.buskingbusking.domain.UserStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {

    Optional<Member> findMemberByEmailAndPassword(String email, String password);
    List<Member> findByModifiedDateBeforeAndStatusEquals(LocalDateTime localDateTime, UserStatus userStatus);
}
