package com.wavods.anystore.controllers.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FileUpload {

	private String fileName;
	private Long fileSize;
	private String filePath;
	private byte[] file;

	public FileUpload(String fileName, Long fileSize, String filePath) {
		this.fileName = fileName;
		this.fileSize = fileSize;
		this.filePath = filePath;
	}
}
