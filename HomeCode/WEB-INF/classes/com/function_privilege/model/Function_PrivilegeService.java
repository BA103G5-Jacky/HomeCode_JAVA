package com.function_privilege.model;

import java.util.List;

import com.function_privilege.model.Function_PrivilegeDAO;
import com.function_privilege.model.Function_PrivilegeDAO_interface;
import com.function_privilege.model.Function_PrivilegeVO;

public class Function_PrivilegeService {

	private Function_PrivilegeDAO_interface dao;

	public Function_PrivilegeService() {
		dao = new Function_PrivilegeDAO();
	}

	public Function_PrivilegeVO addFunction_Privilege(String privilegeName) {

		Function_PrivilegeVO function_privilegeVO = new Function_PrivilegeVO();
		function_privilegeVO.setPrivilegeName(privilegeName);

		dao.insert(function_privilegeVO);

		return function_privilegeVO;
	}

	public Function_PrivilegeVO updatefunction_privilege(String privilegeNo, String privilegeName) {

		Function_PrivilegeVO function_privilegeVO = new Function_PrivilegeVO();

		function_privilegeVO.setPrivilegeNo(privilegeNo);
		function_privilegeVO.setPrivilegeName(privilegeName);
		dao.update(function_privilegeVO);

		return function_privilegeVO;
	}

	public void deleteAdministrator_Privilege(String privilegeNo) {
		dao.delete(privilegeNo);
	}

	public Function_PrivilegeVO getOneAdministrator_Privilege(String privilegeNo) {
		return dao.findByPrimaryKey(privilegeNo);
	}

	public List<Function_PrivilegeVO> getAll() {
		return dao.getAll();
	}
}