package com.sorry.buskingbusking.repository;

import com.sorry.buskingbusking.Repository.CommonFileRepository;
import com.sorry.buskingbusking.domain.CommonFile;
import com.sorry.buskingbusking.setting.FileSetting;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
public class CommonFileRepositoryTest {

    @Autowired
    TestEntityManager testEntityManager;

    @Autowired
    CommonFileRepository commonFileRepository;

    MultipartFile multipartFile;
    MultipartFile updateFile;

    private MultipartFile makeMockMultiPartFile(String fileNm) throws IOException{
        String fileDir = FileSetting.MOCK_FILE_PATH.getValue();
        String fileName = fileNm;
        String fileFullPath = fileDir + "/" + fileName;
        File file = new File(fileFullPath);
        return new MockMultipartFile(fileName, new FileInputStream(file));
    }

    @Before
    public void 파일준비() throws IOException {
        multipartFile = makeMockMultiPartFile("test_file.PNG");
        updateFile = makeMockMultiPartFile("update_file.PNG");
    }

    @Test
    public void 파일저장() throws IOException{

        CommonFile commonFile = new CommonFile();
        commonFile.saveFileList(multipartFile);

        testEntityManager.persist(commonFile);

        CommonFile findFile = commonFileRepository.getOne(commonFile.getId());
        assertThat(findFile).isNotNull();
        assertThat(findFile.getId()).isEqualTo(commonFile.getId());
        assertThat(findFile.getFilePath()).isEqualTo(FileSetting.PERFORMANCE_PATH.getValue());
    }

    @Test
    public void 파일_업데이트() throws Exception {
        CommonFile commonFile = new CommonFile();
        commonFile.saveFileList(multipartFile);
        testEntityManager.persist(commonFile);
        commonFile.updateFile(commonFile.getFullPath(), updateFile);
        testEntityManager.flush();

        assertThat(commonFile.getFileRealName()).isEqualTo(updateFile.getOriginalFilename());
        assertThat(commonFile.getFileSize()).isEqualTo(updateFile.getSize());

        File savedFile = new File(commonFile.getFullPath());

        assertThat(savedFile.getName()).isEqualTo(commonFile.getFileSaveName());
        assertThat(savedFile.exists()).isTrue();
    }

    @Test
    public void 파일_삭제() throws Exception {
        CommonFile commonFile = new CommonFile();
        commonFile.saveFileList(multipartFile);
        testEntityManager.persist(commonFile);
        commonFile.deleteFile(commonFile.getFullPath());
        testEntityManager.flush();

        assertThat(commonFile.getDelYn()).isEqualTo("Y");
        File deleteFile = new File(commonFile.getFullPath());
        assertThat(deleteFile.exists()).isFalse();
    }


}