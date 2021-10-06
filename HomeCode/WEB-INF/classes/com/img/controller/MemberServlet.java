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
	
	
	
	
	if ("getOne_For_Display".equals(action)) { // ä¾†è‡ªselect_page.jsp??„è?‹æ??

		List<String> errorMsgs = new LinkedList<String>();
		// Store this set in the request scope, in case we need to
		// send the ErrorPage view.
		req.setAttribute("errorMsgs", errorMsgs);

		try {
			/***************************1.?¥?”¶è«‹æ?‚å?ƒæ•¸ - è¼¸å…¥? ¼å¼ç?„éŒ¯èª¤è?•ç??**********************/
			String str = req.getParameter("mbNo");
			if (str == null || (str.trim()).length() == 0) {
				errorMsgs.add("?“¡å·¥ç·¨???");
			}
			// Send the use back to the form, if there were errors
			if (!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req
						.getRequestDispatcher("member_profile.jsp");
				failureView.forward(req, res);
				return;//ç¨‹å?ä¸­?–·
			}
			
			String  mbNo = null;
			try {
				mbNo = new String (str);
			} catch (Exception e) {
				errorMsgs.add("? ¼å¼ä?æ­£ç¢?");
			}
			// Send the use back to the form, if there were errors
			if (!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req
						.getRequestDispatcher("member_profile.jsp");
				failureView.forward(req, res);
				return;//ç¨‹å?ä¸­?–·
			}
			
			/***************************2.??‹å?‹æŸ¥è©¢è?‡æ??*****************************************/
			MemberService empSvc = new MemberService();
			MemberVO memberVO = empSvc.getOneMember(mbNo);
			if (memberVO == null) {
				errorMsgs.add("?Ÿ¥?„¡è³‡æ??");
			}
			// Send the use back to the form, if there were errors
			if (!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req
						.getRequestDispatcher("member_profile.jsp");
				failureView.forward(req, res);
				return;//ç¨‹å?ä¸­?–·
			}
			
			/***************************3.?Ÿ¥è©¢å?Œæ??,æº–å?™è?‰äº¤(Send the Success view)*************/
			req.setAttribute("memberVO", memberVO); // è³‡æ?™åº«??–å‡º??„empVO?‰©ä»?,å­˜å…¥req
			String url = "member_profile.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url); // ??å?Ÿè?‰äº¤ listOneEmp.jsp
			successView.forward(req, res);

			/***************************?…¶ä»–å¯?ƒ½??„éŒ¯èª¤è?•ç??*************************************/
		} catch (Exception e) {
			errorMsgs.add("?„¡æ³•å?–å?—è?‡æ??:" + e.getMessage());
			RequestDispatcher failureView = req
					.getRequestDispatcher("member_profile.jsp");
			failureView.forward(req, res);
			}	
		}
	}
}