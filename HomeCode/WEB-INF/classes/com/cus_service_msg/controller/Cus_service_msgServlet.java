package com.cus_service_msg.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cus_service_msg.model.Cus_Service_MsgService;
import com.cus_service_msg.model.Cus_Service_MsgVO;
import com.member.model.MemberService;
import com.member.model.MemberVO;

public class Cus_service_msgServlet extends HttpServlet {

	// 1. 輸入請求參數
	// 2. 查詢
	// 3. 轉交頁面

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		String action = req.getParameter("action");
		req.setCharacterEncoding("UTF-8");
		PrintWriter out = res.getWriter();
		
		if ("addMesg".equals(action)) {
			System.out.print("有進來嗎?");
			String mbNo = req.getParameter("sender");
			String topic = java.net.URLDecoder.decode(req.getParameter("topic"),"UTF-8");
			String mesgContent = java.net.URLDecoder.decode(req.getParameter("mesgContent"),"UTF-8");
//			String servletPath = req.getParameter("servletPath");

			if (topic.trim().length() == 0 || topic == null) {
				topic = "[無標題]";
			}
			if (mesgContent.trim().length() == 0 || mesgContent == null) {
				mesgContent = "[無內容]";
			}

			/*************************** 2.開始寄信給客服 *****************************************/
			Cus_Service_MsgService cusSvc = new Cus_Service_MsgService();

			Cus_Service_MsgVO cus_Service_MsgVO = new Cus_Service_MsgVO();

			cus_Service_MsgVO.setMbNo(mbNo);
			cus_Service_MsgVO.setMesgTitle(topic);
			cus_Service_MsgVO.setMesgContent(mesgContent);

			int a = cusSvc.insert(cus_Service_MsgVO);
			
			if(a == 1){
				out.println(1);
			} else {
				out.println(0);
			}
			
			

//			RequestDispatcher successView = req.getRequestDispatcher(servletPath);
//
//			successView.forward(req, res);
		}

	}
}