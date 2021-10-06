package com.administrator_privilege.model;

import java.util.*;
import java.sql.*;

public class Administrator_PrivilegeJDBCDAO implements Administrator_PrivilegeDAO_interface {
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:XE";
	String userid = "BA103G5";
	String passwd = "iii";

	private static final String INSERT_STMT = "INSERT INTO Administrator_Privilege (privilegeNo,adminNo) VALUES (?, ?)";
	private static final String GET_ALL_STMT = "SELECT privilegeNo,adminNo FROM Administrator_Privilege order by adminNo";
	private static final String GET_ONE_STMT = "SELECT privilegeNo,adminNo FROM Administrator_Privilege  where privilegeNo= ? and adminNo= ?";
	private static final String DELETE = "DELETE FROM Administrator_Privilege where privilegeNo=? ,adminNo = ?";
	private static final String UPDATE = "UPDATE Administrator_Privilege set privilegeNo=?,adminNo=? where adminNo= ?";

	@Override
	public void insert(Administrator_PrivilegeVO administrator_privilegeVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setString(1, administrator_privilegeVO.getPrivilegeNo());
			pstmt.setString(2, administrator_privilegeVO.getAdminNo());

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
	public void update(Administrator_PrivilegeVO administrator_privilegeVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setString(1, administrator_privilegeVO.getPrivilegeNo());
			pstmt.setString(2, administrator_privilegeVO.getAdminNo());
			pstmt.setString(3, administrator_privilegeVO.getAdminNo());

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
	public void delete(String privilegeNo, String adminNo) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(DELETE);

			pstmt.setString(1, privilegeNo);
			pstmt.setString(2, adminNo);

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
	public Administrator_PrivilegeVO findByPrimaryKey(String privilegeNo, String adminNo) {

		Administrator_PrivilegeVO administrator_privilegeVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setString(1, privilegeNo);
			pstmt.setString(2, adminNo);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVo 也稱為 Domain objects
				administrator_privilegeVO = new Administrator_PrivilegeVO();
				administrator_privilegeVO.setPrivilegeNo(rs.getString("privilegeNo"));
				administrator_privilegeVO.setAdminNo(rs.getString("adminNo"));
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
		return administrator_privilegeVO;
	}

	@Override
	public List<Administrator_PrivilegeVO> getAll() {
		List<Administrator_PrivilegeVO> list = new ArrayList<Administrator_PrivilegeVO>();
		Administrator_PrivilegeVO administrator_privilegeVO = null;

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
				administrator_privilegeVO = new Administrator_PrivilegeVO();
				administrator_privilegeVO.setAdminNo(rs.getString("adminNo"));
				administrator_privilegeVO.setPrivilegeNo(rs.getString("privilegeNo"));

				list.add(administrator_privilegeVO); // Store the row in the
														// list
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

		Administrator_PrivilegeJDBCDAO dao = new Administrator_PrivilegeJDBCDAO();

		// 新增
		Administrator_PrivilegeVO empVO1 = new Administrator_PrivilegeVO();
		empVO1.setPrivilegeNo("functionpe00003");
		empVO1.setAdminNo("admin00002");
		dao.insert(empVO1);
		// 修改 明天問
		Administrator_PrivilegeVO empVO2 = new Administrator_PrivilegeVO();
		empVO2.setPrivilegeNo("functionpe00003");
		empVO2.setAdminNo("admin00008");
		empVO2.setAdminNo("admin00004");
		dao.update(empVO2);

		// 刪除 明天問
		dao.delete("functionpe00010", "admin00008");

		// 查詢 明天問
		Administrator_PrivilegeVO empVO3 = dao.findByPrimaryKey("functionpe00008", "admin00006");
		System.out.print(empVO3.getPrivilegeNo() + ",");
		System.out.print(empVO3.getAdminNo() + ",");

		System.out.println("---------------------");

		// 查詢
		List<Administrator_PrivilegeVO> list = dao.getAll();
		for (Administrator_PrivilegeVO aEmp : list) {
			System.out.print(aEmp.getPrivilegeNo() + ",");
			System.out.print(aEmp.getAdminNo() + ",");

		}
	}
}
