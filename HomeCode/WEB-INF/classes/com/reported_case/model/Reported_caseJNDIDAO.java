package com.reported_case.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class Reported_caseJNDIDAO implements Reported_caseDAO_interface {

	private static DataSource ds = null;

	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/BA103G5");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}

	private static final String INSERT_STMT = "INSERT INTO Reported_Case(rpCaseNo,rpedMb,rpedCase,rpReason,rpDesc)"
			+ "Values('RPSC'||lpad( reported_case_sq.nextval, 7, '0'),? ,? ,? ,? )";
	private static final String GET_ALL_STMT = "SELECT rpCaseNo,rpedMb,rpedCase,rpReason,rpDesc,rpResult FROM reported_case order by rpCaseNo";
	private static final String GET_ONE_STMT = "SELECT rpCaseNo,rpedMb,rpedCase,rpReason,rpDesc,rpResult FROM reported_case where rpCaseNo=?";
	private static final String DELETE = "DELETE FROM reported_case where rpCaseNo = ?";
	private static final String UPDATE = "UPDATE reported_case set rpedMb=? ,rpedCase=?, rpReason=?, rpDesc=?, rpResult=? where rpCaseNo = ?";

	@Override
	public void insert(Reported_caseVO reported_caseVO) {
		// TODO Auto-generated method stub

		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setString(1, reported_caseVO.getRpedMb());
			pstmt.setString(2, reported_caseVO.getRpedCase());
			pstmt.setString(3, reported_caseVO.getRpReason());
			pstmt.setString(4, reported_caseVO.getRpDesc());

			pstmt.executeUpdate();

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
	public void update(Reported_caseVO reported_caseVO) {
		// TODO Auto-generated method stub
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setString(1, reported_caseVO.getRpedMb());
			pstmt.setString(2, reported_caseVO.getRpedCase());
			pstmt.setString(3, reported_caseVO.getRpReason());
			pstmt.setString(4, reported_caseVO.getRpDesc());
			pstmt.setString(5, reported_caseVO.getRpResult());
			pstmt.setString(6, reported_caseVO.getRpCaseNo());

			pstmt.executeUpdate();

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
	public void delete(String rpCaseNo) {
		// TODO Auto-generated method stub
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);

			pstmt.setString(1, rpCaseNo);

			pstmt.executeUpdate();

			// Handle any driver errors
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
	public Reported_caseVO findByPrimaryKey(String rpCaseNo) {
		// TODO Auto-generated method stub
		Reported_caseVO reported_caseVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setString(1, rpCaseNo);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVo �]�٬� Domain objects
				reported_caseVO = new Reported_caseVO();
				reported_caseVO.setRpCaseNo(rs.getString("rpCaseNo"));
				reported_caseVO.setRpedMb(rs.getString("rpedMb"));
				reported_caseVO.setRpedCase(rs.getString("rpedCase"));
				reported_caseVO.setRpReason(rs.getString("rpReason"));
				reported_caseVO.setRpDesc(rs.getString("rpDesc"));
				reported_caseVO.setRpResult(rs.getString("rpResult"));

			}

			// Handle any driver errors

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
		return reported_caseVO;
	}

	@Override
	public List<Reported_caseVO> getAll() {
		// TODO Auto-generated method stub
		List<Reported_caseVO> list = new ArrayList<Reported_caseVO>();
		Reported_caseVO reported_caseVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVo �]�٬� Domain objects
				reported_caseVO = new Reported_caseVO();
				reported_caseVO.setRpCaseNo(rs.getString("rpCaseNo"));
				reported_caseVO.setRpedMb(rs.getString("rpedMb"));
				reported_caseVO.setRpedCase(rs.getString("rpedCase"));
				reported_caseVO.setRpReason(rs.getString("rpReason"));
				reported_caseVO.setRpDesc(rs.getString("rpDesc"));
				reported_caseVO.setRpResult(rs.getString("rpResult"));

				list.add(reported_caseVO);
			}

			// Handle any driver errors

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