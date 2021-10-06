package com.skill_type.model;

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

import com.case_type.model.Case_typeVO;

public class Skill_typeJNDIDAO implements Skill_typeDAO_interface {
	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/BA103G5");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}

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
			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setString(1, skill_typeVO.getSkillName());

			pstmt.executeUpdate();

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
	public void update(Skill_typeVO skill_typeVO) {
		// TODO Auto-generated method stub
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);
			pstmt.setString(2, skill_typeVO.getSkillNo());
			pstmt.setString(1, skill_typeVO.getSkillName());

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

	@Override
	public void delete(String skillNo) {
		// TODO Auto-generated method stub
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);

			pstmt.setString(1, skillNo);

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

	@Override
	public Skill_typeVO findByPrimaryKey(String skillNo) {
		// TODO Auto-generated method stub
		Skill_typeVO skill_typeVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
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

			con = ds.getConnection();
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

	@Override
	public Skill_typeVO findBymbNo(String skillNo) {
		// TODO Auto-generated method stub
		Skill_typeVO skill_typeVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
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
