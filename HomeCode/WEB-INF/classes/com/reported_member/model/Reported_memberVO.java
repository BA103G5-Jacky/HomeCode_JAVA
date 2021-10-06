package com.reported_member.model;

public class Reported_memberVO implements java.io.Serializable {
	private String reportNo;
	private String reportMbno;
	private String reportedMbno;
	private String reportReason;
	private String reportDesc;
	private String reportResult;

	public String getReportNo() {
		return reportNo;
	}

	public void setReportNo(String reportNo) {
		this.reportNo = reportNo;
	}

	public String getReportMbno() {
		return reportMbno;
	}

	public void setReportMbno(String reportMbno) {
		this.reportMbno = reportMbno;
	}

	public String getReportedMbno() {
		return reportedMbno;
	}

	public void setReportedMbno(String reportedMbno) {
		this.reportedMbno = reportedMbno;
	}

	public String getReportReason() {
		return reportReason;
	}

	public void setReportReason(String reportReason) {
		this.reportReason = reportReason;
	}

	public String getReportDesc() {
		return reportDesc;
	}

	public void setReportDesc(String reportDesc) {
		this.reportDesc = reportDesc;
	}

	public String getReportResult() {
		return reportResult;
	}

	public void setReportResult(String reportResult) {
		this.reportResult = reportResult;
	}

}