package com.sorry.buskingbusking.util;

import org.springframework.web.multipart.MultipartFile;

import java.util.Optional;
import java.util.function.Consumer;

public class OptionalUtil {

    public static void addNullSafeFile(MultipartFile file, Consumer<MultipartFile> addFile) {
        Optional<MultipartFile> optFile = Optional.ofNullable(file);
        optFile.ifPresent(addFile);
    }
}
