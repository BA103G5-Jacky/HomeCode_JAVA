package com.administrator.model;

import java.util.List;

public class AdministratorService {

	private AdministratorDAO_interface dao;

	public AdministratorService() {
		dao = new AdministratorDAO();
	}

	public AdministratorVO addAdministrator(String adminName, String adminID, String adminPsw, String adminPosition) {

		AdministratorVO administratorVO = new AdministratorVO();

		administratorVO.setAdminName(adminName);
		administratorVO.setAdminID(adminID);
		administratorVO.setAdminPsw(adminPsw);
		administratorVO.setAdminPosition(adminPosition);

		dao.insert(administratorVO);

		return administratorVO;
	}

	public AdministratorVO updateAdministrator(String adminNo, String adminName, String adminID, String adminPsw,
			String adminPosition) {

		AdministratorVO administratorVO = new AdministratorVO();

		administratorVO.setAdminNo(adminNo);
		administratorVO.setAdminName(adminName);
		administratorVO.setAdminID(adminID);
		administratorVO.setAdminPsw(adminPsw);
		administratorVO.setAdminPosition(adminPosition);
		dao.update(administratorVO);

		return administratorVO;
	}

	public void deleteAdministrator(String adminNo) {
		dao.delete(adminNo);
	}

	public AdministratorVO getOneAdministrator(String adminNo) {
		return dao.findByPrimaryKey(adminNo);
	}

	public List<AdministratorVO> getAll() {
		return dao.getAll();
	}
}