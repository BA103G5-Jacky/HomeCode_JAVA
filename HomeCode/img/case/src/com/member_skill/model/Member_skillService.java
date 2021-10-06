package com.member_skill.model;
import java.util.List;


import com.skill_type.model.Skill_typeDAO_interface;
import com.skill_type.model.Skill_typeVO;
public class Member_skillService {

	private Member_skillDAO_interface dao;
	
	public Member_skillService(){
		dao= new Member_skillDAO();
	}
	public Member_skillVO addMember_skill(String skillNo,String mbNo){
		System.out.println("1111111");
		Member_skillVO member_skillVO=new Member_skillVO();
		member_skillVO.setSkillNo(skillNo);
		member_skillVO.setMbNo(mbNo);
		
		System.out.println("222222");
		dao.insert(member_skillVO);
		System.out.println("333333");
		return member_skillVO;
	}
	public List<String> searchmbno(String mbNo){
		return dao.getmbNo(mbNo);
	}
	
	public void deleteOneMbSkill(String mbNo) {
		// TODO Auto-generated method stub
		dao.deleteOneMbSkill(mbNo);
	}
	
	
	}

