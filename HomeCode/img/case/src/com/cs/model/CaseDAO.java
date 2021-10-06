package com.cs.model;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import jdbc.util.CompositeQuery.Case_CompositeQuery;

public class CaseDAO implements CaseDAO_interface{
	
	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/BA103G5");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}

	public final static String INSERT_STMT = "INSERT INTO CASE (CSNO, CSNAME, HIRERNO, CSLOC, CSPAYMENT, STARTDATE, ENDDATE, CSLEVEL, CSDESC)"
			+ "VALUES ('CS'||LPAD(CASE_SQ.NEXTVAL, 7, '0'), ?, ?, ?, ?, to_date(?,'YYYY-MM-DD'), to_date(?,'YYYY-MM-DD'), ?, ?)";
	public final static String UPDATE_BYMB_STMT = "UPDATE CASE SET CSNAME=?, CSLOC=?, CSPAYMENT=?, STARTDATE=to_date(?,'YYYY-MM-DD'), ENDDATE=to_date(?,'YYYY-MM-DD'), CSLEVEL=?, CSDESC=? WHERE CSNO = ?";
	public final static String UPDATE_BYADMIN_STMT = "UPDATE CASE SET CSSTATE = ?, CSPAYSTATE= ? WHERE CSNO = ?";
	public final static String UPDATE_FREELANCER_STMT = "UPDATE CASE SET FREELANCERNO = ? WHERE CSNO = ?";
	public final static String UPDATE_SCHEDULE_STMT = "UPDATE CASE SET SCHEDULE = ? WHERE CSNO = ?";
	public final static String UPDATE_SCHEDULE_DONE_STMT = "UPDATE CASE SET SCHEDULE = ?, REALENDDATE = sysdate WHERE CSNO = ?";
	public final static String UPDATE_HIRERSCORE = "UPDATE CASE SET HIRERSCORE = ?, HIRERCOMMENT = ? WHERE CSNO = ?";
	public final static String UPDATE_FREELANCERSCORE = "UPDATE CASE SET FREELANCERSCORE = ?, FREELANCERCOMMENT = ? WHERE CSNO = ?";
	public final static String DELETE = "DELETE FROM CASE WHERE CSNO = ?";
	public final static String FIND_BYPK_STMT = "SELECT CSNO, CSNAME, HIRERNO, FREELANCERNO, CSPAYMENT, CSSTATE, CSPAYSTATE,CSLEVEL, STARTDATE, ENDDATE,FREELANCERSCORE, CSDESC, postTime, csLoc, SCHEDULE FROM CASE WHERE CSNO = ?";
	public final static String GET_ALL_STMT = "SELECT CSNO, CSNAME, HIRERNO, FREELANCERNO, CSPAYMENT, CSSTATE, CSPAYSTATE,CSLEVEL,to_char(STARTDATE,'YYYY-MM-DD') as StartDate,FREELANCERSCORE,to_char(ENDDATE,'YYYY-MM-DD') AS ENDDATE,csLoc FROM CASE WHERE CSSTATE = '接洽中' ORDER BY CSNO";
	public final static String GET_ALL2_STMT = "SELECT CSNAME,to_char(POSTTIME,'YYYY-MM-DD') as PostDate FROM CASE ORDER BY CSNO";
	public final static String FIND_BYHIRENO_STMT = "SELECT CSNO, CSNAME, FREELANCERNO, SCHEDULE, CSLOC, CSPAYSTATE, CSPAYMENT,to_char(STARTDATE,'YYYY-MM-DD') as StartDate,to_char(ENDDATE,'YYYY-MM-DD') AS ENDDATE, POSTTIME, CSLEVEL, CSDESC, REALENDDATE,"
			+ " HIRERSCORE, FREELANCERSCORE, HIRERCOMMENT, FREELANCERCOMMENT, CSSTATE FROM CASE WHERE CSSTATE = ? AND HIRERNO = ? ORDER BY CSNO DESC";
	public final static String FIND_FREELANCERNO_STMT = "SELECT CSNO, CSNAME, HIRERNO, SCHEDULE, CSLOC, CSPAYSTATE, CSPAYMENT,to_char(STARTDATE,'YYYY-MM-DD') as StartDate,to_char(ENDDATE,'YYYY-MM-DD') AS ENDDATE, POSTTIME, CSLEVEL, CSDESC, REALENDDATE,"
			+ " HIRERSCORE, FREELANCERSCORE, HIRERCOMMENT, FREELANCERCOMMENT  FROM CASE WHERE CSSTATE = ? AND FREELANCERNO = ? ORDER BY CSNO DESC";
	public final static String FIND_BYCSPAYMENT_STMT = "SELECT CSNO, CSNAME, HIRERNO, SCHEDULE, CSLOC, CSPAYSTATE, CSPAYMENT, STARTDATE, ENDDATE, POSTTIME, CSLEVEL, CSDESC"
			+ " FROM CASE WHERE CSPAYMENT > ?  ORDER BY CSPAYMENT DESC";
	public final static String FIND_BYCSLEVEL_STMT = "SELECT CSNO, CSNAME, HIRERNO, SCHEDULE, CSLOC, CSPAYSTATE, CSPAYMENT, STARTDATE, ENDDATE, POSTTIME, CSLEVEL, CSDESC"
			+ " FROM CASE WHERE CSLEVEL = ?  ORDER BY POSTTIME DESC";	
	public final static String GET_MBFRESCOREAVG_STMT = "SELECT FREELANCERSCORE FROM CASE WHERE FREELANCERNO = ? AND CSSTATE = '已完成'";
	public final static String GET_MBSUCCESSTIMES_STMT = "SELECT CSNO, CSNAME, HIRERNO, FREELANCERNO, CSPAYMENT, CSSTATE, CSPAYSTATE,CSLEVEL, STARTDATE, ENDDATE,FREELANCERSCORE, CSDESC, postTime, csLoc, SCHEDULE FROM CASE WHERE SCHEDULE = 100 AND FREELANCERNO = ?";
	public final static String UPDATE_CSSTATE_STMT = "UPDATE CASE SET CSSTATE = ?, REALENDDATE = sysdate WHERE CSNO = ?";
	public final static String GET_MBHIRERSCOREAVG_STMT = "SELECT HIRERSCORE FROM CASE WHERE HIRERNO = ? AND CSSTATE = '已完成'";
	public final static String GET_MBPOSTTIMES_STMT = "SELECT COUNT(*) as PostTImes FROM CASE WHERE HIRERNO = ? ";
	
	@Override
	public String insert(CaseVO caseVO) {
		String csNo = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		String[] cols = {"csNo"};

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT, cols);

			pstmt.setString(1, caseVO.getCsName());
			pstmt.setString(2, caseVO.getHirerNo());
			pstmt.setString(3, caseVO.getCsLoc());
			pstmt.setInt(4, caseVO.getCsPayment());
			pstmt.setString(5, caseVO.getStartDate());
			pstmt.setString(6, caseVO.getEndDate());
			pstmt.setString(7, caseVO.getCsLevel());
			pstmt.setString(8, caseVO.getCsDesc());

			pstmt.executeUpdate();
			
			ResultSet rs = pstmt.getGeneratedKeys();
			
			rs.next();
			csNo = rs.getString(1);
			rs.close();

		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
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
		return csNo;

	}

	@Override
	public void updateByMb(CaseVO caseVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE_BYMB_STMT);

			pstmt.setString(1, caseVO.getCsName());
			pstmt.setString(2, caseVO.getCsLoc());
			pstmt.setInt(3, caseVO.getCsPayment());
			pstmt.setString(4, caseVO.getStartDate());
			pstmt.setString(5, caseVO.getEndDate());
			pstmt.setString(6, caseVO.getCsLevel());
			pstmt.setString(7, caseVO.getCsDesc());
			pstmt.setString(8, caseVO.getCsNo());

			pstmt.executeUpdate();

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
	public void updateFreelancer(CaseVO caseVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE_FREELANCER_STMT);

			pstmt.setString(1, caseVO.getFreelancerNo());
			pstmt.setString(2, caseVO.getCsNo());

			pstmt.executeUpdate();

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
	public void updateByAdmin(CaseVO caseVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE_BYADMIN_STMT);

			pstmt.setString(1, caseVO.getCsState());
			pstmt.setString(2, caseVO.getCsPayState());
			pstmt.setString(3, caseVO.getCsNo());

			pstmt.executeUpdate();

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
	public void updateSchedule(CaseVO caseVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = ds.getConnection();
			if (caseVO.getSchedule() != 100) {
				pstmt = con.prepareStatement(UPDATE_SCHEDULE_STMT);
			} else {
				pstmt = con.prepareStatement(UPDATE_SCHEDULE_DONE_STMT);
			}

			pstmt.setInt(1, caseVO.getSchedule());
			pstmt.setString(2, caseVO.getCsNo());
			pstmt.executeUpdate();

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
	public void updateHireScoreComment(CaseVO caseVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE_HIRERSCORE);

			pstmt.setInt(1, caseVO.getHirerScore());
			pstmt.setString(2, caseVO.getHirerComment());
			pstmt.setString(3, caseVO.getCsNo());

			int a = pstmt.executeUpdate();
			System.out.println("successUpdate" + a);

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
	public void updateFreelancerScoreComment(CaseVO caseVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE_FREELANCERSCORE);

			pstmt.setInt(1, caseVO.getFreelancerScore());
			pstmt.setString(2, caseVO.getFreelancerComment());
			pstmt.setString(3, caseVO.getCsNo());

			pstmt.executeUpdate();

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
	public void delete(String csNo) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);

			pstmt.setString(1, csNo);

			pstmt.executeUpdate();

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
	public CaseVO findByPrimaryKey(String csNo) {
		CaseVO caseVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(FIND_BYPK_STMT);

			pstmt.setString(1, csNo);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				caseVO = new CaseVO();
				caseVO.setCsNo(rs.getString("csNo"));
				caseVO.setCsName(rs.getString("csName"));
				caseVO.setHirerNo(rs.getString("hirerNo"));
				caseVO.setFreelancerNo(rs.getString("freelancerNo"));
				caseVO.setCsPayment(rs.getInt("csPayment"));
				caseVO.setCsState(rs.getString("csState"));
				caseVO.setCsPayState(rs.getString("csPayState"));
				caseVO.setCsLevel(rs.getString("csLevel"));
				caseVO.setStartDate(rs.getString("startDate"));
				caseVO.setEndDate(rs.getString("endDate"));
				caseVO.setFreelancerScore(rs.getInt("freelancerScore"));
				caseVO.setCsDesc(rs.getString("csDesc"));
				caseVO.setPostTime(rs.getDate("postTime"));
				caseVO.setCsLoc(rs.getString("csLoc"));
				caseVO.setSchedule(rs.getInt("schedule"));
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

		return caseVO;
	}

	@Override
	public List<CaseVO> findByHirerNo(String hirerNo, String csState) {
		List<CaseVO> list = new ArrayList<CaseVO>();
		CaseVO caseVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(FIND_BYHIRENO_STMT);
			pstmt.setString(1, csState);
			pstmt.setString(2, hirerNo);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				caseVO = new CaseVO();
				caseVO.setCsNo(rs.getString("csNo"));
				caseVO.setCsName(rs.getString("csName"));
				caseVO.setFreelancerNo(rs.getString("freelancerNo"));
				caseVO.setSchedule(rs.getInt("schedule"));
				caseVO.setCsLoc(rs.getString("csLoc"));
				caseVO.setCsPayState(rs.getString("csPayState"));
				caseVO.setCsPayment(rs.getInt("csPayment"));
				caseVO.setStartDate(rs.getString("startDate"));
				caseVO.setEndDate(rs.getString("endDate"));
				caseVO.setPostTime(rs.getDate("postTime"));
				caseVO.setCsLevel(rs.getString("csLevel"));
				caseVO.setCsDesc(rs.getString("csDesc"));
				caseVO.setRealEndDate(rs.getDate("realEndDate"));
				caseVO.setHirerScore(rs.getInt("HIRERSCORE"));
				caseVO.setFreelancerScore(rs.getInt("FREELANCERSCORE"));
				caseVO.setHirerComment(rs.getString("HIRERCOMMENT"));
				caseVO.setFreelancerComment(rs.getString("FREELANCERCOMMENT"));
				caseVO.setCsState(rs.getString("CSSTATE"));
				

				list.add(caseVO);
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
	public List<CaseVO> findByFreelancerNo(String freelancerNo, String csState) {
		List<CaseVO> list = new ArrayList<CaseVO>();
		CaseVO caseVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(FIND_FREELANCERNO_STMT);
			pstmt.setString(1, csState);
			pstmt.setString(2, freelancerNo);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				caseVO = new CaseVO();
				caseVO.setCsNo(rs.getString("csNo"));
				caseVO.setCsName(rs.getString("csName"));
				caseVO.setHirerNo(rs.getString("hirerNo"));
				caseVO.setSchedule(rs.getInt("schedule"));
				caseVO.setCsLoc(rs.getString("csLoc"));
				caseVO.setCsPayState(rs.getString("csPayState"));
				caseVO.setCsPayment(rs.getInt("csPayment"));
				caseVO.setStartDate(rs.getString("startDate"));
				caseVO.setEndDate(rs.getString("endDate"));
				caseVO.setPostTime(rs.getDate("postTime"));
				caseVO.setCsLevel(rs.getString("csLevel"));
				caseVO.setCsDesc(rs.getString("csDesc"));
				caseVO.setRealEndDate(rs.getDate("realEndDate"));
				caseVO.setHirerScore(rs.getInt("HIRERSCORE"));
				caseVO.setFreelancerScore(rs.getInt("FREELANCERSCORE"));
				caseVO.setHirerComment(rs.getString("HIRERCOMMENT"));
				caseVO.setFreelancerComment(rs.getString("FREELANCERCOMMENT"));
				
				
				list.add(caseVO);
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
	public List<CaseVO> findByCsPayment(Integer csPayment) {
		List<CaseVO> list = new ArrayList<CaseVO>();
		CaseVO caseVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(FIND_BYCSPAYMENT_STMT);
			pstmt.setInt(1, csPayment);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				caseVO = new CaseVO();
				caseVO.setCsNo(rs.getString("csNo"));
				caseVO.setCsName(rs.getString("csName"));
				caseVO.setHirerNo(rs.getString("hirerNo"));
				caseVO.setSchedule(rs.getInt("schedule"));
				caseVO.setCsLoc(rs.getString("csLoc"));
				caseVO.setCsPayState(rs.getString("csPayState"));
				caseVO.setCsPayment(rs.getInt("csPayment"));
				caseVO.setStartDate(rs.getString("startDate"));
				caseVO.setEndDate(rs.getString("endDate"));
				caseVO.setPostTime(rs.getDate("postTime"));
				caseVO.setCsLevel(rs.getString("csLevel"));
				caseVO.setCsDesc(rs.getString("csDesc"));

				list.add(caseVO);
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
	public List<CaseVO> findByCsLevel(String csLevel) {

		List<CaseVO> list = new ArrayList<CaseVO>();
		CaseVO caseVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(FIND_BYCSLEVEL_STMT);
			pstmt.setString(1, csLevel);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				caseVO = new CaseVO();
				caseVO.setCsNo(rs.getString("csNo"));
				caseVO.setCsName(rs.getString("csName"));
				caseVO.setHirerNo(rs.getString("hirerNo"));
				caseVO.setSchedule(rs.getInt("schedule"));
				caseVO.setCsLoc(rs.getString("csLoc"));
				caseVO.setCsPayState(rs.getString("csPayState"));
				caseVO.setCsPayment(rs.getInt("csPayment"));
				caseVO.setStartDate(rs.getString("startDate"));
				caseVO.setEndDate(rs.getString("endDate"));
				caseVO.setPostTime(rs.getDate("postTime"));
				caseVO.setCsLevel(rs.getString("csLevel"));
				caseVO.setCsDesc(rs.getString("csDesc"));

				list.add(caseVO);
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
	public List<CaseVO> getAll() {
		List<CaseVO> list = new ArrayList<CaseVO>();
		CaseVO caseVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				caseVO = new CaseVO();
				caseVO.setCsNo(rs.getString("csNo"));
				caseVO.setCsName(rs.getString("csName"));
				caseVO.setHirerNo(rs.getString("hirerNo"));
				caseVO.setFreelancerNo(rs.getString("freelancerNo"));
				caseVO.setCsPayment(rs.getInt("csPayment"));
				caseVO.setCsState(rs.getString("csState"));
				caseVO.setCsPayState(rs.getString("csPayState"));
				caseVO.setCsLevel(rs.getString("csLevel"));
				caseVO.setStartDate(rs.getString("startDate"));
				caseVO.setEndDate(rs.getString("ENDDATE"));
				caseVO.setFreelancerScore(rs.getInt("FREELANCERSCORE"));
				caseVO.setCsLoc(rs.getString("csLoc"));
				list.add(caseVO);
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
	public List<CaseVO> getAll2() {
		List<CaseVO> list = new ArrayList<CaseVO>();
		CaseVO caseVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL2_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				caseVO = new CaseVO();
				caseVO.setCsName(rs.getString("csName"));
				caseVO.setPostTime(rs.getDate("postTime"));
				list.add(caseVO);
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
	public Double getMbFreelancerScoreAvg(String mbNo) {
		Double scoreAvg = 0.0;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int count = 0;
		double score = 0;
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_MBFRESCOREAVG_STMT);
			pstmt.setString(1, mbNo);
			rs = pstmt.executeQuery();
			
			while(rs.next()){
				count++;
				score = score + rs.getInt("freelancerScore");				
			}
			if( count != 0) scoreAvg = score/count;
			
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
		
		return scoreAvg;
	}
	
	@Override
	public Double getMbHirerScoreAvg(String mbNo) {
		Double scoreAvg = 0.0;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int count = 0;
		double score = 0;
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_MBHIRERSCOREAVG_STMT);
			pstmt.setString(1, mbNo);
			rs = pstmt.executeQuery();
			
			while(rs.next()){
				count++;
				score = score + rs.getInt("hirerScore");				
			}
			if( count != 0) scoreAvg = score/count;
			
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
		
		return scoreAvg;
	}

	@Override
	public List<CaseVO> getMbSuccessCase(String mbNo) {
		List<CaseVO> list = new ArrayList<CaseVO>();
		CaseVO caseVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_MBSUCCESSTIMES_STMT);

			pstmt.setString(1, mbNo);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				caseVO = new CaseVO();
				caseVO.setCsNo(rs.getString("csNo"));
				caseVO.setCsName(rs.getString("csName"));
				caseVO.setHirerNo(rs.getString("hirerNo"));
				caseVO.setFreelancerNo(rs.getString("freelancerNo"));
				caseVO.setCsPayment(rs.getInt("csPayment"));
				caseVO.setCsState(rs.getString("csState"));
				caseVO.setCsPayState(rs.getString("csPayState"));
				caseVO.setCsLevel(rs.getString("csLevel"));
				caseVO.setStartDate(rs.getString("startDate"));
				caseVO.setEndDate(rs.getString("endDate"));
				caseVO.setFreelancerScore(rs.getInt("freelancerScore"));
				caseVO.setCsDesc(rs.getString("csDesc"));
				caseVO.setPostTime(rs.getDate("postTime"));
				caseVO.setCsLoc(rs.getString("csLoc"));
				caseVO.setSchedule(rs.getInt("schedule"));
				
				list.add(caseVO);
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
	public void updateCsState(CaseVO caseVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE_CSSTATE_STMT);

			pstmt.setString(1, caseVO.getCsState());
			pstmt.setString(2, caseVO.getCsNo());

			pstmt.executeUpdate();

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
	public Integer getPostCaseTimes(String mbNo) {
		
		Integer postTimes = 0 ;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_MBPOSTTIMES_STMT);

			pstmt.setString(1, mbNo);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				postTimes = rs.getInt("postTimes");
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

		return postTimes;
	}
	
	@Override
	public List<CaseVO> getAll(Map<String, String[]> map) {
		List<CaseVO> list = new ArrayList<CaseVO>();
		CaseVO caseVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			String finalSQL = "select * from case where csstate = '接洽中' " + Case_CompositeQuery.get_WhereCondition(map);
			pstmt = con.prepareStatement(finalSQL);
			System.out.println("●●finalSQL(by caseDAO) = " + finalSQL);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				caseVO = new CaseVO();
				caseVO.setCsNo(rs.getString("csNo"));
				caseVO.setCsName(rs.getString("csName"));
				caseVO.setHirerNo(rs.getString("hirerNo"));
				caseVO.setFreelancerNo(rs.getString("freelancerNo"));
				caseVO.setCsPayment(rs.getInt("csPayment"));
				caseVO.setCsState(rs.getString("csState"));
				caseVO.setCsPayState(rs.getString("csPayState"));
				caseVO.setCsLevel(rs.getString("csLevel"));
				caseVO.setStartDate(rs.getString("startDate"));
				caseVO.setEndDate(rs.getString("ENDDATE"));
				caseVO.setFreelancerScore(rs.getInt("FREELANCERSCORE"));
				caseVO.setCsLoc(rs.getString("csLoc"));
				caseVO.setFinalSQL(finalSQL);

				list.add(caseVO); // Store the row in the List
			}

			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
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
	public List<CaseVO> changeOrderByMoney_LowToHigh(String SQL) {
		CaseVO caseVO = null;
		List<CaseVO> list = new ArrayList<CaseVO>();
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = ds.getConnection();

			String finalSQL = SQL + " order by cspayment";

			pstmt = con.prepareStatement(finalSQL);
			rs = pstmt.executeQuery();

			System.out.println("價格由低到高SQL: " + finalSQL);
			while (rs.next()) {

				caseVO = new CaseVO();
				caseVO.setCsNo(rs.getString("csNo"));
				caseVO.setCsName(rs.getString("csName"));
				caseVO.setHirerNo(rs.getString("hirerNo"));
				caseVO.setFreelancerNo(rs.getString("freelancerNo"));
				caseVO.setCsPayment(rs.getInt("csPayment"));
				caseVO.setCsState(rs.getString("csState"));
				caseVO.setCsPayState(rs.getString("csPayState"));
				caseVO.setCsLevel(rs.getString("csLevel"));
				caseVO.setStartDate(rs.getString("startDate"));
				caseVO.setEndDate(rs.getString("ENDDATE"));
				caseVO.setFreelancerScore(rs.getInt("FREELANCERSCORE"));
				caseVO.setCsLoc(rs.getString("csLoc"));
				caseVO.setFinalSQL(finalSQL);

				list.add(caseVO);

			}

		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
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
	public List<CaseVO> changeOrderByMoney_HighToLow(String SQL) {
		CaseVO caseVO = null;
		List<CaseVO> list = new ArrayList<CaseVO>();
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = ds.getConnection();
			
			String finalSQL = SQL + " order by cspayment desc";

			pstmt = con.prepareStatement(finalSQL);
			rs = pstmt.executeQuery();
			System.out.println("價格由高到低SQL: " + finalSQL);
			while (rs.next()) {

				caseVO = new CaseVO();
				caseVO.setCsNo(rs.getString("csNo"));
				caseVO.setCsName(rs.getString("csName"));
				caseVO.setHirerNo(rs.getString("hirerNo"));
				caseVO.setFreelancerNo(rs.getString("freelancerNo"));
				caseVO.setCsPayment(rs.getInt("csPayment"));
				caseVO.setCsState(rs.getString("csState"));
				caseVO.setCsPayState(rs.getString("csPayState"));
				caseVO.setCsLevel(rs.getString("csLevel"));
				caseVO.setCsLoc(rs.getString("csLoc"));
				caseVO.setStartDate(rs.getString("startDate"));
				caseVO.setEndDate(rs.getString("ENDDATE"));
				caseVO.setFreelancerScore(rs.getInt("FREELANCERSCORE"));
				caseVO.setFinalSQL(finalSQL);

				list.add(caseVO);

			}

		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
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
