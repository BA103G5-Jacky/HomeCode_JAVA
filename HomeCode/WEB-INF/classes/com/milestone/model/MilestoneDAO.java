package com.milestone.model;

import java.sql.*;
import java.util.*;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.milestone.model.MilestoneDAO;
import com.milestone.model.MilestoneVO;

public class MilestoneDAO implements MilestoneDAO_interface{

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

	private static final String INSERT_STMT = 
		"INSERT INTO milestone (milestoneNo,milestoneName,csNo,milestoneState,mileStartTime,mileEndTime, realEndTime) VALUES ('MSNO'||lpad(milestoneNo_sq.nextval, 7, '0'),?,?,?,?,?,?)";
	private static final String GET_ALL_STMT = 
		"SELECT milestoneNo,milestoneName,csNo,milestoneState,to_char(mileStartTime,'yyyy-mm-dd') mileStartTime,to_char(mileEndTime,'yyyy-mm-dd') mileEndTime, to_char(realEndTime,'yyyy-mm-dd') realEndTime FROM milestone order by milestoneNo";
	private static final String GET_ONE_STMT = 
		"SELECT MILESTONENO,MILESTONENAME,csNo,MILESTONESTATE, MILESTARTTIME, MILEENDTIME,  REALENDTIME FROM milestone where milestoneNO = ?";
	private static final String DELETE = 
		"DELETE FROM milestone where milestoneNo = ?";
	private static final String UPDATE = 
		"UPDATE milestone set milestoneName=?, csNo=?, milestoneState=?, mileStartTime=?, mileEndTime=?, realEndTime=? where milestoneNo = ?";
	private static final String UPDATE_AGREE = 
			"UPDATE milestone set  MILESTONESTATE=?, REALENDTIME = sysdate where MILESTONENO = ?";
	private static final String GET_ONE_MILESTONE_Reverse = "select * from milestone where csno=? order by MILESTONENO desc ";
	private static final String GET_ONE_MILESTONE = "select * from milestone where csno=? order by MILESTONENO ";
	private static final String GET_ALL_HIRERNO ="Select * from case order by Hirerno";
	

	@Override
	public List<MilestoneVO> getMileStoneByCs(String csNo) {
		
		List<MilestoneVO> list = new ArrayList<MilestoneVO>();
		MilestoneVO milestoneVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {

			con = ds.getConnection();
			
			/*get normal order milestone list**/
			pstmt = con.prepareStatement(GET_ONE_MILESTONE);
			pstmt.setString(1, csNo);
			rs=pstmt.executeQuery();
			
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

			System.out.println(csNo+": has been select");
			
		}catch (SQLException se) {
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
		return list;
	}
	

	@Override
	public List<MilestoneVO> getMileStoneByCs_Reverse(String csNo) {
		
		List<MilestoneVO> list = new ArrayList<MilestoneVO>();
		MilestoneVO milestoneVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {

			con = ds.getConnection();
			
			/*get reverse order milestone list**/
			pstmt = con.prepareStatement(GET_ONE_MILESTONE_Reverse);
			pstmt.setString(1, csNo);
			rs=pstmt.executeQuery();
			
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

			System.out.println(csNo+": has been select");
			
		}catch (SQLException se) {
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
		return list;
	}
	
	
	@Override
	public List<MilestoneVO> getOneCase(String csNo) {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	
	
	@Override
	public void update_agree(MilestoneVO milestoneVO) {
	
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE_AGREE);
			
			pstmt.setString(1, milestoneVO.getMilestoneState());
			pstmt.setString(2, milestoneVO.getMilestoneNO());
			
			System.out.println("DAO: milestoneState:"+milestoneVO.getMilestoneState());
			
			pstmt.executeUpdate();

			System.out.println(milestoneVO.getMilestoneNO()+": has been changed");
			
		}catch (SQLException se) {
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

	public static void main(String[] args) {

		MilestoneDAO dao = new MilestoneDAO();

//		// 新增
//		MilestoneVO milestoneVO1 = new MilestoneVO();
//		milestoneVO1.setMilestoneName("里程碑二");
//		milestoneVO1.setCsNo("CS0000016");
//		milestoneVO1.setMilestoneState("已提交");
//		milestoneVO1.setMileStartTime(java.sql.Date.valueOf("2017-01-01"));
//		milestoneVO1.setMileEndTime(java.sql.Date.valueOf("2017-01-01"));
//		milestoneVO1.setRealEndTime(java.sql.Date.valueOf("2017-09-01"));
//		dao.insert(milestoneVO1);
		
//
//		// 修改
//		MilestoneVO mileStoneVO2 = new MilestoneVO();
//		mileStoneVO2.setMilestoneNO("MSNO0000017");
//		mileStoneVO2.setMilestoneName("里程碑二");
//		mileStoneVO2.setCsNo("cs0000016");
//		mileStoneVO2.setMilestoneState("已完成");
//		mileStoneVO2.setMileStartTime(java.sql.Date.valueOf("2017-01-01"));
//		mileStoneVO2.setMileEndTime(java.sql.Date.valueOf("2017-01-01"));
//		dao.update(mileStoneVO2);
//
		// 刪除
//		dao.delete("MSNO0000014");
//
//		// 查詢
		MilestoneVO milestoneVO3 = dao.findByPrimaryKey("MSNO0000014");
		System.out.print(milestoneVO3.getMilestoneNO() + ",");
		System.out.print(milestoneVO3.getMilestoneName() + ",");
		System.out.print(milestoneVO3.getCsNo() + ",");
		System.out.print(milestoneVO3.getMilestoneState() + ",");
		System.out.print(milestoneVO3.getMileStartTime() + ",");
		System.out.print(milestoneVO3.getMileEndTime() + ",");
		System.out.println(milestoneVO3.getRealEndTime());
		System.out.println("---------------------");

		// 查詢
//		List<MilestoneVO> list = dao.getAll();
//		for (MilestoneVO aMilestone : list) {
//			System.out.print(aMilestone.getMilestoneNO() + ",");
//			System.out.print(aMilestone.getMilestoneName() + ",");
//			System.out.print(aMilestone.getCsNo() + ",");
//			System.out.print(aMilestone.getMilestoneState() + ",");
//			System.out.print(aMilestone.getMileStartTime() + ",");
//			System.out.print(aMilestone.getMileEndTime() + ",");
//			System.out.print(aMilestone.getRealEndTime());
//			System.out.println();
//		}
	}
}
