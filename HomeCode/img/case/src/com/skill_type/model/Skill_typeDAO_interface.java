package com.skill_type.model;

import java.util.*;

public interface Skill_typeDAO_interface {

	public void insert(Skill_typeVO skill_typeVO);

	public void update(Skill_typeVO skill_typeVO);

	public void delete(String skillNo);

	public Skill_typeVO findByPrimaryKey(String skillNo);

	public List<Skill_typeVO> getAll();

	public Skill_typeVO findBymbNo(String skillNo);

}
