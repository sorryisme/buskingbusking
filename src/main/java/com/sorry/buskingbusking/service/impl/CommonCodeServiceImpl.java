package com.sorry.buskingbusking.service.impl;

import com.sorry.buskingbusking.Repository.CommonCodeRepository;
import com.sorry.buskingbusking.domain.CommonCode;
import com.sorry.buskingbusking.service.CommonCodeService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class CommonCodeServiceImpl implements CommonCodeService {

    private final CommonCodeRepository commonCodeRepository;

    @Override
    public List<CommonCode> getCodeList() {
        return commonCodeRepository.findAll();
    }



    @Override
    public List<CommonCode> findByCodeName(String codeName) {
        return commonCodeRepository.findByCodeName(codeName);
    }
}
