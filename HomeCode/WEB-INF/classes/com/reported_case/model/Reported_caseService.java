package com.reported_case.model;

import java.util.List;

public class Reported_caseService {
	private Reported_caseDAO_interface dao;

	public Reported_caseService() {
		dao = new Reported_caseDAO();
	}

	public Reported_caseVO addReported_case(String rpedMb, String rpedCase, String rpReason, String rpDesc) {

		Reported_caseVO reported_caseVO = new Reported_caseVO();

		reported_caseVO.setRpedMb(rpedMb);
		reported_caseVO.setRpedCase(rpedCase);
		reported_caseVO.setRpReason(rpReason);
		reported_caseVO.setRpDesc(rpDesc);

		dao.insert(reported_caseVO);

		return reported_caseVO;
	}

	public Reported_caseVO updateReported_case(String rpCaseNo, String rpedMb, String rpedCase, String rpReason,
			String rpDesc, String rpResult) {

		Reported_caseVO reported_caseVO = new Reported_caseVO();

		reported_caseVO.setRpCaseNo(rpCaseNo);
		reported_caseVO.setRpedMb(rpedMb);
		reported_caseVO.setRpedCase(rpedCase);
		reported_caseVO.setRpReason(rpReason);
		reported_caseVO.setRpDesc(rpDesc);
		reported_caseVO.setRpResult(rpResult);
		dao.update(reported_caseVO);

		return reported_caseVO;

	}

	public void deleteReported_case(String rpCaseNo) {
		dao.delete(rpCaseNo);
	}

	public Reported_caseVO getOneReported_case(String rpCaseNo) {
		return dao.findByPrimaryKey(rpCaseNo);
	}

	public List<Reported_caseVO> getAll() {
		return dao.getAll();
	}
}
