package com.sonmat.file.domain.file;

import lombok.Builder;
import lombok.Getter;

import java.util.UUID;

@Getter
public class SaveFile {
    private String uploadName;
    private String originalName;
    private String downloadPath;
    private String extension;

    @Builder
    public SaveFile(String originalName, String extension, String downloadPath) {
        this.originalName = originalName;
        this.extension = extension;
        this.uploadName = UUID.randomUUID().toString() + "." + this.extension ;
        this.downloadPath = downloadPath + '/' + this.uploadName;

    }
}
