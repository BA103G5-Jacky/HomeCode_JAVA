package com.case_type.model;

import java.util.List;

public class Case_typeService {
	private Case_typeDAO_interface dao;

	public Case_typeService() {
		dao = new Case_typeDAO();
	}

	public Case_typeVO addCase_type(String csNo, String skillNo) {
		Case_typeVO case_typeVO = new Case_typeVO();

		case_typeVO.setCsNo(csNo);
		case_typeVO.setSkillNo(skillNo);
		dao.insert(case_typeVO);

		return case_typeVO;
	}

	public Case_typeVO updateCase_type(String csNo, String skillNo) {
		Case_typeVO case_typeVO = new Case_typeVO();

		case_typeVO.setCsNo(csNo);
		case_typeVO.setSkillNo(skillNo);
		dao.update(case_typeVO);

		return case_typeVO;
	}

	public void deleteCase_type(String csNo) {
		dao.delete(csNo);
	}

	public List<Case_typeVO> getOneCase_type(String csNo) {
		return dao.findByPrimaryKey(csNo);
	}

	public List<Case_typeVO> getAll() {
		return dao.getAll();
	}
}