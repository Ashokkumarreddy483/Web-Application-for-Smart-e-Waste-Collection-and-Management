package com.ecocycle.ewastebackend.util;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.*;
import java.util.*;

@Component
public class FileStorageUtil {

    @Value("${app.upload.dir:uploads}")
    private String uploadDir;

    public List<String> storeFiles(Long userId, MultipartFile[] files, String subfolder) throws IOException {
        List<String> storedPaths = new ArrayList<>();
        if (files == null || files.length == 0) return storedPaths;

        Path userFolder = Paths.get(uploadDir, subfolder, String.valueOf(userId));
        Files.createDirectories(userFolder);

        for (MultipartFile file : files) {
            if (file.isEmpty()) continue;
            String original = file.getOriginalFilename();
            String ext = "";
            if (original != null && original.contains(".")) ext = original.substring(original.lastIndexOf("."));
            String filename = UUID.randomUUID().toString() + ext;
            Path dest = userFolder.resolve(filename);
            Files.copy(file.getInputStream(), dest, StandardCopyOption.REPLACE_EXISTING);
            // store relative path for client access (you can map /files/** to uploadDir)
            storedPaths.add(userFolder.resolve(filename).toString().replace("\\", "/"));
        }
        return storedPaths;
    }

}
