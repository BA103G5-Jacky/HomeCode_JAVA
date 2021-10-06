package com.function_privilege.model;

import java.util.*;

public interface Function_PrivilegeDAO_interface {
	public void insert(Function_PrivilegeVO function_privilegeVO);

	public void update(Function_PrivilegeVO function_privilegeVO);

	public void delete(String privilegeNo);

	public Function_PrivilegeVO findByPrimaryKey(String privilegeNo);

	public List<Function_PrivilegeVO> getAll();
}