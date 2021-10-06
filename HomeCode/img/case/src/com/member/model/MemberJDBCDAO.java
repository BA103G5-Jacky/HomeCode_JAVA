package com.member.model;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import jdbc.util.CompositeQuery.People_CompositeQuery;

public class MemberJDBCDAO implements MemberDAO_interface{
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:xe";
	String userid = "G5";
	String passwd = "123456";
	
	public final static String INSERT_STMT = "INSERT INTO MEMBER (MBNO, MBLSTNAME, MBFSTNAME, COMNAME, EMAIL, MBPW, MBLOC, MBADDRESS, "
			+ "PHONE, MBUSRNAME, PORTRAIT, MBINTRODUCE) VALUES( 'MB'||lpad(member_sq.nextval,7,'0'), ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)"; 
	public final static String UPDATE_STMT = "UPDATE MEMBER SET MBLSTNAME = ?, MBFSTNAME = ?, COMNAME = ?, MBLOC = ?, MBADDRESS = ?, "
			+ "PHONE = ?, MBUSRNAME = ?, PORTRAIT = ?, MBINTRODUCE = ? WHERE MBNO = ?";
	public final static String UPDATE_SEC_STMT = "UPDATE MEMBER SET SECQUESTION = ?, SECANSWER = ? WHERE MBNO = ?";
	public final static String GET_ALL_STMT = "SELECT * FROM MEMBER";
	public final static String FIND_BY_SUCCESSTIMES_STMT = "SELECT MBNO, MBLSTNAME, MBFSTNAME, CSTIMES, CSSUCCESSTIMES, MBLOC, PORTRAIT, MBINTRODUCE FROM MEMBER"
			+ " WHERE CSSUCCESSTIMES >= ? ORDER BY CSSUCCESSTIMES";
	public final static String FIND_BY_LOC_STMT = "SELECT MBNO, MBLSTNAME, MBFSTNAME, CSTIMES, CSSUCCESSTIMES, MBLOC, PORTRAIT, MBINTRODUCE FROM MEMBER"
			+ " WHERE MBLOC LIKE ? ORDER BY MBLOC";
	public final static String FIND_BY_SUSPENSIONSTATUS_STMT = "SELECT MBNO, MBLSTNAME, MBFSTNAME, CSTIMES, CSSUCCESSTIMES, CSSTOPTIMES, MBLOC, PORTRAIT, SUSPENSIONSTATUS FROM MEMBER"
			+ " WHERE SUSPENSIONSTATUS = ? ORDER BY MBNO";
	public final static String FIND_BYPK_STMT = "SELECT MBNO, MBLSTNAME, MBFSTNAME, CSTIMES, CSSUCCESSTIMES, CSSTOPTIMES, MBLOC, PORTRAIT, SUSPENSIONSTATUS, MBINTRODUCE FROM MEMBER"
			+ " WHERE MBNO = ?";
//	public final static String LOGIN_CHECK_STMT = "SELECT EMAIL, MBPW FROM MEMBER WHERE EMAIL = ?";
	public final static String UPDATE_IMG = "UPDATE MEMBER SET PORTRAIT = ? WHERE MBNO = ?";
	public final static String EMAIL_CHECK_STMT = "SELECT EMAIL FROM MEMBER WHERE EMAIL = ? ";
	public final static String LOGIN_CHECK_STMT = "SELECT MBUSRNAME, EMAIL, MBPW FROM MEMBER WHERE EMAIL = ?";	
	//新增!	
	public final static String UPDATE_MBPW_STMT = "UPDATE MEMBER SET MBPW = ? WHERE EMAIL = ?";
	public final static String FIND_BYEMAIL_STMT = "SELECT MBNO, MBLSTNAME, MBFSTNAME, CSTIMES, CSSUCCESSTIMES, CSSTOPTIMES, MBLOC, PORTRAIT, SUSPENSIONSTATUS, MBINTRODUCE, sinceDate FROM MEMBER"
			+ " WHERE EMAIL = ?";
	// update 0927 maya for Json
	public final static String FIND_BYPKNOIMG_STMT = "SELECT MBNO, MBLSTNAME, MBFSTNAME, CSTIMES, CSSUCCESSTIMES, CSSTOPTIMES, MBLOC, SUSPENSIONSTATUS, MBINTRODUCE FROM MEMBER"
			+ " WHERE MBNO = ?";
	public final static String UPDATE_CSSUCCESSTIMES_STMT = "UPDATE MEMBER SET CSSUCCESSTIMES = ? WHERE MBNO = ?";
	public final static String UPDATE_CSTIMES_STMT = "UPDATE MEMBER SET CSTIMES = ? WHERE MBNO = ?";
	
	@Override
	public void insert(MemberVO memberVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);
			
			pstmt.setString(1, memberVO.getMbLstName());
			pstmt.setString(2, memberVO.getMbFstName());
			pstmt.setString(3, memberVO.getComName());
			pstmt.setString(4, memberVO.geteMail());
			pstmt.setString(5, memberVO.getMbPw());
			pstmt.setString(6, memberVO.getMbLoc());
			pstmt.setString(7, memberVO.getMbAddress());
			pstmt.setString(8, memberVO.getPhone());
			pstmt.setString(9, memberVO.getMbUserName());
			pstmt.setBytes(10, memberVO.getPortrait());
			pstmt.setString(11, memberVO.getMbIntroduce());
			
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
	public void update(MemberVO memberVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE_STMT);
			
			pstmt.setString(1, memberVO.getMbLstName());
			pstmt.setString(2, memberVO.getMbFstName());
			pstmt.setString(3, memberVO.getComName());
			pstmt.setString(4, memberVO.getMbLoc());
			pstmt.setString(5, memberVO.getMbAddress());
			pstmt.setString(6, memberVO.getPhone());
			pstmt.setString(7, memberVO.getMbUserName());
			pstmt.setBytes(8, memberVO.getPortrait());
			pstmt.setString(9, memberVO.getMbIntroduce());
			pstmt.setString(10, memberVO.getMbNo());
			
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
	public void setSecQuestion(MemberVO memberVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE_SEC_STMT);
			
			pstmt.setString(1, memberVO.getSecQuestion());
			pstmt.setString(2, memberVO.getSecAnswer());
			pstmt.setString(3, memberVO.getMbNo());			
			
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
	public List<MemberVO> getAll() {
		List<MemberVO> list = new ArrayList<MemberVO>();
		MemberVO memberVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();
			
			while(rs.next()){
				memberVO = new MemberVO();
				memberVO.setMbNo(rs.getString(1));
				memberVO.setMbLstName(rs.getString(2));
				memberVO.setMbFstName(rs.getString(3));
				memberVO.setCsTimes(rs.getInt(4));
				memberVO.setCsSuccessTimes(rs.getInt(5));
				memberVO.setCsStopTimes(rs.getInt(6));
				memberVO.setComName(rs.getString(7));
				memberVO.seteMail(rs.getString(8));
				memberVO.setMbLoc(rs.getString(10));
				memberVO.setMbAddress(rs.getString(11));
				memberVO.setPhone(rs.getString(12));
				memberVO.setMbUserName(rs.getString(13));
				memberVO.setSecQuestion(rs.getString(14));
				memberVO.setSecAnswer(rs.getString(15));
				memberVO.setSuspensionStatus(rs.getString(16));
				memberVO.setPortrait(rs.getBytes(17));
				memberVO.setSinceDate(rs.getDate(18));
				memberVO.setMbIntroduce(rs.getString(19));
				list.add(memberVO);				
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
		return list;
	}

	@Override
	public MemberVO findByPrimaryKey(String mbNo) {
		MemberVO memberVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(FIND_BYPK_STMT);
			pstmt.setString(1, mbNo);
			rs = pstmt.executeQuery();
			
			while(rs.next()){
				memberVO = new MemberVO();
				memberVO.setMbNo(rs.getString(1));
				memberVO.setMbLstName(rs.getString(2));
				memberVO.setMbFstName(rs.getString(3));
				memberVO.setCsTimes(rs.getInt(4));
				memberVO.setCsSuccessTimes(rs.getInt(5));
				memberVO.setCsStopTimes(rs.getInt(6));
				memberVO.setMbLoc(rs.getString(7));
				memberVO.setPortrait(rs.getBytes(8));
				memberVO.setSuspensionStatus(rs.getString(9));	
				memberVO.setMbIntroduce(rs.getString(10));
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
		return memberVO;
	}
	
	@Override
	public MemberVO findByEMail(String eMail) {
		MemberVO memberVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(FIND_BYPK_STMT);
			pstmt.setString(1, eMail);
			rs = pstmt.executeQuery();
			
			while(rs.next()){
				memberVO = new MemberVO();
				memberVO.setMbNo(rs.getString(1));
				memberVO.setMbLstName(rs.getString(2));
				memberVO.setMbFstName(rs.getString(3));
				memberVO.setCsTimes(rs.getInt(4));
				memberVO.setCsSuccessTimes(rs.getInt(5));
				memberVO.setCsStopTimes(rs.getInt(6));
				memberVO.setMbLoc(rs.getString(7));
				memberVO.setPortrait(rs.getBytes(8));
				memberVO.setSuspensionStatus(rs.getString(9));	
				memberVO.setMbIntroduce(rs.getString(10));
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
		return memberVO;
	}

	@Override
	public List<MemberVO> findBySuccessTimes(Integer csSuccessTimes) {
		List<MemberVO> list = new ArrayList<MemberVO>();
		MemberVO memberVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(FIND_BY_SUCCESSTIMES_STMT);
			pstmt.setInt(1, csSuccessTimes);
			rs = pstmt.executeQuery();
			
			while(rs.next()){
				memberVO = new MemberVO();
				memberVO.setMbNo(rs.getString(1));
				memberVO.setMbLstName(rs.getString(2));
				memberVO.setMbFstName(rs.getString(3));
				memberVO.setCsTimes(rs.getInt(4));
				memberVO.setCsSuccessTimes(rs.getInt(5));				
				memberVO.setMbLoc(rs.getString(6));
				memberVO.setPortrait(rs.getBytes(7));
				memberVO.setMbIntroduce(rs.getString(8));
				list.add(memberVO);				
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
		return list;
	}

	@Override
	public List<MemberVO> findByLoc(String mbLoc) {
		List<MemberVO> list = new ArrayList<MemberVO>();
		MemberVO memberVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(FIND_BY_LOC_STMT);
			pstmt.setString(1, "%" + mbLoc + "%");
			rs = pstmt.executeQuery();
			
			while(rs.next()){
				memberVO = new MemberVO();
				memberVO.setMbNo(rs.getString(1));
				memberVO.setMbLstName(rs.getString(2));
				memberVO.setMbFstName(rs.getString(3));
				memberVO.setCsTimes(rs.getInt(4));
				memberVO.setCsSuccessTimes(rs.getInt(5));				
				memberVO.setMbLoc(rs.getString(6));
				memberVO.setPortrait(rs.getBytes(7));
				memberVO.setMbIntroduce(rs.getString(8));
				list.add(memberVO);				
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
		return list;
	}

	@Override
	public List<MemberVO> findBySuspenionStatus(String suspensionStatus) {
		List<MemberVO> list = new ArrayList<MemberVO>();
		MemberVO memberVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(FIND_BY_SUSPENSIONSTATUS_STMT);
			pstmt.setString(1, suspensionStatus);
			rs = pstmt.executeQuery();
			
			while(rs.next()){
				memberVO = new MemberVO();
				memberVO.setMbNo(rs.getString(1));
				memberVO.setMbLstName(rs.getString(2));
				memberVO.setMbFstName(rs.getString(3));
				memberVO.setCsTimes(rs.getInt(4));
				memberVO.setCsSuccessTimes(rs.getInt(5));
				memberVO.setCsStopTimes(rs.getInt(6));
				memberVO.setMbLoc(rs.getString(7));
				memberVO.setPortrait(rs.getBytes(8));
				memberVO.setSuspensionStatus(rs.getString(9));
				list.add(memberVO);				
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
		return list;
	}
	
//	@Override
//	public MemberVO loginCheck(String eMail, String passWord) {
//		MemberVO memberVO = null;
//		Connection con = null;
//		PreparedStatement pstmt = null;
//		ResultSet rs = null;
//
//		try {
//			Class.forName(driver);
//			con = DriverManager.getConnection(url, userid, passwd);
//			pstmt = con.prepareStatement(LOGIN_CHECK_STMT);
//			pstmt.setString(1, eMail);
//			pstmt.setString(2, passWord);
//			rs = pstmt.executeQuery();
//
//			while (rs.next()) {
//				memberVO = new MemberVO();
//				memberVO.setMbUserName(rs.getString(1));
//				memberVO.seteMail(rs.getString(2));
//				memberVO.setMbPw(rs.getString(3));
//			}
//
//		} catch (SQLException e) {
//			e.printStackTrace();
//		} catch (ClassNotFoundException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} finally {
//			if (rs != null) {
//				try {
//					rs.close();
//				} catch (SQLException e) {
//					e.printStackTrace();
//				}
//			}
//			if (pstmt != null) {
//				try {
//					pstmt.close();
//				} catch (SQLException e) {
//					e.printStackTrace();
//				}
//			}
//			if (con != null) {
//				try {
//					con.close();
//				} catch (SQLException e) {
//					e.printStackTrace();
//				}
//			}
//		}
//		return memberVO;
//	}
	
	@Override
	public MemberVO loginCheck(String eMail) {
		MemberVO memberVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(LOGIN_CHECK_STMT);
			pstmt.setString(1, eMail);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()){
				memberVO = new MemberVO();
				memberVO.setMbUserName(rs.getString(1));
				memberVO.seteMail(rs.getString(2));
				memberVO.setMbPw(rs.getString(3));
				
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
		return memberVO;
	}

	@Override
	public MemberVO emailCheck(String email) {
		Connection con = null;
		PreparedStatement pstmt = null;
		MemberVO memberVO = null;
		ResultSet rs = null;
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(EMAIL_CHECK_STMT);
			pstmt.setString(1, email);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				memberVO = new MemberVO();
				memberVO.seteMail(rs.getString(1));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
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
		return memberVO;
	}
	
	public static void main(String[] args) {
		MemberJDBCDAO dao = new MemberJDBCDAO();
		
		//insert member
//		MemberVO memberVO = new MemberVO();		
//		memberVO.setMbFstName("坂田");
//		memberVO.setMbLstName("銀時");
//		memberVO.setComName("");
//		memberVO.seteMail("sakada@gmail.com");
//		memberVO.setMbPw("123456");
//		memberVO.setMbLoc("海外地區");
//		memberVO.setMbAddress("");
//		memberVO.setPhone("");
//		memberVO.setMbUserName("阿銀");
//		memberVO.setPoritait(null);
//		memberVO.setMbIntroduce("阿姆斯特朗旋風朗砲");
//		dao.insert(memberVO);
		
		//update member
//		byte[] buf = null;
//		File file = new File("C:\\Users\\user\\Desktop\\Android_icon\\gintoki.jpg");
//		try {
//			FileInputStream fis = new FileInputStream(file);
//			BufferedInputStream bis = new BufferedInputStream(fis);
//			buf = new byte[bis.available()];
//			int len;
//			while((len= bis.read(buf)) != -1){
//				System.out.println(len);
//			}
//		} catch (FileNotFoundException e) {
//			e.printStackTrace();
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//		MemberVO memberVO2 = new MemberVO();		
//		memberVO2.setMbFstName("銀時");
//		memberVO2.setMbLstName("坂田");
//		memberVO2.setComName("");
//		memberVO2.setMbLoc("海外地區");
//		memberVO2.setMbAddress("");
//		memberVO2.setPhone("");
//		memberVO2.setMbUserName("笨蛋阿銀");
//		memberVO2.setPoritait(buf);
//		memberVO2.setMbIntroduce("阿姆斯特朗旋風朗砲");
//		memberVO2.setMbNo("MB0000030");
//		dao.update(memberVO2);
		
		//update member security question
//		MemberVO memberVO3 = new MemberVO();		
//		memberVO3.setMbNo("MB0000030");
//		memberVO3.setSecQuestion("Jacky的鼻子有幾個");
//		memberVO3.setSecAnswer("我愛草莓牛奶");
//		dao.setSecQuestion(memberVO3);
		
		//get all members
//		List<MemberVO> list = dao.getAll();
//		for( MemberVO memberVO4 : list){
//			System.out.print(memberVO4.getMbNo() + ", ");
//			System.out.print(memberVO4.getMbLstName() + ", ");
//			System.out.print(memberVO4.getMbFstName() + ", ");
//			System.out.print(memberVO4.getCsTimes() + ", ");
//			System.out.print(memberVO4.getCsSuccessTimes() + ", ");
//			System.out.print(memberVO4.getCsStopTimes() + ", ");
//			System.out.print(memberVO4.getComName() + ", ");
//			System.out.print(memberVO4.geteMail() + ", ");
//			System.out.print(memberVO4.getMbLoc() + ", ");
//			System.out.print(memberVO4.getMbAddress() + ", ");
//			System.out.print(memberVO4.getPhone() + ", ");
//			System.out.print(memberVO4.getMbUserName() + ", ");
//			System.out.print(memberVO4.getSecQuestion() + ", ");
//			System.out.print(memberVO4.getSecAnswer() + ", ");
//			System.out.print(memberVO4.getSuspensionStatus() + ", ");
//			System.out.print(memberVO4.getPoritait() + ", ");
//			System.out.print(memberVO4.getSinceDate() + ", ");
//			System.out.print(memberVO4.getMbIntroduce());
//			System.out.println();
//		}
		
		//find by successtimes
//		List<MemberVO> list = dao.findBySuccessTimes(0);
//		for( MemberVO memberVO5 : list){
//			System.out.print(memberVO5.getMbNo() + ", ");
//			System.out.print(memberVO5.getMbLstName() + ", ");
//			System.out.print(memberVO5.getMbFstName() + ", ");
//			System.out.print(memberVO5.getCsTimes() + ", ");
//			System.out.print(memberVO5.getCsSuccessTimes() + ", ");			
//			System.out.print(memberVO5.getMbLoc() + ", ");			
//			System.out.print(memberVO5.getPoritait() + ", ");
//			System.out.print(memberVO5.getMbIntroduce());
//			System.out.println();
//		}
		
		//find by loc
//		List<MemberVO> list = dao.findByLoc("海外");
//		for( MemberVO memberVO6 : list){
//			System.out.print(memberVO6.getMbNo() + ", ");
//			System.out.print(memberVO6.getMbLstName());
//			System.out.print(memberVO6.getMbFstName() + ", ");
//			System.out.print(memberVO6.getCsTimes() + ", ");
//			System.out.print(memberVO6.getCsSuccessTimes() + ", ");			
//			System.out.print(memberVO6.getMbLoc() + ", ");			
//			System.out.print(memberVO6.getPoritait() + ", ");
//			System.out.print(memberVO6.getMbIntroduce());
//			System.out.println();
//		}
		
		//find by suspension status
//		List<MemberVO> list = dao.findBySuspenionStatus("否");
//		for( MemberVO memberVO7 : list){
//			System.out.print(memberVO7.getMbNo() + ", ");
//			System.out.print(memberVO7.getMbLstName());
//			System.out.print(memberVO7.getMbFstName() + ", ");
//			System.out.print(memberVO7.getCsTimes() + ", ");
//			System.out.print(memberVO7.getCsSuccessTimes() + ", ");
//			System.out.print(memberVO7.getCsStopTimes() + ", ");
//			System.out.print(memberVO7.getMbLoc() + ", ");			
//			System.out.print(memberVO7.getPoritait() + ", ");
//			System.out.print(memberVO7.getSuspensionStatus());
//			System.out.println();
//		}
		
//		find by mbNo
//		MemberVO memberVO8 = dao.findByPrimaryKey("sakada@gmail.com");
//		MemberVO memberVO8 = dao.findByPKNoImg("MB0000009");
//		System.out.print(memberVO8.getMbNo() + ", ");
//		System.out.print(memberVO8.getMbLstName());
//		System.out.print(memberVO8.getMbFstName() + ", ");
//		System.out.print(memberVO8.getCsTimes() + ", ");
//		System.out.print(memberVO8.getCsSuccessTimes() + ", ");
//		System.out.print(memberVO8.getCsStopTimes() + ", ");
//		System.out.print(memberVO8.getMbLoc() + ", ");			
////		System.out.print(memberVO8.getPortrait() + ", ");
//		System.out.print(memberVO8.getSuspensionStatus() + ", ");
//		System.out.print(memberVO8.getMbIntroduce());
//		System.out.println();
		
		//LOGININ CHECK
//		MemberVO memberVO9 = dao.loginCheck("123");
//		System.out.print(memberVO9.geteMail() + ", ");
//		System.out.print(memberVO9.getMbPw());
//		System.out.println();
		
		
//		byte[] buf = null;
//		File file = new File("C:\\Users\\user\\Desktop\\Android_icon\\RDJ.jpg");
//		try {
//			FileInputStream fis = new FileInputStream(file);
//			BufferedInputStream bis = new BufferedInputStream(fis);
//			buf = new byte[bis.available()];
//			int len;
//			while((len= bis.read(buf)) != -1){
//				System.out.println(len);
//			}
//		} catch (FileNotFoundException e) {
//			e.printStackTrace();
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//		MemberVO memberVO2 = new MemberVO();
//		memberVO2.setPortrait(buf);
//		memberVO2.setMbNo("MB0000009");
//		dao.updateImg(memberVO2);
		
//		MemberVO mbVO = new MemberVO();
//		mbVO.setCsSuccessTimes(5);
//		mbVO.setMbNo("MB0000005");
//		dao.updateCsSuccessTimes(mbVO);
		
		MemberVO mbVO = new MemberVO();
		mbVO.setCsTimes(5);
		mbVO.setMbNo("MB0000005");
		dao.updateCsTimes(mbVO);

		
	}

	public void updateImg(MemberVO memberVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE_IMG);
			pstmt.setBytes(1, memberVO.getPortrait());
			pstmt.setString(2, memberVO.getMbNo());			
			
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
	public byte[] getPoritait(String eMail) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void updateMbPw(String mbPw,String eMail) {
		MemberVO memberVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE_MBPW_STMT);
			pstmt.setString(1, mbPw);
			pstmt.setString(2, eMail);
			
			pstmt.executeUpdate();
			System.out.println("有進來更新密碼 快去資料庫檢查一下!");
			
				
			
			
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
	public List<MemberVO> getAll(Map<String, String[]> map) {
		List<MemberVO> list = new ArrayList<MemberVO>();
		MemberVO memberVO = null;
	
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
	
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			
			String finalSQL = "select * from member "
		          + People_CompositeQuery.get_WhereCondition(map)
		          + "order by mbno";
			pstmt = con.prepareStatement(finalSQL);
			System.out.println("●●finalSQL(by DAO) = "+finalSQL);
			rs = pstmt.executeQuery();
	
			while (rs.next()) {
				memberVO = new MemberVO();
				memberVO.setMbUserName(rs.getString("mbusername"));
				memberVO.setMbIntroduce(rs.getString("mbintroduce"));
				memberVO.seteMail(rs.getString("email"));
				memberVO.setMbLoc(rs.getString("mbloc"));
				memberVO.setCsSuccessTimes(rs.getInt("cssuccesstimes"));
			
				
				list.add(memberVO); // Store the row in the List
			}
	
			// Handle any SQL errors
		}catch (ClassNotFoundException e) {
			e.printStackTrace();
		}  catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
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
	public MemberVO findByPKNoImg(String mbNo) {
		MemberVO memberVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(FIND_BYPKNOIMG_STMT);
			pstmt.setString(1, mbNo);
			rs = pstmt.executeQuery();
			
			while(rs.next()){
				memberVO = new MemberVO();
				memberVO.setMbNo(rs.getString(1));
				memberVO.setMbLstName(rs.getString(2));
				memberVO.setMbFstName(rs.getString(3));
				memberVO.setCsTimes(rs.getInt(4));
				memberVO.setCsSuccessTimes(rs.getInt(5));
				memberVO.setCsStopTimes(rs.getInt(6));
				memberVO.setMbLoc(rs.getString(7));
				memberVO.setSuspensionStatus(rs.getString(8));	
				memberVO.setMbIntroduce(rs.getString(9));
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
		return memberVO;
	}

	@Override
	public MemberVO findByUsrName(String mbUserName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void updateCsSuccessTimes(MemberVO memberVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE_CSSUCCESSTIMES_STMT);
			
			pstmt.setInt(1, memberVO.getCsSuccessTimes());
			pstmt.setString(2, memberVO.getMbNo());				
			
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
	public void updateCsTimes(MemberVO memberVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE_CSTIMES_STMT);
			
			pstmt.setInt(1, memberVO.getCsTimes());
			pstmt.setString(2, memberVO.getMbNo());	
			
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
	public List<String> getAllMbUserName() {
		// TODO Auto-generated method stub
		return null;
	}

	

}
