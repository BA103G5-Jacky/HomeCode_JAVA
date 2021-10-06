package tools;

import javax.servlet.http.HttpServlet;
import java.io.*;
import java.sql.*;

import javax.naming.Context;
import javax.naming.NamingException;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.sql.DataSource;
public class DBGifReader4 extends HttpServlet{

	
	Connection con;

	public void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {

    req.setCharacterEncoding("UTF-8");
		res.setContentType("image/gif");
		ServletOutputStream out = res.getOutputStream();

		try {
			Statement stmt = con.createStatement();
      String mbno = req.getParameter("mbno");
      String mbno2 = new String(mbno.getBytes("ISO-8859-1"),"UTF-8");
			ResultSet rs = stmt.executeQuery(
				"SELECT portrait FROM member WHERE mbno='"+mbno2+"'");

			if (rs.next()) {
				BufferedInputStream in = new BufferedInputStream(rs.getBinaryStream("portrait"));
				byte[] buf = new byte[4 * 1024]; // 4K buffer
				int len;
				while ((len = in.read(buf)) != -1) {
					out.write(buf, 0, len);
				}
				in.close();
			} else {
				//res.sendError(HttpServletResponse.SC_NOT_FOUND);
				InputStream in = getServletContext().getResourceAsStream("/img/user.png");
				byte[] buf = new byte[in.available()];
				in.read(buf);
				out.write(buf);
				in.close();
			}
			rs.close();
			stmt.close();
		} catch (Exception e) {
			//System.out.println(e);
			InputStream in = getServletContext().getResourceAsStream("/img/user.png");
			byte[] buf = new byte[in.available()];
			in.read(buf);
			out.write(buf);
			in.close();
		}
	}

	public void init() throws ServletException {
		try {
			Context ctx = new javax.naming.InitialContext();
			DataSource ds = (DataSource) ctx.lookup("java:comp/env/jdbc/HOMECODE!");
			con = ds.getConnection();
		} catch (NamingException e) {
			e.printStackTrace();
		} catch (SQLException e) {
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
