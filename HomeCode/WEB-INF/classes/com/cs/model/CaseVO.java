package com.cs.model;


import java.sql.Date;
import java.sql.Timestamp;
public class CaseVO implements	java.io.Serializable {
	
	private String csNo;
	private String csName;
	private String csState;
	private String hirerNo;
	private String freelancerNo;
	private Integer schedule;
	private String csLoc;
	private String csPayState;
	private Integer csPayment;
	private Integer hirerScore;
	private Integer freelancerScore;
	private String hirerComment;
	private String freelancerComment;
	private String startDate;
	private String endDate;
	private Date realEndDate;
	private Timestamp postTime;
	private String csLevel;
	private String csDesc;
	private String finalSQL;
	
	public String getFinalSQL() {
		return finalSQL;
	}
	public void setFinalSQL(String finalSQL) {
		this.finalSQL = finalSQL;
	}
	public String getCsNo() {
		return csNo;
	}
	public void setCsNo(String csNo) {
		this.csNo = csNo;
	}
	public String getCsName() {
		return csName;
	}
	public void setCsName(String csName) {
		this.csName = csName;
	}
	public String getCsState() {
		return csState;
	}
	public void setCsState(String csState) {
		this.csState = csState;
	}
	public String getHirerNo() {
		return hirerNo;
	}
	public void setHirerNo(String hirerNo) {
		this.hirerNo = hirerNo;
	}
	public String getFreelancerNo() {
		return freelancerNo;
	}
	public void setFreelancerNo(String freelancerNo) {
		this.freelancerNo = freelancerNo;
	}
	public Integer getSchedule() {
		return schedule;
	}
	public void setSchedule(Integer schedule) {
		this.schedule = schedule;
	}
	public String getCsLoc() {
		return csLoc;
	}
	public void setCsLoc(String csLoc) {
		this.csLoc = csLoc;
	}
	public String getCsPayState() {
		return csPayState;
	}
	public void setCsPayState(String csPayState) {
		this.csPayState = csPayState;
	}
	public Integer getCsPayment() {
		return csPayment;
	}
	public void setCsPayment(Integer csPayment) {
		this.csPayment = csPayment;
	}
	public Integer getHirerScore() {
		return hirerScore;
	}
	public void setHirerScore(Integer hirerScore) {
		this.hirerScore = hirerScore;
	}
	public Integer getFreelancerScore() {
		return freelancerScore;
	}
	public void setFreelancerScore(Integer freelancerScore) {
		this.freelancerScore = freelancerScore;
	}
	public String getHirerComment() {
		return hirerComment;
	}
	public void setHirerComment(String hirerComment) {
		this.hirerComment = hirerComment;
	}
	public String getFreelancerComment() {
		return freelancerComment;
	}
	public void setFreelancerComment(String freelancerComment) {
		this.freelancerComment = freelancerComment;
	}
	public String getStartDate() {
		return startDate;
	}
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	public String getEndDate() {
		return endDate;
	}
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	public Date getRealEndDate() {
		return realEndDate;
	}
	public void setRealEndDate(Date realEndDate) {
		this.realEndDate = realEndDate;
	}
	public Timestamp getPostTime() {
		return postTime;
	}
	public void setPostTime(Timestamp postTime) {
		this.postTime = postTime;
	}
	public String getCsLevel() {
		return csLevel;
	}
	public void setCsLevel(String csLevel) {
		this.csLevel = csLevel;
	}
	public String getCsDesc() {
		return csDesc;
	}
	public void setCsDesc(String csDesc) {
		this.csDesc = csDesc;
	}


}
