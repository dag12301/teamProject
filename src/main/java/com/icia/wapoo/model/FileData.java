package com.icia.wapoo.model;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FileData implements Serializable {
	private static final long serialVersionUID = -2662137800290034364L;
	
	// input name
	private String name;
	// 파일명
	private String fileName;
	// 원본 파일명
	private String fileOrgName;
	// 파일 저장 경로
	private String filePath;
	// 파일 확장자
	private String fileExt;
	// 파일 크기(byte)
	private long fileSize;
	// 컨텐츠 타입
	private String contentType;
}
