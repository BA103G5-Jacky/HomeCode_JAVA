package com.timeline.model;

import java.sql.*;
import java.util.*;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.milestone.model.MilestoneDAO;
import com.timeline.model.TimelineVO;

public class TimelineDAO implements TimelineDAO_interface {

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

	private static final String INSERT_STMT_with_file = "INSERT INTO timeline (noteNo, noteTitle, recordDate, noteContent,csNo, uploadTime, fileName, filePath ) VALUES ('TLNO'||lpad(timeline_noteNo_sq.nextval, 7, '0'),?,?,?,?,systimestamp,?,?)";
	// check
	private static final String GET_ALL_STMT = "SELECT noteNo,noteTitle,noteContent,to_char(uploadTime,'yyyy-MM-dd HH:MI:ss') uploadTime, to_char(recordDate,'yyyy-mm-dd') recordDate, fileName, CSNO FROM timeline order by recordDate desc";
	// check
	private static final String GET_ONE_STMT = "SELECT noteNo,noteTitle,noteContent,to_char(uploadTime,'yyyy-MM-dd HH:MI:ss') uploadTime, to_char(recordDate,'yyyy-mm-dd') recordDate, fileName, filePath, CSNO  FROM timeline where csNo = ?  order by recordDate desc, uploadTIme desc";

	private static final String DELETE = "DELETE FROM timeline where noteNo = ?";
	private static final String UPDATE = "UPDATE timeline set noteTitle=?, noteContent=?, recordDate=?, fileName=?,  filePath=? where noteNo = ?";
	private static final String UPDATE_no_file = "UPDATE timeline set noteTitle=?, noteContent=?, recordDate=? where noteNo = ?";

	@Override
	public void insert_withFile(TimelineVO timelineVO) {

		System.out.println("TimelineDAO: I am here in timelineDAO");
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT_with_file);
			pstmt.setString(1, timelineVO.getNoteTitle());
			pstmt.setDate(2, timelineVO.getRecordDate());
			pstmt.setString(3, timelineVO.getNoteContent());
			pstmt.setString(4, timelineVO.getCsNo());
			pstmt.setString(5, timelineVO.getFileName());
			pstmt.setString(6, timelineVO.getFilePath());
			System.out.println("TimelineDAO: here i am aahahahhh");

			int i = pstmt.executeUpdate();
			System.out.println("TimelineDAO: 有幾筆進去= " + i);
			System.out.println("TimelineDAO: One Timeline note has been insert");

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
	public void update(TimelineVO timelineVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			System.out.println(timelineVO.getFileName());
			if (timelineVO.getFileName() != null) {
				pstmt = con.prepareStatement(UPDATE);

				pstmt.setString(1, timelineVO.getNoteTitle());
				pstmt.setString(2, timelineVO.getNoteContent());
				pstmt.setDate(3, timelineVO.getRecordDate());
				pstmt.setString(4, timelineVO.getFileName());
				pstmt.setString(5, timelineVO.getFilePath());
				pstmt.setString(6, timelineVO.getNoteNo());
			} else {
				pstmt = con.prepareStatement(UPDATE_no_file);
				pstmt.setString(1, timelineVO.getNoteTitle());
				pstmt.setString(2, timelineVO.getNoteContent());
				pstmt.setDate(3, timelineVO.getRecordDate());
				pstmt.setString(4, timelineVO.getNoteNo());

			}

			int a = pstmt.executeUpdate();

			System.out.println(a + ": has been changed");
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
	public void delete(String noteNo) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);

			pstmt.setString(1, noteNo);

			pstmt.executeUpdate();

			System.out.println(noteNo + ": delete has been complete!");

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
	public List<TimelineVO> findByOneCase(String csNo) {
		List<TimelineVO> list = new ArrayList<TimelineVO>();
		TimelineVO timelineVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			// log
			// System.out.println("TimelineDAO"+ds.getConnection());
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setString(1, csNo);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				timelineVO = new TimelineVO();
				timelineVO.setNoteNo(rs.getString("noteNo"));
				timelineVO.setNoteTitle(rs.getString("noteTitle"));
				timelineVO.setNoteContent(rs.getString("noteContent"));
				timelineVO.setUploadTime(rs.getTimestamp("uploadTime"));
				System.out.println(timelineVO.getUploadTime());
				timelineVO.setRecordDate(rs.getDate("recordDate"));
				timelineVO.setFileName(rs.getString("fileName"));
				timelineVO.setFilePath(rs.getString("FILEPATH"));
				timelineVO.setCsNo(rs.getString("csNo"));
				list.add(timelineVO);

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

	@Override
	public List<TimelineVO> getAll() {
		List<TimelineVO> list = new ArrayList<TimelineVO>();
		TimelineVO timelineVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVO 也稱為 Domain objects
				timelineVO = new TimelineVO();
				timelineVO.setNoteNo(rs.getString("noteNo"));
				timelineVO.setNoteTitle(rs.getString("noteTitle"));
				timelineVO.setNoteContent(rs.getString("noteContent"));
				timelineVO.setUploadTime(rs.getTimestamp("uploadTime"));
				timelineVO.setRecordDate(rs.getDate("recordDate"));
				timelineVO.setFileName(rs.getString("fileName"));
				timelineVO.setCsNo(rs.getString("CSNO"));
				list.add(timelineVO); // Store the row in the list
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

		MilestoneDAO dao = new MilestoneDAO();

		// // 新增
		// MilestoneVO milestoneVO1 = new MilestoneVO();
		// milestoneVO1.setMilestoneName("里程碑二");
		// milestoneVO1.setCsNo("CS0000016");
		// milestoneVO1.setMilestoneState("已提交");
		// milestoneVO1.setMileStartTime(java.sql.Date.valueOf("2017-01-01"));
		// milestoneVO1.setMileEndTime(java.sql.Date.valueOf("2017-01-01"));
		// milestoneVO1.setRealEndTime(java.sql.Date.valueOf("2017-09-01"));
		// dao.insert(milestoneVO1);

		//
		// // 修改
		// MilestoneVO mileStoneVO2 = new MilestoneVO();
		// mileStoneVO2.setMilestoneNO("MSNO0000017");
		// mileStoneVO2.setMilestoneName("里程碑二");
		// mileStoneVO2.setCsNo("cs0000016");
		// mileStoneVO2.setMilestoneState("已完成");
		// mileStoneVO2.setMileStartTime(java.sql.Date.valueOf("2017-01-01"));
		// mileStoneVO2.setMileEndTime(java.sql.Date.valueOf("2017-01-01"));
		// dao.update(mileStoneVO2);
		//
		// 刪除
		// dao.delete("MSNO0000014");
		//
		// // 查詢
		// TimelineVO milestoneVO3 = dao.findByPrimaryKey("MSNO0000014");
		// System.out.print(milestoneVO3.getMilestoneNO() + ",");
		// System.out.print(milestoneVO3.getNoteTitle() + ",");
		// System.out.print(milestoneVO3.getCsNo() + ",");
		// System.out.print(milestoneVO3.getNoteTitle() + ",");
		// System.out.print(milestoneVO3.getMileStartTime() + ",");
		// System.out.print(milestoneVO3.getMileEndTime() + ",");
		// System.out.println(milestoneVO3.getRealEndTime());
		// System.out.println("---------------------");

		// 查詢
		// List<MilestoneVO> list = dao.getAll();
		// for (MilestoneVO aMilestone : list) {
		// System.out.print(aMilestone.getMilestoneNO() + ",");
		// System.out.print(aMilestone.getMilestoneName() + ",");
		// System.out.print(aMilestone.getCsNo() + ",");
		// System.out.print(aMilestone.getMilestoneState() + ",");
		// System.out.print(aMilestone.getMileStartTime() + ",");
		// System.out.print(aMilestone.getMileEndTime() + ",");
		// System.out.print(aMilestone.getRealEndTime());
		// System.out.println();
		// }
	}
}
