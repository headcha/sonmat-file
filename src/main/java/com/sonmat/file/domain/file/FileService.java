package com.sonmat.file.domain.file;

import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@Service
public class FileService {

    @Value("${spring.http.multipart.location}")
    private String rootPath;

    public SaveFile store(MultipartFile file) throws IOException {
        String savePath;

        savePath = FileSavePathGenerator.generate();
        SaveFile fileDto = parse(file, savePath);

        Path uploadPath = Paths.get(rootPath, savePath);

        shouldEmptyDirAndCreateDir(uploadPath);

        Files.copy(file.getInputStream(), uploadPath.resolve(fileDto.getUploadName()), StandardCopyOption.REPLACE_EXISTING);

        return fileDto;
    }

    private void shouldEmptyDirAndCreateDir(Path uploadPath) throws IOException {
        if (Files.notExists(uploadPath))
            Files.createDirectories(uploadPath);
    }

    private SaveFile parse(MultipartFile file, String uploadPath) throws UnsupportedEncodingException {
        String originalFilename = URLDecoder.decode(file.getOriginalFilename(), "utf-8");
        String fileName = StringUtils.cleanPath(FilenameUtils.getName(originalFilename));
        String fileExtension = FilenameUtils.getExtension(originalFilename).toLowerCase();

        return SaveFile.builder()
                .originalName(fileName)
                .extension(fileExtension)
                .downloadPath(uploadPath)
                .build();
    }
}