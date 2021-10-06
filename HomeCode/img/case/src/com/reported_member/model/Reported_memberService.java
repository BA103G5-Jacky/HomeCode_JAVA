package com.reported_member.model;

import java.util.List;

public class Reported_memberService {
	private Reported_memberDAO_interface dao;

	public Reported_memberService() {
		dao = new Reported_memberDAO();
	}

	public Reported_memberVO addReported_member(String reportMbno, String reportedMbno, String reportReason,
			String reportDesc) {
		Reported_memberVO reported_memberVO = new Reported_memberVO();

		reported_memberVO.setReportMbno(reportMbno);
		reported_memberVO.setReportedMbno(reportedMbno);
		reported_memberVO.setReportReason(reportReason);
		reported_memberVO.setReportDesc(reportDesc);

		dao.insert(reported_memberVO);

		return reported_memberVO;
	}

	public Reported_memberVO updateReported_member(String reportNo, String reportMbno, String reportedMbno,
			String reportReason, String reportDesc, String reportresult) {
		Reported_memberVO reported_memberVO = new Reported_memberVO();

		reported_memberVO.setReportNo(reportNo);
		reported_memberVO.setReportMbno(reportMbno);
		reported_memberVO.setReportedMbno(reportedMbno);
		reported_memberVO.setReportReason(reportReason);
		reported_memberVO.setReportDesc(reportDesc);
		reported_memberVO.setReportResult(reportresult);
		dao.update(reported_memberVO);

		return reported_memberVO;

	}

	public void deleteReported_member(String reportNo) {
		dao.delete(reportNo);
	}

	public Reported_memberVO getOneReported_member(String reportNo) {
		return dao.findByPrimaryKey(reportNo);
	}

	public List<Reported_memberVO> getAll() {
		return dao.getAll();
	}
}
