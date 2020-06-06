package com.sorry.buskingbusking.service;

import com.sorry.buskingbusking.domain.CommonCode;

import java.util.List;

public interface CommonCodeService {

    List<CommonCode> getCodeList();

    List<CommonCode> findByCodeName(String codeName);
}
