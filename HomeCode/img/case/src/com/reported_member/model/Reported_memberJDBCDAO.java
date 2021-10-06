package com.reported_member.model;

import java.util.*;

import com.reported_case.model.Reported_caseVO;
import com.reported_member.model.Reported_memberVO;

import java.sql.*;

public class Reported_memberJDBCDAO implements Reported_memberDAO_interface {

	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:XE";
	String userid = "BA103G5";
	String passwd = "iii";

	// TODO Auto-generated method stub

	private static final String INSERT_STMT = "INSERT INTO  Reported_member(reportNo,reportMbNo,reportedMbNo,reportReason,reportDesc)VALUES( 'RPMB'||lpad(reported_member_sq.nextval,5,'0'),?,?,?,?)";
	private static final String GET_ALL_STMT = "SELECT reportNo,reportMbNo,reportedMbNo,reportReason,reportDesc,reportResult FROM reported_member order by reportNo";
	private static final String GET_ONE_STMT = "SELECT reportNo,reportMbNo,reportedMbNo,reportReason,reportDesc,reportResult FROM reported_member  where reportNo=?";
	private static final String DELETE = "DELETE FROM reported_member where reportNo=?";
	private static final String UPDATE = "UPDATE reported_member set reportNo=?,reportedMbNo=?,reportReason=?,reportDesc=?,reportResult=? where reportNo=?";

	@Override
	public void insert(Reported_memberVO reported_memberVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setString(1, reported_memberVO.getReportMbno());
			pstmt.setString(2, reported_memberVO.getReportedMbno());
			pstmt.setString(3, reported_memberVO.getReportReason());
			pstmt.setString(4, reported_memberVO.getReportDesc());

			pstmt.executeUpdate();

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
	public void update(Reported_memberVO reported_memberVO) {
		// TODO Auto-generated method stub

		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setString(1, reported_memberVO.getReportMbno());
			pstmt.setString(2, reported_memberVO.getReportedMbno());
			pstmt.setString(3, reported_memberVO.getReportReason());
			pstmt.setString(4, reported_memberVO.getReportDesc());
			pstmt.setString(5, reported_memberVO.getReportResult());
			pstmt.setString(6, reported_memberVO.getReportNo());

			pstmt.executeUpdate();

		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
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
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(DELETE);

			pstmt.setString(1, reportNo);

			pstmt.executeUpdate();

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

	public Reported_memberVO findByPrimaryKey(String reportNo) {
		// TODO Auto-generated method stub
		Reported_memberVO reported_memberVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
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
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
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

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
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
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
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

		Reported_memberJDBCDAO RM = new Reported_memberJDBCDAO();

		// �s�W
		// Reported_memberVO reported_memberVO1 = new Reported_memberVO();

		// reported_memberVO1.setReportMbno("MB0000027");
		// reported_memberVO1.setReportedMbno("MB0000003");
		// reported_memberVO1.setReportReason("�Q���|");
		// reported_memberVO1.setReportDesc("�]���n");
		// reported_memberVO1.setReportResult("���f��");
		// RM.insert(reported_memberVO1);

		// �ק�
		// Reported_memberVO reported_memberVO2 = new Reported_memberVO();
		// reported_memberVO2 .setReportNo("RPMB00014");
		// reported_memberVO2 .setReportMbno("MB0000014");
		// reported_memberVO2 .setReportedMbno("MB0000015");
		// reported_memberVO2 .setReportReason("�S�z�ѴN�Q���|");
		// reported_memberVO2 .setReportDesc("�]���ڲn");
		// reported_memberVO2 .setReportResult("���f��");
		// RM.update(reported_memberVO2 );

		// �R��
		// RM.delete("RPMB00014");

		// �d��
		Reported_memberVO reported_memberVO3 = RM.findByPrimaryKey("RPMB00003");
		System.out.println(reported_memberVO3.getReportNo() + ",");
		System.out.println(reported_memberVO3.getReportMbno() + ",");
		System.out.println(reported_memberVO3.getReportedMbno() + ",");
		System.out.println(reported_memberVO3.getReportReason() + ",");
		System.out.println(reported_memberVO3.getReportDesc() + ",");
		System.out.println(reported_memberVO3.getReportResult() + ",");
		System.out.println("---------------------");

		// �d��
		// List<Reported_memberVO > list = RM.getAll();
		// for (Reported_memberVO aRM : list) {
		// System.out.println(aRM.getReportNo() + ",");
		// System.out.println(aRM.getReportMbno() + ",");
		// System.out.println(aRM.getReportedMbno() + ",");
		// System.out.println(aRM.getReportReason() + ",");
		// System.out.println(aRM.getReportDesc() + ",");
		// System.out.println(aRM.getReportResult() + ",");
		// System.out.println("---------------------");
		// }
	}
}
