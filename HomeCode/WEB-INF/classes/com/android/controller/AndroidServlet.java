package com.android.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cs.model.CaseService;
import com.cs.model.CaseVO;
import com.milestone.model.MilestoneService;
import com.milestone.model.MilestoneVO;
import com.timeline.model.TimelineService;
import com.timeline.model.TimelineVO;

@WebServlet("/android/AndroidServlet")
public class AndroidServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
     
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		
		req.setCharacterEncoding("UTF-8"); // 先：post
		String action = req.getParameter("action");
		System.out.println("Android - action = " + action);
		
		
		if ("timeline_Android".equals(action)) { 

			try {
				req.getSession().setAttribute("android", "android");
				
				String csNo = req.getParameter("csNo");
				
				//get Case Timeline
				TimelineService timelineSvc = new TimelineService();
				List<TimelineVO> timelineList = timelineSvc.findByOneCase(csNo);
				
				//get Case info
				CaseService csSvc = new CaseService();
				CaseVO caseVO = csSvc.getOneCase(csNo);
				
				
				req.setAttribute("timelineList", timelineList);
				req.setAttribute("caseVO", caseVO);
				
				String url = "/android/timeline_android.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); 
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 *************************************/
			} catch (Exception e) {

				RequestDispatcher failureView = req.getRequestDispatcher("/index.jsp");
				failureView.forward(req, res);
			}
		}	
		
		if ("milestone_Android".equals(action)) { // 來自select_page.jsp的請求
			try {
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				req.getSession().setAttribute("android", "android");
				String csNo = req.getParameter("csNo");
				System.out.println("MilestoneServlet - csNo = " + csNo);
				String mbNo = req.getParameter("mbNo");
				System.out.println("MilestoneServlet - mbNo = " + mbNo);
				
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
				req.setAttribute("mbNo", mbNo);
				req.getSession().setAttribute("mbNo", mbNo);
				
				String url = "/android/milestone_android.jsp"; //
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneEmp.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理*************************************/
			} catch (Exception e) {
				
				RequestDispatcher failureView = req.getRequestDispatcher("/android/milestone_android.jsp");
				failureView.forward(req, res);
			}
		}
	}

}
