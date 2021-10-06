package com.milestone.controller;

import java.io.IOException;
import java.sql.Date;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.milestone.model.MilestoneService;
import com.milestone.model.MilestoneVO;
import com.timeline.model.TimelineService;
import com.timeline.model.TimelineVO;
import com.util.tool.tools;
import com.cs.model.CaseService;
import com.cs.model.CaseVO;
import com.member.model.MemberService;
import com.member.model.MemberVO;

public class MilestoneServlet extends HttpServlet{

	
	public void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8"); //先：post
		String action = req.getParameter("action");
		
		if ("getCase_milestone".equals(action)) { // 來自select_page.jsp的請求
			try {
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				String csNo = req.getParameter("csNo");
				System.out.println("MilestoneServlet - csNo = " + csNo);
				
				/***************************2.開始查詢資料*****************************************/
				MilestoneService empSvc = new MilestoneService();
				List<MilestoneVO> milestoneList = empSvc.getMileStoneByCs(csNo);
				List<MilestoneVO> milestoneListReverse = empSvc.getMileStoneByCs_Reverse(csNo);
				System.out.println("MilestoneServlet - mileStoneSize = "+milestoneListReverse.size());
				CaseService csSvc = new CaseService();
				CaseVO csVO = csSvc.getOneCase(csNo);
				
				
				/***************************3.查詢完成,準備轉交(Send the Success view)*************/
				req.setAttribute("milestoneList", milestoneList); // milestoneList
				req.setAttribute("milestoneListReverse", milestoneListReverse); // milestoneList
				req.setAttribute("csVO", csVO);	//csName
				
				
				String url = "/milestone/milestone.jsp"; //
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneEmp.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理*************************************/
			} catch (Exception e) {
				
				RequestDispatcher failureView = req.getRequestDispatcher("/milestone/milestone.jsp");
				failureView.forward(req, res);
			}
		}
		
		
		if ("agree-milestone".equals(action)){ //來自milestone.jsp的請求
			
			/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
			String milestoneState = req.getParameter("milestoneState");
			String milestoneNo = req.getParameter("milestoneNo");
			
		
			MilestoneService empSvc = new MilestoneService();
			empSvc.update_agreeMilestone(milestoneState, milestoneNo);
			MilestoneVO milestoneVO = empSvc.getOneMilestone(milestoneNo);
			System.out.println("milestoneState:"+milestoneVO.getMilestoneState());
			
			String csNo = req.getParameter("csNo");
			System.out.println("換角色的時候有把csNo帶過來嗎？"+csNo);
			
			List<MilestoneVO> milestoneList = empSvc.getMileStoneByCs(csNo);
			int agreeTimes = 0;
			for(MilestoneVO msVO : milestoneList){
				String msState = msVO.getMilestoneState();
				if(msState.equals("已完成")){
					agreeTimes++;
				}
			}
			
			//update schdule to case
			int schedule = agreeTimes*100/milestoneList.size();
			System.out.println("milestoneServlet - schedule = " + schedule);
			CaseService csSvc = new CaseService();
			csSvc.updateSchedule(schedule, csNo);
			
			//if agree one milestone plus to timeLine
			if("已完成".equals(milestoneState)){
				TimelineService timelineSvc = new TimelineService();
				TimelineVO timelineVO = new TimelineVO();
				timelineVO.setCsNo(csNo);
				timelineVO.setFileName("");
				timelineVO.setFilePath("");
				timelineVO.setNoteContent("完成案件進度 " + milestoneVO.getMilestoneName());
				timelineVO.setNoteTitle("完成案件進度 " + milestoneVO.getMilestoneName());
				timelineVO.setRecordDate(new java.sql.Date(new java.util.Date().getTime()));
				timelineVO.setFilePath("milestone");
				timelineSvc.insert_withFile(timelineVO);
			}
			
			//if milestone all done member's successTimes plus one
			MemberService mbSvc = new MemberService();
			CaseVO csVO = csSvc.getOneCase(csNo);
			if(schedule == 100){
				mbSvc.updateCsSuccesTimes(csSvc.getMbSuccessCase(csVO.getFreelancerNo()).size(), csVO.getFreelancerNo());
				csSvc.updateCsState("已完成", csNo);
			}
			
			
			/***************************3.修改完成,準備轉交(Send the Success view)*************/
			String url = "/milestone/milestone.do?action=getCase_milestone&csNo=" + csNo;
			RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneEmp.jsp
			successView.forward(req, res);
		}
		
		if ("disagree-milestone".equals(action)){ //來自milestone.jsp的請求
			
			/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
			String milestoneState = req.getParameter("milestoneState");
			String milestoneNo = req.getParameter("milestoneNo");
			
		
			MilestoneService empSvc = new MilestoneService();
			MilestoneVO milestoneVO = empSvc.update_agreeMilestone(milestoneState, milestoneNo);
			
			System.out.println("milestoneState:"+milestoneVO.getMilestoneState());

			String csNo = req.getParameter("csNo");
			System.out.println("換角色的時候有把csNo帶過來嗎？"+csNo);
			
			/***************************3.修改完成,準備轉交(Send the Success view)*************/
			String url = "/milestone/milestone.do?action=getCase_milestone&csNo=" + csNo;
			RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneEmp.jsp
			successView.forward(req, res);
		}
		
		
		if("insert".equals(action)){
			String mbNo = req.getParameter("mbNo");
			String csNo = req.getParameter("csNo");
			String milestoneNameList[] = req.getParameterValues("milestoneName");
			String startdateList[] = req.getParameterValues("startDate");
			String enddateList[] = req.getParameterValues("endDate");
			MilestoneService milestoneSvc = new MilestoneService();
			TimelineService tlSvc = new TimelineService();
			CaseService csSvc = new CaseService();
			
			if(milestoneNameList == null && startdateList == null && enddateList == null){
				milestoneSvc.addMilestone("合約成立", csNo, "已完成", new java.sql.Date(new java.util.Date().getTime()),
						new java.sql.Date(new java.util.Date().getTime()), new java.sql.Date(new java.util.Date().getTime()) );
				
				milestoneSvc.addMilestone("案件完成", csNo, "未完成", new java.sql.Date(new java.util.Date().getTime()),
						Date.valueOf(csSvc.getOneCase(csNo).getEndDate().substring(0,10)), null );
				
				TimelineVO timelineVO = new TimelineVO();
				timelineVO.setCsNo(csNo);
				timelineVO.setFileName("");
				timelineVO.setFilePath("");
				timelineVO.setNoteContent("合約成立");
				timelineVO.setNoteTitle("合約成立");
				timelineVO.setRecordDate(new java.sql.Date(new java.util.Date().getTime()));
				timelineVO.setFilePath("milestone");
				tlSvc.insert_withFile(timelineVO);
				
				
				
			} else {
				milestoneSvc.addMilestone("合約成立", csNo, "已完成", new java.sql.Date(new java.util.Date().getTime()),
						new java.sql.Date(new java.util.Date().getTime()), new java.sql.Date(new java.util.Date().getTime()) );
				TimelineVO timelineVO = new TimelineVO();
				timelineVO.setCsNo(csNo);
				timelineVO.setFileName("");
				timelineVO.setFilePath("");
				timelineVO.setNoteContent("合約成立");
				timelineVO.setNoteTitle("合約成立");
				timelineVO.setRecordDate(new java.sql.Date(new java.util.Date().getTime()));
				timelineVO.setFilePath("milestone");
				tlSvc.insert_withFile(timelineVO);
				for(int i = 0; i<milestoneNameList.length; i++){
					Date startDate = null;
					Date endDate = null;
					if(milestoneNameList[i].length() == 0){
						milestoneNameList[i] = "進度里程" + (i+1);
					}
					if( startdateList[i].length() == 0){
						startDate = new java.sql.Date(new java.util.Date().getTime());
					}
					if( enddateList[i].length() == 0){
						endDate = new java.sql.Date(new java.util.Date().getTime());
					}
					if(startdateList[i].contains("/")){
						try{
							startDate = Date.valueOf(tools.toStrDate(startdateList[i]));
						} catch (Exception e){
							startDate = new java.sql.Date(new java.util.Date().getTime());
						}
						
					}
					if(enddateList[i].contains("/")){
						try{
							endDate = Date.valueOf(tools.toStrDate(enddateList[i]));
						} catch (Exception e){
							endDate = new java.sql.Date(new java.util.Date().getTime());
						}
					}
					milestoneSvc.addMilestone(milestoneNameList[i], csNo, "未完成", startDate, endDate, null );
				}
			}
			
			List<MilestoneVO> milestoneList = milestoneSvc.getMileStoneByCs(csNo);
			int agreeTimes = 0;
			for(MilestoneVO msVO : milestoneList){
				String msState = msVO.getMilestoneState();
				if(msState.equals("已完成")){
					agreeTimes++;
				}
			}
			System.out.println("milestoneServlet - agreeTimes = " + agreeTimes);
			System.out.println("milestoneList.size() = " + milestoneList.size());
			//update schdule to case
			int schedule = agreeTimes*100/milestoneList.size();
			System.out.println("milestoneServlet - schedule = " + schedule);
			
			csSvc.updateCsState("進行中", csNo);
			
			csSvc.updateSchedule(schedule, csNo);
			
			req.setAttribute("csNo", csNo);
			
			String url = req.getContextPath()+"/case/case.do?action=applicant&actor=hirer&mbNo="+mbNo;
			
			res.sendRedirect(url);
//			RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneEmp.jsp
//			successView.forward(req, res);
			
			
		}
		
		
	}
	
	public void updateSchedule(){
		
	}
}


