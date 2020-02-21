package com.myclass.dto;

import org.springframework.web.multipart.MultipartFile;

public class AvatarDTO {
	private String id;
	private MultipartFile file;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public MultipartFile getFile() {
		return file;
	}

	public void setFile(MultipartFile file) {
		this.file = file;
	}

	public AvatarDTO() {

	}
}
