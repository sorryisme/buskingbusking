package com.sorry.buskingbusking.util;

import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletContext;
import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static com.sun.tools.javac.util.Constants.format;

public class FileUtil {

    private static String getLocalDateTime(){
        return LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd_HHmmssSSS"));
    }

    private static String getSaveFileName(String fileName){
        return getLocalDateTime() + extractFileExt(fileName);
    }

    private static String extractFileExt(String fileName){
        int dotPosition = fileName.lastIndexOf(".");

        if(dotPosition == -1) {
            return "";
        }
        return fileName.substring(dotPosition);
    }

    private static void checkAndMakeDir(File file){
        if(!file.exists()){
            file.mkdirs();
        }
    }

    public static String saveMultipartFile(MultipartFile file, String path) throws IOException {
        checkAndMakeDir(new File(path));

        String fileName = getSaveFileName(file.getOriginalFilename());
        File saveFile = new File(path + "/" + fileName);
        file.transferTo(saveFile);

        return fileName;
    }
}
