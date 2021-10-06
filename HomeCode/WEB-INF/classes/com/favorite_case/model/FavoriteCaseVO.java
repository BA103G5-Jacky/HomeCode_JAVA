package com.favorite_case.model;

public class FavoriteCaseVO implements java.io.Serializable{
	
	private String csNo;
	private String mbNo;
	
	//defalut constructor
		public FavoriteCaseVO(){
			super(); //why use super()? because father class is Object?
		}

		public String getCsNo() {
			return csNo;
		}

		public void setCsNo(String csNo) {
			this.csNo = csNo;
		}

		public String getMbNo() {
			return mbNo;
		}

		public void setMbNo(String mbNo) {
			this.mbNo = mbNo;
		}
		
		

}
