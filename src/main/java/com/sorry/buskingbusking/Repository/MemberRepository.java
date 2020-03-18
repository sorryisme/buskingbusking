package com.sorry.buskingbusking.Repository;

import com.sorry.buskingbusking.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long> {


}
