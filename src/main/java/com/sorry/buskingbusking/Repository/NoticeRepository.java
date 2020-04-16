package com.sorry.buskingbusking.Repository;

import com.sorry.buskingbusking.domain.Notice;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NoticeRepository extends JpaRepository<Notice, Long> {
}
