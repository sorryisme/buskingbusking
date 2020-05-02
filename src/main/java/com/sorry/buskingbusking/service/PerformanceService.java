package com.sorry.buskingbusking.service;

import com.sorry.buskingbusking.domain.Performance;
import com.sorry.buskingbusking.domain.dto.PerformanceDTO;

import java.util.List;

public interface PerformanceService {

    List<Performance> getPerformanceListAll();

    Performance findPerformanceById(Long id);

    Long insertPerformance(PerformanceDTO performanceDTO) throws Exception;
}
