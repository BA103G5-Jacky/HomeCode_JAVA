package com.administrator.model;

public class AdministratorVO implements java.io.Serializable {

	private String adminNo;
	private String adminName;
	private String adminID;
	private String adminPsw;
	private String adminPosition;

	public String getAdminNo() {
		return adminNo;
	}

	public void setAdminNo(String adminNo) {
		this.adminNo = adminNo;
	}

	public String getAdminName() {
		return adminName;
	}

	public void setAdminName(String adminName) {
		this.adminName = adminName;
	}

	public String getAdminID() {
		return adminID;
	}

	public void setAdminID(String adminID) {
		this.adminID = adminID;
	}

	public String getAdminPsw() {
		return adminPsw;
	}

	public void setAdminPsw(String adminPsw) {
		this.adminPsw = adminPsw;
	}

	public String getAdminPosition() {
		return adminPosition;
	}

	public void setAdminPosition(String adminPosition) {
		this.adminPosition = adminPosition;
	}

}
