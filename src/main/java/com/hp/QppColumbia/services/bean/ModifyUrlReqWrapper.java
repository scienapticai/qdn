package com.hp.QppColumbia.services.bean;

import org.springframework.web.multipart.MultipartFile;


public class ModifyUrlReqWrapper {
	private MultipartFile file;
	private String urlDetails;

	public MultipartFile getFile() {
		return file;
	}

	public void setFile(MultipartFile file) {
		this.file = file;
	}

	public String getUrlDetails() {
		return urlDetails;
	}

	public void setUrlDetails(String urlDetails) {
		this.urlDetails = urlDetails;
	}
}
