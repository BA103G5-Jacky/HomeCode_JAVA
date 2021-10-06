package com.administrator_privilege.model;

import java.util.*;

public interface Administrator_PrivilegeDAO_interface {
	public void insert(Administrator_PrivilegeVO administrator_privilegeVO);

	public void update(Administrator_PrivilegeVO administrator_privilegeVO);

	public void delete(String privilegeNo, String adminNo);

	public Administrator_PrivilegeVO findByPrimaryKey(String privilegeNo, String adminNo);

	public List<Administrator_PrivilegeVO> getAll();
}