package com.reported_case.model;

import java.util.*;

public interface Reported_caseDAO_interface {
	public void insert(Reported_caseVO reported_caseVO);

	public void update(Reported_caseVO reported_caseVO);

	public void delete(String rpCaseNo);

	public Reported_caseVO findByPrimaryKey(String rpCaseNo);

	public List<Reported_caseVO> getAll();
	// public List<EmpVO> getAll(Map<String, String[]> map);
}
