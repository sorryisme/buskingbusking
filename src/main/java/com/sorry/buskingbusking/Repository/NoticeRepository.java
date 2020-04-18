package com.sorry.buskingbusking.Repository;

import com.sorry.buskingbusking.domain.Member;
import com.sorry.buskingbusking.domain.Notice;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NoticeRepository extends JpaRepository<Notice, Long> {

    List<Notice> findByMember(Member member);
}
