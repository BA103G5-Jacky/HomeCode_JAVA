package com.cus_service_msg.model;

import java.sql.Date;

public class Cus_Service_MsgVO implements java.io.Serializable{
	private String CusmesgNo;
	private String mbNo;
	private String mesgTitle;
	private String mesgContent;
	private String responseState;
	private Date mailTime;
	
	
	public String getCusmesgNo() {
		return CusmesgNo;
	}
	public void setCusmesgNo(String cusmesgNo) {
		CusmesgNo = cusmesgNo;
	}
	public String getMbNo() {
		return mbNo;
	}
	public void setMbNo(String mbNo) {
		this.mbNo = mbNo;
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
	public String getResponseState() {
		return responseState;
	}
	public void setResponseState(String responceState) {
		this.responseState = responceState;
	}
	public Date getMailTime() {
		return mailTime;
	}
	public void setMailTime(Date mailTime) {
		this.mailTime = mailTime;
	}
	
	
}
