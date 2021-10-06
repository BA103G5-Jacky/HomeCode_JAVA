package com.work.controller;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

public class WorkPicReader extends HttpServlet{
	
	// 一個應用程式中,針對一個資料庫 ,共用一個DataSource即可
			private static DataSource ds = null;
			static {
				try {
					Context ctx = new InitialContext();
					ds = (DataSource) ctx.lookup("java:comp/env/jdbc/BA103G5");
				} catch (NamingException e) {
					e.printStackTrace();
				}
			}
			
			
	

	public void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {

		Connection con = null;
		try {
			con = ds.getConnection();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		res.setContentType("image/gif");
		ServletOutputStream out = res.getOutputStream(); //----------------->由左往右
		
		String wkNo = req.getParameter("wkNo");

		try {
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(
				"SELECT wkpic FROM work WHERE wkno = '"+wkNo+"'");

			if (rs.next()) {
				BufferedInputStream in = new BufferedInputStream(rs.getBinaryStream("wkpic")); //<----------------由右往左 inputstream:rs.getBinaryStream("image")
				byte[] buf = new byte[4 * 1024]; // 4K buffer
				int len;
				while ((len = in.read(buf)) != -1) { //---------->
					out.write(buf, 0, len); //why 0? <-----------
				}
				in.close();
			} else {
				//res.sendError(HttpServletResponse.SC_NOT_FOUND);
			}
			rs.close();
			stmt.close();
		} catch (Exception e) {
			System.out.println(e);
		}
	}

}
