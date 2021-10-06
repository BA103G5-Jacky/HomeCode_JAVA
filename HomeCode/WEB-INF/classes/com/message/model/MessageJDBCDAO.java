package com.message.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MessageJDBCDAO implements MessageDAO_interface{
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:xe";
	String userid = "BA103G5";
	String passwd = "iii";
	
	public final static String FIND_BYMAILMB_STMT = "SELECT MESGNO, MAILRECVMBNO, MESGTITLE, MESGCONTENT, MAILTIME"
			+ " FROM MESSAGE WHERE MAILMBNO = ? and ISDELETEBYMAILMB = '否' ";
	public final static String FIND_BYRECVMB_STMT = "SELECT MESGNO, MAILMBNO, MESGTITLE, MESGCONTENT, READSTATUS, MAILTIME "
			+ "FROM MESSAGE WHERE MAILRECVMBNO = ? and ISDELETEBYRECVMB = '否' ";
	public final static String FIND_BYADMIN_STMT = "SELECT MESGNO, MAILRECVMBNO, MESGTITLE, MESGCONTENT, READSTATUS, MAILTIME "
			+ "FROM MESSAGE WHERE MAILMBNO = 'MB0000001' AND MAILRECVMBNO = ?";
	public final static String DELETE_BYMAILMB_STMT = "UPDATE MESSAGE SET ISDELETEBYMAILMB = ? WHERE MESGNO = ?";
	public final static String DELETE_BYRECVMB_STMT = "UPDATE MESSAGE SET ISDELETEBYRECVMB = ? WHERE MESGNO = ?";
	public final static String INSERT_STMT = "Insert Into Message (mesgNo, mailMbNo, mailRecvMbNo, mesgTitle, mesgContent)"
			+ "Values('MSG'||lpad(Message_sq.nextval, 7, '0'), ?, ?, ?, ?)";
	public final static String UPDATE_READSTATUS_STMT = "UPDATE MESSAGE SET READSTATUS = '已讀' WHERE MESGNO = ?";
	
	
	@Override
	public void insert(MessageVO messageVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);
			
			pstmt.setString(1, messageVO.getMailMbNo());
			pstmt.setString(2, messageVO.getMailRecvMbNo());
			pstmt.setString(3, messageVO.getMesgTitle());
			pstmt.setString(4, messageVO.getMesgContent());
			
			int a = pstmt.executeUpdate();
			System.out.println("更新了"+a+"筆");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if(pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if(con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}		
	}

	@Override
	public void updateReadStatus(MessageVO messageVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE_READSTATUS_STMT);
			
			pstmt.setString(1, messageVO.getReadStatus());
			pstmt.setString(2, messageVO.getMesgNo());
			
			int a = pstmt.executeUpdate();
			System.out.println("更新了"+a+"筆");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if(pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if(con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}		
	}

	@Override
	public void deleteByMailMb(String mesgNo) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(DELETE_BYMAILMB_STMT);
			
			pstmt.setString(1, "是");
			pstmt.setString(2, mesgNo);
			
			int a = pstmt.executeUpdate();
			System.out.println("更新了"+a+"筆");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if(pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if(con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		
	}
	
	@Override
	public void deleteByRecvMb(String mesgNo) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(DELETE_BYRECVMB_STMT);
			
			pstmt.setString(1, "是");
			pstmt.setString(2, mesgNo);
			
			int a = pstmt.executeUpdate();
			System.out.println("更新了"+a+"筆");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if(pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if(con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		
	}


	@Override
	public List<MessageVO> findByMailMbNo(String mailMbNo) {
		List<MessageVO> list = new ArrayList<MessageVO>();
		MessageVO messageVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(FIND_BYMAILMB_STMT);
			pstmt.setString(1, mailMbNo);
			rs = pstmt.executeQuery();
			
			while(rs.next()){
				messageVO = new MessageVO();
				messageVO.setMailRecvMbNo(rs.getString(1));
				messageVO.setMesgTitle(rs.getString(2));
				messageVO.setMesgContent(rs.getString(3));
				messageVO.setMailTime(rs.getDate(4));
				list.add(messageVO);
			}
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if(rs != null){
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if(pstmt != null){
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if(con != null){
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
	public List<MessageVO> findByMailRecvMbNo(String mailRecvMbNo) {
		List<MessageVO> list = new ArrayList<MessageVO>();
		MessageVO messageVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(FIND_BYRECVMB_STMT);
			pstmt.setString(1, mailRecvMbNo);
			rs = pstmt.executeQuery();
			
			while(rs.next()){
				messageVO = new MessageVO();
				messageVO.setMailRecvMbNo(rs.getString(1));
				messageVO.setMesgTitle(rs.getString(2));
				messageVO.setMesgContent(rs.getString(3));
				messageVO.setReadStatus(rs.getString(4));
				messageVO.setMailTime(rs.getDate(5));
				list.add(messageVO);
			}
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if(rs != null){
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if(pstmt != null){
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if(con != null){
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return list;
	}
	
	public List<MessageVO> findByAdmin(String mailRecvMbNo) {
		List<MessageVO> list = new ArrayList<MessageVO>();
		MessageVO messageVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(FIND_BYADMIN_STMT);
			pstmt.setString(1, mailRecvMbNo);
			rs = pstmt.executeQuery();
			
			while(rs.next()){
				messageVO = new MessageVO();
				messageVO.setMailRecvMbNo(rs.getString(1));
				messageVO.setMesgTitle(rs.getString(2));
				messageVO.setMesgContent(rs.getString(3));
				messageVO.setReadStatus(rs.getString(4));
				messageVO.setMailTime(rs.getDate(5));
				list.add(messageVO);
			}
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if(rs != null){
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if(pstmt != null){
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if(con != null){
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return list;
	}

	
	public static void main(String[] args) {
		
		MessageJDBCDAO dao = new MessageJDBCDAO();
		//insert message
//		MessageVO messageVO = new MessageVO();
//		messageVO.setMailMbNo("MB0000001");
//		messageVO.setMailRecvMbNo("MB0000016");
//		messageVO.setMesgTitle("系統信件");
//		messageVO.setMesgContent("系統測試");		
//		dao.insert(messageVO);
		
		//update readStatus
//		MessageVO messageVO2 = new MessageVO();
//		messageVO2.setReadStatus("已讀");
//		messageVO2.setMesgNo("MSG0000001");
//		dao.updateReadStatus(messageVO2);
		
		//delete by mailMb
//		dao.deleteByMailMb("MSG0000001");
		
		//delete by RecvMb
//		dao.deleteByRecvMb("MSG0000001");
		
		//find by mail member
//		List<MessageVO> list = dao.findByMailMbNo("MB0000009");
//		for(MessageVO messageVO : list){
//			System.out.print(messageVO.getMailRecvMbNo() + ", ");
//			System.out.print(messageVO.getMesgTitle() + ", ");
//			System.out.print(messageVO.getMesgContent() + ", ");
//			System.out.print(messageVO.getMailTime() + ", ");
//			System.out.println();
//		}
		
		//find by mail member
//		List<MessageVO> list2 = dao.findByMailRecvMbNo("MB0000009");
//		for(MessageVO messageVO : list2){
//			System.out.print(messageVO.getMailRecvMbNo() + ", ");
//			System.out.print(messageVO.getMesgTitle() + ", ");
//			System.out.print(messageVO.getMesgContent() + ", ");
//			System.out.print(messageVO.getReadStatus() + ", ");
//			System.out.print(messageVO.getMailTime() + ", ");
//			System.out.println();
//		}
		
		//find by adminMail
//		List<MessageVO> list3 = dao.findByAdmin("MB0000016");
//		for(MessageVO messageVO : list3){
//			System.out.print(messageVO.getMailRecvMbNo() + ", ");
//			System.out.print(messageVO.getMesgTitle() + ", ");
//			System.out.print(messageVO.getMesgContent() + ", ");
//			System.out.print(messageVO.getReadStatus() + ", ");
//			System.out.print(messageVO.getMailTime() + ", ");
//			System.out.println();
//		}
		
	}

}
