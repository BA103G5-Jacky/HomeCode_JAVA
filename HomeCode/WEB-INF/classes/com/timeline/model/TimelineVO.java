package com.timeline.model;

import java.sql.*;

public class TimelineVO implements java.io.Serializable{

	private String noteNo;
	private String noteTitle;
	private String noteContent;
	private String csNo;
	private Timestamp uploadTime;
	private Date recordDate;
	private String fileName;
	private String filePath;
	
	//defalut constructor
	public TimelineVO(){
		super(); //why use super()? because father class is Object?
	}
	
	//Attribute setter & getter
	public String getNoteNo() {
		return noteNo;
	}

	public void setNoteNo(String noteNo) {
		this.noteNo = noteNo;
	}

	public String getNoteTitle() {
		return noteTitle;
	}

	public void setNoteTitle(String noteTitle) {
		this.noteTitle = noteTitle;
	}

	public String getNoteContent() {
		return noteContent;
	}

	public void setNoteContent(String noteContent) {
		this.noteContent = noteContent;
	}

	public String getCsNo() {
		return csNo;
	}

	public void setCsNo(String csNo) {
		this.csNo = csNo;
	}



	public Timestamp getUploadTime() {
		return uploadTime;
	}

	public void setUploadTime(Timestamp uploadTime) {
		this.uploadTime = uploadTime;
	}

	public Date getRecordDate() {
		return recordDate;
	}

	public void setRecordDate(Date recordDate) {
		this.recordDate = recordDate;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}
	
	
}
