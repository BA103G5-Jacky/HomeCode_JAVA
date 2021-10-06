package com.member.controller;

import java.io.*;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.cs.model.CaseService;
import com.cs.model.CaseVO;
import com.member.model.*;
import com.member_skill.model.Member_skillService;
import com.sun.net.httpserver.Authenticator.Success;
import com.work.model.WorkService;
import com.work.model.WorkVO;

import tools.MailService;


@MultipartConfig(fileSizeThreshold = 1024 * 1024, maxFileSize = 1000 * 1024 * 1024, maxRequestSize = 5 * 1000 * 1024 * 1024)
public class MemberServlet extends HttpServlet {

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");

		HttpSession session = req.getSession();
		System.out.println("MemberServlet-action = " + action);

		// 會員登入-按下登入按鈕後發送過來的請求
		if ("login".equals(action)) {
			

			List<String> errorMsgs = new ArrayList<String>();
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				String email = req.getParameter("email");
				String password = req.getParameter("password");
				if (email.trim().length() == 0 || password.trim().length() == 0) { // 檢查有沒有打字!
					errorMsgs.add("eMail及密碼不可為空白!");
				}
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("vistor_login.jsp");
					failureView.forward(req, res);
					return;
				}

				MemberService mbSvc = new MemberService();
				MemberVO memberVO = new MemberVO();
				memberVO = mbSvc.loginCheck(email);

				try {
					// 開始查詢資料瞜~

					if (!memberVO.getMbPw().equals(password)) { // 這邊對查詢出來的物件VO使用getxxx,如果出現nullpointer代表無此帳號!
						// 如果查詢出來得密碼跟他輸入不一樣,代表他密碼打錯
						errorMsgs.add("密碼錯誤!!");
						System.out.println(memberVO.getMbNo() + " : " + memberVO.getMbUserName() + "嘗試登入");
						System.out.println("但輸入錯誤的密碼: " + password);

					}

				} catch (NullPointerException ne) {

					errorMsgs.add("帳號並不存在。");
					if (!errorMsgs.isEmpty()) {
						RequestDispatcher failureView = req.getRequestDispatcher("vistor_login.jsp");
						failureView.forward(req, res);
						return;

					}

				}

				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("vistor_login.jsp");
					failureView.forward(req, res);
					return;

				}

				session.setAttribute("mbNo", memberVO.getMbNo());
				session.setAttribute("username", memberVO.getMbUserName());
				String url = null;
				if (session.getAttribute("location") != null) {
					url = (String) session.getAttribute("location");
					
				} else {
					url = req.getContextPath() + "/index.jsp";
				}
				
				res.sendRedirect(url);

			} catch (Exception e) {

				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("404");
				failureView.forward(req, res);
			}
		}
		// 會員登入結束

		// 註冊會員瞜! - 再填完註冊的資料後

		if ("signup".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>(); // 錯誤訊息箱子 要塞錯誤訊息

			MemberService mbSvc = new MemberService(); // 呼叫service準備做事瞜
			req.setAttribute("errorMsgs", errorMsgs);
			// try {

			// 接受請求參數(驗證ㄊ們輸入的東西)

			String firstname = req.getParameter("firstName"); // 驗證firstname

			if (firstname.length() == 0 || firstname.contains(" ")) {
				errorMsgs.add("名子不可為空白");
			} else if (firstname.length() > 8) {
				errorMsgs.add("名子長度不可超過8個字!");
			} else {
				req.setAttribute("firstname", firstname);
			}

			String lastname = req.getParameter("lastName"); // 驗證lastname

			if (lastname.length() == 0 || lastname.contains(" ") || lastname.length() > 13) {
				errorMsgs.add("姓名不可為空白");
			} else if (lastname.length() > 8) {
				errorMsgs.add("姓名長度不可超過8個字!");
			} else {
				req.setAttribute("lastname", lastname);
			}

			String email = req.getParameter("email"); // 驗證信箱

			if (!email.contains("@")) { // 判斷email那邊它是不是輸入信箱
				errorMsgs.add("email格示不正確!");
			} else if (email.trim().length() == 0 || email == null) { // 判斷ㄊ他有沒有打字
				errorMsgs.add("Email不可以空白!");
			}

			// 判斷有ㄇ有跟原本ㄉ會員信箱重複!
			MemberVO memberVO_checkEmail = mbSvc.emailCheck(email);

			try {

				if (email.equals(memberVO_checkEmail.geteMail())) {
					errorMsgs.add("此信箱已註冊過瞜!");
				}

			} catch (NullPointerException ne) { // 發生此例外就代表資料庫沒有

				// req.setAttribute("email", email); //此信箱註冊過,可以申請!

			}

			String password = req.getParameter("password");

			if (password.trim().length() >= 20) {
				errorMsgs.add("密碼不得超過20字!");
			}
			if (password.trim().length() <= 7) {
				errorMsgs.add("密碼至少要8個字!");
			} else {
				req.setAttribute("password", password);
			}

			String location = req.getParameter("location");

			// 如果上面有發生任何一個錯誤的話!
			if (!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req.getRequestDispatcher("visitor_signup.jsp");
				failureView.forward(req, res);
				return;
			}

			// 如果驗證都通過的話!

			MemberVO memberVO = mbSvc.addMember(lastname, firstname, "null", email, password, location, "null", "null",
					lastname, null, "null");

			session.setAttribute("memberVO", memberVO);

			RequestDispatcher seccussView = req.getRequestDispatcher("/member_front_page.jsp");

			seccussView.forward(req, res);

			// } catch (Exception se) {
			// System.out.println("i dont know what's happening");
			//
			// }

		}
		// 會員註冊結束

		// 忘記密碼開始

		if ("forgetpassword".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>(); // 錯誤訊息箱子 要塞錯誤訊息ㄉ

			MemberService mbSvc = new MemberService(); // 呼叫service準備做事
			req.setAttribute("errorMsgs", errorMsgs);
			// try {

			// 接受請求參數(驗證他們輸入的東西)

			String email = req.getParameter("email"); // 驗證信箱
			System.out.println(email);
			MemberVO memberVO = mbSvc.findByEmail(email);
			System.out.println(memberVO);
			if (!email.contains("@")) { // 判斷email那邊他是不是輸入信箱
				errorMsgs.add("email格示不正確!");
			} else if (email.trim().length() == 0 || email == null) { // 判斷他有沒有打字
				errorMsgs.add("Email不可以空白!");
			} else if(memberVO == null){
				errorMsgs.add("無此EMAIL，請重新輸入");
			}

			// 如果email有錯誤的話!
			if (!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req.getRequestDispatcher("/login/forgetPassword.jsp");
				failureView.forward(req, res);
				return;
			}

			// 如果驗證都通過的話!
			MailService mailService = new MailService();
			String userFirstName = memberVO.getMbFstName();

			String newPassword = "homecode" + ((int) (Math.random() * (100000)) + 1000);
			String to = email; // 要寄給誰
			String subject = "HomeCode!忘記密碼信"; // 主旨

			String messageText = "您好, " + userFirstName + "這是您的新密碼 : " + newPassword + "\n 請妥善保管並盡快更改密碼!";

			mbSvc.updateMbPw(newPassword, email); // 更改密碼!

			mailService.sendMail(email, subject, messageText); // 寄出新密碼信件
			
			
			String url = req.getContextPath() + "/login/sendPassword.jsp";
			res.sendRedirect(url);
//			RequestDispatcher seccussView = req.getRequestDispatcher("/sendPassword.jsp");
//
//			seccussView.forward(req, res);

		}

		// 忘記密碼結束

		// 找人才開始

		if ("SEARCH_PEOPLE".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

//			try {

				/*************************** 1.將輸入資料轉為Map **********************************/
				// 採用Map<String,String[]> getParameterMap()的方法
				// 注意:an immutable java.util.Map
				// Map<String, String[]> map = req.getParameterMap();
				session = req.getSession();
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
				
//				RequestDispatcher successView = req.getRequestDispatcher("/search/People_Search.jsp"); // 成功轉交listEmps_ByCompositeQuery.jsp
//				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 **********************************/
//			} catch (Exception e) {
//				errorMsgs.add(e.getMessage());
//				RequestDispatcher failureView = req.getRequestDispatcher("People_Search.jsp");
//				failureView.forward(req, res);
//			}
		}

				// 找人才結束

		
		
		
		//logout
		if("logout".equals(action)){
			session.removeAttribute("mbNo");
			session.removeAttribute("username");
			session.removeAttribute("location");
			String url = req.getContextPath() + "/index.jsp";
			res.sendRedirect(url);
		}
		
		//get member infomation
		if("getMbInfo".equals(action)){
			
			String mbNo = req.getParameter("mbNo");
			
			//get mb info
			MemberService mbSvc = new MemberService();
			MemberVO memberVO = mbSvc.getOneMemberNoImg(mbNo);
			req.setAttribute("memberVO", memberVO);
			
			//get mb skill
			Member_skillService mkSvc = new Member_skillService();
			List<String> mbSkillNoList = mkSvc.searchmbno(mbNo);
			req.setAttribute("mbSkillNoList", mbSkillNoList);
			
			//get mb DoneCase by hirer
			CaseService csSvc = new CaseService();
			List<CaseVO> csFreeList = csSvc.findByFreelancerNo(mbNo, "已完成");
			List<CaseVO> csHirerList = csSvc.findByHirerNo(mbNo, "已完成");
			req.setAttribute("csFreeList", csFreeList);
			req.setAttribute("csHirerList", csHirerList);
			
			//get mb workList
			WorkService wkSvc = new WorkService();
			List<WorkVO> workList = wkSvc.findByMbNo(mbNo);
			req.setAttribute("workList", workList);
			
			
			
			String url = "/member/member_profile.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);
			successView.forward(req, res);
			
			
		}
		
		
		//
		if ("getOne_For_Update".equals(action)) { // 來自listAllEmp.jsp的請求
			
			
				/***************************1.接收請求參數****************************************/
				String mbNo = req.getParameter("mbNo");
				/***************************2.開始查詢資料****************************************/
				MemberService memSvc = new MemberService();
				MemberVO memberVO = memSvc.getOneMember(mbNo);
				
				//get mb skill
				Member_skillService mkSvc = new Member_skillService();
				List<String> mbSkillNoList = mkSvc.searchmbno(mbNo);
				req.setAttribute("mbSkillNoList", mbSkillNoList);
				
				/***************************3.查詢完成,準備轉交(Send the Success view)************/
				req.setAttribute("memberVO", memberVO);         // 資料庫取出的empVO物件,存入req
				String url = "/member/ModifyMember.jsp";
				System.out.println(url);	
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
			
		}
		
	

		 if ("Update".equals(action)) { //
				
				List<String> errorMsgs = new LinkedList<String>();
				// Store this set in the request scope, in case we need to
				// send the ErrorPage view.
				req.setAttribute("errorMsgs", errorMsgs);
				
				MemberVO memberVO =new MemberVO();
				
				try {
					/***********************1.接收請求參數*************************/
					String mbNo = req.getParameter("mbNo").trim();
					memberVO.setMbNo(mbNo);
					String mbLstName = req.getParameter("mbLstName").trim();
					if (mbLstName == null || (mbLstName.trim()).length() == 0) {
						errorMsgs.add("請輸入姓氏");
					}
					memberVO.setMbLstName(mbLstName);
					
					String mbFstName = req.getParameter("mbFstName").trim();
					if (mbFstName == null || (mbFstName.trim()).length() == 0) {
						errorMsgs.add("請輸入姓名");
					}
					memberVO.setMbFstName(mbFstName);
					
					String comName= req.getParameter("comName").trim();
					memberVO.setComName(comName);
					
					String mbLoc= req.getParameter("mbLoc").trim();
					memberVO.setMbLoc(mbLoc);
					
					String mbAddress=req.getParameter("mbAddress").trim();
					memberVO.setMbAddress(mbAddress);
					
					String phone= req.getParameter("phone").trim();
					memberVO.setPhone(phone);
					
					String mbUsrName= req.getParameter("mbUsrName").trim();
					if (mbUsrName == null || (mbUsrName.trim()).length() == 0) {
						errorMsgs.add("請輸入使用名稱");
					} else {
						MemberService mbSvc = new MemberService();
						List<String> allUserName = mbSvc.getAllMbUserName();
						if(allUserName.contains(mbUsrName) && !mbSvc.getOneMember(mbNo).getMbUserName().equals(mbUsrName)){
							errorMsgs.add("該使用者名稱已有人使用");
						}
					}
					memberVO.setMbUserName(mbUsrName);
					
					String mbIntroduce= req.getParameter("mbIntroduce").trim();
					memberVO.setMbIntroduce(mbIntroduce);
					
					//Skill
					Member_skillService memskSvc = new Member_skillService();
					
					memskSvc.deleteOneMbSkill(mbNo);
					List<String> mbSkillNoList = new ArrayList<String>();
					String[] skillNo=req.getParameterValues("skillNo");
					for(int i=0; i<skillNo.length; i++){
						mbSkillNoList.add(skillNo[i]);
						memskSvc.addMember_skill(skillNo[i],mbNo);
					}
					
					
					
					MemberService mbSvc = new MemberService();
					//存圖
					InputStream por=req.getPart("poritait").getInputStream();//暫存BYTE陣列
					ByteArrayOutputStream tatotea=new ByteArrayOutputStream();//緩衝
					byte[]poritait=null;
					if(!req.getPart("poritait").getContentType().contains("image")){
						poritait = mbSvc.getOneMember(mbNo).getPortrait();
					} else{
						int img = 0;
						byte imgtato[] = new byte[por.available()]; //紀錄讀進來長度
						while( (img = por.read(imgtato)) != -1){//假如等於-1代表沒有資料
							tatotea.write(imgtato,0,img);
				            //從緩衝區讀取buffer裡面0~length-1的位置	 
						}
						poritait=tatotea.toByteArray();
						
					}
					memberVO.setPortrait(poritait);
					
					
					// Send the use back to the form, if there were errors
					if (!errorMsgs.isEmpty()) {
						req.setAttribute("memberVO", memberVO);
						req.setAttribute("mbSkillNoList", mbSkillNoList);
						
						RequestDispatcher failureView = req.getRequestDispatcher("/member/ModifyMember.jsp");
						failureView.forward(req, res);
						return;
					}
//					if (!errorMsgs.isEmpty()) {
//						req.setAttribute("member_skillVO", member_skillVO); // 
//						RequestDispatcher failureView = req
//								.getRequestDispatcher("/ModifyMember.jsp");
//						failureView.forward(req, res);
//						return;
//					}
					
					System.out.println("test");
					/***************************2.開始修改資料***************************************/
					MemberService memSvc = new MemberService();
					memSvc.updateMember(mbLstName,mbFstName,comName,mbLoc,mbAddress,phone,mbUsrName,poritait,mbIntroduce,mbNo);
					System.out.println("test1");
					
					System.out.println("success");
					
					
					
					/***************************3.查詢完成,準備轉交(Send the Success view)***********/
					String url = req.getContextPath() + "/member/member.do?action=getMbInfo&mbNo="+mbNo;
					
					res.sendRedirect(url);
//					RequestDispatcher successView = req.getRequestDispatcher(url); // 
//					successView.forward(req, res);	
					System.out.println("test2");
					
					/************************************************************/
				} catch (Exception e) {
					errorMsgs.add(e.getMessage());
					RequestDispatcher failureView = req
							.getRequestDispatcher("/ModifyMember.jsp");
					failureView.forward(req, res);
				}
			}
		 
		 if("resetPassword".equals(action)){
			 
			 String mbNo = (String) req.getSession().getAttribute("mbNo");
			 String oldPsw = req.getParameter("oldPsw").trim();
			 String newPsw = req.getParameter("newPsw").trim();
			 String checkNewPsw = req.getParameter("checkNewPsw").trim();
			 
			 List<String> errorMsgs = new LinkedList<String>();
				// Store this set in the request scope, in case we need to
				// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			
			MemberService mbSvc = new MemberService();
			MemberVO memberVO = mbSvc.getOneMember(mbNo);
			//check oldPsw
			MemberVO checkMbVO = mbSvc.loginCheck(memberVO.geteMail());
			if(!checkMbVO.getMbPw().equals(oldPsw)){
				errorMsgs.add("舊密碼輸入錯誤");
			}
			
			//double check newPsw
			if(!newPsw.equals(checkNewPsw)){
				errorMsgs.add("新密碼確認錯誤");
			}
			//if have errorMsgs back
			if (!errorMsgs.isEmpty()) {				
				RequestDispatcher failureView = req.getRequestDispatcher("/member/resetPassword.jsp");
				failureView.forward(req, res);
				return;
			}
			
			mbSvc.updateMbPw(newPsw, memberVO.geteMail());
			
			req.setAttribute("resetSuccess", "密碼重設成功");
			
			String url = "/member/resetPassword.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url); 
			successView.forward(req, res);	
			
			
		 }

	}
}
