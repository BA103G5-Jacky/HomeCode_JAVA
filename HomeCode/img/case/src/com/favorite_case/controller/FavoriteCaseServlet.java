package com.favorite_case.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cs.model.CaseService;
import com.cs.model.CaseVO;
import com.favorite_case.model.FavoriteCaseService;


public class FavoriteCaseServlet extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		System.out.println("FavoriteCaseServlet - action = " + action);
		
		FavoriteCaseService fcSvc = new FavoriteCaseService();
		
		//add favoriteCase
		if("addFavorite".equals(action)){
			String mbNo = req.getParameter("mbNo");
			String csNo = req.getParameter("csNo");
			
			System.out.println("FavoriteCaseServlet - add - mbNo = " + mbNo);
			System.out.println("FavoriteCaseServlet - add - csNo = " + csNo);
			
			fcSvc.insert(mbNo, csNo);
			
		}
		
		//remove favoriteCase
		if("removeFavorite".equals(action)){
			String mbNo = req.getParameter("mbNo");
			String csNo = req.getParameter("csNo");
			
			System.out.println("FavoriteCaseServlet - remove - mbNo = " + mbNo);
			System.out.println("FavoriteCaseServlet - remove - csNo = " + csNo);
			
			fcSvc.delete(csNo, mbNo);
			
			String url = req.getContextPath() + "/favoriteCase/favoriteCase.do?action=favoriteCaseList&mbNo=" + mbNo;
			res.sendRedirect(url);
		}
		
		//show mb favoriteCase
		if("favoriteCaseList".equals(action)){
			String mbNo = req.getParameter("mbNo");
			
			//get mb favoriteCsNoList
			List<String> fcNoList = fcSvc.getOneMbFavoriteCase(mbNo);
			
			//get CsList
			CaseService csSvc = new CaseService();
			List<CaseVO> fcList = new ArrayList<CaseVO>();
			for(String fcNo : fcNoList){
				CaseVO cs = csSvc.getOneCase(fcNo);
				fcList.add(cs);
			}
			
			req.setAttribute("fcList", fcList);
			
			String url = "/favoriteCase/favoriteCase.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url); 
			successView.forward(req, res);	
		}
	}

}
