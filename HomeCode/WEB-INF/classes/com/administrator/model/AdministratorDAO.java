package com.administrator.model;

import java.util.*;
import java.sql.*;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class AdministratorDAO implements AdministratorDAO_interface {

	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/BA103G5");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}

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

			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setString(1, administratorVO.getAdminName());
			pstmt.setString(2, administratorVO.getAdminID());
			pstmt.setString(3, administratorVO.getAdminPsw());
			pstmt.setString(4, administratorVO.getAdminPosition());

			pstmt.executeUpdate();

			// Handle any driver errors
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

			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setString(1, administratorVO.getAdminNo());
			pstmt.setString(2, administratorVO.getAdminName());
			pstmt.setString(3, administratorVO.getAdminID());
			pstmt.setString(4, administratorVO.getAdminPsw());
			pstmt.setString(5, administratorVO.getAdminPosition());
			pstmt.setString(6, administratorVO.getAdminNo());
			pstmt.executeUpdate();

			// Handle any driver errors
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

			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);

			pstmt.setString(1, privilegeNo);

			pstmt.executeUpdate();

			// Handle any driver errors
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

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setString(1, adminNo);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVo ????????? Domain objects
				administratorVO = new AdministratorVO();
				administratorVO.setAdminNo(rs.getString("adminNo"));
				administratorVO.setAdminName(rs.getString("adminName"));
				administratorVO.setAdminID(rs.getString("adminID"));
				administratorVO.setAdminPsw(rs.getString("adminPsw"));
				administratorVO.setAdminPosition(rs.getString("adminPosition"));

			}

			// Handle any driver errors
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

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVO ????????? Domain objects
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
}
