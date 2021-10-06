package com.reported_case.model;

import java.util.*;
import java.sql.*;

public class Reported_caseJDBCDAO implements Reported_caseDAO_interface {

	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:XE";
	String userid = "BA103G5";
	String passwd = "iii";

	private static final String INSERT_STMT = "INSERT INTO Reported_Case(rpCaseNo,rpedMb,rpedCase,rpReason,rpDesc)"
			+ "Values('RPSC'||lpad( reported_case_sq.nextval, 7, '0'),? ,? ,? ,? )";

	// "INSERT INTO reported_case(rpCaseNo,rpedMb,rpedCase,rpReason,rpDesc)
	// VALUES('RPSC'||lpad( reported_case_sq.nextval,7,'0'),?,?,?,?)";
	/*
	 * "INSERT INTO reported_case(rpCaseNo,rpedMb,rpedCase,rpReason,rpDesc) VALUES('RPSC'||lpad(reported_case_sq.nextval, 5,'0'),?,?,?,?)"
	 * ;
	 */
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
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setString(1, reported_caseVO.getRpedMb());
			pstmt.setString(2, reported_caseVO.getRpedCase());
			pstmt.setString(3, reported_caseVO.getRpReason());
			pstmt.setString(4, reported_caseVO.getRpDesc());

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
	public void update(Reported_caseVO reported_caseVO) {
		// TODO Auto-generated method stub
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setString(1, reported_caseVO.getRpedMb());
			pstmt.setString(2, reported_caseVO.getRpedCase());
			pstmt.setString(3, reported_caseVO.getRpReason());
			pstmt.setString(4, reported_caseVO.getRpDesc());
			pstmt.setString(5, reported_caseVO.getRpResult());
			pstmt.setString(6, reported_caseVO.getRpCaseNo());

			pstmt.executeUpdate();

			// Handle any SQL errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
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

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(DELETE);

			pstmt.setString(1, rpCaseNo);

			pstmt.executeUpdate();

		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
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

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
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

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
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

		Reported_caseJDBCDAO RC = new Reported_caseJDBCDAO();

		// �s�W
		Reported_caseVO reported_caseVO1 = new Reported_caseVO();
		reported_caseVO1.setRpedMb("MB0000003");
		reported_caseVO1.setRpedCase("CS0000002");
		reported_caseVO1.setRpReason("違約");
		reported_caseVO1.setRpDesc("對另一方頻繁騷擾");
		RC.insert(reported_caseVO1);

		// �ק�
		// Reported_caseVO reported_caseVO2 = new Reported_caseVO();
		// reported_caseVO2.setRpCaseNo("RPSC00015");
		// reported_caseVO2.setRpedMb("MB0000027");
		// reported_caseVO2.setRpedCase("CS0000008");
		// reported_caseVO2.setRpReason("��");
		// reported_caseVO2.setRpDesc("�]��");
		// reported_caseVO2.setRpResult("���f��");
		// RC.update(reported_caseVO2);

		// �R��
		// RC.delete("RPSC00015");

		// �d��
		// Reported_caseVO reported_caseVO3 = RC.findByPrimaryKey("RPSC00014");
		// System.out.println(reported_caseVO3.getRpCaseNo() + ",");
		// System.out.println(reported_caseVO3.getRpedMb() + ",");
		// System.out.println(reported_caseVO3.getRpedCase() + ",");
		// System.out.println(reported_caseVO3.getRpReason() + ",");
		// System.out.println(reported_caseVO3.getRpDesc() + ",");
		// System.out.println(reported_caseVO3.getRpResult() + ",");
		// System.out.println("---------------------");

		// �d��
		// List<Reported_caseVO> list = RC.getAll();
		// for (Reported_caseVO ARC : list) {
		// System.out.print(ARC.getRpCaseNo() + ",");
		// System.out.print(ARC.getRpedMb() + ",");
		// System.out.print(ARC.getRpedCase() + ",");
		// System.out.print(ARC.getRpReason() + ",");
		// System.out.print(ARC.getRpDesc() + ",");
		// System.out.print(ARC.getRpResult() + ",");
		// System.out.println();
		// }
	}
}
