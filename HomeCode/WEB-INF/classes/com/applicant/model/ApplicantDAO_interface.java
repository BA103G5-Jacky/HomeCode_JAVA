package com.applicant.model;

import java.util.*;

public interface ApplicantDAO_interface {
	
	public void insert(ApplicantVO applicantVO);

	public void delete(String csNO, String freelancerNo);

	public ApplicantVO findByPrimaryKey(String csNO, String freelancerNo);

	public List<ApplicantVO> getAll();

	public List<String> getFreelancerApplicantCase(String freelancerNo);

	public List<String> getOneCaseWhoApply(String csNo);

	public void deleteAllApply(String csNo);
	

}
