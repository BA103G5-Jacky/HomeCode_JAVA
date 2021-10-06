package com.cs.model;

import java.util.List;
import java.util.Map;

public class CaseService {

	private CaseDAO_interface dao;

	public CaseService() {
		dao = new CaseDAO();
	}
	
	public CaseVO addCase(String csName, String hirerNo, String csLoc, Integer csPayment, String startDate,
			String endDate, String csLevel, String csDesc) {
		CaseVO caseVO = new CaseVO();

		caseVO.setCsName(csName);
		caseVO.setHirerNo(hirerNo);
		caseVO.setCsLoc(csLoc);
		caseVO.setCsPayment(csPayment);
		caseVO.setStartDate(startDate);
		caseVO.setEndDate(endDate);
		caseVO.setCsLevel(csLevel);
		caseVO.setCsDesc(csDesc);

		String csNo = dao.insert(caseVO);
		caseVO.setCsNo(csNo);
		return caseVO;

	}
	
	
	
	public CaseVO updateCase(String csName, String csLoc, Integer csPayment, String startDate, String endDate,
			String csLevel, String csDesc, String csNo) {

		CaseVO caseVO = new CaseVO();
		caseVO.setCsNo(csNo);
		caseVO.setCsName(csName);
		caseVO.setCsLoc(csLoc);
		caseVO.setCsPayment(csPayment);
		caseVO.setStartDate(startDate);
		caseVO.setEndDate(endDate);
		caseVO.setCsLevel(csLevel);
		caseVO.setCsDesc(csDesc);

		dao.updateByMb(caseVO);
		return caseVO;
	}
	
	public CaseVO updateFreelancer(String freelancerNo, String csNo){
		CaseVO caseVO = new CaseVO();
		caseVO.setFreelancerNo(freelancerNo);
		caseVO.setCsNo(csNo);
		dao.updateFreelancer(caseVO);
		
		return caseVO;
	}
	
	public CaseVO updateByAdmin(String csState, String csPayState, String csNo){
		CaseVO caseVO = new CaseVO();
		caseVO.setCsState(csState);
		caseVO.setCsPayState(csPayState);
		caseVO.setCsNo(csNo);
		
		dao.updateByAdmin(caseVO);
		return caseVO;
	}
	
	public CaseVO updateSchedule(Integer schedule, String csNo){
		CaseVO caseVO = new CaseVO();
		caseVO.setSchedule(schedule);
		caseVO.setCsNo(csNo);
		
		dao.updateSchedule(caseVO);
		return caseVO;
	}
	
	public CaseVO updateHireScoreComment(Integer hirerScore, String hirerComment, String csNo){
		CaseVO caseVO = new CaseVO();
		caseVO.setHirerScore(hirerScore);
		caseVO.setHirerComment(hirerComment);
		caseVO.setCsNo(csNo);
		
		dao.updateHireScoreComment(caseVO);
		return caseVO;
	}
	
	public CaseVO updateFreelancerScoreComment(Integer freelancerScore, String freelancerComment, String csNo){
		CaseVO caseVO = new CaseVO();
		caseVO.setFreelancerScore(freelancerScore);
		caseVO.setFreelancerComment(freelancerComment);
		caseVO.setCsNo(csNo);
		
		dao.updateFreelancerScoreComment(caseVO);
		return caseVO;
	}
	
	public void delete(String csNo){
		dao.delete(csNo);
	}

	public CaseVO getOneCase(String csNo) {
		return dao.findByPrimaryKey(csNo);
	}
	
	public List<CaseVO> findByHirerNo(String hirerNo, String csState){
		return dao.findByHirerNo(hirerNo, csState);
	}
	
	public List<CaseVO> findByFreelancerNo(String freelancerNo, String csState) {
		return dao.findByFreelancerNo(freelancerNo, csState);
	}
	
	public List<CaseVO> findByCsPayment(Integer csPayment){
		return dao.findByCsPayment(csPayment);
	}
	
	public List<CaseVO> findByCsLevel(String csLevel) {
		return dao.findByCsLevel(csLevel);
	}
	
	public List<CaseVO> getAll() {
		return dao.getAll();
	}
	
	public List<CaseVO> getAll2() {
		return dao.getAll2();
	}

	public Double getMbFreelancerScoreAvg(String mbNo){
		return dao.getMbFreelancerScoreAvg(mbNo);
	}
	
	public List<CaseVO> getMbSuccessCase(String mbNo){
		return dao.getMbSuccessCase(mbNo);
	}
	
	public void updateCsState(String csState, String csNo){
		CaseVO caseVO = new CaseVO();
		caseVO.setCsState(csState);
		caseVO.setCsNo(csNo);
		
		dao.updateCsState(caseVO);
	}
	
	public Double getMbHirerScoreAvg(String mbNo){
		return dao.getMbHirerScoreAvg(mbNo);
	}
	
	public Integer getPostCaseTimes(String mbNo){
		return dao.getPostCaseTimes(mbNo);
	}
	
	public List<CaseVO> getAll(Map<String, String[]> map) {
		return dao.getAll(map);
	}
	
	public List<CaseVO> changeOrderByMoney_LowToHigh(String SQL){
		return  dao.changeOrderByMoney_LowToHigh(SQL);
	}
	   
	  
	public List<CaseVO> changeOrderByMoney_HighToLow(String SQL){
		return  dao.changeOrderByMoney_HighToLow(SQL);
	}

}
