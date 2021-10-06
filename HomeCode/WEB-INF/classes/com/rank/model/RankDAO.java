package com.rank.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.util.*;
public class RankDAO implements RankDAO_interface {

	
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
				"INSERT INTO rank (rankno,mbno) VALUES (rank_sq.nextval,?)";
		private static final String GET_ALL_STMT = 
				"SELECT * FROM rank order by rankno";
		private static final String GET_ONE_STMT = 
				"SELECT rankno,mbno FROM rank where mbno = ?";
		private static final String GET_ONE_STMT2 = 
				"SELECT rankno,mbno FROM rank where rankno = ?";
		 private static final String UPDATE =
		 "UPDATE rank set rank=?,mbno=? where mbno = ?";
		private static final String RANK = "select rownum as rank ,mbno,rankno from(select mbno,rankno from rank order by rankno) where rownum<=?";
		private static final String DELETE = 
				"DELETE FROM RANK where MBNO = ?";
		@Override
		public void insert(RankVO rankVO) {

			Connection con = null;
			PreparedStatement pstmt = null;

			try {

				
				con = ds.getConnection();
				pstmt = con.prepareStatement(INSERT_STMT);

				pstmt.setString(1, rankVO.getMbno());

				pstmt.executeUpdate();

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

		
		//排行榜不可以讓人修改!
		@Override
		public void update(RankVO rankVO) {

			Connection con = null;
			PreparedStatement pstmt = null;

			try {

				
				con = ds.getConnection();
				pstmt = con.prepareStatement(UPDATE);

//				pstmt.setString(1, empVO.getEname());
//				pstmt.setString(2, empVO.getJob());
//				pstmt.setDate(3, empVO.getHiredate());
//				pstmt.setDouble(4, empVO.getSal());
//				pstmt.setDouble(5, empVO.getComm());
//				pstmt.setInt(6, empVO.getDeptno());
//				pstmt.setInt(7, empVO.getEmpno());

				pstmt.executeUpdate();

				// Handle any driver errors
			}  catch (SQLException se) {
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
		//所以偶沒動ㄊ
		
		
		@Override
		public void delete(String mbno) {
			Connection con = null;
			PreparedStatement pstmt = null;

			try {

				
				con = ds.getConnection();
				pstmt = con.prepareStatement(DELETE);

				pstmt.setString(1, mbno);

				pstmt.executeUpdate();

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
		public RankVO findRankByRankNo(String rankno) {
			RankVO rankVO = null;
			Connection con = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;

			try {

				
				con = ds.getConnection();
				pstmt = con.prepareStatement(GET_ONE_STMT2);

				pstmt.setString(1, rankno);

				rs = pstmt.executeQuery();

				while (rs.next()) {
					// empVo 也稱為 Domain objects
					rankVO = new RankVO();
					rankVO.setRankno(rs.getString("rankno"));
					rankVO.setMbno(rs.getString("mbno"));
					
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
			return rankVO;
		}

		@Override
		public RankVO findRankByMbNo(String mbno) {
			RankVO rankVO = null;
			Connection con = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;

			try {

				
				con = ds.getConnection();
				pstmt = con.prepareStatement(GET_ONE_STMT);

				pstmt.setString(1, mbno);

				rs = pstmt.executeQuery();

				while (rs.next()) {
					// empVo 也稱為 Domain objects
					rankVO = new RankVO();
					rankVO.setRankno(rs.getString("rankno"));
					rankVO.setMbno(rs.getString("mbno"));
					
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
			return rankVO;
		}

		@Override
		public List<RankVO> findtopN(Integer num) {
			List<RankVO> list = new ArrayList<RankVO>();
			RankVO rankVO = null;

			Connection con = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;

			try {
			//	"select rownum as rank ,mbno from(select mbno from rank order by rankno) where rownum<= ? "
				
				con = ds.getConnection();
				pstmt = con.prepareStatement(RANK);
				
				pstmt.setInt(1, num);
				
				rs = pstmt.executeQuery();

				
				
				while (rs.next()) {
					// empVO 也稱為 Domain objects
					rankVO = new RankVO();
					rankVO.setRankno(rs.getString("rankno"));
					rankVO.setMbno(rs.getString("mbno"));
					list.add(rankVO); // Store the row in the list
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
		public List<RankVO> getAll() {
			List<RankVO> list = new ArrayList<RankVO>();
			RankVO empVO = null;

			Connection con = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;

			try {

				
				con = ds.getConnection();
				pstmt = con.prepareStatement(GET_ALL_STMT);
				rs = pstmt.executeQuery();

				while (rs.next()) {
					// empVO 也稱為 Domain objects
					empVO = new RankVO();
					empVO.setRankno(rs.getString("rankno"));
					empVO.setMbno(rs.getString("mbno"));
					
					list.add(empVO); // Store the row in the list
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

		
		public static void main(String[] args) {

			RankJDBCDAO dao = new RankJDBCDAO();

			// 新增   新增有問題!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
//			RankVO empVO1 = new RankVO();
//			empVO1.setMbno("MB00000006");
//			
//			dao.insert(empVO1);

			// 修改 //排行榜不可以讓人修改!
//			RankVO empVO2 = new RankVO();
//			empVO2.setEmpno(7001);
//			empVO2.setEname("吳永志2");
//			empVO2.setJob("MANAGER2");
//			empVO2.setHiredate(java.sql.Date.valueOf("2002-01-01"));
//			empVO2.setSal(new Double(20000));
//			empVO2.setComm(new Double(200));
//			empVO2.setDeptno(20);
//			dao.update(empVO2);
	//
//			// 刪除
//			dao.delete("MB0000027");
	//
//			// 查詢 findRankByRankNo
			RankVO empVO3 = dao.findRankByRankNo("2");
			System.out.print(empVO3.getRankno() + ",");
			System.out.print(empVO3.getMbno() + ",");
			System.out.println(dao.findRankByRankNo("2").getMbno());
			System.out.println("---------------------");
			
			
			// 查詢 findRankByMbno
//			RankVO empVO3 = dao.findRankByMbNo("MB0000027");
//			System.out.print(empVO3.getRankno() + ",");
//			System.out.print(empVO3.getMbno() + ",");
//			
//			System.out.println("---------------------");
//			
			
	//
//			// 查詢
//			List<RankVO> list = dao.getAll();
//			for (RankVO aEmp : list) {
//				System.out.print(aEmp.getRankno() + ",");
//				System.out.print(aEmp.getMbno() + ",");
//				
//				System.out.println();
//		}
			
			
			// 查詢 排行榜! 輸入X多少來決定顯示前X筆的資料
//	"select rownum as rank ,mbno,rankno from(select mbno,rankno from rank order by rankno) where rownum<=?"
//			List<RankVO> list = dao.findtopN(5);
//			for (RankVO aEmp : list) {
//				System.out.print(aEmp.getRankno() + ",");
//				System.out.print(aEmp.getMbno() + ",");
//				
//				System.out.println();
//		}
	}
}
