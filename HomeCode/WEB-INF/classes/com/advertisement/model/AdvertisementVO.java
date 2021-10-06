package com.advertisement.model;

import java.sql.Date;

public class AdvertisementVO implements java.io.Serializable {

	private String adcontent;
	private byte[] adimg;
	private Date aduploaddate;
	private String adno;
	private String adname;

	public String getAdno() {
		return adno;
	}

	public void setAdno(String adno) {
		this.adno = adno;
	}

	public String getAdname() {
		return adname;
	}

	public void setAdname(String adname) {
		this.adname = adname;
	}

	public String getAdcontent() {
		return adcontent;
	}

	public void setAdcontent(String adcontent) {
		this.adcontent = adcontent;
	}

	public byte[] getAdimg() {
		return adimg;
	}

	public void setAdimg(byte[] adimg) {
		this.adimg = adimg;
	}

	public Date getAduploaddate() {
		return aduploaddate;
	}

	public void setAduploaddate(Date aduploaddate) {
		this.aduploaddate = aduploaddate;
	}

}
