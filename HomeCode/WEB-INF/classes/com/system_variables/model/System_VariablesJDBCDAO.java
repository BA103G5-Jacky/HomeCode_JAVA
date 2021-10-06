package com.system_variables.model;

import java.util.*;
import java.sql.*;

public class System_VariablesJDBCDAO implements System_VariablesDAO_interface {
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:XE";
	String userid = "BA103G5";
	String passwd = "iii";

	private static final String INSERT_STMT = "INSERT INTO System_Variables (svNo,svname,svValue) "
			+ "VALUES ( 'SV'||LPAD(SYSTEMVAR_SQ.nextval, 4, '0'), ?, ?)";
	private static final String GET_ALL_STMT = "SELECT svNo,svname, svValue FROM System_Variables order by svNo";
	private static final String GET_ONE_STMT = "SELECT svNo,svname,svValue FROM System_Variables where svNo = ?";
	private static final String DELETE = "DELETE FROM System_Variables where svNo = ?";
	private static final String UPDATE = "UPDATE System_Variables set svname=?, svValue=? where svNo= ?";

	@Override
	public void insert(System_VariablesVO system_variablesVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setString(1, system_variablesVO.getSvname());
			pstmt.setInt(2, system_variablesVO.getSvValue());
			pstmt.executeUpdate();

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
	public void update(System_VariablesVO system_variablesVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setString(1, system_variablesVO.getSvname());
			pstmt.setInt(2, system_variablesVO.getSvValue()); // int=integer??
			pstmt.setString(3, system_variablesVO.getSvNo());

			pstmt.executeUpdate();

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
	public void delete(String svNo) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(DELETE);

			pstmt.setString(1, svNo);

			pstmt.executeUpdate();

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
	public System_VariablesVO findByPrimaryKey(String svNo) {

		System_VariablesVO system_variablesVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setString(1, svNo);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVo 也稱為 Domain objects
				system_variablesVO = new System_VariablesVO();
				system_variablesVO.setSvNo(rs.getString("svNo"));

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
		return system_variablesVO;
	}

	@Override
	public List<System_VariablesVO> getAll() {
		List<System_VariablesVO> list = new ArrayList<System_VariablesVO>();
		System_VariablesVO system_variablesVO = null;

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
				system_variablesVO = new System_VariablesVO();
				system_variablesVO.setSvNo(rs.getString("svNo"));
				system_variablesVO.setSvname(rs.getString("svname"));
				system_variablesVO.setSvValue(rs.getInt("svValue"));
				list.add(system_variablesVO); // Store the row in the list
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

		System_VariablesJDBCDAO dao = new System_VariablesJDBCDAO();

		// 新增
		System_VariablesVO empVO1 = new System_VariablesVO();
		empVO1.setSvNo("sysvar00007");
		empVO1.setSvname("自動更新ex1");
		empVO1.setSvValue(7);
		dao.insert(empVO1);

		// 修改
		System_VariablesVO empVO2 = new System_VariablesVO();
		empVO2.setSvNo("sysvar00007");
		empVO2.setSvname("自動更新ex2");
		empVO2.setSvValue(1);

		dao.update(empVO2);

		// 刪除
		dao.delete("sysvar00007");

		// 查詢
		System_VariablesVO empVO3 = dao.findByPrimaryKey("svNO");
		System.out.print(empVO3.getSvNo() + ",");
		System.out.print(empVO3.getSvname() + ",");
		System.out.print(empVO3.getSvValue() + ",");
		System.out.println("---------------------");

		// 查詢
		List<System_VariablesVO> list = dao.getAll();
		for (System_VariablesVO aEmp : list) {
			System.out.print(aEmp.getSvNo() + ",");
			System.out.print(aEmp.getSvname() + ",");
			System.out.print(aEmp.getSvValue() + ",");
			System.out.println();

		}
	}
}