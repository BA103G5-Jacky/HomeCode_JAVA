package com.case_type.model;

import java.util.*;
import java.sql.*;

public class Case_typeJDBCDAO implements Case_typeDAO_interface {



		String driver = "oracle.jdbc.driver.OracleDriver";
		String url = "jdbc:oracle:thin:@localhost:1521:XE";
		String userid = "G5";
		String passwd = "123456";

		private static final String INSERT_STMT = 
			"INSERT INTO case_type (csNo,skillNo) VALUES (?,?)";
		private static final String GET_ALL_STMT = 
			"SELECT csNo,skillNo FROM case_type order by csNo";
		private static final String GET_ONE_STMT = 
			"SELECT csNo,skillNo FROM case_type where  csNo= ?";
		private static final String DELETE = 
			"DELETE FROM case_type  where csNo = ?";
		private static final String UPDATE = 
			"UPDATE case_type SET skillNo= ? where csNo = ?";
		@Override
		public void insert(Case_typeVO case_typeVO) {
			// TODO Auto-generated method stub
			Connection con = null;
			PreparedStatement pstmt = null;

			try {

				Class.forName(driver);
				con = DriverManager.getConnection(url, userid, passwd);
				pstmt = con.prepareStatement(INSERT_STMT);

				pstmt.setString(1, case_typeVO.getCsNo());
				pstmt.setString(2, case_typeVO.getSkillNo());
			

				pstmt.executeUpdate();

				// Handle any SQL errors
			} catch (ClassNotFoundException e) {
				throw new RuntimeException("Couldn't load database driver. "
						+ e.getMessage());
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
		public void update (Case_typeVO case_typeVO) {
			// TODO Auto-generated method stub
			Connection con = null;
			PreparedStatement pstmt = null;

			try {

				Class.forName(driver);
				con = DriverManager.getConnection(url, userid, passwd);
				pstmt = con.prepareStatement(UPDATE);

				pstmt.setString(2, case_typeVO.getCsNo());
				pstmt.setString(1, case_typeVO.getSkillNo());
			
				pstmt.executeUpdate();

			} catch (ClassNotFoundException e) {
				throw new RuntimeException("Couldn't load database driver. "
						+ e.getMessage());
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
		public void delete(String csNo) {
			// TODO Auto-generated method stub
			
			Connection con = null;
			PreparedStatement pstmt = null;

			try {

				Class.forName(driver);
				con = DriverManager.getConnection(url, userid, passwd);
				pstmt = con.prepareStatement(DELETE);
				
				pstmt.setString(1,csNo);

				pstmt.executeUpdate();

				// Handle any driver errors
			} catch (ClassNotFoundException e) {
				throw new RuntimeException("Couldn't load database driver. "
						+ e.getMessage());
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
		public List<Case_typeVO> findByPrimaryKey(String csNo) {
			// TODO Auto-generated method stub
			List<Case_typeVO> list = new ArrayList<Case_typeVO>();
			Case_typeVO case_typeVO = null;
			Connection con = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;

			try {

				Class.forName(driver);
				con = DriverManager.getConnection(url, userid, passwd);
				pstmt = con.prepareStatement(GET_ONE_STMT);
				
				pstmt.setString(1, csNo);

				rs = pstmt.executeQuery();

				while (rs.next()) {
					case_typeVO = new Case_typeVO();
					case_typeVO.setCsNo(rs.getString("csNo"));
					case_typeVO.setSkillNo(rs.getString("skillNo"));
					
					list.add(case_typeVO);
				}

				// Handle any driver errors
			} catch (ClassNotFoundException e) {
				throw new RuntimeException("Couldn't load database driver. "
						+ e.getMessage());
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
		public List<Case_typeVO> getAll() {
			// TODO Auto-generated method stub
			List<Case_typeVO> list = new ArrayList<Case_typeVO>();
			Case_typeVO case_typeVO = null;

			Connection con = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;

			try {

				Class.forName(driver);
				con = DriverManager.getConnection(url, userid, passwd);
				pstmt = con.prepareStatement(GET_ALL_STMT);
				rs = pstmt.executeQuery();

				while (rs.next()) {
					// empVO �]�٬� Domain objects
					 case_typeVO = new  Case_typeVO();
						case_typeVO.setCsNo(rs.getString("csNo"));
						case_typeVO.setSkillNo(rs.getString("skillNo"));
				
					list.add(case_typeVO); // Store the row in the list
				}

				// Handle any driver errors
			} catch (ClassNotFoundException e) {
				throw new RuntimeException("Couldn't load database driver. "
						+ e.getMessage());
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

		Case_typeJDBCDAO CT = new Case_typeJDBCDAO();

			// �s�W
//		 	Case_typeVO case_typeVO1 = new  Case_typeVO();
//			 case_typeVO1.setCsNo("CS0000009");
//			 case_typeVO1.setSkillNo("ST0000001");
//			 CT.insert(case_typeVO1);
		
			// �ק�
			 Case_typeVO case_typeVO2 = new  Case_typeVO();
			 case_typeVO2.setCsNo("CS0000007");
			 case_typeVO2.setSkillNo("ST0000002");
			 CT.update(case_typeVO2);
			 	System.out.println("---------");
		
			// �R��
//			 CT.delete("CS0000009");

			// �d��
//			List<Case_typeVO> list = CT.findByPrimaryKey("CS0000010");
//			for (Case_typeVO ACS : list) {
//				System.out.print(ACS.getCsNo() + ",");
//				System.out.print(ACS.getSkillNo() + ",");
//	
//				System.out.println();
//			}
			
//			System.out.println("---------------------");

			// �d��
//			List<Case_typeVO> list = CT.getAll();
//			for (Case_typeVO ACS : list) {
//				System.out.print(ACS.getCsNo() + ",");
//				System.out.print(ACS.getCsNo() + ",");
//	
//				System.out.println();
//				}
			}

		
}


