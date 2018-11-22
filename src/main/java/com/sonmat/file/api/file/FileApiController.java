package com.sonmat.file.api.file;

import com.sonmat.file.domain.file.FileService;
import com.sonmat.file.domain.file.SaveFile;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Api(tags = "FileApi" , description = "File api")
@RestController
@RequestMapping("/api/files/")
public class FileApiController {


    @Autowired
    private FileService fileService;
    @Value("${sonmat.file.host}")
    private String host;

    @ApiOperation(value = "파일 업로드 처리", notes = "파일 업로드 처리")
    @PostMapping(value = "/upload")
    public ResponseEntity<FileDto> upload(@RequestParam("file") MultipartFile file) throws IOException {

        SaveFile saveFile = fileService.store(file);
        return ResponseEntity.ok(new FileDto(host , saveFile));
    }
}
