package com.sorry.buskingbusking.service.impl;

import com.sorry.buskingbusking.repository.CommonFileRepository;
import com.sorry.buskingbusking.repository.PerformanceRepository;
import com.sorry.buskingbusking.domain.CommonFile;
import com.sorry.buskingbusking.domain.Performance;
import com.sorry.buskingbusking.domain.dto.PerformanceDTO;
import com.sorry.buskingbusking.service.PerformanceService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

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

      /*  fileList.forEach(file -> {
            try{
                CommonFile commonFile = new CommonFile();
                commonFile.saveFileList(file);
                CommonFile savedFile = commonFileRepository.save(commonFile);
                performance.getFileList().add(savedFile);
            } catch (IOException ex){
                throw new Exception(ex);
            }
        });*/

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
