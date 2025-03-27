package com.frontend.service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;

public class FileService {

    public boolean uploadFile(File file, String targetPath) {
        try {
            File targetDir = new File(targetPath).getParentFile();
            if (!targetDir.exists()) {
                targetDir.mkdirs();
            }
            Files.copy(file.toPath(), new File(targetPath).toPath(),
                    StandardCopyOption.REPLACE_EXISTING);
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public File downloadFile(String filePath) {
        return new File(filePath);
    }

    public boolean deleteFile(String filePath) {
        File file = new File(filePath);
        return file.delete();
    }
}