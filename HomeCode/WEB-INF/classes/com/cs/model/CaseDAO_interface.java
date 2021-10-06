package com.cs.model;

import java.util.List;
import java.util.Map;


public interface CaseDAO_interface {
	public String insert(CaseVO caseVO);
	
    public void updateByMb(CaseVO caseVO);
    public void updateFreelancer(CaseVO caseVO);
    public void updateByAdmin(CaseVO caseVO);    
    public void updateSchedule(CaseVO caseVO);
    public void updateHireScoreComment(CaseVO caseVO);
    public void updateFreelancerScoreComment(CaseVO caseVO);
    public void updateCsPayState(String csNo);
    
    public void delete(String csNo);
    
    public CaseVO findByPrimaryKey(String csNo);
    public List<CaseVO> findByHirerNo(String hirerNo, String csState);
    public List<CaseVO> findByFreelancerNo(String freelancerNo, String csState);
    public List<CaseVO> findByCsPayment(Integer csPayment);
    public List<CaseVO> findByCsLevel(String csLevel);
    public List<CaseVO> getAll();
    
    public List<CaseVO> getAll2();
    public Double getMbFreelancerScoreAvg(String mbNo);
    public Double getMbHirerScoreAvg(String mbNo);
    
    //0930 
    public List<CaseVO> getMbSuccessCase(String mbNo);	//取得接案者已完成的所有案件
    public void updateCsState(CaseVO caseVO);
    
    public Integer getPostCaseTimes(String mbNo);
	
    //萬用複合查詢(傳入參數型態Map)(回傳 List)
    public List<CaseVO> getAll(Map<String, String[]> map);
    public List<CaseVO> changeOrderByMoney_LowToHigh(String SQL);
    public List<CaseVO> changeOrderByMoney_HighToLow(String SQL);
}
