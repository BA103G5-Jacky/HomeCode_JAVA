package com.member.model;

import java.sql.Date;

public class MemberVO implements java.io.Serializable{
	private String mbNo;
	private String mbLstName;
	private String mbFstName;
	private Integer csTimes;
	private Integer csSuccessTimes;
	private Integer csStopTimes;
	private String comName;
	private String eMail;
	private String mbPw;
	private String mbLoc;
	private String mbAddress;
	private String phone;
	private String mbUserName;
	private String secQuestion;
	private String secAnswer;
	private String suspensionStatus;
	private byte[] portrait;
	private Date sinceDate;
	private String mbIntroduce;
	
	
	public String getMbNo() {
		return mbNo;
	}
	public void setMbNo(String mbNo) {
		this.mbNo = mbNo;
	}
	public String getMbLstName() {
		return mbLstName;
	}
	public void setMbLstName(String mbLstName) {
		this.mbLstName = mbLstName;
	}
	public String getMbFstName() {
		return mbFstName;
	}
	public void setMbFstName(String mbFstName) {
		this.mbFstName = mbFstName;
	}
	public Integer getCsTimes() {
		return csTimes;
	}
	public void setCsTimes(Integer csTimes) {
		this.csTimes = csTimes;
	}
	public Integer getCsSuccessTimes() {
		return csSuccessTimes;
	}
	public void setCsSuccessTimes(Integer csSuccessTimes) {
		this.csSuccessTimes = csSuccessTimes;
	}
	public Integer getCsStopTimes() {
		return csStopTimes;
	}
	public void setCsStopTimes(Integer csStopTimes) {
		this.csStopTimes = csStopTimes;
	}
	public String getComName() {
		return comName;
	}
	public void setComName(String comName) {
		this.comName = comName;
	}
	public String geteMail() {
		return eMail;
	}
	public void seteMail(String eMail) {
		this.eMail = eMail;
	}
	public String getMbPw() {
		return mbPw;
	}
	public void setMbPw(String mbPw) {
		this.mbPw = mbPw;
	}
	public String getMbLoc() {
		return mbLoc;
	}
	public void setMbLoc(String mbLoc) {
		this.mbLoc = mbLoc;
	}
	public String getMbAddress() {
		return mbAddress;
	}
	public void setMbAddress(String mbAddress) {
		this.mbAddress = mbAddress;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getMbUserName() {
		return mbUserName;
	}
	public void setMbUserName(String mbUserName) {
		this.mbUserName = mbUserName;
	}
	public String getSecQuestion() {
		return secQuestion;
	}
	public void setSecQuestion(String secQuestion) {
		this.secQuestion = secQuestion;
	}
	public String getSecAnswer() {
		return secAnswer;
	}
	public void setSecAnswer(String secAnswer) {
		this.secAnswer = secAnswer;
	}
	public String getSuspensionStatus() {
		return suspensionStatus;
	}
	public void setSuspensionStatus(String suspensionStatus) {
		this.suspensionStatus = suspensionStatus;
	}
	public byte[] getPortrait() {
		return portrait;
	}
	public void setPortrait(byte[] portrait) {
		this.portrait = portrait;
	}
	public Date getSinceDate() {
		return sinceDate;
	}
	public void setSinceDate(Date sinceDate) {
		this.sinceDate = sinceDate;
	}
	public String getMbIntroduce() {
		return mbIntroduce;
	}
	public void setMbIntroduce(String mbIntroduce) {
		this.mbIntroduce = mbIntroduce;
	}
	
	
}
