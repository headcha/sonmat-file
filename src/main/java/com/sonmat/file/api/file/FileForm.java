package com.sonmat.file.api.file;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;

@ApiModel
@Getter
public class FileForm {
    @ApiModelProperty(required = true , example = "testfile.png")
    private String fileName;
}
