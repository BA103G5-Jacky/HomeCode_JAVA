package com.skill_type.model;

import java.util.List;

public class Skill_typeService {

	private Skill_typeDAO_interface dao;

	public Skill_typeService() {
		dao = new Skill_typeDAO();

	}

	public Skill_typeVO addSkill_type(String skillNo, String skillName, String skillDesc) {
		Skill_typeVO skill_typeVO = new Skill_typeVO();

		skill_typeVO.setSkillNo(skillNo);
		skill_typeVO.setSkillName(skillName);
		skill_typeVO.setSkillDesc(skillDesc);
		dao.insert(skill_typeVO);

		return skill_typeVO;
	}

	public Skill_typeVO updateSkill_type(String skillNo, String skillName, String skillDesc) {
		Skill_typeVO skill_typeVO = new Skill_typeVO();

		skill_typeVO.setSkillNo(skillNo);
		skill_typeVO.setSkillName(skillName);
		skill_typeVO.setSkillDesc(skillDesc);
		dao.update(skill_typeVO);

		return skill_typeVO;
	}

	public void deleteSkill_type(String skillNo) {
		dao.delete(skillNo);
	}

	public Skill_typeVO getOneSkill_type(String skillNo) {
		return dao.findByPrimaryKey(skillNo);
	}

	public List<Skill_typeVO> getAll() {
		return dao.getAll();
	}

	public Skill_typeVO getMbno(String skillNo) {
		return dao.findBymbNo(skillNo);
	}
}
