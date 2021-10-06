package com.function_privilege.model;

import java.util.*;
import java.sql.*;

public class Function_PrivilegeJDBCDAO implements Function_PrivilegeDAO_interface {
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:XE";
	String userid = "BA103G5";
	String passwd = "iii";

	private static final String INSERT_STMT = "INSERT INTO Function_Privilege (privilegeNo,privilegeName) "
			+ "VALUES ( 'FP'||LPAD(FUNCPRI_SQ.nextval,3,'0') , ?)";
	private static final String GET_ALL_STMT = "SELECT privilegeNo,privilegeName FROM Function_Privilege order by privilegeNo";
	private static final String GET_ONE_STMT = "SELECT privilegeNo,privilegeName FROM Function_Privilege where privilegeNo = ?";
	private static final String DELETE = "DELETE FROM Function_Privilege where privilegeNo = ?";
	private static final String UPDATE = "UPDATE Function_Privilege set  privilegeName=? where privilegeNo=?"; // 語法
																												// set放被改項
																												// where從哪裡改

	@Override
	public void insert(Function_PrivilegeVO function_privilegeVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setString(1, function_privilegeVO.getPrivilegeName());

			pstmt.executeUpdate();

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}

	}

	@Override
	public void update(Function_PrivilegeVO function_privilegeVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setString(1, function_privilegeVO.getPrivilegeName());
			pstmt.setString(2, function_privilegeVO.getPrivilegeNo());

			pstmt.executeUpdate();

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}

	}

	@Override
	public void delete(String privilegeNo) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(DELETE);

			pstmt.setString(1, privilegeNo);

			pstmt.executeUpdate();

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}

	}

	@Override
	public Function_PrivilegeVO findByPrimaryKey(String privilegeNo) {

		Function_PrivilegeVO function_privilegeVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setString(1, privilegeNo);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVo 也稱為 Domain objects
				function_privilegeVO = new Function_PrivilegeVO();
				function_privilegeVO.setPrivilegeNo(rs.getString("privilegeNo"));

			}

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
		return function_privilegeVO;
	}

	@Override
	public List<Function_PrivilegeVO> getAll() {
		List<Function_PrivilegeVO> list = new ArrayList<Function_PrivilegeVO>();
		Function_PrivilegeVO function_privilegeVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVO 也稱為 Domain objects
				function_privilegeVO = new Function_PrivilegeVO();
				function_privilegeVO.setPrivilegeNo(rs.getString("privilegeNo"));
				function_privilegeVO.setPrivilegeName(rs.getString("privilegeName"));

				list.add(function_privilegeVO); // Store the row in the list
			}

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
		return list;
	}

	public static void main(String[] args) {

		Function_PrivilegeJDBCDAO dao = new Function_PrivilegeJDBCDAO();

		// 新增
		Function_PrivilegeVO empVO1 = new Function_PrivilegeVO();
		empVO1.setPrivilegeNo("functionpe00015");
		empVO1.setPrivilegeName("xxx管理");
		dao.insert(empVO1);

		// 修改 ????
		Function_PrivilegeVO empVO2 = new Function_PrivilegeVO();
		empVO2.setPrivilegeNo("functionpe00006");
		empVO2.setPrivilegeName("檢舉會員管理");
		dao.update(empVO2);

		// // 刪除
		dao.delete("functionpe00015");

		// 查詢
		Function_PrivilegeVO empVO3 = dao.findByPrimaryKey("functionpe00001");
		System.out.print(empVO3.getPrivilegeNo() + ",");
		System.out.print(empVO3.getPrivilegeName() + ",");
		System.out.println("---------------------");

		// // 查詢
		List<Function_PrivilegeVO> list = dao.getAll();
		for (Function_PrivilegeVO aEmp : list) {
			System.out.print(aEmp.getPrivilegeNo() + ",");
			System.out.print(aEmp.getPrivilegeName() + ",");

		}
	}
}