package com.reportedcase.controller;

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
import com.reported_case.model.Reported_caseService;
import com.reported_case.model.Reported_caseVO;

public class Reported_caseServlet extends HttpServlet{


		public void doGet(HttpServletRequest req, HttpServletResponse res)
				throws ServletException, IOException {
			doPost(req, res);
		}

		public void doPost(HttpServletRequest req, HttpServletResponse res)
				throws ServletException, IOException {

			req.setCharacterEncoding("UTF-8");
			String action = req.getParameter("action");
			System.out.println("action = "+action);
		

	        if ("insert".equals(action)) { 
				
				List<String> errorMsgs = new LinkedList<String>();
				// Store this set in the request scope, in case we need to
				// send the ErrorPage view.
				req.setAttribute("errorMsgs", errorMsgs);
				
				try {
					String rpedMb = req.getParameter("rpedMb").trim();
					String rpedCase = req.getParameter("rpedCase").trim();
					String rpReason = "";
					if(req.getParameter("rpReason") == null){
						errorMsgs.add("請選擇檢舉原因");
					} else {
						rpReason = req.getParameter("rpReason").trim();
					}					
					String rpDesc = req.getParameter("rpDesc").trim();
					System.out.println(req.getParameter("rpReason"));
					if(rpDesc == null || rpDesc.length() == 0){
						errorMsgs.add("請輸入檢舉描述");
					}
					String url = req.getParameter("url");
					req.setAttribute("url", url);
					
					CaseService csSvc = new CaseService();
		        	CaseVO caseVO= csSvc.getOneCase(rpedCase);

					Reported_caseVO reported_caseVO = new Reported_caseVO();
					reported_caseVO.setRpedMb(rpedMb);
					reported_caseVO.setRpedCase(rpedCase);
					reported_caseVO.setRpReason(rpReason);
					reported_caseVO.setRpDesc(rpDesc);
					reported_caseVO.setRpReason(rpReason);
			
					// Send the use back to the form, if there were errors
					if (!errorMsgs.isEmpty()) {
						req.setAttribute("reported_caseVO", reported_caseVO);
						req.setAttribute("caseVO", caseVO); 
						RequestDispatcher failureView = req
								.getRequestDispatcher("/Reported_case/Reported_case.jsp");
						failureView.forward(req, res);
						return;
					}

					
					System.out.println("test");
					/***************************2.開始新增資料***************************************/
					Reported_caseService RpsSvc = new Reported_caseService();
					reported_caseVO = RpsSvc.addReported_case(rpedMb, rpedCase,rpReason, rpDesc);
					/***************************3.新增完成,準備轉交(Send the Success view)***********/
//					String url = "/case/case_info.jsp";
					res.sendRedirect(req.getContextPath() + "/case/case.do?"+url);
//					RequestDispatcher successView = req.getRequestDispatcher("/case/case.do?"+url); 
//					successView.forward(req, res);				
					System.out.println("final2");
					/***************************其他可能的錯誤處理**********************************/
					} catch (Exception e) {
					errorMsgs.add(e.getMessage());
					RequestDispatcher failureView = req
							.getRequestDispatcher("/Reported_case/Reported_case.jsp");
					failureView.forward(req, res);
					System.out.println("final3");
					}
				}
	        
	        if("rpCase".equals(action)){	//reportCase from case_info.jsp
	        	
	        	String csNo = req.getParameter("csNo");
	        	CaseService csSvc = new CaseService();
	        	CaseVO caseVO= csSvc.getOneCase(csNo);
	        	
	        	
	        	String url = req.getParameter("url");
	        	req.setAttribute("url", url); 
	        	
	        	req.setAttribute("caseVO", caseVO); 
	        	RequestDispatcher failureView = req
						.getRequestDispatcher("/Reported_case/Reported_case.jsp");
				failureView.forward(req, res);
	        }
			
		}
	}
