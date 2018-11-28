package com.sonmat.file.api.file;

import com.sonmat.file.domain.file.FileService;
import com.sonmat.file.domain.file.SaveFile;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Api(tags = "FileApi" , description = "File api")
@RestController
@RequestMapping("/api/files/")
public class FileApiController {


    @Autowired
    private FileService fileService;
    @Value("${sonmat.file.host}")
    private String host;

    @ApiOperation(value = "파일 업로드 처리 (1)", notes = "파일 업로드 처리")
    @PostMapping(value = "/upload")
    public ResponseEntity<FileDto> upload(@RequestParam("file") MultipartFile file) throws IOException {

        SaveFile saveFile = fileService.store(file);
        return ResponseEntity.ok(new FileDto(host , saveFile));
    }

    @ApiOperation(value = "파일 업로드 처리 (n)", notes = "여러 파일 업로드 처리")
    @PostMapping(value = "multiple/upload")
    public ResponseEntity<List<FileDto>> multipleUpload(@RequestParam("files") MultipartFile[] files) throws IOException {

        List<FileDto> results = new ArrayList<>();
        for (MultipartFile file : files) {
            SaveFile saveFile = fileService.store(file);
            results.add(new FileDto(host , saveFile));
        }

        return ResponseEntity.ok(results);
    }
}
