package com.frontend.utils;

import java.io.File;
import java.nio.file.Files;
import java.util.Base64;

public class FileUploadUtils {
    public static String encodeFileToBase64(File file) throws Exception {
        byte[] fileContent = Files.readAllBytes(file.toPath());
        return Base64.getEncoder().encodeToString(fileContent);
    }

    public static String getFileExtension(File file) {
        String name = file.getName();
        int lastIndexOf = name.lastIndexOf(".");
        if (lastIndexOf == -1) return "";
        return name.substring(lastIndexOf);
    }
}