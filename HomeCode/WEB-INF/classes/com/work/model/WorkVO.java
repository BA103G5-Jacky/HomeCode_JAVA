package com.work.model;

import java.io.Serializable;

public class WorkVO implements Serializable {

			private String wkno;
			private String mbno;
			private byte[] wkpic;
			private String wkname;
			private String wkdecr;
			public String getWkno() {
				return wkno;
			}
			public void setWkno(String wkno) {
				this.wkno = wkno;
			}
			public String getMbno() {
				return mbno;
			}
			public void setMbno(String mbno) {
				this.mbno = mbno;
			}
			public byte[] getWkpic() {
				return wkpic;
			}
			public void setWkpic(byte[] wkpic) {
				this.wkpic = wkpic;
			}
			public String getWkname() {
				return wkname;
			}
			public void setWkname(String wkname) {
				this.wkname = wkname;
			}
			public String getWkdecr() {
				return wkdecr;
			}
			public void setWkdecr(String wkdecr) {
				this.wkdecr = wkdecr;
			}
			
}
