package com.sorry.buskingbusking.util;

import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

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

    private static void checkAndMakeDir(File file) throws IOException{
        if(!file.exists()){
            Files.createDirectories(Paths.get(file.getCanonicalPath()));
        }
    }

    public static String saveMultipartFile(MultipartFile file, String path) throws IOException {
        checkAndMakeDir(new File(path));

        String fileName = getSaveFileName(file.getOriginalFilename());
        File saveFile = new File(path + "/" + fileName);
        file.transferTo(saveFile);

        return fileName;
    }

    public static Boolean deleteMultipartFile(String filePath) throws IOException{
        File file = new File(filePath);
        if(!file.exists()){
            throw new IOException("파일이 존재하지 않습니다.");
        }
        return file.delete();
    }


}
