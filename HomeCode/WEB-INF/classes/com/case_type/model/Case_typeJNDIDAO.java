package com.case_type.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class Case_typeJNDIDAO implements Case_typeDAO_interface{
	
	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/BA103G5");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}




	// �@�����ε{����,�w��@�Ӹ�Ʈw ,�@�Τ@��DataSource�Y�i
	
	private static final String INSERT_STMT = 
		"INSERT INTO case_type (csNo,skillNo) VALUES (?,?)";
	private static final String GET_ALL_STMT = 
		"SELECT csNo,skillNo FROM case_type order by csNo";
	private static final String GET_ONE_STMT = 
		"SELECT csNo,skillNo FROM case_type where  csNo= ?";
	private static final String DELETE = 
		"DELETE FROM case_type  where csNo = ?";
	private static final String UPDATE = 
		"UPDATE case_type SET skillNo= ? where csNo = ?";
	
	@Override
	public void insert(Case_typeVO case_typeVO) {
		// TODO Auto-generated method stub
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setString(1, case_typeVO.getCsNo());
			pstmt.setString(2, case_typeVO.getSkillNo());
		

			pstmt.executeUpdate();

			// Handle any SQL errors
	
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
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
	public void update (Case_typeVO case_typeVO) {
		// TODO Auto-generated method stub
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setString(2, case_typeVO.getCsNo());
			pstmt.setString(1, case_typeVO.getSkillNo());
		
			pstmt.executeUpdate();

	
			// Handle any driver errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
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
	public void delete(String csNo) {
		// TODO Auto-generated method stub
		
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);
			
			pstmt.setString(1,csNo);

			pstmt.executeUpdate();

			// Handle any driver errors

		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
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
	public List<Case_typeVO> findByPrimaryKey(String csNo) {
		// TODO Auto-generated method stub
		List<Case_typeVO> list = new ArrayList<Case_typeVO>();
		Case_typeVO case_typeVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);
			
			pstmt.setString(1, csNo);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				case_typeVO = new Case_typeVO();
				case_typeVO.setCsNo(rs.getString("csNo"));
				case_typeVO.setSkillNo(rs.getString("skillNo"));
				
				list.add(case_typeVO);
			
			}

			// Handle any driver errors
	
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
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
	@Override
	public List<Case_typeVO> getAll() {
		// TODO Auto-generated method stub
		List<Case_typeVO> list = new ArrayList<Case_typeVO>();
		Case_typeVO case_typeVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVO �]�٬� Domain objects
				 case_typeVO = new  Case_typeVO();
					case_typeVO.setCsNo(rs.getString("csNo"));
					case_typeVO.setSkillNo(rs.getString("skillNo"));
			
				list.add(case_typeVO); // Store the row in the list
			}

			// Handle any driver errors
	
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
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