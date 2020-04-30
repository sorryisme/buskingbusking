package com.sorry.buskingbusking.service.impl;

import com.sorry.buskingbusking.Repository.PerformanceRepository;
import com.sorry.buskingbusking.domain.Performance;
import com.sorry.buskingbusking.service.PerformanceService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class PerformanceServiceImpl implements PerformanceService {

    private final PerformanceRepository performanceRepository;

    @Override
    public List<Performance> getPerformanceListAll() {
        return performanceRepository.findAll();
    }
}
