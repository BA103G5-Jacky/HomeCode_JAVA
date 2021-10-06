package com.timeline.model;

import java.util.*;

public interface TimelineDAO_interface {
	
	public void insert_withFile(TimelineVO timelineVO);
	public void update(TimelineVO timelineVO);
	public void delete(String noteNo);
	/*check*/public List<TimelineVO> getAll();
	/*check*/public List<TimelineVO> findByOneCase(String csNO);

}
