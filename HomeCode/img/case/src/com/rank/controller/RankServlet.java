package com.rank.controller;

import java.io.*;
import java.util.*;

import javax.servlet.*;
import javax.servlet.http.*;


import com.rank.model.*;


public class RankServlet extends HttpServlet {

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");

		if ("getOne_For_Display".equals(action)) { // 來自首頁.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
				String str = req.getParameter("mbno");
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.add("請輸入會員編號");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("rankHomePage.jsp");
					failureView.forward(req, res);
					return;// 程式中斷
				}

				String mbno = null;
				try {
					mbno = new String(str);
				} catch (Exception e) {
					errorMsgs.add("會員編號格式不正確");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("rankHomePage.jsp");
					failureView.forward(req, res);
					return;// 程式中斷
				}

				/*************************** 2.開始查詢資料 *****************************************/
				RankService rankSvc = new RankService();
				RankVO rankVO = rankSvc.getOneRankByMbNo(mbno);
				if (rankVO == null) {

					errorMsgs.add("查無資料");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("rankHomePage.jsp");
					failureView.forward(req, res);
					return;// 程式中斷
				}

				/*************************** 3.查詢完成,準備轉交(Send the Success view) *************/
				req.setAttribute("rankVO",rankVO); // 資料庫取出的empVO物件,存入req
				String url = "onepeopleRank.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneEmp.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 *************************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("rankHomePage.jsp");
				failureView.forward(req, res);
			}
		}

		
		
		if ("delete".equals(action)) { // 來自listAllEmp.jsp

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
	
			try {
				/***************************1.接收請求參數***************************************/
				String mbno = new String(req.getParameter("mbno"));
				
				/***************************2.開始刪除資料***************************************/
				RankService rankSvc = new RankService();
				rankSvc.deleteRank(mbno);
				
				/***************************3.刪除完成,準備轉交(Send the Success view)***********/								
				String url = "RankSearch.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 刪除成功後,轉交回送出刪除的來源網頁
				successView.forward(req, res);
				
				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add("刪除資料失敗:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("RankSearch.jsp");
				failureView.forward(req, res);
			}
		}
		
		if ("find_Top_N".equals(action)) { // 來自listAllEmp.jsp

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
	
			try {
				/***************************1.接收請求參數 + 錯誤驗證***************************************/
			
				String firstnum = req.getParameter("num");
				if (firstnum == null || (firstnum.trim()).length() == 0) {
					errorMsgs.add("不打字是要查三小朋友拉");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("rankHomePage.jsp");
					failureView.forward(req, res);
					return;// 程式中斷
				}

				Integer num = null;
				try {
					num = new Integer(firstnum);
				} catch (Exception e) {
					errorMsgs.add("請輸入數字就好 敗偷ㄅ要那ㄇ蠢");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("rankHomePage.jsp");
					failureView.forward(req, res);
					return;// 程式中斷
				}
				/***************************2.開始搜尋前n筆資料***************************************/
				req.setAttribute("num", num);
				RankService rankSvc = new RankService();
				List<RankVO> rankVO1 = rankSvc.findtopN(num);
				req.setAttribute("rankVO1", rankVO1);

				
				/***************************3.準備轉交(Send the Success view)***********/		
				
				String url = "RankSearch.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 刪除成功後,轉交回送出刪除的來源網頁
				successView.forward(req, res);
				
				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add("I dont know why it fail, it's impossible:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("RankSearch.jsp");
				failureView.forward(req, res);
			}
		}
	}
}
