package com.message.model;

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

public class MessageDAO implements MessageDAO_interface{
	
	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/BA103G5");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
	
//	public final static String FIND_BYMAILMB_STMT = "SELECT MESGNO, MAILRECVMBNO, MESGTITLE, MESGCONTENT, MAILTIME"
//			+ " FROM MESSAGE WHERE MAILMBNO = ? and ISDELETEBYMAILMB = '否' ";
//	public final static String FIND_BYRECVMB_STMT = "SELECT MESGNO, MAILMBNO, MESGTITLE, MESGCONTENT, READSTATUS, MAILTIME "
//			+ "FROM MESSAGE WHERE MAILRECVMBNO = ? and ISDELETEBYRECVMB = '否' ";
//	public final static String FIND_BYADMIN_STMT = "SELECT MESGNO, MAILRECVMBNO, MESGTITLE, MESGCONTENT, READSTATUS, MAILTIME "
//			+ "FROM MESSAGE WHERE MAILMBNO = 'MB0000001' AND MAILRECVMBNO = ?";
//	public final static String DELETE_BYMAILMB_STMT = "UPDATE MESSAGE SET ISDELETEBYMAILMB = ? WHERE MESGNO = ?";
//	public final static String DELETE_BYRECVMB_STMT = "UPDATE MESSAGE SET ISDELETEBYRECVMB = ? WHERE MESGNO = ?";
//	public final static String INSERT_STMT = "Insert Into Message (mesgNo, mailMbNo, mailRecvMbNo, mesgTitle, mesgContent)"
//			+ "Values('MSG'||lpad(Message_sq.nextval, 7, '0'), ?, ?, ?, ?)";
//	public final static String UPDATE_READSTATUS_STMT = "UPDATE MESSAGE SET READSTATUS = '已讀' WHERE MESGNO = ?";
	
	public final static String FIND_BYMAILMB_STMT = "SELECT MESGNO, MAILRECVMBNO, MESGTITLE, MESGCONTENT, MAILTIME,READSTATUS FROM MESSAGE WHERE MAILMBNO = ? and ISDELETEBYMAILMB = '否' order by mailTime desc";
	public final static String FIND_BYRECVMB_STMT = "SELECT MESGNO, MAILMBNO, MAILRECVMBNO, MESGTITLE, MESGCONTENT, READSTATUS, MAILTIME "
			+ "FROM MESSAGE WHERE MAILRECVMBNO = ? and ISDELETEBYRECVMB = '否' order by mailTime desc";
	public final static String FIND_BYADMIN_STMT = "SELECT MESGNO, MAILRECVMBNO, MESGTITLE, MESGCONTENT, READSTATUS, MAILTIME "
			+ "FROM MESSAGE WHERE MAILMBNO = 'MB0000001' AND MAILRECVMBNO = ? order by mailTime desc";
	public final static String DELETE_BYMAILMB_STMT = "UPDATE MESSAGE SET ISDELETEBYMAILMB = ? WHERE MESGNO = ?";
	public final static String DELETE_BYRECVMB_STMT = "UPDATE MESSAGE SET ISDELETEBYRECVMB = ? WHERE MESGNO = ?";
	public final static String INSERT_STMT = "Insert Into Message (mesgNo, mailMbNo, mailRecvMbNo, mesgTitle, mesgContent)"
			+ "Values('MSG'||lpad(Message_sq.nextval, 7, '0'), ?, ?, ?, ?)";
	public final static String UPDATE_READSTATUS_STMT = "UPDATE MESSAGE SET READSTATUS = ? WHERE MESGNO = ?";

	@Override
	public void insert(MessageVO messageVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);
			
			pstmt.setString(1, messageVO.getMailMbNo());
			pstmt.setString(2, messageVO.getMailRecvMbNo());
			pstmt.setString(3, messageVO.getMesgTitle());
			pstmt.setString(4, messageVO.getMesgContent());
			
			int a = pstmt.executeUpdate();
			System.out.println("更新了"+a+"筆");
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
			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE_READSTATUS_STMT);
			
			pstmt.setString(1, messageVO.getReadStatus());
			pstmt.setString(2, messageVO.getMesgNo());
			
			int a = pstmt.executeUpdate();
			System.out.println("更新了"+a+"筆");
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
			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE_BYMAILMB_STMT);
			
			pstmt.setString(1, "是");
			pstmt.setString(2, mesgNo);
			
			int a = pstmt.executeUpdate();
			System.out.println("已經刪除了"+a+"筆："+mesgNo);
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
			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE_BYRECVMB_STMT);
			
			pstmt.setString(1, "是");
			pstmt.setString(2, mesgNo);
			
			int a = pstmt.executeUpdate();
			System.out.println("更新了"+a+"筆");
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
			con = ds.getConnection();
			pstmt = con.prepareStatement(FIND_BYMAILMB_STMT);
			System.out.println("[messageDAO]-[mbNo]:"+ mailMbNo);
			pstmt.setString(1, mailMbNo);
			rs = pstmt.executeQuery();
			
			while(rs.next()){
				messageVO = new MessageVO();
				messageVO.setMesgNo(rs.getString(1));	
				messageVO.setMailRecvMbNo(rs.getString(2));				
				messageVO.setMesgTitle(rs.getString(3));
				messageVO.setMesgContent(rs.getString(4));
				messageVO.setMailTime(rs.getDate(5));
				messageVO.setReadStatus(rs.getString(6));
				list.add(messageVO);
			}
			
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
			con = ds.getConnection();
			pstmt = con.prepareStatement(FIND_BYRECVMB_STMT);
			pstmt.setString(1, mailRecvMbNo);
			rs = pstmt.executeQuery();
			
			while(rs.next()){
				messageVO = new MessageVO();
				messageVO.setMesgNo(rs.getString(1));
				messageVO.setMailMbNo(rs.getString(2));
				messageVO.setMailRecvMbNo(rs.getString(3));
				messageVO.setMesgTitle(rs.getString(4));
				messageVO.setMesgContent(rs.getString(5));
				messageVO.setReadStatus(rs.getString(6));
				messageVO.setMailTime(rs.getDate(7));
				list.add(messageVO);
			}
			
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
			con = ds.getConnection();
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

}
