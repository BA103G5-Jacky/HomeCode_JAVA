package com.skill_type.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.case_type.model.Case_typeJDBCDAO;
import com.case_type.model.Case_typeVO;

public class Skill_typeJDBCDAO implements Skill_typeDAO_interface {

	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:XE";
	String userid = "G5mark";
	String passwd = "123456";

	private static final String INSERT_STMT = "INSERT INTO skill_type (skillNo,skillName)  VALUES ('ST'||lpad(skill_type_sq.nextval,7,'0'), ?)";
	private static final String GET_ALL_STMT = "SELECT skillNo,skillName,skillDesc FROM skill_type order by skillNo";
	private static final String GET_ONE_STMT = "SELECT skillNo,skillName,skillDesc FROM skill_type where  skillNo= ?";
	private static final String DELETE = "DELETE FROM skill_type  where skillNo = ?";
	private static final String UPDATE = "UPDATE skill_type SET skillName= ? where skillNo = ?";
	private static final String GET_MBNO = "SELECT skillname FROM skill_type where skillno in(select skillno FROM member_skill where mbno=?)";

	@Override
	public void insert(Skill_typeVO skill_typeVO) {
		// TODO Auto-generated method stub
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setString(1, skill_typeVO.getSkillName());

			pstmt.executeUpdate();

			// Handle any SQL errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
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
	public void update(Skill_typeVO skill_typeVO) {
		// TODO Auto-generated method stub
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE);
			pstmt.setString(2, skill_typeVO.getSkillNo());
			pstmt.setString(1, skill_typeVO.getSkillName());

			pstmt.executeUpdate();

		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
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
	public void delete(String skillNo) {
		// TODO Auto-generated method stub
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(DELETE);

			pstmt.setString(1, skillNo);

			pstmt.executeUpdate();

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
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
	public Skill_typeVO findByPrimaryKey(String skillNo) {
		// TODO Auto-generated method stub
		Skill_typeVO skill_typeVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setString(1, skillNo);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVo �]�٬� Domain objects
				skill_typeVO = new Skill_typeVO();
				skill_typeVO.setSkillNo(rs.getString("skillNo"));
				skill_typeVO.setSkillName(rs.getString("skillName"));
				skill_typeVO.setSkillDesc(rs.getString("skillDesc"));
			}

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
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
		return skill_typeVO;
	}

	@Override
	public List<Skill_typeVO> getAll() {
		// TODO Auto-generated method stub

		List<Skill_typeVO> list = new ArrayList<Skill_typeVO>();
		Skill_typeVO skill_typeVO = null;

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
				skill_typeVO = new Skill_typeVO();
				skill_typeVO.setSkillNo(rs.getString("skillNo"));
				skill_typeVO.setSkillName(rs.getString("skillName"));
				skill_typeVO.setSkillDesc(rs.getString("skillDesc"));

				list.add(skill_typeVO); // Store the row in the list
			}

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
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

		Skill_typeJDBCDAO ST = new Skill_typeJDBCDAO();

		// �s�W
		Skill_typeVO skill_typeVO1 = new Skill_typeVO();
		skill_typeVO1.setSkillName("Linux");
		ST.insert(skill_typeVO1);
		System.out.println("---------");
		// �R��
		// �ק�
		// Skill_typeVO skill_typeVO2 = new Skill_typeVO();
		// skill_typeVO2.setSkillNo("ST0000002");
		// skill_typeVO2.setSkillName("H");
		// ST.update(skill_typeVO2);
		// System.out.println("---------"+ST);
		// �R��
		// ST.delete("ST0000024");

		// �d��
		// Skill_typeVO stVO3 = ST.findByPrimaryKey("ST0000002");
		// System.out.print(stVO3.getSkillNo() + ",");
		// System.out.print(stVO3.getSkillName() + ",");
		// System.out.println("");

		// �d��
		// List<Skill_typeVO> list = ST.getAll();
		// for (Skill_typeVO AST : list) {
		// System.out.print(AST.getSkillNo() + ",");
		// System.out.print(AST.getSkillNo() + ",");
		// System.out.println("");
	}

	@Override
	public Skill_typeVO findBymbNo(String skillNo) {
		// TODO Auto-generated method stub
		Skill_typeVO skill_typeVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_MBNO);

			pstmt.setString(1, skillNo);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVo �]�٬� Domain objects
				skill_typeVO = new Skill_typeVO();
				skill_typeVO.setSkillNo(rs.getString("skillNo"));
				skill_typeVO.setSkillName(rs.getString("skillName"));
				skill_typeVO.setSkillDesc(rs.getString("skillDesc"));
			}

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
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
		return skill_typeVO;
	}

}
