package com.message.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.member.model.MemberService;
import com.member.model.MemberVO;
import com.message.model.MessageService;
import com.message.model.MessageVO;

public class MessageServlet extends HttpServlet {

	// 1. 輸入請求參數
	// 2. 查詢
	// 3. 轉交頁面

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	String saveDirectory = "/timelinefile_uploaded";

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8"); // 先：post
		String action = req.getParameter("action");
		HttpSession session = req.getSession();
		String mbNo = (String) session.getAttribute("mbNo");
		System.out.println("MessageServlet - action = " + action);

		if ("changeReadStatus".equals(action)) {

			String mesgNo = req.getParameter("mesgNo");
			String servletPath = req.getParameter("servletPath");
			// log
			System.out.println("[MessageServlet-changeReadStatus]-[servletPath]:" + servletPath);
			String readStatus = req.getParameter("readStatus");
			System.out.println("[MessageServlet-changeReadStatus]-[readStatus]:" + readStatus);
			String readStatus2 = new String(readStatus.getBytes("ISO-8859-1"), "UTF-8"); // 再
			// log
			System.out.println("[MessageServlet-changeReadStatus]-[readStatus2]:" + readStatus2);
			// log
			System.out.println("[MessageServlet-changeReadStatus]-[mesgNo]:" + mesgNo);
			MessageService mesgSvc = new MessageService();
			mesgSvc.updateReadStatus(readStatus, mesgNo);

			RequestDispatcher successView = req.getRequestDispatcher(servletPath); // 成功轉交
			// listOneEmp.jsp
			successView.forward(req, res);
		}

		if ("deletMail".equals(action)) {

			String mesgNo = req.getParameter("mesgNo");
			String servletPath = req.getParameter("servletPath");
			String fromSysBox = req.getParameter("fromSysBox");
			MessageService mesgSvc = new MessageService();

			if (fromSysBox != null) {
				mesgSvc.deleteByMailMb(mesgNo);
			} else {
				mesgSvc.deleteByRecvMb(mesgNo);
			}

			RequestDispatcher successView = req.getRequestDispatcher(servletPath); // 成功轉交
			// listOneEmp.jsp
			successView.forward(req, res);

		}

		if ("checkMbExist".equals(action)) {
			String recipient = req.getParameter("recipient").trim(); // 接收收件人資訊
			String recipient2 = new String(recipient.getBytes("ISO-8859-1"), "UTF-8"); // 再

			MemberService mbSvc = new MemberService();
			PrintWriter out = res.getWriter();
			String mbCheckStatus = "2";
			// log
			System.out.println("[MessageServlet-findByUsrName]-[recipient]:" + recipient2);
			MemberVO mbVO = null;

			if (recipient.contains("@")) {
				mbVO = mbSvc.findByEmail(recipient2);

			} else {
				mbVO = mbSvc.findByUsrName(recipient2);
			}
			if (mbVO == null) {
				// log
				System.out.println("[MessageServlet-findByUsrName]-[recipient2]:" + recipient2);
				mbCheckStatus = "1";
			}
			out.print(mbCheckStatus); // 中文問題轉碼
		}

		if ("addMesg".equals(action)) { // 來自select_page.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			String servletPath = req.getParameter("servletPath"); // 接收servletPath
			try {
				/******************************************************
				 * 1.接收請求參數 - 輸入格式的錯誤處理
				 ******************************************************/
				System.out.println("recipient = " + req.getParameter("recipient"));
				String recipient = req.getParameter("recipient").trim(); // 接收收件人資訊
				String sender = req.getParameter("sender").trim(); // 接收寄件人資訊
				String topic = req.getParameter("topic"); // 接收主旨資訊
				String mesgContent = req.getParameter("mesgContent"); // 接收內容資訊
				System.out.println("recipient = " + recipient);
				System.out.println("sender = " + sender);

				/*************************** 2.開始查詢資料 *****************************************/
				MemberService mbSvc = new MemberService();
				MemberVO mbVO = null;
				String recipientMbNo = null;
				if (recipient.contains("@")) {
					mbVO = mbSvc.findByEmail(recipient);
					// log
					System.out.println("[MessageServlet-findByEmail]-[recipient]:" + recipient);
					// log
					System.out.println("[MessageServlet-findByEmail]-[has mbVO null?]:" + (mbVO == null));
					recipientMbNo = mbVO.getMbNo();
				} else {
					mbVO = mbSvc.findByUsrName(recipient);
					// log
					System.out.println("[MessageServlet-findByUsrName]-[recipient]:" + recipient);
					// log
					System.out.println("[MessageServlet-findByUsrName]-[has mbVO null?]:" + (mbVO == null));
					recipientMbNo = mbVO.getMbNo();

				}

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/milestone/select_page.jsp");
					failureView.forward(req, res);
					return;// 程式中斷
				}

				if (recipient == null || (recipient.trim()).length() == 0) {
					errorMsgs.add("請輸入收件人帳號 或 email");
					// log
					System.out.println("[MessageServlet]-[servletPath]:" + servletPath);

					// RequestDispatcher successView =
					// req.getRequestDispatcher(servletPath); // 成功轉交
					// listOneEmp.jsp
					// successView.forward(req, res);

				}

				if (topic == null || (topic.trim()).length() == 0) {
					topic = "[無主旨]";
				}

				if (mesgContent == null || (mesgContent.trim()).length() == 0) {
					mesgContent = "[無內文]";
				}
				// log
				System.out.println("[MessageServlet-findByEmail]-[recipient]:" + recipient);
				// log
				System.out.println("[MessageServlet-findByEmail]-[sender]:" + sender);
				// log
				System.out.println("[MessageServlet-findByEmail]-[topic]:" + topic);
				// log
				System.out.println("[MessageServlet-findByEmail]-[mesgContent]:" + mesgContent);

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher(servletPath);
					failureView.forward(req, res);
					return;// 程式中斷
				}

				// log
				System.out.println("[MessageServlet]-[req.getSerletPath()]:" + req.getServletPath());
				// log
				System.out.println("[MessageServlet]-[servletPath]:" + servletPath);

				/******************************************************
				 * 3.查詢完成,準備轉交(Send the Success view)
				 ******************************************************/
				MessageService mesgSvc = new MessageService();
				String senderMbNo = mbSvc.findByUsrName(sender).getMbNo();

				MessageVO messageVO = new MessageVO();
				messageVO.setMailMbNo(senderMbNo);
				messageVO.setMailRecvMbNo(recipientMbNo);
				messageVO.setMesgTitle(topic);
				messageVO.setMesgContent(mesgContent);

				mesgSvc.insertMessagebyVO(messageVO);
				req.getSession().setAttribute("sendSuccess", "信件發送成功");
				System.out.println("success");
				String url = "/message/inbox.jsp";
				res.sendRedirect(req.getContextPath()+servletPath);
//				RequestDispatcher successView = req.getRequestDispatcher(servletPath); // 成功轉交
//				// listOneEmp.jsp
//				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 *************************************/
			} catch (Exception e) {
				System.out.println("error");
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher(servletPath);
				failureView.forward(req, res);
			}
		}
		
		//enter inbox or enter sysbox
		if("inbox".equals(action) || "sysbox".equals(action)){
			
			MessageService msgSvc = new MessageService();
			MemberService mbSvc = new MemberService();
			MemberVO memberVO = mbSvc.getOneMember(mbNo);
			List<MessageVO> receivingMailList = msgSvc.findByMailRecvMbNo(mbNo);
			System.out.println("MessageServlet - mbUserName = " + memberVO.getMbUserName());
			req.setAttribute("memberVO", memberVO);
			req.setAttribute("receivingMailList", receivingMailList);
			
			String url = null;
			if("inbox".equals(action)){
				url = "/message/inbox.jsp";
			} else {
				url = "/message/sysbox.jsp";
			}
			RequestDispatcher successView = req.getRequestDispatcher(url);
			successView.forward(req, res);
			
		}
		
		//enter outbox
		if("outbox".equals(action)){
			
			MessageService msgSvc = new MessageService();
			MemberService mbSvc = new MemberService();
			MemberVO memberVO = mbSvc.getOneMember(mbNo);
			List<MessageVO> sendingMailList = msgSvc.findByMailMbNo(mbNo);
			List<MessageVO> receivingMailList = msgSvc.findByMailRecvMbNo(mbNo);
			
			System.out.println("MessageServlet - mbUserName = " + memberVO.getMbUserName());
			
			req.setAttribute("memberVO", memberVO);
			req.setAttribute("sendingMailList", sendingMailList);
			req.setAttribute("receivingMailList", receivingMailList);
			
			String url = "/message/outbox.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);
			successView.forward(req, res);
		}
		
	}

}
