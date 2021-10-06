package com.system_variables.model;

import java.util.*;

public interface System_VariablesDAO_interface {
	public void insert(System_VariablesVO system_variablesVO);

	public void update(System_VariablesVO system_variablesVO);

	public void delete(String svNo);

	public System_VariablesVO findByPrimaryKey(String svNo);

	public List<System_VariablesVO> getAll();
}