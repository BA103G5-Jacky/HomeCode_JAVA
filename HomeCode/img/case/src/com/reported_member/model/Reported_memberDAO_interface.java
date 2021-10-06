package com.reported_member.model;

import java.util.*;

public interface Reported_memberDAO_interface {

	public void insert(Reported_memberVO reported_memberVO);

	public void update(Reported_memberVO reported_memberVO);

	public void delete(String reportNo);

	public Reported_memberVO findByPrimaryKey(String reportNo);

	public List<Reported_memberVO> getAll();

}
