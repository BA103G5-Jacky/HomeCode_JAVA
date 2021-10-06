package com.livechat.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.member.model.MemberService;
import com.member.model.MemberVO;

@WebServlet("/liveChat/LiveChat")
public class LiveChat extends HttpServlet {
	private static final long serialVersionUID = 1L;
     
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String mbNo = request.getParameter("mbNo");
		String hirerNo = request.getParameter("hirerNo");
		String freelancerNo = request.getParameter("freelancerNo");
		
		MemberService mbSvc = new MemberService();
		MemberVO mbVO = mbSvc.getOneMember(mbNo);
		String mbUsrName = mbVO.getMbUserName();
		
		request.setAttribute("hirerNo", hirerNo);
		request.setAttribute("freelancerNo", freelancerNo);
		request.setAttribute("mbUsrName", mbUsrName);
		
		RequestDispatcher successView = request.getRequestDispatcher("/liveChat/liveChat.jsp");
		successView.forward(request, response);
		
		
	}

}
