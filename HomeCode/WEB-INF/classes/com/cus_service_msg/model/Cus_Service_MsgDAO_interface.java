package com.cus_service_msg.model;

import java.util.List;

public interface Cus_Service_MsgDAO_interface {
	public Integer insert(Cus_Service_MsgVO cus_Service_MsgVO);
    public void update(Cus_Service_MsgVO cus_Service_MsgVO);
    public void delete(String cusmesgNo);
    public Cus_Service_MsgVO findByPrimaryKey(String cusmesgNo);
    public List<Cus_Service_MsgVO> findByMbNo(String mbNo);
    public List<Cus_Service_MsgVO> findByResponseState(String responseState);
    public List<Cus_Service_MsgVO> getAll();

}
