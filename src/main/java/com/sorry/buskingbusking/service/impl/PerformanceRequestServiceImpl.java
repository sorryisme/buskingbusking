package com.sorry.buskingbusking.service.impl;

import com.sorry.buskingbusking.repository.PerformanceRequestRepository;
import com.sorry.buskingbusking.domain.PerformanceRequest;
import com.sorry.buskingbusking.service.PerformanceRequestService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class PerformanceRequestServiceImpl implements PerformanceRequestService {

    private final PerformanceRequestRepository performanceRequestRepository;

    @Override
    public List<PerformanceRequest> getPerformanceRequestList() {
        return performanceRequestRepository.findAll();
    }
}
