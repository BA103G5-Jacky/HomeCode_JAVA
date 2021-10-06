package com.administrator_privilege.model;

import java.util.List;

public class Administrator_PrivilegeService {

	private Administrator_PrivilegeDAO_interface dao;

	public Administrator_PrivilegeService() {
		dao = new Administrator_PrivilegeDAO();
	}

	public Administrator_PrivilegeVO addAdministrator_Privilege(String privilegeNo, String adminNo) {

		Administrator_PrivilegeVO administrator_privilegeVO = new Administrator_PrivilegeVO();

		administrator_privilegeVO.setPrivilegeNo(privilegeNo);
		administrator_privilegeVO.setAdminNo(adminNo);

		dao.insert(administrator_privilegeVO);

		return administrator_privilegeVO;
	}

	public Administrator_PrivilegeVO updateAdministrator_Privilege(String privilegeNo, String adminNo) {

		Administrator_PrivilegeVO administrator_privilegeVO = new Administrator_PrivilegeVO();

		administrator_privilegeVO.setPrivilegeNo(privilegeNo);
		administrator_privilegeVO.setAdminNo(adminNo);
		dao.update(administrator_privilegeVO);

		return administrator_privilegeVO;
	}

	public void deleteAdministrator_Privilege(String privilegeNo, String adminNo) {
		dao.delete(privilegeNo, adminNo);
	}

	public Administrator_PrivilegeVO getOneAdministrator_Privilege(String privilegeNo, String adminNo) {
		return dao.findByPrimaryKey(privilegeNo, adminNo);
	}

	public List<Administrator_PrivilegeVO> getAll() {
		return dao.getAll();
	}
}
