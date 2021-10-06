package com.milestone.model;

import java.util.*;

public interface MilestoneDAO_interface {
	
	//public void insert(String milestoneNo, String milestoneName, String csNo, String milestoneState, Date mileStartTime, Date mileEndTime, Date realEndTime);
	public void insert(MilestoneVO milestoneVO);
	public void update(MilestoneVO milestoneVO);
	
	public void update_agree(MilestoneVO milestoneVO);
	public List<MilestoneVO> getOneCase(String csNo);
	
	public void delete(String milestoneNo);
	
	public MilestoneVO findByPrimaryKey(String milestoneNO);
	
	public List<MilestoneVO> getAll();
	
	public List<MilestoneVO> getMileStoneByCs(String csNo);
	public List<MilestoneVO> getMileStoneByCs_Reverse(String csNo);

	 
}
