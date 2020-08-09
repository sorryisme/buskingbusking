package com.sorry.buskingbusking.repository;

import com.sorry.buskingbusking.domain.CommonCode;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommonCodeRepository extends JpaRepository<CommonCode, Long> {
    List<CommonCode> findByCodeName(String codeName);
}
