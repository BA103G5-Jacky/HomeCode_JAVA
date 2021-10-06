package com.img.controller;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.member.model.MemberService;
import com.member.model.MemberVO;

public class MemberServlet extends HttpServlet{
	
	public void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
	
	
	
	
	if ("getOne_For_Display".equals(action)) { // 來自select_page.jsp??��?��??

		List<String> errorMsgs = new LinkedList<String>();
		// Store this set in the request scope, in case we need to
		// send the ErrorPage view.
		req.setAttribute("errorMsgs", errorMsgs);

		try {
			/***************************1.?��?��請�?��?�數 - 輸入?��式�?�錯誤�?��??**********************/
			String str = req.getParameter("mbNo");
			if (str == null || (str.trim()).length() == 0) {
				errorMsgs.add("?��工編???");
			}
			// Send the use back to the form, if there were errors
			if (!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req
						.getRequestDispatcher("member_profile.jsp");
				failureView.forward(req, res);
				return;//程�?�中?��
			}
			
			String  mbNo = null;
			try {
				mbNo = new String (str);
			} catch (Exception e) {
				errorMsgs.add("?��式�?�正�?");
			}
			// Send the use back to the form, if there were errors
			if (!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req
						.getRequestDispatcher("member_profile.jsp");
				failureView.forward(req, res);
				return;//程�?�中?��
			}
			
			/***************************2.??��?�查詢�?��??*****************************************/
			MemberService empSvc = new MemberService();
			MemberVO memberVO = empSvc.getOneMember(mbNo);
			if (memberVO == null) {
				errorMsgs.add("?��?��資�??");
			}
			// Send the use back to the form, if there were errors
			if (!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req
						.getRequestDispatcher("member_profile.jsp");
				failureView.forward(req, res);
				return;//程�?�中?��
			}
			
			/***************************3.?��詢�?��??,準�?��?�交(Send the Success view)*************/
			req.setAttribute("memberVO", memberVO); // 資�?�庫??�出??�empVO?���?,存入req
			String url = "member_profile.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url); // ??��?��?�交 listOneEmp.jsp
			successView.forward(req, res);

			/***************************?��他可?��??�錯誤�?��??*************************************/
		} catch (Exception e) {
			errorMsgs.add("?��法�?��?��?��??:" + e.getMessage());
			RequestDispatcher failureView = req
					.getRequestDispatcher("member_profile.jsp");
			failureView.forward(req, res);
			}	
		}
	}
}