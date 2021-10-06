package com.member.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import jdbc.util.CompositeQuery.People_CompositeQuery;

public class MemberDAO implements MemberDAO_interface {

	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/BA103G5");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}

	public final static String INSERT_STMT = "INSERT INTO MEMBER (MBNO, MBLSTNAME, MBFSTNAME, COMNAME, EMAIL, MBPW, MBLOC, MBADDRESS, "
			+ "PHONE, MBUSRNAME, PORTRAIT, MBINTRODUCE) VALUES( 'MB'||lpad(member_sq.nextval,7,'0'), ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
	public final static String UPDATE_STMT = "UPDATE MEMBER SET MBLSTNAME = ?, MBFSTNAME = ?, COMNAME = ?, MBLOC = ?, MBADDRESS = ?, "
			+ "PHONE = ?, MBUSRNAME = ?, PORTRAIT = ?, MBINTRODUCE = ? WHERE MBNO = ?";
	
	public final static String UPDATE_SEC_STMT = "UPDATE MEMBER SET SECQUESTION = ?, SECANSWER = ? WHERE MBNO = ?";
	public final static String GET_ALL_STMT = "SELECT * FROM MEMBER where mbNo != 'MB0000001'";
	public final static String FIND_BY_SUCCESSTIMES_STMT = "SELECT MBNO, MBLSTNAME, MBFSTNAME, CSTIMES, CSSUCCESSTIMES, MBLOC, PORTRAIT, MBINTRODUCE FROM MEMBER"
			+ " WHERE CSSUCCESSTIMES >= ? ORDER BY CSSUCCESSTIMES";
	public final static String FIND_BY_LOC_STMT = "SELECT MBNO, MBLSTNAME, MBFSTNAME, CSTIMES, CSSUCCESSTIMES, MBLOC, PORTRAIT, MBINTRODUCE FROM MEMBER"
			+ " WHERE MBLOC LIKE ? ORDER BY MBLOC";
	public final static String FIND_BY_SUSPENSIONSTATUS_STMT = "SELECT MBNO, MBLSTNAME, MBFSTNAME, CSTIMES, CSSUCCESSTIMES, CSSTOPTIMES, MBLOC, PORTRAIT, SUSPENSIONSTATUS FROM MEMBER"
			+ " WHERE SUSPENSIONSTATUS = ? ORDER BY MBNO";
	// public final static String FIND_BYPK_STMT = "SELECT MBNO, MBLSTNAME,
	// MBFSTNAME, CSTIMES, CSSUCCESSTIMES, CSSTOPTIMES, MBLOC, PORTRAIT,
	// SUSPENSIONSTATUS, MBINTRODUCE FROM MEMBER"
	// + " WHERE EMAIL = ?"; //for Android
	public final static String FIND_BYPK_STMT = "SELECT MBNO, MBLSTNAME, MBFSTNAME, CSTIMES, CSSUCCESSTIMES, CSSTOPTIMES, MBLOC, PORTRAIT, SUSPENSIONSTATUS, MBINTRODUCE, sinceDate, MBUSRNAME,"
			+ " COMNAME, MBADDRESS,PHONE, eMail FROM MEMBER WHERE MBNO = ?";
	public final static String FIND_BYEMAIL_STMT = "SELECT MBNO, MBLSTNAME, MBFSTNAME, CSTIMES, CSSUCCESSTIMES, CSSTOPTIMES, MBLOC, PORTRAIT, SUSPENSIONSTATUS, MBINTRODUCE, sinceDate FROM MEMBER"
			+ " WHERE EMAIL = ?";
	// public final static String LOGIN_CHECK_STMT = "SELECT EMAIL, MBPW FROM
	// MEMBER WHERE EMAIL = ?";
//	public final static String LOGIN_CHECK_STMT = "SELECT MBUSRNAME, EMAIL, MBPW FROM MEMBER WHERE EMAIL = ? and mbpw=?";
	public final static String EMAIL_CHECK_STMT = "SELECT EMAIL FROM MEMBER WHERE EMAIL = ? ";
	
	//圖片
	public final static String GET_IMAGE_STMT = "SELECT PORTRAIT FROM MEMBER WHERE EMAIL = ?";
	
	//這ㄍ有修改!
	public final static String LOGIN_CHECK_STMT = "SELECT MBUSRNAME, EMAIL, MBPW,MBNO FROM MEMBER WHERE EMAIL = ?";
	
	//這是新增的
	public final static String UPDATE_MBPW_STMT = "UPDATE MEMBER SET MBPW = ? WHERE EMAIL = ?";
	// update 0927 maya for Json
	public final static String FIND_BYPKNOIMG_STMT = "SELECT MBNO, MBLSTNAME, MBFSTNAME, CSTIMES, CSSUCCESSTIMES, CSSTOPTIMES, MBLOC, SUSPENSIONSTATUS, MBINTRODUCE FROM MEMBER"
			+ " WHERE MBNO = ?";
	// update 0927 jacky
	public final static String FIND_BY_USERNAME = "SELECT MBNO, MBLSTNAME,"
			 +"MBFSTNAME, CSTIMES, CSSUCCESSTIMES, CSSTOPTIMES, MBLOC, PORTRAIT,"
			 +"SUSPENSIONSTATUS, MBINTRODUCE, sinceDate, MBUSRNAME FROM MEMBER"
			 + " WHERE MBUSRNAME = ?"; 
	
	public final static String UPDATE_CSSUCCESSTIMES_STMT = "UPDATE MEMBER SET CSSUCCESSTIMES = ? WHERE MBNO = ?";
	public final static String UPDATE_CSTIMES_STMT = "UPDATE MEMBER SET CSTIMES = ? WHERE MBNO = ?";
	public final static String GETALLMBUSERNAME_STME = "SELECT MBUSRNAME FROM MEMBER";
	
	

	@Override
	public void insert(MemberVO memberVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = ds.getConnection();
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
			System.out.println("更新了" + a + "筆");
		} catch (SQLException e) {
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

	}

	@Override
	public void update(MemberVO memberVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = ds.getConnection();
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
			System.out.println("更新了" + a + "筆");
		} catch (SQLException e) {
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

	}

	@Override
	public void setSecQuestion(MemberVO memberVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE_SEC_STMT);

			pstmt.setString(1, memberVO.getSecQuestion());
			pstmt.setString(2, memberVO.getSecAnswer());
			pstmt.setString(3, memberVO.getMbNo());

			int a = pstmt.executeUpdate();
			System.out.println("更新了" + a + "筆");
		} catch (SQLException e) {
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

	}

	@Override
	public List<MemberVO> getAll() {
		List<MemberVO> list = new ArrayList<MemberVO>();
		MemberVO memberVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
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
	public MemberVO findByPrimaryKey(String mbNo) {
		MemberVO memberVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(FIND_BYPK_STMT);
			pstmt.setString(1, mbNo);
			rs = pstmt.executeQuery();

			while (rs.next()) {
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
				memberVO.setSinceDate(rs.getDate("sinceDate"));
				memberVO.setMbUserName(rs.getString("mbUsrName"));
				memberVO.setComName(rs.getString("comName"));
				memberVO.setMbAddress(rs.getString("mbAddress"));
				memberVO.setPhone(rs.getString("phone"));
				memberVO.seteMail(rs.getString("eMail"));
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
		return memberVO;
	}
	
	@Override
	public MemberVO findByEMail(String eMail) {
		MemberVO memberVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(FIND_BYEMAIL_STMT);
			pstmt.setString(1, eMail);
			rs = pstmt.executeQuery();

			while (rs.next()) {
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
				memberVO.setSinceDate(rs.getDate("sinceDate"));
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
			con = ds.getConnection();
			pstmt = con.prepareStatement(FIND_BY_SUCCESSTIMES_STMT);
			pstmt.setInt(1, csSuccessTimes);
			rs = pstmt.executeQuery();

			while (rs.next()) {
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
	public List<MemberVO> findByLoc(String mbLoc) {
		List<MemberVO> list = new ArrayList<MemberVO>();
		MemberVO memberVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(FIND_BY_LOC_STMT);
			pstmt.setString(1, "%" + mbLoc + "%");
			rs = pstmt.executeQuery();

			while (rs.next()) {
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
	public List<MemberVO> findBySuspenionStatus(String suspensionStatus) {
		List<MemberVO> list = new ArrayList<MemberVO>();
		MemberVO memberVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(FIND_BY_SUSPENSIONSTATUS_STMT);
			pstmt.setString(1, suspensionStatus);
			rs = pstmt.executeQuery();

			while (rs.next()) {
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

//	@Override
//	public MemberVO loginCheck(String eMail, String passWord) {
//		MemberVO memberVO = null;
//		Connection con = null;
//		PreparedStatement pstmt = null;
//		ResultSet rs = null;
//
//		try {
//			con = ds.getConnection();
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
			con = ds.getConnection();
			pstmt = con.prepareStatement(LOGIN_CHECK_STMT);
			pstmt.setString(1, eMail);
			
			rs = pstmt.executeQuery();

			while (rs.next()) {
				memberVO = new MemberVO();
				memberVO.setMbUserName(rs.getString(1));
				memberVO.seteMail(rs.getString(2));
				memberVO.setMbPw(rs.getString(3));
				memberVO.setMbNo(rs.getString(4));
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
		return memberVO;
	}

	@Override
	public MemberVO emailCheck(String email) {
		Connection con = null;
		PreparedStatement pstmt = null;
		MemberVO memberVO = null;
		ResultSet rs = null;
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(EMAIL_CHECK_STMT);
			pstmt.setString(1, email);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				memberVO = new MemberVO();
				memberVO.seteMail(rs.getString(1));
			}
		} catch (SQLException e) {
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
	
	@Override
	public byte[] getPoritait(String eMail) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		byte[] poritait = null;
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_IMAGE_STMT);
			pstmt.setString(1, eMail);
			rs = pstmt.executeQuery();
			
			while(rs.next()){
				poritait = rs.getBytes(1);
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
		return poritait;
	}

	@Override
	public void updateMbPw(String mbPw,String eMail) {
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			
			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE_MBPW_STMT);
			pstmt.setString(1, mbPw);
			pstmt.setString(2, eMail);
			
			pstmt.executeUpdate();
			System.out.println("忘記密碼的人成工更新密碼 快去資料庫檢查一下!");
			
				
			
			
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
	
	//這是人才的複合查詢!
		@Override
		public List<MemberVO> getAll(Map<String, String[]> map) {
			List<MemberVO> list = new ArrayList<MemberVO>();
			MemberVO memberVO = null;
		
			Connection con = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
		
			try {
				
				con = ds.getConnection();
				
				String condition = People_CompositeQuery.get_WhereCondition(map);
				String orderBy = null;
				if(condition.contains("csSuccessTimes")){
					orderBy = " order by csSuccessTimes desc";
				} else {
					orderBy = " order by mbno";
				}
				
				
				String finalSQL = "select * from member where mbNo != 'MB0000001'"
			          + condition
			          + orderBy;
				pstmt = con.prepareStatement(finalSQL);
				System.out.println("●●finalSQL(by DAO) = "+finalSQL);
				rs = pstmt.executeQuery();
		
				while (rs.next()) {
					memberVO = new MemberVO();
					
					memberVO.setMbNo(rs.getString("mbno"));
					memberVO.setMbLstName(rs.getString("mbLstName"));
					memberVO.setMbFstName(rs.getString("mbFstName"));
					memberVO.setCsTimes(rs.getInt("csTimes"));
					memberVO.setCsSuccessTimes(rs.getInt("cssuccesstimes"));
					memberVO.setCsStopTimes(rs.getInt("csStopTimes"));
					memberVO.setComName(rs.getString("comName"));
					memberVO.seteMail(rs.getString("email"));
					memberVO.setMbLoc(rs.getString("mbloc"));
					memberVO.setMbAddress(rs.getString("mbAddress"));
					memberVO.setPhone(rs.getString("phone"));
					memberVO.setMbUserName(rs.getString("mbusrname"));
					memberVO.setSecQuestion(rs.getString("secQuestion"));
					memberVO.setSecAnswer(rs.getString("secAnswer"));
					memberVO.setSuspensionStatus(rs.getString("suspensionStatus"));
					memberVO.setPortrait(rs.getBytes("portrait"));
					memberVO.setSinceDate(rs.getDate("sincedate"));
					memberVO.setMbIntroduce(rs.getString("mbintroduce"));
					list.add(memberVO); // Store the row in the List
				}
		
				// Handle any SQL errors
			}catch (SQLException se) {
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
				con = ds.getConnection();
				pstmt = con.prepareStatement(FIND_BYPK_STMT);
				pstmt.setString(1, mbNo);
				rs = pstmt.executeQuery();

				while (rs.next()) {
					memberVO = new MemberVO();
					memberVO.setMbNo(rs.getString("mbNo"));
					memberVO.setMbLstName(rs.getString("mbLstName"));
					memberVO.setMbFstName(rs.getString("mbFstName"));
					memberVO.setCsTimes(rs.getInt("csTimes"));
					memberVO.setCsSuccessTimes(rs.getInt("csSuccessTImes"));
					memberVO.setCsStopTimes(rs.getInt("csStopTimes"));
					memberVO.setMbLoc(rs.getString("mbLoc"));
					memberVO.setSuspensionStatus(rs.getString("suspensionStatus"));
					memberVO.setMbIntroduce(rs.getString("mbIntroduce"));
					memberVO.setSinceDate(rs.getDate("sinceDate"));
					memberVO.setComName(rs.getString("comName"));
					memberVO.seteMail(rs.getString("eMail"));
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
			return memberVO;
		}
		
		@Override
		public MemberVO findByUsrName(String mbUserName) {
			MemberVO memberVO = null;
			Connection con = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;

			try {
				con = ds.getConnection();
				pstmt = con.prepareStatement(FIND_BY_USERNAME);
				pstmt.setString(1, mbUserName);
				rs = pstmt.executeQuery();

				while (rs.next()) {
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
					memberVO.setSinceDate(rs.getDate("sinceDate"));
					memberVO.setMbUserName(rs.getString(12));
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
			return memberVO;
		}

		@Override
		public void updateCsSuccessTimes(MemberVO memberVO) {
			Connection con = null;
			PreparedStatement pstmt = null;
			
			try {
				con = ds.getConnection();
				pstmt = con.prepareStatement(UPDATE_CSSUCCESSTIMES_STMT);
				
				pstmt.setInt(1, memberVO.getCsSuccessTimes());
				pstmt.setString(2, memberVO.getMbNo());				
				
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
		public void updateCsTimes(MemberVO memberVO) {
			Connection con = null;
			PreparedStatement pstmt = null;
			
			try {
				con = ds.getConnection();
				pstmt = con.prepareStatement(UPDATE_CSTIMES_STMT);
				
				pstmt.setInt(1, memberVO.getCsTimes());
				pstmt.setString(2, memberVO.getMbNo());		
				
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
		public List<String> getAllMbUserName() {
			List<String> list = new ArrayList<String>();
			Connection con = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			String userName = null;
			try {
				
				con = ds.getConnection();
				
				
				pstmt = con.prepareStatement(GETALLMBUSERNAME_STME);
				rs = pstmt.executeQuery();
		
				while (rs.next()) {
					userName = rs.getString("mbUsrName");
					
					list.add(userName); // Store the row in the List
				}
		
				// Handle any SQL errors
			}catch (SQLException se) {
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
}
