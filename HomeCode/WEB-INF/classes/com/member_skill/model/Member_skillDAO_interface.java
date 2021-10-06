package com.member_skill.model;

import java.util.List;
import java.util.Set;

public interface Member_skillDAO_interface {

	public void insert(Member_skillVO member_skillVO);
	
	public void update(Member_skillVO member_skillVO);

	// public void update(ApplicantVO applicantVO);
	public void delete(String skillNo, String mbNo);
	public void deleteOneMbSkill(String mbNo);

	public Member_skillVO findByPrimaryKey(String skillNo, String mbNo);

	public List<Member_skillVO> getAll();
	
	public List<String> getmbNo(String mbNo);
}
