package com.applicant.model;

import java.sql.*;
import java.util.*;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.applicant.model.ApplicantVO;

public class ApplicantDAO implements ApplicantDAO_interface {

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

	/* 欄位名稱 */
	// private String csNo; //fk, pk
	// private String freelancerNo; //fk, pk
	// private String hirerNo;

	// 三個問號
	private static final String INSERT_STMT = "INSERT INTO Applicant (csNO, freelancerNo, hirerNo) values (?,?,?)";
	// Applicant 應該不用修改，因為案件都是互相綁定的
	// private static final String UPDATE_STMT = "update Applicant set csNO=?,
	// freelancerNo=?, hirerNo=? where csNO=? and freelanceNO=?";
	private static final String DELETE = "DELETE from Applicant where csNo=? and freelancerNO=?";
	private static final String GET_ONE_STMT = "Select csNO, freelancerNo, hirerNo from Applicant where csNo=? and freelancerNo=?";
	private static final String GET_ALL_STMT = "Select csNO, freelancerNo, hirerNo from Applicant order by csNO";
	private static final String GET_FREEAPPLI_STMT = "SELECT csNo FROM Applicant WHERE freelancerNo = ?";
	private static final String GET_ONECASE_WHOAPPLY_STMT = "SELECT FREELANCERNO FROM APPLICANT WHERE CSNO = ?";
	private static final String DELETE_ALLAPPLY_STMT = "DELETE APPLICANT WHERE CSNO = ?";

	public void insert(ApplicantVO applicantVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setString(1, applicantVO.getCsNo());
			pstmt.setString(2, applicantVO.getFreelancerNo());
			pstmt.setString(3, applicantVO.getHirerNo());

			pstmt.executeUpdate();

			System.out.println("Data has been in");

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
	public void delete(String csNo, String freelancerNo) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);

			pstmt.setString(1, csNo);
			pstmt.setString(2, freelancerNo);

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
	public ApplicantVO findByPrimaryKey(String csNo, String freelancerNo) {

		ApplicantVO applicantVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setString(1, csNo);
			pstmt.setString(2, freelancerNo);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVo 也稱為 Domain objects
				applicantVO = new ApplicantVO();
				applicantVO.setCsNo(rs.getString("csNo"));
				applicantVO.setFreelancerNo(rs.getString("freelancerNo"));
				applicantVO.setHirerNo(rs.getString("hirerNo"));

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
		return applicantVO;
	}

	@Override
	public List<ApplicantVO> getAll() {
		List<ApplicantVO> list = new ArrayList<ApplicantVO>();
		ApplicantVO applicantVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVO 也稱為 Domain objects
				applicantVO = new ApplicantVO();
				applicantVO.setCsNo(rs.getString("csNo"));
				applicantVO.setFreelancerNo(rs.getString("freelancerNo"));
				applicantVO.setHirerNo(rs.getString("hirerNo"));
				list.add(applicantVO); // Store the row in the list
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

	public static void main(String[] args) {

		ApplicantDAO dao = new ApplicantDAO();

		// 新增
		// ApplicantVO applicantVO1 = new ApplicantVO();
		// applicantVO1.setCsNo("CS0000015");
		// applicantVO1.setFreelancerNo("MB0000044");
		// applicantVO1.setHirerNo("MB0000054");
		// dao.insert(applicantVO1);

		// // 修改

		//
		// // 刪除
		// dao.delete("CS0000015","MB0000044");
		//
		// // 查詢
		ApplicantVO applicantVO3 = dao.findByPrimaryKey("CS0000014", "MB0000044");
		System.out.print(applicantVO3.getCsNo() + ",");
		System.out.print(applicantVO3.getFreelancerNo() + ",");
		System.out.println(applicantVO3.getHirerNo());
		System.out.println("---------------------");

		//// // 查詢
		// List<ApplicantVO> list = dao.getAll();
		// for (ApplicantVO aApplicant : list) {
		// System.out.print(aApplicant.getCsNo() + ",");
		// System.out.print(aApplicant.getFreelancerNo() + ",");
		// System.out.print(aApplicant.getHirerNo());
		//
		// System.out.println();
		// }
	}

	@Override
	public List<String> getFreelancerApplicantCase(String freelancerNo) {
		List<String> list = new ArrayList<String>();
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_FREEAPPLI_STMT);
			pstmt.setString(1, freelancerNo);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				list.add(rs.getString("csNo"));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}

		return list;
	}

	@Override
	public List<String> getOneCaseWhoApply(String csNo) {
		List<String> list = new ArrayList<String>();
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONECASE_WHOAPPLY_STMT);
			pstmt.setString(1, csNo);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				list.add(rs.getString("FREELANCERNO"));
			}

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
		return list;
	}

	@Override
	public void deleteAllApply(String csNo) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE_ALLAPPLY_STMT);
			pstmt.setString(1, csNo);
			
			int a = pstmt.executeUpdate();
			System.out.println("ApplicantDAO - deleteAllApply = " + a);
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

}
