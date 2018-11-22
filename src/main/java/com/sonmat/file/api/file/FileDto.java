package com.sonmat.file.api.file;

import com.sonmat.file.domain.file.SaveFile;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;

@ApiModel(description = "파일 업로드 정보")
@Getter
@NoArgsConstructor
public class FileDto {
	@ApiModelProperty(required = true , notes = "서버 호스트 " , example = "http://son-mat.com:8080")
	private String host;

	@ApiModelProperty(required = true , notes = "업로드 파일명 " , example = "b35f39a9-a049-4aa9-b8a0-5cea0c712b19.png")
	private String uploadName;
	@ApiModelProperty(required = true , notes = "원본 파일명" , example = "cake.jpg")
	private String originalName;
	@ApiModelProperty(required = true , notes = "다운로드 경로 ex : host + downloadPath" , example = "/files/image/b35f39a9-a049-4aa9-b8a0-5cea0c712b19.png")
	private String downloadPath;

	public FileDto(String host , SaveFile saveFile) {
		this.host = host;
		this.originalName = saveFile.getOriginalName();
		this.uploadName = saveFile.getUploadName();
		this.downloadPath = saveFile.getDownloadPath();

	}
}
