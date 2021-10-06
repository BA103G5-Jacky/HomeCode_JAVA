package com.milestone.model;

import java.sql.*;
import java.util.*;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.milestone.model.MilestoneJNDIDAO;
import com.milestone.model.MilestoneVO;

public class MilestoneJNDIDAO implements MilestoneDAO_interface{

	// 一個應用程式中,針對一個資料庫 ,共用一個DataSource即可
		private static DataSource ds = null;
		static {
			try {
				Context ctx = new InitialContext();
				ds = (DataSource) ctx.lookup("java:comp/env/jdbc/TestDB");
			} catch (NamingException e) {
				e.printStackTrace();
			}
		}

	private static final String INSERT_STMT = 
		"INSERT INTO milestone (milestoneNo,milestoneName,csNo,milestoneState,mileStartTime,mileEndTime,realEndTime) VALUES ('MSNO'||lpad(milestoneNo_sq.nextval, 7, '0'),?,?,?,?,?,?)";
	private static final String GET_ALL_STMT = 
		"SELECT milestoneNo,milestoneName,csNo,milestoneState,to_char(mileStartTime,'yyyy-mm-dd') mileStartTime,to_char(mileEndTime,'yyyy-mm-dd') mileEndTime, to_char(realEndTime,'yyyy-mm-dd') realEndTime FROM milestone order by milestoneNo";
	private static final String GET_ONE_STMT = 
		"SELECT MILESTONENO,MILESTONENAME,csNo,MILESTONESTATE, MILESTARTTIME, MILEENDTIME,  REALENDTIME FROM milestone where milestoneNO = ?";
	private static final String DELETE = 
		"DELETE FROM milestone where milestoneNo = ?";
	private static final String UPDATE = 
		"UPDATE milestone set milestoneName=?, csNo=?, milestoneState=?, mileStartTime=?, mileEndTime=?, realEndTime=? where milestoneNo = ?";

	@Override
	public void update_agree(MilestoneVO milestoneVO) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<MilestoneVO> getOneCase(String csNo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<MilestoneVO> getMileStoneByCs(String csNo) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public void insert(MilestoneVO milestoneVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setString(1, milestoneVO.getMilestoneName());
			pstmt.setString(2, milestoneVO.getCsNo());
			pstmt.setString(3, milestoneVO.getMilestoneState());
			pstmt.setDate(4, milestoneVO.getMileStartTime());
			pstmt.setDate(5, milestoneVO.getMileEndTime());
			pstmt.setDate(6, milestoneVO.getRealEndTime());

			pstmt.executeUpdate();
			
			System.out.println("one row has been insert");

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
	public void update(MilestoneVO milestoneVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setString(1, milestoneVO.getMilestoneName());
			pstmt.setString(2, milestoneVO.getCsNo());
			pstmt.setString(3, milestoneVO.getMilestoneState());
			pstmt.setDate(4, milestoneVO.getMileStartTime());
			pstmt.setDate(5, milestoneVO.getMileEndTime());
			pstmt.setDate(6, milestoneVO.getRealEndTime());
			pstmt.setString(7, milestoneVO.getMilestoneNO());

			pstmt.executeUpdate();

			System.out.println(milestoneVO.getMilestoneNO()+": has been changed");
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
	public void delete(String milestoneNo) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);

			pstmt.setString(1, milestoneNo);

			pstmt.executeUpdate();
			
			System.out.println(milestoneNo +": delete has been complete!");

			// Handle any driver errors
		}  catch (SQLException se) {
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
	public MilestoneVO findByPrimaryKey(String milestoneNO) {

		MilestoneVO milestoneVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			System.out.println(ds.getConnection());
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setString(1, milestoneNO);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVo 也稱為 Domain objects
				milestoneVO = new MilestoneVO();
				milestoneVO.setMilestoneNO(rs.getString("milestoneNO"));
				milestoneVO.setMilestoneName(rs.getString("milestoneName"));
				milestoneVO.setCsNo(rs.getString("csNo"));
				milestoneVO.setMilestoneState(rs.getString("milestoneState"));
				milestoneVO.setMileStartTime(rs.getDate("mileStartTime"));
				milestoneVO.setMileEndTime(rs.getDate("mileEndTime"));
				milestoneVO.setRealEndTime(rs.getDate("realEndTime"));
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
		return milestoneVO;
	}

	@Override
	public List<MilestoneVO> getAll() {
		List<MilestoneVO> list = new ArrayList<MilestoneVO>();
		MilestoneVO milestoneVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVO 也稱為 Domain objects
				milestoneVO = new MilestoneVO();
				milestoneVO.setMilestoneNO(rs.getString("milestoneNo"));
				milestoneVO.setMilestoneName(rs.getString("milestoneName"));
				milestoneVO.setCsNo(rs.getString("csNo"));
				milestoneVO.setMilestoneState(rs.getString("milestoneState"));
				milestoneVO.setMileStartTime(rs.getDate("mileStartTime"));
				milestoneVO.setMileEndTime(rs.getDate("mileEndTime"));
				milestoneVO.setRealEndTime(rs.getDate("realEndTime"));
				list.add(milestoneVO); // Store the row in the list
			}

			// Handle any driver errors
		}  catch (SQLException se) {
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
	public List<MilestoneVO> getMileStoneByCs_Reverse(String csNo) {
		// TODO Auto-generated method stub
		return null;
	}



	
}
