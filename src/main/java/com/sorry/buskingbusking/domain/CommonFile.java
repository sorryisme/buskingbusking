package com.sorry.buskingbusking.domain;

import com.sorry.buskingbusking.util.FileUtil;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.*;
import java.io.IOException;
import java.time.LocalDateTime;

import static com.sorry.buskingbusking.setting.FileSetting.PERFORMANCE_PATH;

@Entity
@Getter
@NoArgsConstructor
public class CommonFile {

    @Id
    @Column(name="file_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String filePath;

    private String fileSaveName;

    private String fileRealName;

    private Long fileSize;

    private String delYn;

    private LocalDateTime regDt;

    private LocalDateTime updDt;

    @Builder
    public CommonFile(String filePath, String fileSaveName, String fileRealName, Long fileSize, String delYn, LocalDateTime regDt, LocalDateTime updDt){
        this.filePath = filePath;
        this.fileSaveName = fileSaveName;
        this.fileRealName = fileRealName;
        this.fileSize = fileSize;
        this.delYn = delYn;
        this.regDt = regDt;
        this.updDt = updDt;
    }

    public void saveFileList(MultipartFile file) throws IOException {
        String filePath = PERFORMANCE_PATH.getValue();
        String saveFileName = FileUtil.saveMultipartFile(file, filePath);
        this.fileSaveName = saveFileName;
        this.fileRealName = file.getOriginalFilename();
        this.fileSize = file.getSize();
        this.filePath = filePath;
        this.delYn = "N";
        this.regDt = LocalDateTime.now();
    }

    public String getFullPath (){
        return filePath + "/" + fileSaveName;
    }

    public void deleteFile(String filePath) throws IOException {
        FileUtil.deleteMultipartFile(filePath);
        this.delYn = "Y";
    }

    public void updateFile(String deleteFilePath ,MultipartFile updateFile) throws IOException {
        boolean result = FileUtil.deleteMultipartFile(deleteFilePath);

        if(result){
            saveFileList(updateFile);
        }
    }

}
