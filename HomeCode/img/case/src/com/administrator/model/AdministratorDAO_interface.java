package com.administrator.model;

import java.util.*;

public interface AdministratorDAO_interface {
	public void insert(AdministratorVO administratorVO);

	public void update(AdministratorVO administratorVO);

	public void delete(String adminNo);

	public AdministratorVO findByPrimaryKey(String adminNo);

	public List<AdministratorVO> getAll();
}