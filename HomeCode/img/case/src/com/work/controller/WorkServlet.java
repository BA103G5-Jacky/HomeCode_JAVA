package com.work.controller;

import javax.servlet.http.HttpServlet;
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
import javax.servlet.http.Part;

import com.work.model.WorkService;
import com.work.model.WorkVO;

import tools.BLOB;

@MultipartConfig(fileSizeThreshold = 1024 * 1024, maxFileSize = 1000 * 1024 * 1024, maxRequestSize = 5 * 1000 * 1024
		* 1024)

public class WorkServlet extends HttpServlet {

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		HttpSession session = req.getSession();
		// 整合要注意地方！！！輸入請求參數：作品集編號wkNo
		String wkNo = req.getParameter("wkNo");
		
		// 處理刪除作品集   整合要注意地方！！！
		if ("deleteWork".equals(action)) {
			try {
				WorkService workSvc = new WorkService();
				workSvc.delete(wkNo);
			} catch (Exception e) {
				                                                         //整合要注意地方！！！
				RequestDispatcher failureView = req.getRequestDispatcher("/work_manage/work_manage.jsp");
				failureView.forward(req, res);
			}
		}
		
		//取得要修改的作品
		if("getOneUpdate".equals(action)){
			
			WorkService workSvc = new WorkService();
			WorkVO workVO = workSvc.getOneWork(wkNo);
			
			req.setAttribute("workVO", workVO);
			
			String url = "/work_manage/work_modify.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);
			successView.forward(req, res);
		}

	
			
		//修改
		if ("modifyWork".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				WorkService workSvc = new WorkService();
				WorkVO workVO = new WorkVO();
				String wkname = req.getParameter("wkName");
				if (wkname == null || (wkname.trim()).length() == 0) {
					errorMsgs.add("請輸入作品名稱");
				}
				String wkdecr = req.getParameter("wkDecr");
				String mbNo = req.getParameter("mbNo");
				byte[] wkpic = null;

//				Collection<Part> parts = req.getParts(); // Servlet3.0新增了Part介面，讓我們方便的進行檔案上傳處理
//				int parts_size = parts.size();
//				// log
//				// System.out.println("[WorkServlet]-[parts_size]:"+parts_size);
//				// //log
//				// System.out.println("[WorkServlet]-[parts.iterator()]:"+parts.iterator());
//				if (parts != null) {
//					for (Part part : parts) {
//						// log
//						System.out.println("[WorkServlet]-[part.getContentType()]" + part.getContentType());
//						if (getFileNameFromPart(part) != null && part.getContentType() != null) {
//							// log
//							System.out.println("[WorkServlet]-[Here I am !]");
//
//							// String name1 = part.getName();
//							//
//							// // I/O測試
//							InputStream in = part.getInputStream();
//
//							wkpic = new byte[in.available()]; // 4K buffer
//
//							in.read(wkpic); // 把東西寫到wkpic裡面
//
//							// log
//							System.out.println("[WorkServlet]-[wkpic.length]:" + wkpic.length);
//
//							in.close();
//
//						} else {
//							
//							wkpic = workSvc.getOneWork(wkNo).getWkpic();
//						}
//					}
//			}
				
					
					InputStream por=req.getPart("wkPic").getInputStream();//暫存BYTE陣列
					ByteArrayOutputStream tatotea=new ByteArrayOutputStream();//緩衝
					
					if(!req.getPart("wkPic").getContentType().contains("image")){
						wkpic = workSvc.getOneWork(wkNo).getWkpic();
					} else{
						int img = 0;
						byte imgtato[] = new byte[por.available()]; //紀錄讀進來長度
						while( (img = por.read(imgtato)) != -1){//假如等於-1代表沒有資料
							tatotea.write(imgtato,0,img);
				            //從緩衝區讀取buffer裡面0~length-1的位置	 
						}
						wkpic=tatotea.toByteArray();
						
					}
					
					// log
					System.out.println("[WorkServlet]-[wkname]:" + wkname);
					
					// log
					System.out.println("[WorkServlet]-[wkdecr]:" + wkdecr);
					// log
					System.out.println("[WorkServlet]-[wkpic]:" + wkpic);
					// log
					System.out.println("[WorkServlet]-[mbNo]:" + mbNo);
					// log
					System.out.println("[WorkServlet]-[wkNo]:" + wkNo);

					workVO.setWkname(wkname);
					workVO.setWkdecr(wkdecr);
					workVO.setWkpic(wkpic);
					workVO.setMbno(mbNo);
					workVO.setWkno(wkNo);
					
					if (!errorMsgs.isEmpty()) {
						req.setAttribute("workVO", workVO);
						RequestDispatcher failureView = req
								.getRequestDispatcher("/work_manage/work_modify.jsp");
						failureView.forward(req, res);
						return;
					}
					
					workSvc.update(workVO);
					             //整合要注意地方！！！
					String url = req.getContextPath() + "/member/member.do?action=getMbInfo&mbNo="+mbNo;
					res.sendRedirect(url);
				
			}
			
			 catch (Exception e) {
	
				RequestDispatcher failureView = req.getRequestDispatcher("/work_manage/work_manage.jsp");
				failureView.forward(req, res);
			}
		}

		// 處理新增作品集
		if ("addWork".equals(action)) {
			
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			
			try{
			WorkService workSvc = new WorkService();
			WorkVO workVO = new WorkVO();

			String wkname = req.getParameter("wkName");
			if (wkname == null || (wkname.trim()).length() == 0) {
				errorMsgs.add("請輸入作品名稱");
			}
			
			String wkdecr = req.getParameter("wkDecr");
			String mbNo = req.getParameter("mbNo");
			byte[] wkpic = null;

			Collection<Part> parts = req.getParts(); // Servlet3.0新增了Part介面，讓我們方便的進行檔案上傳處理
			int parts_size = parts.size();
			// log
			System.out.println("[WorkServlet]-[parts_size]:" + parts_size);
			// log
			System.out.println("[WorkServlet]-[parts.iterator()]:" + parts.iterator());

			for (Part part : parts) {
				// log
				System.out.println("[WorkServlet]-[part.getContentType()]" + part.getContentType());
				if (getFileNameFromPart(part) != null && part.getContentType() != null) {
					// log
					System.out.println("[WorkServlet]-[Here I am !]");

					// String name1 = part.getName();
					//
					// // I/O測試
					InputStream in = part.getInputStream();

					wkpic = new byte[in.available()]; // 4K buffer

					in.read(wkpic); // 把東西寫到wkpic裡面

					// log
					System.out.println("[WorkServlet]-[wkpic.length]:" + wkpic.length);

					in.close();

				}
			}
			// log
			System.out.println("[WorkServlet]-[wkname]:" + wkname);
			// log
			System.out.println("[WorkServlet]-[wkdecr]:" + wkdecr);
			// log
			System.out.println("[WorkServlet]-[wkpic]:" + wkpic);
			// log
			System.out.println("[WorkServlet]-[mbNo]:" + mbNo);

			workVO.setWkname(wkname);
			workVO.setWkdecr(wkdecr);
			workVO.setWkpic(wkpic);
			workVO.setMbno(mbNo);
			
			if (!errorMsgs.isEmpty()) {
				req.setAttribute("workVO", workVO);
				RequestDispatcher failureView = req
						.getRequestDispatcher("/work_manage/work_add.jsp");
				failureView.forward(req, res);
				return;
			}
			
			
			workSvc.insert(workVO);
			
			
			String url = req.getContextPath() + "/member/member.do?action=getMbInfo&mbNo="+mbNo;
			res.sendRedirect(url);
//			String url = "/work_manage/work_manage.jsp"; // 注意！整合時要回到瀏覽個人頁面
//			RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交
//																			// member_timeline.jsp
//			successView.forward(req, res);
		}catch(Exception e){
			// 重導入個人瀏覽頁面
			RequestDispatcher failureView = req.getRequestDispatcher("/work_manage/work_manage.jsp");
			failureView.forward(req, res);}
		

}}

	// 取出上傳的檔案名稱 (因為API未提供method,所以必須自行撰寫)
	public String getFileNameFromPart(Part part) {
		String header = part.getHeader("content-disposition");
		System.out.println("header=" + header); // 測試用
		String filename = header.substring(header.lastIndexOf("=") + 2, header.length() - 1);
		// String filename = new File(header.substring(header.lastIndexOf("=") +
		// 2, header.length() - 1)).getName();
		System.out.println(header.substring(header.lastIndexOf("=") + 2, header.length() - 1));
		// System.out.println("filename=" + filename); // 測試用
		if (filename.length() == 0) {
			return null;
		}
		return filename;
	}
}
