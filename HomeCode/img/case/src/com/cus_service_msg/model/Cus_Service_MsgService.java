package com.cus_service_msg.model;

public class Cus_Service_MsgService {

	
	private Cus_Service_MsgDAO_interface dao;

	public Cus_Service_MsgService() {
		dao = new Cus_Service_MsgDAO();
	}
	
	
	public Integer insert(Cus_Service_MsgVO cus_Service_MsgVO){
		return dao.insert(cus_Service_MsgVO);
	}
}
