package com.milestone.model;

import java.sql.Date;

public class MilestoneVO implements java.io.Serializable {
	
	private String milestoneNO; //
	private String milestoneName;
	private String csNo;
	private String milestoneState;
	private Date mileStartTime;
	private Date mileEndTime;
	private Date realEndTime;
	
	
	public String getMilestoneNO() {
		return milestoneNO;
	}
	public void setMilestoneNO(String milestoneNO) {
		this.milestoneNO = milestoneNO;
	}
	public String getMilestoneName() {
		return milestoneName;
	}
	public void setMilestoneName(String milestoneName) {
		this.milestoneName = milestoneName;
	}
	public String getCsNo() {
		return csNo;
	}
	public void setCsNo(String csNo) {
		this.csNo = csNo;
	}
	public String getMilestoneState() {
		return milestoneState;
	}
	public void setMilestoneState(String milestoneState) {
		this.milestoneState = milestoneState;
	}
	public Date getMileStartTime() {
		return mileStartTime;
	}
	public void setMileStartTime(Date mileStartTime) {
		this.mileStartTime = mileStartTime;
	}
	public Date getMileEndTime() {
		return mileEndTime;
	}
	public void setMileEndTime(Date mileEndTime) {
		this.mileEndTime = mileEndTime;
	}
	public Date getRealEndTime() {
		return realEndTime;
	}
	public void setRealEndTime(Date realEndTime) {
		this.realEndTime = realEndTime;
	}
	
	
	

}
