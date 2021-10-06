package com.cs.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.applicant.model.ApplicantService;
import com.applicant.model.ApplicantVO;
import com.case_type.model.Case_typeService;
import com.case_type.model.Case_typeVO;
import com.cs.model.CaseService;
import com.cs.model.CaseVO;
import com.favorite_case.model.FavoriteCaseService;
import com.member.model.MemberService;
import com.member.model.MemberVO;
import com.skill_type.model.Skill_typeService;
import com.util.tool.tools;

public class CaseServlet extends HttpServlet {

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		System.out.println("CaseServlet - action = " + action);
		if ("insert".equals(action)) {

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/***********************
				 * 1.接收請求參數 - 輸入格式的錯誤處理
				 *************************/
				String hirerNo = req.getParameter("hirerNo").trim();

				String csName = req.getParameter("csName").trim();
				if (csName == null || (csName.trim()).length() == 0) {
					errorMsgs.add("請填入案件名稱");
				}

				String csDesc = req.getParameter("csDesc").trim();
				if (csDesc == null || (csDesc.trim()).length() == 0) {
					errorMsgs.add("請填入案件詳情");
				} else {
					csDesc.replace(" ", "\n");
				}

				Integer csPayment = new Integer(0);
				if (req.getParameter("csPayment").trim().length() == 0) {
					errorMsgs.add("請輸入正確金額");
				} else {
					csPayment = new Integer(req.getParameter("csPayment").trim());
				}

				String csLoc = req.getParameter("csLoc").trim();

				String csLevel = req.getParameter("csLevel").trim();

				String startDate = req.getParameter("startDate").trim();
				String endDate = req.getParameter("endDate").trim();

				if (startDate.length() == 0) {
					errorMsgs.add("請輸入案件預計開始時間");
				}
				if (endDate.length() == 0) {
					errorMsgs.add("請輸入案件預計結束時間");
				}
				if (startDate.contains("/")) {
					try {
						startDate = tools.toStrDate(startDate);
					} catch (Exception e) {
						errorMsgs.add("請選擇正確日期");
					}

				}
				if (endDate.contains("/")) {
					try {
						endDate = tools.toStrDate(endDate);
					} catch (Exception e) {
						errorMsgs.add("請選擇正確日期");
					}
				}

				Case_typeService ctkSvc = new Case_typeService();

				String[] skillNoList = req.getParameterValues("skillNo");
				List<String> ctSkNoList = new ArrayList<String>();
				Case_typeService ctSvc = new Case_typeService();
				if(skillNoList != null && skillNoList.length!=0){
					for (String skillNo : skillNoList) {
						ctSkNoList.add(skillNo);
					}
				}
				

				if (startDate == null || (startDate.trim()).length() == 0) {
					errorMsgs.add("請填入開始日期");
				}

				if (endDate == null || (endDate.trim()).length() == 0) {
					errorMsgs.add("請填入結束日期");
				}

				CaseVO caseVO = new CaseVO();
				caseVO.setCsName(csName);
				caseVO.setHirerNo(hirerNo);
				caseVO.setCsLoc(csLoc);
				caseVO.setCsPayment(csPayment);
				caseVO.setStartDate(startDate);
				caseVO.setEndDate(endDate);
				caseVO.setCsLevel(csLevel);
				caseVO.setCsDesc(csDesc);

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("caseVO", caseVO);
					req.setAttribute("ctSkNoList", ctSkNoList);
					RequestDispatcher failureView = req.getRequestDispatcher("/case/case_post.jsp");
					failureView.forward(req, res);
					return;
				}

				/*************************** 2.新增張貼案件 ******************************/
				CaseService csSvc = new CaseService();
				caseVO = csSvc.addCase(csName, hirerNo, csLoc, csPayment, startDate, endDate, csLevel, csDesc);
				
				if(skillNoList != null && skillNoList.length!=0){
					for (String skillNo : skillNoList) {
						ctSkNoList.add(skillNo);
						ctSvc.addCase_type(caseVO.getCsNo(), skillNo);
					}
				}
				String newPost = caseVO.getCsNo();
				/*************************** 3.跳轉頁面 ***********************************/
				String url = req.getContextPath() + "/case/case.do?action=applicant&actor=hirer&mbNo=" + hirerNo
						+ "&newPost=" + newPost;
				res.sendRedirect(url);
				// RequestDispatcher successView =
				// req.getRequestDispatcher(url); //
				// successView.forward(req, res);

			} catch (Exception e) {
				errorMsgs.add("新增失敗");
				RequestDispatcher failureView = req.getRequestDispatcher("/case/case_post.jsp");
				failureView.forward(req, res);
			}
		}

		// forward to case_info.jsp
		if ("caseInfo".equals(action)) {

			try {
				String csNo = req.getParameter("csNo");
				CaseService csSrv = new CaseService();
				CaseVO caseVO = csSrv.getOneCase(csNo);
				req.setAttribute("caseVO", caseVO);

				Case_typeService ctSrv = new Case_typeService();
				List<Case_typeVO> ctList = ctSrv.getOneCase_type(caseVO.getCsNo());
				req.setAttribute("ctList", ctList);

				Skill_typeService stSrv = new Skill_typeService();
				req.setAttribute("stSrv", stSrv);

				MemberService mbSrv = new MemberService();
				req.setAttribute("mbSrv", mbSrv);

				ApplicantService appSvc = new ApplicantService();
				req.setAttribute("appSvc", appSvc);

				String url = "/case/case_info.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);

			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}

		// forward to DoneCase(DoneByH.jsp/DoneByF.jsp)
		if ("doneCase".equals(action)) {
			String mbNo = req.getParameter("mbNo");
			String actor = req.getParameter("actor");
			// search doneCsList by mbNo
			CaseService csSvc = new CaseService();
			List<CaseVO> doneCsList = null;
			String url = null;
			if ("hirer".equals(actor)) {
				doneCsList = csSvc.findByHirerNo(mbNo, "已完成");
				System.out.println(doneCsList.size());
				url = "/case/DoneByH.jsp";
			} else {
				doneCsList = csSvc.findByFreelancerNo(mbNo, "已完成");
				url = "/case/DoneByF.jsp";
			}
			req.setAttribute("doneCsList", doneCsList);

			RequestDispatcher successView = req.getRequestDispatcher(url);
			successView.forward(req, res);
		}

		// forward to OnGoCase.jsp
		if ("onGoCase".equals(action)) {
			String mbNo = req.getParameter("mbNo");
			String actor = req.getParameter("actor");
			// search onGoCsList by mbNo
			CaseService csSvc = new CaseService();
			List<CaseVO> onGoCsList = null;
			String url = null;
			if ("hirer".equals(actor)) {
				onGoCsList = csSvc.findByHirerNo(mbNo, "進行中");
				url = "/case/OnGoByH.jsp";
			} else {
				onGoCsList = csSvc.findByFreelancerNo(mbNo, "進行中");
				url = "/case/OnGoByF.jsp";
			}

			req.setAttribute("onGoCsList", onGoCsList);

			RequestDispatcher successView = req.getRequestDispatcher(url);
			successView.forward(req, res);
		}

		// forward to applicant.jsp
		if ("applicant".equals(action)) {
			String mbNo = req.getParameter("mbNo");
			String actor = req.getParameter("actor");
			// search onGoCsList by mbNo
			CaseService csSvc = new CaseService();
			ApplicantService applicantSvc = new ApplicantService();
			List<CaseVO> applicantCaseList = new ArrayList<CaseVO>();

			String url = null;
			if ("hirer".equals(actor)) {
				String newPost = req.getParameter("newPost");
				// get this member's case state = "wait"
				applicantCaseList = csSvc.findByHirerNo(mbNo, "接洽中");
				url = "/case/applicant_ByH.jsp";
				req.setAttribute("newPost", newPost);
			} else {
				String newApply = req.getParameter("newApply");
				List<String> appNoList = applicantSvc.getFreelancerApplicantCase(mbNo);
				for (String appNo : appNoList) {
					CaseVO csVO = csSvc.getOneCase(appNo);
					applicantCaseList.add(csVO);
				}
				url = "/case/applicant_ByF.jsp";
				req.setAttribute("newApply", newApply);
			}

			req.setAttribute("applicantCaseList", applicantCaseList);

			RequestDispatcher successView = req.getRequestDispatcher(url);
			successView.forward(req, res);
		}

		// get caseInfo to Update
		if ("getOneUpdate".equals(action)) {
			String csNo = req.getParameter("csNo");
			System.out.println(csNo);

			CaseService csSvc = new CaseService();
			CaseVO caseVO = csSvc.getOneCase(csNo);

			Case_typeService ctSvc = new Case_typeService();
			List<Case_typeVO> ctList = ctSvc.getOneCase_type(csNo);
			List<String> ctSkNoList = new ArrayList<String>();
			for (Case_typeVO ctVO : ctList) {
				ctSkNoList.add(ctVO.getSkillNo());
			}

			req.setAttribute("caseVO", caseVO);
			req.setAttribute("ctSkNoList", ctSkNoList);
			req.setAttribute("url", req.getParameter("url"));
			String url = "/case/updateCase.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);
			successView.forward(req, res);
		}

		// update caseInfo
		if ("updateCaseInfo".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			CaseVO caseVO = new CaseVO();

			try {
				/***********************
				 * 1.接收請求參數 - 輸入格式的錯誤處理
				 *************************/
				String csName = req.getParameter("csName").trim();
				if (req.getParameter("csName") == null || req.getParameter("csName").trim().length() == 0) {
					errorMsgs.add("請輸入案件名稱");
				}
				String csNo = req.getParameter("csNo").trim();

				String csDesc = req.getParameter("csDesc").trim();
				if (req.getParameter("csDesc") == null || req.getParameter("csDesc").trim().length() == 0) {
					csDesc = "";
				}
				Integer csPayment = new Integer(0);
				if (req.getParameter("csPayment").trim().length() == 0) {
					errorMsgs.add("請輸入正確金額");
				} else {
					csPayment = new Integer(req.getParameter("csPayment").trim());
				}

				String csLoc = req.getParameter("csLoc").trim();
				String csLevel = req.getParameter("csLevel").trim();
				String startDate = req.getParameter("startDate").trim();
				String endDate = req.getParameter("endDate").trim();
				System.out.println(startDate);
				if (startDate.length() == 0) {
					errorMsgs.add("請輸入案件預計開始時間");
				}
				if (endDate.length() == 0) {
					errorMsgs.add("請輸入案件預計結束時間");
				}
				if (startDate.contains("/")) {
					try {
						startDate = tools.toStrDate(startDate);
						caseVO.setStartDate(startDate);
						System.out.println("update case startDate = " + caseVO.getStartDate());
					} catch (Exception e) {
						startDate = "";
						errorMsgs.add("請選擇正確日期");
					}

				} else {
					if (!startDate.contains("-")) {
						startDate = "";
						errorMsgs.add("請選擇正確日期");
					} else {
						caseVO.setStartDate(startDate);
						System.out.println("update case startDate = " + caseVO.getStartDate());
					}
				}
				if (endDate.contains("/")) {
					try {
						endDate = tools.toStrDate(endDate);
						caseVO.setEndDate(endDate);
					} catch (Exception e) {
						endDate = "";
						errorMsgs.add("請選擇正確日期");
					}
				} else {
					if (!endDate.contains("-")) {
						endDate = "";
						errorMsgs.add("請選擇正確日期");
					} else {

						caseVO.setEndDate(endDate);
						System.out.println("update case startDate = " + caseVO.getEndDate());
					}
				}

				String[] skillNoList = req.getParameterValues("skillNo");
				Case_typeService ctSvc = new Case_typeService();
				List<String> ctSkNoList = new ArrayList<String>();
				if (skillNoList == null) {
					ctSvc.deleteCase_type(csNo);
				} else {
					ctSvc.deleteCase_type(csNo);
					for (String skillNo : skillNoList) {
						ctSkNoList.add(skillNo);
						ctSvc.addCase_type(csNo, skillNo);
					}
				}

				caseVO.setCsName(csName);
				caseVO.setCsNo(csNo);
				caseVO.setCsLoc(csLoc);
				caseVO.setCsPayment(csPayment);
				caseVO.setCsLevel(csLevel);
				caseVO.setCsDesc(csDesc);
				caseVO.setEndDate(endDate);
				caseVO.setStartDate(startDate);

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("caseVO", caseVO);
					req.setAttribute("ctSkNoList", ctSkNoList);
					String url = req.getParameter("url");
					req.setAttribute("url", url);

					RequestDispatcher failureView = req.getRequestDispatcher("/case/updateCase.jsp");
					failureView.forward(req, res);
					return;
				}

				/*************************** 2.開始新增資料 ***************************************/
				CaseService csSvc = new CaseService();
				caseVO = csSvc.updateCase(csName, csLoc, csPayment, startDate, endDate, csLevel, csDesc, csNo);

				/***************************
				 * 3.新增完成,準備轉交(Send the Success view)
				 ***********/
				req.setAttribute("updateSuccess", "資料更新成功");

				String url2 = req.getContextPath() + "/case/case.do?action=caseInfo&csNo=" + csNo;
				res.sendRedirect(url2);
				// String url2 = "/case/case.do?" + req.getParameter("url");
				// RequestDispatcher successView =
				// req.getRequestDispatcher(url2);
				// successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 *********************************/
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/case/updateCase.jsp");
				failureView.forward(req, res);
			}
		}

		// updateFreelancerScore
		if ("updateFreelancerScore".equals(action)) {
			String csNo = req.getParameter("csNo");
			String freelancerComment = req.getParameter("freelancerComment");
			Integer freelancerScore = Integer.parseInt(req.getParameter("freelancerScore"));

			CaseService csSvc = new CaseService();
			CaseVO cs = csSvc.updateFreelancerScoreComment(freelancerScore, freelancerComment, csNo);

			System.out.println("cs" + cs.getCsNo());

			String url = req.getContextPath() + "/case/DoneByH.jsp";
			res.sendRedirect(url);
		}

		// updateHirerScore
		if ("updateHirerScore".equals(action)) {
			String csNo = req.getParameter("csNo");
			String hirerComment = req.getParameter("hirerComment");
			Integer hirerScore = Integer.parseInt(req.getParameter("hirerScore"));

			CaseService csSvc = new CaseService();
			csSvc.updateHireScoreComment(hirerScore, hirerComment, csNo);
			String url = req.getContextPath() + "/case/DoneByF.jsp";
			res.sendRedirect(url);
		}

		// delete Case
		if ("deleteCase".equals(action)) {
			String csNo = req.getParameter("csNo");
			String mbNo = req.getParameter("mbNo");
			// delete case's case_type
			Case_typeService ctSvc = new Case_typeService();
			ctSvc.deleteCase_type(csNo);

			// delete all applicant
			ApplicantService appSvc = new ApplicantService();
			appSvc.deleteAllApply(csNo);

			// delete who favorite this case
			FavoriteCaseService fcSvc = new FavoriteCaseService();
			fcSvc.deleteAll(csNo);

			// delete case
			CaseService csSvc = new CaseService();
			csSvc.delete(csNo);

			String url = req.getContextPath() + "/case/case.do?action=applicant&actor=hirer&mbNo=" + mbNo;
			res.sendRedirect(url);
		}

		// 找案件ㄉ附合查詢~

		if ("listCases_ByCompositeQuery".equals(action)) { // 來自Case_Search的複合查詢請求
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			try {
				/*************************** 1.將輸入資料轉為Map **********************************/
				// 採用Map<String,String[]> getParameterMap()的方法
				// 注意:an immutable java.util.Map
				// Map<String, String[]> map = req.getParameterMap();
				HttpSession session = req.getSession();
				Map<String, String[]> csMap = (Map<String, String[]>) session.getAttribute("csMap");
				if (req.getParameter("whichPage") == null) {
					HashMap<String, String[]> csMap1 = (HashMap<String, String[]>) req.getParameterMap();
					HashMap<String, String[]> csMap2 = new HashMap<String, String[]>();
					csMap2 = (HashMap<String, String[]>) csMap1.clone();
					session.setAttribute("csMap", csMap2);
					csMap = (HashMap<String, String[]>) req.getParameterMap();
				}
				/*************************** 2.開始複合查詢 ***************************************/
				CaseService caseSvc = new CaseService();
				List<CaseVO> list = caseSvc.getAll(csMap);
				CaseVO caseVO = null;
				try {
					if (list.contains(list.get(0))) { // 檢查是否有查到東西 ,去get
														// list它的第一個位子看DAO查出來回傳的VO有沒有東西!
						System.out.println("有查到東西!");
						caseVO = list.get(0); // 從第一個caseVO抓我們在DAO塞的SQL指令
						String finalSQL = caseVO.getFinalSQL(); // 指令塞在finalSQL字串裡
						session.setAttribute("finalSQL", finalSQL);
					}
				} catch (IndexOutOfBoundsException e) {
					System.out.println("沒抓到東西");
					errorMsgs.add("查無資料。"); // 沒抓到東西跟使用者提示他-查無資料!
				}
				/***************************
				 * 3.查詢完成,準備轉交(Send the Success view)
				 ************/
				session.setAttribute("listCases_ByCompositeQuery", list); // 資料庫取出的list物件,存入request

				String url = req.getContextPath() + "/search/Case_Search.jsp?whichPage=1";
				res.sendRedirect(url);

				// RequestDispatcher successView =
				// req.getRequestDispatcher("Case_Search.jsp"); //
				// 成功轉交listEmps_ByCompositeQuery.jsp
				// successView.forward(req, res);
				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("Case_Search.jsp");
				failureView.forward(req, res);
			}
		}

		// 價格由高到低的排序開始
		if ("highToLow".equals(action)) { // 來自Case_brow的價格排序請求

			HttpSession session = req.getSession();

			try {
				System.out.println("進來highToLow區塊了!");
				String finalSQL = session.getAttribute("finalSQL").toString();
				System.out.println("finalSQL" + finalSQL);
				CaseService csSvc = new CaseService();
				List<CaseVO> list = csSvc.changeOrderByMoney_HighToLow(finalSQL);

				session.setAttribute("listCases_ByCompositeQuery", list); // 資料庫取出的list物件,存入request

			} catch (NullPointerException ne) {
				System.out.println("直接返回搜尋頁面,不做錯誤提醒");
			}
			String url = req.getContextPath() + "/search/Case_Search.jsp?whichPage=1";
			res.sendRedirect(url);
		}
		// 價格由高到低的排序結束

		// 價格由低到高的排序開始

		if ("lowToHigh".equals(action)) { // 來自Case_brow的價格排序請求

			System.out.println("進來lowToHigh區塊了!");
			HttpSession session = req.getSession();

			try {
				String finalSQL = session.getAttribute("finalSQL").toString();
				;

				CaseService csSvc = new CaseService();
				List<CaseVO> list = csSvc.changeOrderByMoney_LowToHigh(finalSQL);

				session.setAttribute("listCases_ByCompositeQuery", list); // 資料庫取出的list物件,存入request

			} catch (NullPointerException ne) {
				System.out.println("直接返回搜尋頁面,不做錯誤提醒");
			}

			String url = req.getContextPath() + "/search/Case_Search.jsp?whichPage=1";
			res.sendRedirect(url);
		}
		
		//付款
		if("creditPay".equals(action)){
			
			String csNo = req.getParameter("csNo");
			String mbNo = (String) req.getSession().getAttribute("mbNo");
			
			CaseService csSvc = new CaseService();
			csSvc.updateCsPayState(csNo);
			
			String url = req.getContextPath()+"/case/case.do?csNo="+ csNo +"&action=caseInfo";
			res.sendRedirect(url);
		}
	}
}
