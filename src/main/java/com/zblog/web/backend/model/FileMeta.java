package com.zblog.web.backend.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

//ignore "bytes" when return json format
@JsonIgnoreProperties({"bytes"}) 
public class FileMeta {
    private String fileName;
    private String fileSize;
    private String fileType;
    private String filePath;
    private String smallFileName;
	private byte[] bytes;
    public String getFileName() {
		return fileName;
	}
	public String getFileSize() {
		return fileSize;
	}
	public String getFileType() {
		return fileType;
	}
	public byte[] getBytes() {
		return bytes;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public void setFileSize(String fileSize) {
		this.fileSize = fileSize;
	}
	public void setFileType(String fileType) {
		this.fileType = fileType;
	}
	public void setBytes(byte[] bytes) {
		this.bytes = bytes;
	}
	public String getFilePath() {
		return filePath;
	}
	public String getSmallFileName() {
		return smallFileName;
	}
	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}
	public void setSmallFileName(String smallFileName) {
		this.smallFileName = smallFileName;
	}

}
