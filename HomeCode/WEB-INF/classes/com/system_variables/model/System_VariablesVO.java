package com.system_variables.model;

public class System_VariablesVO implements java.io.Serializable {
	private String svNo;
	private String svname;
	private Integer svValue;

	public String getSvNo() {
		return svNo;
	}

	public void setSvNo(String svNo) {
		this.svNo = svNo;
	}

	public String getSvname() {
		return svname;
	}

	public void setSvname(String svname) {
		this.svname = svname;
	}

	public Integer getSvValue() {
		return svValue;
	}

	public void setSvValue(Integer svValue) {
		this.svValue = svValue;
	}

}
