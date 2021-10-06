package com.applicant.model;

import java.util.List;

public class ApplicantService {
	ApplicantDAO_interface dao;

	public ApplicantService() {
		dao = new ApplicantDAO();
	}

	public ApplicantVO insert(String csNo, String freelancerNo, String hirerNo) {
		ApplicantVO applicantVO = new ApplicantVO();
		applicantVO.setCsNo(csNo);
		applicantVO.setFreelancerNo(freelancerNo);
		applicantVO.setHirerNo(hirerNo);

		dao.insert(applicantVO);
		return applicantVO;
	}

	public void delete(String csNO, String freelancerNo) {
		dao.delete(csNO, freelancerNo);
	}

	public ApplicantVO findByPrimaryKey(String csNo, String freelancerNo) {
		ApplicantVO applicantVO = new ApplicantVO();
		applicantVO.setCsNo(csNo);
		applicantVO.setFreelancerNo(freelancerNo);

		dao.findByPrimaryKey(csNo, freelancerNo);
		return applicantVO;
	}

	public List<ApplicantVO> getAll() {
		return dao.getAll();
	}

	public List<String> getFreelancerApplicantCase(String freelancerNo) {
		return dao.getFreelancerApplicantCase(freelancerNo);
	}

	public List<String> getOneCaseWhoApply(String csNo) {
		return dao.getOneCaseWhoApply(csNo);
	}

	public void deleteAllApply(String csNo) {
		dao.deleteAllApply(csNo);
	}
}
