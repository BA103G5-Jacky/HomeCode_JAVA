package com.message.model;

import java.sql.Date;

public class MessageVO implements java.io.Serializable{
	
	private String mesgNo;
	private String mailMbNo;
	private String mailRecvMbNo;
	private String mesgTitle;
	private String mesgContent;
	private String readStatus;
	private Date mailTime;
	private String isDeleteByMailMb;
	private String isDeleteByRecvMb;
	
	
	public String getMesgNo() {
		return mesgNo;
	}
	public void setMesgNo(String mesgNo) {
		this.mesgNo = mesgNo;
	}
	public String getMailMbNo() {
		return mailMbNo;
	}
	public void setMailMbNo(String mailMbNo) {
		this.mailMbNo = mailMbNo;
	}
	public String getMailRecvMbNo() {
		return mailRecvMbNo;
	}
	public void setMailRecvMbNo(String mailRecvMbNo) {
		this.mailRecvMbNo = mailRecvMbNo;
	}
	public String getMesgTitle() {
		return mesgTitle;
	}
	public void setMesgTitle(String mesgTitle) {
		this.mesgTitle = mesgTitle;
	}
	public String getMesgContent() {
		return mesgContent;
	}
	public void setMesgContent(String mesgContent) {
		this.mesgContent = mesgContent;
	}
	public String getReadStatus() {
		return readStatus;
	}
	public void setReadStatus(String readStatus) {
		this.readStatus = readStatus;
	}
	public Date getMailTime() {
		return mailTime;
	}
	public void setMailTime(Date mailTime) {
		this.mailTime = mailTime;
	}
	public String getIsDeleteByMailMb() {
		return isDeleteByMailMb;
	}
	public void setIsDeleteByMailMb(String isDeleteByMailMb) {
		this.isDeleteByMailMb = isDeleteByMailMb;
	}
	public String getIsDeleteByRecvMb() {
		return isDeleteByRecvMb;
	}
	public void setIsDeleteByRecvMb(String isDeleteByRecvMb) {
		this.isDeleteByRecvMb = isDeleteByRecvMb;
	}
	
	
}
