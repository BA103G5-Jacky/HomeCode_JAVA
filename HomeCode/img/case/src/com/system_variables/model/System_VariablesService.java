package com.system_variables.model;

import java.util.List;

import com.system_variables.model.System_VariablesDAO;
import com.system_variables.model.System_VariablesDAO_interface;
import com.system_variables.model.System_VariablesVO;

public class System_VariablesService {

	private System_VariablesDAO_interface dao;

	public System_VariablesService() {
		dao = new System_VariablesDAO();
	}

	public System_VariablesVO addSystem_Variables(String svname, Integer svValue) {

		System_VariablesVO system_variablesVO = new System_VariablesVO();
		system_variablesVO.setSvname(svname);
		system_variablesVO.setSvValue(svValue);

		dao.insert(system_variablesVO);

		return system_variablesVO;
	}

	public System_VariablesVO updateSystem_Variables(String svNo, String svname, Integer svValue) {

		System_VariablesVO system_variablesVO = new System_VariablesVO();

		system_variablesVO.setSvNo(svNo);
		system_variablesVO.setSvname(svname);
		system_variablesVO.setSvValue(svValue);
		dao.update(system_variablesVO);

		return system_variablesVO;
	}

	public void deleteAdministrator_Privilege(String svNo) {
		dao.delete(svNo);
	}

	public System_VariablesVO getOneAdministrator_Privilege(String svNo) {
		return dao.findByPrimaryKey(svNo);
	}

	public List<System_VariablesVO> getAll() {
		return dao.getAll();
	}
}
