package com.member_skill.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class Member_skillJDBCDAO implements Member_skillDAO_interface {

	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:Oracle:thin:@localhost:1521:XE";
	String userid = "BA103G5";
	String passwd = "iii";

	private static final String INSERT_STMT = "INSERT INTO Member_skill (skillno,mbno) values (?,?)";
	private static final String DELETE = "DELETE from Applicant where skillno=? and mbno=?";
	private static final String GET_ONE_STMT = "Select skillno,mbno from Member_skill where skillno=? and mbno=?";
	private static final String GET_ALL_STMT = "Select skillno,mbno from Member_skill order by mbno";
	private static final String GET_MBNO="SELECT SKILLNAME FROM skill_type where SKILLNO in(select SKILLNO FROM member_skill where MBNO=?)";
	private static final String DELETE2 = "DELETE from Member_skill where mbNo=?";
	@Override
	public void insert(Member_skillVO member_skillVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setString(1, member_skillVO.getSkillNo());
			pstmt.setString(2, member_skillVO.getMbNo());

			pstmt.executeUpdate();

			System.out.println("insert mehtod有跑到!");

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
			// Handle any SQL errors
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
	public void delete(String skillNo, String mbNo) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(DELETE);

			pstmt.setString(1, skillNo);
			pstmt.setString(2, mbNo);

			pstmt.executeUpdate();
			
			System.out.println("有跑到刪除這ㄍmethod ㄑ資料庫看看ㄅ");
			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
			// Handle any SQL errors
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
	public Member_skillVO findByPrimaryKey(String skillNo, String mbNo) {
		Member_skillVO member_skillVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setString(1, skillNo);
			pstmt.setString(2, mbNo);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVo 也稱為 Domain objects
				member_skillVO = new Member_skillVO();
				member_skillVO.setSkillNo(rs.getString("skillno"));
				member_skillVO.setMbNo(rs.getString("mbno"));

			}

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
			// Handle any SQL errors
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
		return member_skillVO;
	}

	@Override
	public List<Member_skillVO> getAll() {
		List<Member_skillVO> list = new ArrayList<Member_skillVO>();
		Member_skillVO member_skillVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVO 也稱為 Domain objects
				member_skillVO = new Member_skillVO();
				member_skillVO.setSkillNo(rs.getString("skillno"));
				member_skillVO.setMbNo(rs.getString("mbno"));

				list.add(member_skillVO); // Store the row in the list
			}

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
			// Handle any SQL errors
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
		Member_skillJDBCDAO dao = new Member_skillJDBCDAO();

	// 新增
		Member_skillVO member_skillVO1 = new Member_skillVO();
		
	member_skillVO1.setSkillNo("ST0000003");
	member_skillVO1.setMbNo("MB0000025");

	dao.insert(member_skillVO1);

//	// 修改

//
//	// 刪除
//dao.delete("CS0000015","MB0000044");
//
//	// 查詢											
//		Member_skillVO member_skillVO3 = dao.findByPrimaryKey("ST0000001", "MB0000024");
//	System.out.print(member_skillVO3.getSkillNo() + ",");
//	System.out.print(member_skillVO3.getMbNo() + ",");
//	
//	System.out.println("---------------------");
//
//	// 查詢
//	List<Member_skillVO> list = dao.getAll();
//	for (Member_skillVO aMemberSkillList : list) {
//		System.out.print(aMemberSkillList.getSkillNo() + ",");
//		System.out.print(aMemberSkillList.getMbNo() + "!");
//		
//	
//		System.out.println();
//	}
//	List<String> list2 = dao.getmbNo("MB0000002");
//	for (String skillName : list2) {
//		System.out.print(skillName);	
//		System.out.println();
//	}
	
}

	@Override
	public List<String> getmbNo(String mbNo) {
		// TODO Auto-generated method stub
		List<String> list = new ArrayList<String>();

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_MBNO);
			pstmt.setString(1,mbNo);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVO 也稱為 Domain objects
				list.add(rs.getString("skillName")); // Store the row in the list
			}

			// Handle any driver errors
		
			// Handle any SQL errors
		} catch (SQLException | ClassNotFoundException se) {
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
	public void update(Member_skillVO member_skillVO) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteOneMbSkill(String mbNo) {
		// TODO Auto-generated method stub
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(DELETE2);
			
			pstmt.setString(1,mbNo);

			pstmt.executeUpdate();

			// Handle any driver errors

		} catch (SQLException | ClassNotFoundException se) {
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
		
	}

