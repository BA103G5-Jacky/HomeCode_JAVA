package com.system_variables.model;

import java.util.*;
import java.sql.*;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class System_VariablesDAO implements System_VariablesDAO_interface {

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
	private static final String INSERT_STMT = "INSERT INTO System_Variables (svNo,svname,svValue) "
			+ "VALUES ( 'SV'||LPAD(SYSTEMVAR_SQ.nextval, 4, '0'), ?, ?)";
	private static final String GET_ALL_STMT = "SELECT svNo,svname, svValue FROM System_Variables order by svNo";
	private static final String GET_ONE_STMT = "SELECT svNo,svname,svValue FROM System_Variables where svNo = ?";
	private static final String DELETE = "DELETE FROM System_Variables where svNo = ?";
	private static final String UPDATE = "UPDATE System_Variables set svname=?, svValue=? where svNo= ?";

	@Override
	public void insert(System_VariablesVO system_variablesVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setString(1, system_variablesVO.getSvname());
			pstmt.setInt(2, system_variablesVO.getSvValue());
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
	public void update(System_VariablesVO system_variablesVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setString(1, system_variablesVO.getSvname());
			pstmt.setInt(2, system_variablesVO.getSvValue()); // int=integer??
			pstmt.setString(3, system_variablesVO.getSvNo());

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
	public void delete(String svNo) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);

			pstmt.setString(1, svNo);

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
	public System_VariablesVO findByPrimaryKey(String svNo) {

		System_VariablesVO system_variablesVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setString(1, svNo);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVo 也稱為 Domain objects
				system_variablesVO = new System_VariablesVO();
				system_variablesVO.setSvNo(rs.getString("svNo"));

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
		return system_variablesVO;
	}

	@Override
	public List<System_VariablesVO> getAll() {
		List<System_VariablesVO> list = new ArrayList<System_VariablesVO>();
		System_VariablesVO system_variablesVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVO 也稱為 Domain objects
				system_variablesVO = new System_VariablesVO();
				system_variablesVO.setSvNo(rs.getString("svNo"));
				system_variablesVO.setSvname(rs.getString("svname"));
				system_variablesVO.setSvValue(rs.getInt("svValue"));
				list.add(system_variablesVO); // Store the row in the list
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