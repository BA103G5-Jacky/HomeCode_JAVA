package com.applicant.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.applicant.model.ApplicantService;
import com.applicant.model.ApplicantVO;
import com.cs.model.CaseService;
import com.cs.model.CaseVO;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.member.model.MemberService;
import com.member.model.MemberVO;
import com.message.model.MessageService;

@WebServlet("/Applicant/ApplicantServlet")
public class ApplicantServlet extends HttpServlet {
	
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		String csNo = req.getParameter("csNo");
		ApplicantService applicantSvc = new ApplicantService();
		
		if("insert".equals(action)){
			String freelancerNo = req.getParameter("freelancerNo");
			String hirerNo = req.getParameter("hirerNo");
			
			ApplicantVO applicantVO = applicantSvc.insert(csNo, freelancerNo, hirerNo);
			List<String> appCsList = applicantSvc.getFreelancerApplicantCase(freelancerNo);
			List<CaseVO> applicantCaseList = new ArrayList<CaseVO>();
			CaseService csSvc = new CaseService();
			for(String appCsNo:appCsList){
				CaseVO csVO = csSvc.getOneCase(appCsNo);
				applicantCaseList.add(csVO);
			}
			String newApply = applicantVO.getCsNo();
			
			
			res.sendRedirect(req.getContextPath()+"/case/case.do?action=applicant&actor=freelancer&mbNo="+freelancerNo + "&newApply=" + newApply);
//			RequestDispatcher successView = req.getRequestDispatcher("/case/applicant_ByF.jsp");
//			successView.forward(req, res);
		}
		
		if("deleteByFree".equals(action)){
			String freelancerNo = req.getParameter("mbNo");
			
			applicantSvc.delete(csNo, freelancerNo);
			
			
			if("hirer".equals(req.getParameter("from")) ){
				CaseService csSvc = new CaseService();
				CaseVO caseVO = csSvc.getOneCase(csNo);
				MemberService mbSvc = new MemberService();
				MemberVO memberVO = mbSvc.getOneMemberNoImg(caseVO.getHirerNo());
				
				MessageService mesgScv = new MessageService();
				mesgScv.insertMessage("MB0000001", freelancerNo, "您已被拒絕申請",
						"您好\n您已被用戶 "+ memberVO.getMbLstName()+memberVO.getMbFstName() + " 拒絕案件 "+caseVO.getCsName()+" 的申請");
			}
			
			
			
			String url = "/case/case.do?mbNo=" + freelancerNo + "&action=applicant&actor=freelancer";
			RequestDispatcher successView = req.getRequestDispatcher(url);
			successView.forward(req, res);
			
		}
		
		//get one case's all applicant
		if("getAllApplicant".equals(action)){
			
			MemberService mbScv = new MemberService();
			List<MemberVO> applicantList = new ArrayList<MemberVO>();
			List<String> freeNoList = applicantSvc.getOneCaseWhoApply(csNo);
			
			for(String freeNo : freeNoList){
				MemberVO mbVO = mbScv.getOneMemberNoImg(freeNo);
				applicantList.add(mbVO);
			}
			req.setAttribute("applicantList", applicantList);
			
			String url = "/applicant/getOneCaseApplicant.jap";
			RequestDispatcher successView = req.getRequestDispatcher(url);
			successView.forward(req, res);
					
		}
		
		//checkApplicant
		if("checkApplicant".equals(action)){
			
			
			String freelancerNo = req.getParameter("freelancerNo");
			String hirerNo = req.getParameter("hirerNo");
			
			CaseService csSvc = new CaseService();
			csSvc.updateFreelancer(freelancerNo, csNo);
			int csTimes = csSvc.findByFreelancerNo(freelancerNo, "接洽中").size()
					+ csSvc.findByFreelancerNo(freelancerNo, "進行中").size()
					+ csSvc.findByFreelancerNo(freelancerNo, "已完成").size();
			
			
			MemberService mbSvc = new MemberService();
			mbSvc.updateCsTimes( csTimes, freelancerNo);
			
			applicantSvc.deleteAllApply(csNo);
			
			req.setAttribute("csNo", csNo);
			
//			String url = "/case/case.do?action=applicant&actor=hirer&mbNo="+hirerNo;
			String url = "/milestone/addMilestone.jsp";
			System.out.println(url);
			RequestDispatcher successView = req.getRequestDispatcher(url);
			successView.forward(req, res);
		}
		
		
	}

}
