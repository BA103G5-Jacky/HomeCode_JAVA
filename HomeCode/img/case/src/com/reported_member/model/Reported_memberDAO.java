package com.reported_member.model;

import java.util.*;
import java.sql.*;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class Reported_memberDAO implements Reported_memberDAO_interface {

	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/BA103G5");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}

	private static final String INSERT_STMT = "INSERT INTO  Reported_member(reportNo,reportMbNo,reportedMbNo,reportReason,reportDesc)VALUES( 'RPMB'||lpad(reported_member_sq.nextval,7,'0'),?,?,?,?)";
	private static final String GET_ALL_STMT = "SELECT reportNo,reportMbNo,reportedMbNo,reportReason,reportDesc,reportResult FROM reported_member order by reportNo";
	private static final String GET_ONE_STMT = "SELECT reportNo,reportMbNo,reportedMbNo,reportReason,reportDesc,reportResult FROM reported_member  where reportNo=?";
	private static final String DELETE = "DELETE FROM reported_member where reportNo=?";
	private static final String UPDATE = "UPDATE reported_member set reportNo=?,reportedMbNo=?,reportReason=?,reportDesc=?,reportResult=? where reportNo=?";

	@Override
	public void insert(Reported_memberVO reported_memberVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setString(1, reported_memberVO.getReportMbno());
			pstmt.setString(2, reported_memberVO.getReportedMbno());
			pstmt.setString(3, reported_memberVO.getReportReason());
			pstmt.setString(4, reported_memberVO.getReportDesc());

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
	public void update(Reported_memberVO reported_memberVO) {
		// TODO Auto-generated method stub

		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setString(1, reported_memberVO.getReportMbno());
			pstmt.setString(2, reported_memberVO.getReportedMbno());
			pstmt.setString(3, reported_memberVO.getReportReason());
			pstmt.setString(4, reported_memberVO.getReportDesc());
			pstmt.setString(5, reported_memberVO.getReportResult());
			pstmt.setString(6, reported_memberVO.getReportNo());

			pstmt.executeUpdate();

		} catch (SQLException se) {
			throw new RuntimeException("A database error occured." + se.getMessage());

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
	public void delete(String reportNo) {
		// TODO Auto-generated method stub

		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);

			pstmt.setString(1, reportNo);

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

	public Reported_memberVO findByPrimaryKey(String reportNo) {
		// TODO Auto-generated method stub
		Reported_memberVO reported_memberVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setString(1, reportNo);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVo �]�٬� Domain objects
				reported_memberVO = new Reported_memberVO();
				reported_memberVO.setReportNo(rs.getString(1));
				reported_memberVO.setReportMbno(rs.getString(2));
				reported_memberVO.setReportedMbno(rs.getString(3));
				reported_memberVO.setReportReason(rs.getString(4));
				reported_memberVO.setReportDesc(rs.getString(5));
				reported_memberVO.setReportResult(rs.getString(6));

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
		return reported_memberVO;
	}

	@Override
	public List<Reported_memberVO> getAll() {
		// TODO Auto-generated method stub
		List<Reported_memberVO> list = new ArrayList<Reported_memberVO>();
		Reported_memberVO reported_memberVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVo �]�٬� Domain objects
				reported_memberVO = new Reported_memberVO();
				reported_memberVO.setReportNo(rs.getString("reportno"));
				reported_memberVO.setReportMbno(rs.getString("reportmbno"));
				reported_memberVO.setReportedMbno(rs.getString("reportedmbno"));
				reported_memberVO.setReportReason(rs.getString("reportreason"));
				reported_memberVO.setReportDesc(rs.getString("reportdesc"));
				reported_memberVO.setReportResult(rs.getString("reportresult"));
				list.add(reported_memberVO);
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