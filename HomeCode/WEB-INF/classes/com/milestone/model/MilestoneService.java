package com.milestone.model;

import java.sql.Date;
//pre-work
import java.util.List;


public class MilestoneService {
	
	//建立介面物件 提供MilestoneDAO的多形創造
	//父介面來宣告的話，可以有比較大的彈性，不一定要指定的類別（多形的重要概念）
	private MilestoneDAO_interface dao;
	
	//why建立建構子..因為在呼叫的時候就產生一個dao物件
	//S:在呼叫工頭的時候，就可以把工人一併生出來
	//service 跟 dao 是生命共同體，所以要在預設建構子者邊就產出
	public MilestoneService(){
		
		dao = new MilestoneDAO();
	}
	//dao是工人
	//service 行為：
	//controller接收到請求(要修改的員工姓名＆薪水...)=>呼叫service(傳遞要修改的資訊，"打包到VO裏頭")
	//=>
	//按照每一個工人的特定能力去分派功能給他
	
	//addMileston:	準備材料給工人1的地方
	public MilestoneVO addMilestone(String milestoneName, String csNo, String milestoneState, Date mileStartTime, Date mileEndTime, Date realEndTime) {
		
		//準備裝載材料的卡車
		MilestoneVO milestoneVO = new MilestoneVO();

		//工頭把工人1要的材料 依依裝上去卡車
		milestoneVO.setMilestoneName(milestoneName);
		milestoneVO.setCsNo(csNo);
		milestoneVO.setMilestoneState(milestoneState);
		milestoneVO.setMileStartTime(mileStartTime);
		milestoneVO.setMileEndTime(mileEndTime);
		milestoneVO.setRealEndTime(realEndTime);
		
		//工人1實現施工的地方
		dao.insert(milestoneVO);
		
		//工人把做好的房間給工頭看
		return milestoneVO;
	}
	//updateMilestone:準備材料給工人2的地方
	public MilestoneVO updateMilestone(String milestoneName, String csNo, String milestoneState, Date mileStartTime, Date mileEndTime,  Date realEndTime, String milestoneNo ) {
		
		//準備裝載材料的卡車
		MilestoneVO milestoneVO = new MilestoneVO();

		//工頭把工人2要的材料 依依裝上去卡車
		milestoneVO.setMilestoneName(milestoneName);
		milestoneVO.setCsNo(csNo);
		milestoneVO.setMilestoneState(milestoneState);
		milestoneVO.setMileStartTime(mileStartTime);
		milestoneVO.setMileEndTime(mileEndTime);
		milestoneVO.setRealEndTime(realEndTime);
		milestoneVO.setMilestoneNO(milestoneNo);
		
		//工人2實現施工的地方
		dao.insert(milestoneVO);
		
		//工人把做好的房間給工頭看
		return milestoneVO;
	}

	public MilestoneVO update_agreeMilestone(String milestoneState,String milestoneNo){
		//準備裝載材料的卡車
				MilestoneVO milestoneVO = new MilestoneVO();
				
				milestoneVO.setMilestoneState(milestoneState);
				milestoneVO.setMilestoneNO(milestoneNo);
				
				//工人2實現施工的地方
				dao.update_agree(milestoneVO);
				
				return milestoneVO;
	}
	
 	public void deleteEmp(String milestoneNo) {
		dao.delete(milestoneNo);
	}

	public MilestoneVO getOneMilestone(String milestoneNo) {
		return dao.findByPrimaryKey(milestoneNo);
	}

	public List<MilestoneVO> getAll() {
		return dao.getAll();
	}
	
	public List<MilestoneVO> getMileStoneByCs(String csNo){
		
		
		return dao.getMileStoneByCs(csNo);
	}
	
	public List<MilestoneVO> getMileStoneByCs_Reverse(String csNo){
		
		
		return dao.getMileStoneByCs_Reverse(csNo);
	}


}
