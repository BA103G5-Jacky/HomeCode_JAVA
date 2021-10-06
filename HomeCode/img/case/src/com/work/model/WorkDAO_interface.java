package com.work.model;

import java.util.*;



public interface WorkDAO_interface {
	public void insert(WorkVO workVO);
    public void update(WorkVO workVO);
    public void delete(String wkno);
    public WorkVO findByPrimaryKey(String wkno);
    public List<WorkVO> getAll();
//  public List<EmpVO> getAll(Map<String, String[]> map); 
    public List<WorkVO> findByMbNo(String mbNo);
}
