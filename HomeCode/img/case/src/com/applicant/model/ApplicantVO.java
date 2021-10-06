package com.applicant.model;

public class ApplicantVO implements java.io.Serializable{
	
	private String csNo; //fk, pk
	private String freelancerNo; //fk, pk
	private String hirerNo;
	
	//不帶參數的預設建構子
	public ApplicantVO(){
		super(); //繼承父類別Object的參數
	}
	
	public String getCsNo() {
		return csNo;
	}
	public void setCsNo(String csNo) {
		this.csNo = csNo;
	}
	public String getFreelancerNo() {
		return freelancerNo;
	}
	public void setFreelancerNo(String freelancerNo) {
		this.freelancerNo = freelancerNo;
	}
	public String getHirerNo() {
		return hirerNo;
	}
	public void setHirerNo(String hirerNo) {
		this.hirerNo = hirerNo;
	}
	
	

}
