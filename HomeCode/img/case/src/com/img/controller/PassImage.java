package com.img.controller;
import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.*;

import javax.naming.Context;
import javax.naming.NamingException;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.sql.DataSource;

public class PassImage extends HttpServlet{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	Connection con;

	public void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		
		
		req.setCharacterEncoding("UTF-8");
		res.setContentType("image/gif");
		ServletOutputStream out = res.getOutputStream();
		
		ResultSet rs=null;
		try {
			Statement stmt = con.createStatement();
			String MBNO=req.getParameter("MBNO");
			String WKNO=req.getParameter("wkNo");
			String index=req.getParameter("index");
		if(index.equals("0")){
			 rs = stmt.executeQuery(
				"SELECT PORTRAIT FROM MEMBER  where MBNO = '"+MBNO+"' ");
		}if(index.equals("1")){
			rs = stmt.executeQuery(
					"SELECT WKPIC FROM WORK  where wkno = '"+WKNO+"' " ); 
			 
		}else{
			
		 
				
		}

			if (rs.next()) {
				BufferedInputStream in = new BufferedInputStream(rs.getBinaryStream(1));
				byte[] buf = new byte[4 * 1024]; // 4K buffer
				int len;
				while ((len = in.read(buf)) != -1) {
					out.write(buf, 0, len);
				}
				in.close();
			} else {
				//res.sendError(HttpServletResponse.SC_NOT_FOUND);
				InputStream in = getServletContext().getResourceAsStream("/img/account.png");
				byte[] buf = new byte[in.available()];
				in.read(buf);
				out.write(buf);
				in.close();
			}
			rs.close();
			stmt.close();
		} catch (Exception e) {
			InputStream in = getServletContext().getResourceAsStream("/img/account.png");
			byte[] buf = new byte[in.available()];
			in.read(buf);
			out.write(buf);
			in.close();
		}
	}

	public void init() throws ServletException {
		try {
			Context ctx = new javax.naming.InitialContext();
			DataSource ds = (DataSource) ctx.lookup("java:comp/env/jdbc/BA103G5");
				 con = ds.getConnection();
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void destroy() {
		try {
			if (con != null) con.close();
		} catch (SQLException e) {
			System.out.println(e);
		}
	}

}
