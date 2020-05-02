package com.sorry.buskingbusking.service.impl;

import com.sorry.buskingbusking.Repository.CommonFileRepository;
import com.sorry.buskingbusking.Repository.PerformanceRepository;
import com.sorry.buskingbusking.domain.CommonFile;
import com.sorry.buskingbusking.domain.Performance;
import com.sorry.buskingbusking.domain.dto.PerformanceDTO;
import com.sorry.buskingbusking.service.PerformanceService;
import com.sorry.buskingbusking.setting.FileSetting;
import com.sorry.buskingbusking.util.FileUtil;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

import static com.sorry.buskingbusking.setting.FileSetting.PERFORMANCE_PATH;

@Service
@AllArgsConstructor
public class PerformanceServiceImpl implements PerformanceService {

    private final PerformanceRepository performanceRepository;

    private final CommonFileRepository commonFileRepository;

    @Override
    public List<Performance> getPerformanceListAll() {
        return performanceRepository.findAll();
    }

    @Override
    public Performance findPerformanceById(Long id) {
        Optional<Performance> optionalPerformance = performanceRepository.findById(id);
        return optionalPerformance.get();
    }

    @Override
    public Long insertPerformance(PerformanceDTO performanceDTO) throws Exception {

        Performance performance = performanceDTO.toEntity();
        List<MultipartFile> fileList = performanceDTO.getCommonFileList();

        for(MultipartFile files : fileList){
            CommonFile commonFile = new CommonFile();
            commonFile.saveFileList(files);
            CommonFile savedFile = commonFileRepository.save(commonFile);
            performance.getFileList().add(savedFile);
        }

        Performance savedPerformance =  performanceRepository.save(performance);
        return savedPerformance.getId();
    }
}
