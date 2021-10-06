package com.timeline.model;

import java.sql.*;
import java.sql.Date;
import java.util.*;


import com.timeline.model.TimelineVO;


public class TimelineJDBCDAO implements TimelineDAO_interface {
	
	//內部類別可以宣告static
	public static final class time{
		
	}
	
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@10.211.55.3:1521:XE";
	String userid = "BA103G5";
	String passwd = "iii";
	
	public static final String INSERT_STMT = "INSERT INTO Timeline (noteNo, noteTitle, noteContent, csNo, "
			+ "UPLOADEDATE, recordDate, fileName, filePath) "
			+ "VALUES (('TLNO' || lpad(noteNO_sq.nextval, 7, '0')), ?, ?, ?, ?, ?, ?, ?)";
	
	public static final String UPDATE = "UPDATE Timeline SET noteTitle=?, noteContent=?, csNo=?, "
			+ "UPLOADEDATE=?, recordDate=?, fileName=?, filePath=? where noteNO=? ";
	public static final String DELETE = "DELETE FROM Timeline where noteNo= ?";
	
	public static final String GET_ONE_STMT = "SELECT noteNo, noteTitle, noteContent, csNo, to_char(UPLOADEDATE,'yyyy-mm-dd') UPLOADEDATE, to_char(RECORDDATE,'yyyy-mm-dd')  RECORDDATE, fileName, filePath FROM Timeline WHERE noteNo=?";
	
	public static final String GET_ALL_STMT = "SELECT noteNo, noteTitle, noteContent, csNo, "
			+ "to_char(UPLOADTIME,'yyyy-mm-dd HH:MM:SS') UPLOADTIME, to_char(RECORDDATE,'yyyy-mm-dd')  RECORDDATE, "
			+ "fileName, filePath FROM Timeline ORDER BY noteNo";


	@Override
	public void insert_withFile(TimelineVO timelineVO) {
		
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);

			
			//小兵拿工具 
			pstmt.setString(1, timelineVO.getNoteTitle());
			pstmt.setString(2, timelineVO.getNoteContent());
			pstmt.setString(3, timelineVO.getCsNo());
			pstmt.setTimestamp(4, timelineVO.getUploadTime());
			pstmt.setDate(5, timelineVO.getRecordDate());
			pstmt.setString(6, timelineVO.getFileName());
			pstmt.setString(7, timelineVO.getFilePath());

			pstmt.executeUpdate();
			
			System.out.println("one row has been insert");

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
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
	public void update(TimelineVO timelineVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setString(1, timelineVO.getNoteTitle());
			pstmt.setString(2, timelineVO.getNoteContent());
			pstmt.setString(3, timelineVO.getCsNo());
			pstmt.setTimestamp(4, timelineVO.getUploadTime());
			pstmt.setDate(5, timelineVO.getRecordDate());
			pstmt.setString(6, timelineVO.getFileName());
			pstmt.setString(7, timelineVO.getFilePath());
			pstmt.setString(8, timelineVO.getNoteNo());

			pstmt.executeUpdate();

			System.out.println(timelineVO.getNoteNo()+": has been changed");
			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
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
	public void delete(String noteNo) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(DELETE);

			pstmt.setString(1, noteNo);

			pstmt.executeUpdate();
			
			System.out.println(noteNo +": delete has been complete!");

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
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
	//把全數的大兵用一輛一輛VO廠牌的巴士...裝載在火車上...運送回來
	public List<TimelineVO> getAll() {
		
		
		List<TimelineVO> list = new ArrayList<TimelineVO>();
		TimelineVO timelineVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			
			//故事也是發生在一個被挾持的場景...要把全數的美國大兵救回來...沒有特定人物(get all)
			//索性個連隊都有一名僥倖的大兵脫困...因此長官跟著“衛星定位器(Class.forName)”找到了他們
			Class.forName(driver);
			//長官(DriverManager)...開著悍馬車(getConnection)...醫護站(Connection)的903號診間(con)來探望僥倖的大兵...
			//並叫僥倖的大兵輸入被挾持連隊的地點(url)...挾持人(userid)...挾持人身分證(passwd)
			con = DriverManager.getConnection(url, userid, passwd);
			//長官馬上從診間(con)...以線索(Get_All_STMT)...通知ＰＳ部隊(prepareStatement)...的救援兵(pstmt)去救援
			pstmt = con.prepareStatement(GET_ALL_STMT);
			//救援兵(pstmt)...前去搜查(executeQuery()...並用(rs)運輸機送回
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVO 也稱為 Domain objects
				//找一部牌子叫VO的巴士來載人
				timelineVO = new TimelineVO();
				//從運輸機把人拿出來...再安排人員上巴士的座位...
				timelineVO.setNoteNo(rs.getString("noteNo"));
				timelineVO.setNoteTitle(rs.getString("noteTitle"));
				timelineVO.setNoteContent(rs.getString("noteContent"));
				timelineVO.setCsNo(rs.getString("csNo"));
				
				timelineVO.setUploadTime(rs.getTimestamp("UPLOADTIME"));
				timelineVO.setRecordDate(rs.getDate("RECORDDATE"));
				
				timelineVO.setFileName(rs.getString("fileName"));
				timelineVO.setFilePath(rs.getString("filePath"));
				
				//但是巴士太多所以...改用火車將巴士裝載上去運送回華府
				list.add(timelineVO); // Store the row in the list
			}

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (rs != null) {
				try {
					//隱藏運輸機
					rs.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (pstmt != null) {
				try {
					//隱藏救援兵
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					//隱藏救護站
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
		return list;
	}

	@Override
	//noteNo是雷恩的名字
	public List<TimelineVO> findByOneCase(String noteNo) {
		List<TimelineVO> list = null;
		TimelineVO timelineVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			//有一個倖存的大兵逃出來...帶著衛星定位器
			Class.forName(driver);
			//長官知道消息後趕來...倖存的大兵目前休息的醫護站...要輸入雷恩的位置, 國家名稱，國碼
			con = DriverManager.getConnection(url, userid, passwd);
			//從醫護站...通知PS救援部隊 ...找到雷恩的線索
			pstmt = con.prepareStatement(GET_ONE_STMT);
			//PS大兵 就用 拿工具 把準備把雷恩找出來
			pstmt.setString(1, noteNo);
			//PS大兵就馬上去找雷恩大兵跟隊友...最後用rs運輸機送回
			rs = pstmt.executeQuery();
			
			//當rs運輸機有人員回來時候
			while (rs.next()) {
				// empVo 也稱為 Domain objects
				//找一部牌子叫VO的巴士來載人
				timelineVO = new TimelineVO();
				//從運輸機把人拿出來...再安排人員上巴士的座位...送回華府
				timelineVO.setNoteNo(rs.getString("noteNo"));
				timelineVO.setNoteTitle(rs.getString("noteTitle"));
				timelineVO.setNoteContent(rs.getString("noteContent"));
				timelineVO.setCsNo(rs.getString("csNo"));
				timelineVO.setUploadTime(rs.getTimestamp("UPLOADTIME"));
				timelineVO.setRecordDate(rs.getDate("recordDate"));
				timelineVO.setFileName(rs.getString("fileName"));
				timelineVO.setFilePath(rs.getString("filePath"));

			}

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
			// Handle any SQL errors
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
	
	public TimelineVO getOneByNoteNo(String noteNo){
		
		TimelineVO timelineVO = new TimelineVO();
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try{
		Class.forName(driver);
		con = DriverManager.getConnection(url, userid, passwd);
		pstmt = con.prepareStatement(GET_ONE_STMT);
		
		
		pstmt.setString(1,noteNo);
		
		rs = pstmt.executeQuery();
		
		while(rs.next()){
			
			timelineVO.setNoteNo(rs.getString("noteNO"));
			timelineVO.setNoteTitle(rs.getString("noteTitle"));
			timelineVO.setNoteContent(rs.getString("noteContent"));
			timelineVO.setCsNo(rs.getString("csNo"));
			timelineVO.setUploadTime(rs.getTimestamp("uploadTIME"));
			timelineVO.setRecordDate(rs.getDate("recordDate"));
			timelineVO.setFileName(rs.getString("fileName"));
			timelineVO.setFilePath(rs.getString("filePath"));
			
		}
		
		
		
		}catch(ClassNotFoundException e){
			
			System.out.println(e.getMessage());
			
		}catch(SQLException se){
			
			System.out.println(se.getMessage());
			
		}finally{
			
			if(rs!=null){
				try {
					rs.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
			if(pstmt!=null){
				try {
					pstmt.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
			if(con!=null){
				try {
					con.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
		}
		
		
		
		
		
		return timelineVO;
	}
	
	
	public static void main(String args[]){
		TimelineJDBCDAO dao = new TimelineJDBCDAO();

//		// 新增
//		TimelineVO timelineVO1 = new TimelineVO();
//		timelineVO1.setNoteTitle("我叫小鶴");
//		timelineVO1.setNoteContent("我是內容");
//		timelineVO1.setCsNo("CS0000014");
//		timelineVO1.setUploadDate(java.sql.Date.valueOf("2017-01-01"));
//		timelineVO1.setRecordDate(java.sql.Date.valueOf("2017-09-01"));
//		timelineVO1.setFileName("會議記錄一");
//		timelineVO1.setFilePath("c://.....");
//
//		dao.insert(timelineVO1);
//		
//		noteNo;
//		noteTitle;
//		noteContent;
//		csNo;
//		 uploadDate;
//		 recordDate;
//		fileName;
//		filePath;
		
//
//		// 修改
//		TimelineVO timelineVO2 = new TimelineVO();
//		timelineVO2.setNoteNo("TLNO0000025");
//		timelineVO2.setNoteTitle("我叫小鶴");
//		timelineVO2.setNoteContent("我是內容");
//		timelineVO2.setCsNo("CS0000014");
//		timelineVO2.setUploadDate(java.sql.Date.valueOf("2017-01-01"));
//		timelineVO2.setRecordDate(java.sql.Date.valueOf("2017-09-01"));
//		timelineVO2.setFileName("會議記錄一");
//		timelineVO2.setFilePath("c://.....");
//		dao.update(timelineVO2);
//
		// 刪除
//		dao.delete("TLNO0000025");
//
		// 查詢
//		TimelineVO timelineVO3 = dao.findByPrimaryKey("NOTE0000002");
//		System.out.print(timelineVO3.getNoteNo() + ",");
//		System.out.print(timelineVO3.getNoteTitle() + ",");
//		System.out.print(timelineVO3.getNoteContent() + ",");
//		System.out.print(timelineVO3.getCsNo() + ",");
//		System.out.print(timelineVO3.getUploadTime() + ",");
//		System.out.print(timelineVO3.getRecordDate() + ",");
//		System.out.println(timelineVO3.getFileName()+ ",");
//		System.out.println(timelineVO3.getFilePath());
//
//		System.out.println("---------------------");

		// 查詢
		List<TimelineVO> list = dao.getAll();
		for (TimelineVO aTimeline : list) {
			System.out.print(aTimeline.getNoteNo() + ",");
			System.out.print(aTimeline.getNoteTitle() + ",");
			System.out.print(aTimeline.getNoteContent() + ",");
			System.out.print(aTimeline.getCsNo() + ",");
			System.out.print(aTimeline.getUploadTime() + ",");
			System.out.print(aTimeline.getRecordDate() + ",");
			System.out.println(aTimeline.getFileName()+ ",");
			System.out.println(aTimeline.getFilePath());
			System.out.println();
		}
		
		
	}

}
