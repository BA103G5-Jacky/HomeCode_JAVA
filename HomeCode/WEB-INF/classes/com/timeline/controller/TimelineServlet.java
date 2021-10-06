package com.timeline.controller;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import com.cs.model.CaseService;
import com.cs.model.CaseVO;
import com.milestone.model.MilestoneService;
import com.milestone.model.MilestoneVO;
import com.timeline.model.TimelineService;
import com.timeline.model.TimelineVO;

@MultipartConfig(fileSizeThreshold = 1024 * 1024, maxFileSize = 1000 * 1024 * 1024, maxRequestSize = 5 * 1000 * 1024 * 1024)
public class TimelineServlet extends HttpServlet {

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		
		doPost(req, res);
	}
	String saveDirectory = "/timelinefile_uploaded";

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8"); // 先：post
		String action = req.getParameter("action");
		System.out.println("Timeline - action = " + action);

		// 修改timeline資料
		if ("delete_TimelineNote".equals(action)) {
			TimelineService timelineSvc = new TimelineService();

			try {
				
				String noteNo = req.getParameter("noteNo");
				String csNo = req.getParameter("csNo");

				timelineSvc.delete(noteNo);
				
				
				
				String url = null;
				if(req.getSession().getAttribute("android") != null){
					System.out.println("session - android = " +req.getSession().getAttribute("android"));
					req.getSession().removeAttribute("android");
					url = req.getContextPath() + "/android/AndroidServlet?action=timeline_Android&csNo="+csNo;
				} else{
					url = req.getContextPath() + "/timeline/timeline.do?action=getCaseTimeline&csNo="+csNo;
				}
				
				
				res.sendRedirect(url);
//				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 member_timeline.jsp
//				successView.forward(req, res);

			} catch (Exception e) {
				res.sendRedirect(req.getContextPath() + "/index.jsp");
//				RequestDispatcher failureView = req.getRequestDispatcher("/index.jsp");
//				failureView.forward(req, res);
			}
		}
		
		if ("update_TimelineNote".equals(action)) {

			TimelineService timelineSvc = new TimelineService();
			TimelineVO timelineVO = new TimelineVO();
			
			try {

				String noteTitle = new String(req.getParameter("timeline-title").trim());
				

				java.sql.Date recordDate = null;
				try {
					recordDate = java.sql.Date.valueOf(req.getParameter("timeline-date").trim());
				} catch (IllegalArgumentException e) {
					recordDate = new java.sql.Date(System.currentTimeMillis());
					// errorMsgs.add("請輸入日期!");
				}
			

				String noteContent = null;
				try {
					noteContent = new String(req.getParameter("timeline-content"));
				} catch (NumberFormatException e) {
					noteContent = "";
					// errorMsgs.add("薪水請填數字.");
				}
				

				String csNo = null;
				try {
					csNo = new String(req.getParameter("uploadFile_CsNo"));
				} catch (NumberFormatException e) {
					noteContent = "";
					// errorMsgs.add("薪水請填數字.");
				}
				

				String fileName = null;								
				String filePath = null;
				String noteNo = null;
				
				try {
					noteNo = new String(req.getParameter("uploadFile_NoteNo").trim());
				} catch (NumberFormatException e) {
					filePath = "";
					// errorMsgs.add("獎金請填數字.");
				}
				// file upload zone

				//String realPath = getServletContext().getRealPath(saveDirectory);
				//淺層的資料夾
				String realPath = saveDirectory+"/"+csNo;
				System.out.println("realPath=" + realPath); // 測試用
//				File fsaveDirectory = new File(realPath);
				//換電腦要改路徑
				File fsaveDirectory = new File("C:\\BA103-Tomcat\\eclipse_servlet_workspace\\"+req.getContextPath()+"\\WebContent"+realPath);
//				File fsaveDirectory = new File("C:\\BA103-Tomcat\\apache-tomcat-7.0.75\\webapps"+req.getContextPath()+realPath);
				if (!fsaveDirectory.exists())
					fsaveDirectory.mkdirs(); // 於 ContextPath 之下,自動建立目地目錄

				Collection<Part> parts = req.getParts(); // Servlet3.0新增了Part介面，讓我們方便的進行檔案上傳處理
				if(parts!=null){
					for (Part part : parts) {
						if (getFileNameFromPart(part) != null && part.getContentType() != null) {
	
							String name = part.getName();
							fileName = getFileNameFromPart(part).replace(" ", "");
							String ContentType = part.getContentType();
							long size = part.getSize();
							File f = new File(fsaveDirectory, fileName);
	
							// 利用File物件,寫入目地目錄,上傳成功
							part.write(f.toString());
	
							// 額外測試秀圖
							filePath = realPath + "/" + fileName; 
							//log
							System.out.println("[timelineServlet]-[filePath]:"+filePath);
							System.out.println("\"" + req.getContextPath() + saveDirectory + "/" + fileName);
							System.out.println("File: " + f.toString());
	
							// I/O測試
							InputStream in = part.getInputStream();
	
							byte[] buf = new byte[in.available()]; // 4K buffer
							int len;
	
							in.read(buf);
							
	
							System.out.println(buf.length);
	
							in.close();
					}

					}
					timelineVO.setFileName(fileName);
					timelineVO.setFilePath(filePath);
				}

				
				timelineVO.setNoteTitle(noteTitle);
				timelineVO.setRecordDate(recordDate);
				timelineVO.setNoteContent(noteContent);
				timelineVO.setCsNo(csNo);
				timelineVO.setNoteNo(noteNo);
				timelineSvc.update(timelineVO);

				String url = null;
				if(req.getSession().getAttribute("android") != null){
					System.out.println("session - android = " +req.getSession().getAttribute("android"));
					req.getSession().removeAttribute("android");
					url = req.getContextPath() + "/android/AndroidServlet?action=timeline_Android&csNo="+csNo;
				} else{
					url = req.getContextPath() + "/timeline/timeline.do?action=getCaseTimeline&csNo="+csNo;
				}
				res.sendRedirect(url);
//				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 member_timeline.jsp
//				successView.forward(req, res);

			} catch (Exception e) {
				res.sendRedirect(req.getContextPath() + "/index.jsp");
//				RequestDispatcher failureView = req.getRequestDispatcher("/index.jsp");
//				failureView.forward(req, res);
			}
		}

		if ("upload_TimelineNote".equals(action)) {

			TimelineService timelineSvc = new TimelineService();

//			try {

				String noteTitle = new String(req.getParameter("timeline-title").trim());
				// test
				System.out.println(noteTitle);

				java.sql.Date recordDate = null;
				try {
					recordDate = java.sql.Date.valueOf(req.getParameter("timeline-date").trim());
				} catch (IllegalArgumentException e) {
					recordDate = new java.sql.Date(System.currentTimeMillis());
					// errorMsgs.add("請輸入日期!");
				}
				// test
				System.out.println(recordDate);

				String noteContent = null;
				try {
					noteContent = new String(req.getParameter("timeline-content"));
				} catch (NumberFormatException e) {
					noteContent = "";
					// errorMsgs.add("薪水請填數字.");
				}
				//
				System.out.println(noteContent);

				String csNo = null;
				
				csNo = new String(req.getParameter("uploadFile_CsNo"));
				
				System.out.println(csNo);

				String fileName = null;

				String filePath = null;
				

				// file upload zone
				
				String realPath = saveDirectory+"/"+csNo;
				//淺的資料夾
				//String realPath = "/Users/Apple/Dropbox/Servelet/BA103_WebApp/WorkspaceforMac/Local-BA103G5_Jacky/WebContent"+saveDirectory;
				
				System.out.println("realPath=" + realPath); // 測試用
//				File fsaveDirectory = new File("C:\\BA103-Tomcat\\eclipse_servlet_workspace\\"+req.getContextPath()+"\\WebContent"+realPath);
				File fsaveDirectory = new File("C:\\BA103-Tomcat\\apache-tomcat-7.0.75\\webapps"+req.getContextPath()+realPath);
				if (!fsaveDirectory.exists())
					fsaveDirectory.mkdirs(); // 於 ContextPath 之下,自動建立目地目錄

				Collection<Part> parts = req.getParts(); // Servlet3.0新增了Part介面，讓我們方便的進行檔案上傳處理

				for (Part part : parts) {
					//log
					System.out.println("[WorkServlet]-[part.getContentType()]"+part.getContentType());
					if (getFileNameFromPart(part) != null && part.getContentType() != null) {

						String name = part.getName();
						fileName = getFileNameFromPart(part).replace(" ", "");
						String ContentType = part.getContentType();
						long size = part.getSize();
						File f = new File(fsaveDirectory, fileName);

						// 利用File物件,寫入目地目錄,上傳成功
						part.write(f.toString());

						// 額外測試秀圖
						filePath = realPath + "/" + fileName; 
						//log
						System.out.println("[timelineServlet]-[filePath]:"+filePath);
						System.out.println("\"" + realPath + "/" + fileName);
						System.out.println("File: " + f.toString());

						// I/O測試
						InputStream in = part.getInputStream();

						byte[] buf = new byte[in.available()]; // 4K buffer
						int len;

						in.read(buf);

						System.out.println(buf.length);

						in.close();

					}
				}
				
				if(noteTitle.length() == 0){
					noteTitle = fileName;
				}

				TimelineVO timelineVO = new TimelineVO();
				timelineVO.setNoteTitle(noteTitle);
				timelineVO.setRecordDate(recordDate);
				timelineVO.setNoteContent(noteContent);
				timelineVO.setFileName(fileName);
				timelineVO.setFilePath(filePath);
				timelineVO.setCsNo(csNo);

				timelineSvc.insert_withFile(timelineVO);
				
				
				String url = null;
				if(req.getSession().getAttribute("android") != null){
					System.out.println("session - android = " +req.getSession().getAttribute("android"));
					req.getSession().removeAttribute("android");
					url = req.getContextPath() + "/android/AndroidServlet?action=timeline_Android&csNo="+csNo;
				} else{
					url = req.getContextPath() + "/timeline/timeline.do?action=getCaseTimeline&csNo="+csNo;
				}
				
				
				
				res.sendRedirect(url);
//				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 member_timeline.jsp
//				successView.forward(req, res);

//			} catch (Exception e) {
//				res.sendRedirect(req.getContextPath() + "/index.jsp");
////				RequestDispatcher failureView = req.getRequestDispatcher("/index.jsp");
////				failureView.forward(req, res);
//			}
		}

		if ("getCaseTimeline".equals(action)) { 

			try {
				
				String csNo = req.getParameter("csNo");
				
				//get Case Timeline
				TimelineService timelineSvc = new TimelineService();
				List<TimelineVO> timelineList = timelineSvc.findByOneCase(csNo);
				
				//get Case info
				CaseService csSvc = new CaseService();
				CaseVO caseVO = csSvc.getOneCase(csNo);
				
				
				req.setAttribute("timelineList", timelineList);
				req.setAttribute("caseVO", caseVO);
				
				String url = "/timeline/member_timeline.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); 
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 *************************************/
			} catch (Exception e) {

				RequestDispatcher failureView = req.getRequestDispatcher("/index.jsp");
				failureView.forward(req, res);
			}
		}

		
	}

	// 取出上傳的檔案名稱 (因為API未提供method,所以必須自行撰寫)
	public String getFileNameFromPart(Part part) {
		String header = part.getHeader("content-disposition");
		System.out.println("header=" + header); // 測試用
		String filename = header.substring(header.lastIndexOf("=") + 2, header.length() - 1);
		// String filename = new File(header.substring(header.lastIndexOf("=") +
		// 2, header.length() - 1)).getName();
		System.out.println(header.substring(header.lastIndexOf("=") + 2, header.length() - 1));
		//System.out.println("filename=" + filename); // 測試用
		if (filename.length() == 0) {
			return null;
		}
		return filename;
	}

}
