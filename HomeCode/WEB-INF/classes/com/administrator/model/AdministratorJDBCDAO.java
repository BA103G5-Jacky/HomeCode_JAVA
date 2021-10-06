package com.administrator.model;

import java.util.*;
import java.sql.*;

public class AdministratorJDBCDAO implements AdministratorDAO_interface {
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:XE";
	String userid = "BA103G5";
	String passwd = "iii";

	private static final String INSERT_STMT = "INSERT INTO Administrator (adminNo,adminName,adminID,adminPsw,adminPosition) "
			+ "VALUES ('ADMIN'||LPAD(ADMINISTRATOR_SQ.nextval,5,'0') , ?, ?, ?, ?)";
	private static final String GET_ALL_STMT = "SELECT adminNo,adminName,adminID,adminPsw,adminPosition FROM Administrator order by adminNo";
	private static final String GET_ONE_STMT = "SELECT adminNo,adminName,adminID,adminPsw,adminPosition FROM Administrator where adminNo = ?";
	private static final String DELETE = "DELETE FROM Administrator where adminNo = ?";
	private static final String UPDATE = "UPDATE Administrator set adminNo=? ,adminName= ? ,adminID= ? ,adminPsw= ? ,adminPosition= ? where adminNo = ?";

	@Override
	public void insert(AdministratorVO administratorVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setString(1, administratorVO.getAdminName());
			pstmt.setString(2, administratorVO.getAdminID());
			pstmt.setString(3, administratorVO.getAdminPsw());
			pstmt.setString(4, administratorVO.getAdminPosition());

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
	public void update(AdministratorVO administratorVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setString(1, administratorVO.getAdminNo());
			pstmt.setString(2, administratorVO.getAdminName());
			pstmt.setString(3, administratorVO.getAdminID());
			pstmt.setString(4, administratorVO.getAdminPsw());
			pstmt.setString(5, administratorVO.getAdminPosition());
			pstmt.setString(6, administratorVO.getAdminNo());
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
	public AdministratorVO findByPrimaryKey(String adminNo) {

		AdministratorVO administratorVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setString(1, adminNo);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVo 也稱為 Domain objects
				administratorVO = new AdministratorVO();
				administratorVO.setAdminNo(rs.getString("adminNo"));
				administratorVO.setAdminName(rs.getString("adminName"));
				administratorVO.setAdminID(rs.getString("adminID"));
				administratorVO.setAdminPsw(rs.getString("adminPsw"));
				administratorVO.setAdminPosition(rs.getString("adminPosition"));

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
		return administratorVO;
	}

	@Override
	public List<AdministratorVO> getAll() {
		List<AdministratorVO> list = new ArrayList<AdministratorVO>();
		AdministratorVO administratorVO = null;

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
				administratorVO = new AdministratorVO();
				administratorVO = new AdministratorVO();
				administratorVO.setAdminNo(rs.getString("adminNo"));
				administratorVO.setAdminName(rs.getString("adminName"));
				administratorVO.setAdminID(rs.getString("adminID"));
				administratorVO.setAdminPsw(rs.getString("adminPsw"));
				administratorVO.setAdminPosition(rs.getString("adminPosition"));
				list.add(administratorVO); // Store the row in the list
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

		AdministratorJDBCDAO dao = new AdministratorJDBCDAO();

		// 新增
		AdministratorVO empVO1 = new AdministratorVO();
		empVO1.setAdminNo("admin000011");
		empVO1.setAdminName("yyy");
		empVO1.setAdminID("TS00011");
		empVO1.setAdminPsw("PS11");
		empVO1.setAdminPosition("管理員k");
		dao.insert(empVO1);

		// 修改
		AdministratorVO empVO2 = new AdministratorVO();
		empVO2.setAdminNo("admin000011");
		empVO2.setAdminName("xxx");
		empVO2.setAdminID("TS00012");
		empVO2.setAdminPsw("PS12");
		empVO2.setAdminPosition("管理員z");
		dao.update(empVO2);

		// 刪除
		dao.delete("admin000011");

		// 查詢
		AdministratorVO empVO3 = dao.findByPrimaryKey("admin00005");
		System.out.print(empVO3.getAdminNo() + ",");

		// 查詢
		List<AdministratorVO> list = dao.getAll();
		for (AdministratorVO aEmp : list) {
			System.out.print(aEmp.getAdminNo() + ",");
			System.out.print(aEmp.getAdminName() + ",");
			System.out.print(aEmp.getAdminID() + ",");
			System.out.print(aEmp.getAdminPsw() + ",");
			System.out.print(aEmp.getAdminPosition() + ",");

		}
	}
}
