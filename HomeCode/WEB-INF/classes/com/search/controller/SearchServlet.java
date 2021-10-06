package com.search.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.cs.model.CaseService;
import com.cs.model.CaseVO;
import com.member.model.MemberService;
import com.member.model.MemberVO;

/**
 * Servlet implementation class SearchServlet
 */
@WebServlet("/search/SearchServlet")
public class SearchServlet extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		System.out.println("SearchServlet - action = " + action);
		
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
		
		
		
		// 找人才開始

				if ("SEARCH_PEOPLE".equals(action)) {
					List<String> errorMsgs = new LinkedList<String>();
					// Store this set in the request scope, in case we need to
					// send the ErrorPage view.
					req.setAttribute("errorMsgs", errorMsgs);

//					try {

						/*************************** 1.將輸入資料轉為Map **********************************/
						// 採用Map<String,String[]> getParameterMap()的方法
						// 注意:an immutable java.util.Map
						// Map<String, String[]> map = req.getParameterMap();
						HttpSession session = req.getSession();
						Map<String, String[]> mbMap = (Map<String, String[]>) session.getAttribute("mbMap");
						if (req.getParameter("whichPage") == null) {
							HashMap<String, String[]> mbMap1 = (HashMap<String, String[]>) req.getParameterMap();
							HashMap<String, String[]> mbMap2 = new HashMap<String, String[]>();
							mbMap2 = (HashMap<String, String[]>) mbMap1.clone();
							session.setAttribute("mbMap", mbMap2);
							mbMap = (HashMap<String, String[]>) req.getParameterMap();
						}

						

						/*************************** 2.開始複合查詢 ***************************************/
						MemberService memberSvc = new MemberService();
						List<MemberVO> list = memberSvc.getAll_CompositeQuery(mbMap);
						try {
							if (list.size() != 0) { // 檢查是否有查到東西
								System.out.println("抓到" + list.size() + "筆資料");
							}
						} catch (IndexOutOfBoundsException e) {
							System.out.println("沒抓到東西");
							errorMsgs.add("查無資料。"); // 沒抓到東西跟使用者提示他查無資料!
						}

						/***************************
						 * 3.查詢完成,準備轉交(Send the Success view)
						 ************/
						session.setAttribute("listPeople_ByCompositeQuery", list); // 資料庫取出的list物件,存入request
						
						String url = req.getContextPath() + "/search/People_Search.jsp?whichPage=1";
						res.sendRedirect(url);
						
				}
	}

}
