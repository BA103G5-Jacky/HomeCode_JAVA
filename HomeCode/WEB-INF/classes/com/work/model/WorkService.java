package com.work.model;

import java.util.List;

public class WorkService {
	
	private WorkDAO_interface dao;
	
	public WorkService(){
		dao= new WorkDAO();
	}
	
	public List<WorkVO>getAll(){
		return dao.getAll();
	}
	public WorkVO getOneWork(String wkno){
		return dao.findByPrimaryKey(wkno);
		
	}
	
	public void insert(WorkVO workVO){
		dao.insert(workVO);
		
	}
	
	public void update(WorkVO workVO){
		dao.update(workVO);
		
	}
	
	public void delete(String wkno){
		dao.delete(wkno);
		
	}
	
	public List<WorkVO> findByMbNo(String mbNo){
		return dao.findByMbNo(mbNo);
	}
}
