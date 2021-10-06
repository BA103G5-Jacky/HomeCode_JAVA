package com.reportedmember.controller;


import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cs.model.CaseService;
import com.cs.model.CaseVO;
import com.member.model.MemberService;
import com.member.model.MemberVO;
import com.reported_case.model.Reported_caseService;
import com.reported_member.model.Reported_memberService;
import com.reported_member.model.Reported_memberVO;

public class Reported_memberServlet extends HttpServlet{


		public void doGet(HttpServletRequest req, HttpServletResponse res)
				throws ServletException, IOException {
			doPost(req, res);
		}

		public void doPost(HttpServletRequest req, HttpServletResponse res)
				throws ServletException, IOException {

			req.setCharacterEncoding("UTF-8");
			String action = req.getParameter("action");
			

	        if ("insert".equals(action)) { // 來自addEmp.jsp??��?��??  
				
				List<String> errorMsgs = new LinkedList<String>();
				// Store this set in the request scope, in case we need to
				// send the ErrorPage view.
				req.setAttribute("errorMsgs", errorMsgs);

			try {
					
					String reportMbno = req.getParameter("reportMbno");
					String reportedMbno = req.getParameter("reportedMbno");	
					
					String reportReason = "";
					if(req.getParameter("reportReason") == null){
						errorMsgs.add("請選擇檢舉原因");
					} else {
						reportReason = req.getParameter("reportReason").trim();
					}
					
					String reportDesc = req.getParameter("reportDesc").trim();	
					if(reportDesc == null || reportDesc.length() == 0){
						errorMsgs.add("請輸入檢舉描述");
					}
					
					String url = req.getParameter("url");
					req.setAttribute("url", url);
					
					MemberService mbSvc = new MemberService();
		        	MemberVO memberVO= mbSvc.getOneMember(reportedMbno);

					Reported_memberVO reported_memberVO = new Reported_memberVO();
					reported_memberVO.setReportMbno(reportMbno);
					reported_memberVO.setReportedMbno(reportedMbno);
					reported_memberVO.setReportReason(reportReason);
					reported_memberVO.setReportDesc(reportDesc);
					
		

					// Send the use back to the form, if there were errors
					if (!errorMsgs.isEmpty()) {
						req.setAttribute("reported_memberVO", reported_memberVO); 
						req.setAttribute("memberVO", memberVO);
						RequestDispatcher failureView = req
								.getRequestDispatcher("/Reported_member/Reported_member.jsp");
						failureView.forward(req, res);
						return;
					}
					
					
					Reported_memberService rmSvc = new Reported_memberService();
					reported_memberVO = rmSvc.addReported_member(reportMbno,reportedMbno,reportReason,reportDesc);
						
					
					res.sendRedirect(req.getContextPath() + "/member/member.do?"+url);
					
				} catch (Exception e) {
					
					RequestDispatcher failureView = req
							.getRequestDispatcher("/Reported_member/Reported_member.jsp");
					failureView.forward(req, res);
					System.out.println("final3");
				}
			}
	        
	        if("rpMember".equals(action)){	//reportMember from member_profile.jsp
	        	
	        	String mbNo = req.getParameter("mbNo");
	        	MemberService mbSvc = new MemberService();
	        	MemberVO memberVO= mbSvc.getOneMember(mbNo);
	        	
	        	
	        	String url = req.getParameter("url");
	        	req.setAttribute("url", url); 
	        	
	        	req.setAttribute("memberVO", memberVO); 
	        	RequestDispatcher failureView = req
						.getRequestDispatcher("/Reported_member/Reported_member.jsp");
				failureView.forward(req, res);
	        }
		}
	}
	
