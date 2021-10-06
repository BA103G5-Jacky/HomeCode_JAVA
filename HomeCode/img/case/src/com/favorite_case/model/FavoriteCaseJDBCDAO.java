package com.favorite_case.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class FavoriteCaseJDBCDAO implements FavoriteCaseDAO_interface {

	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:XE";
	String userid = "BA103G5";
	String passwd = "iii";

	private static final String INSERT_STMT = "INSERT INTO Favorite_Case (csNO, mbNO) VALUES (?,?)";
	private static final String GET_ALL_STMT = "SELECT csNO, mbNO FROM Favorite_Case order by csNO";
	private static final String GET_ONE_STMT = "SELECT csNO, mbNO FROM Favorite_Case where csNO = ? and mbNO=?";
	private static final String DELETE = "DELETE FROM Favorite_Case where csNO = ? and mbNO=?";
	private static final String GET_ONEMBFC_STMT = "SELECT csNo FROM Favorite_Case WHERE mbNo = ?";
	private static final String DELETEALL_STMT = "DELETE FROM FAVORITE_CASE WHERE CSNO = ?";
	private static final String GET_ONECS_STMT = "SELECT mbNo FROM Favorite_Case WHERE csNo = ?";

	@Override
	public void insert(FavoriteCaseVO favoriteCaseVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setString(1, favoriteCaseVO.getCsNo());
			pstmt.setString(2, favoriteCaseVO.getMbNo());

			pstmt.executeUpdate();

			System.out.println("Data has been insert");

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
	public void delete(String csNo, String mbNo) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(DELETE);

			pstmt.setString(1, csNo);
			pstmt.setString(2, mbNo);

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
	public List<FavoriteCaseVO> getAll() {
		List<FavoriteCaseVO> list = new ArrayList<FavoriteCaseVO>();
		FavoriteCaseVO favoriteCaseVO = null;
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
				favoriteCaseVO = new FavoriteCaseVO();
				favoriteCaseVO.setCsNo(rs.getString("csNo"));
				favoriteCaseVO.setMbNo(rs.getString("mbNo"));
				list.add(favoriteCaseVO); // Store the row in the list
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

	@Override
	public FavoriteCaseVO findByPrimaryKey(String csNo, String mbNo) {
		FavoriteCaseVO favoriteCaseVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setString(1, csNo);
			pstmt.setString(2, mbNo);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVo 也稱為 Domain objects
				favoriteCaseVO = new FavoriteCaseVO();
				favoriteCaseVO.setCsNo(rs.getString("csNo"));
				favoriteCaseVO.setMbNo(rs.getString("mbNo"));

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
		return favoriteCaseVO;
	}

	@Override
	public List<String> getOneMbFavoriteCase(String mbNo) {
		List<String> list = new ArrayList<String>();
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONEMBFC_STMT);
			pstmt.setString(1, mbNo);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				list.add(rs.getString("csNo"));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
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

	public static void main(String args[]) {

		FavoriteCaseJDBCDAO dao = new FavoriteCaseJDBCDAO();

		// 新增
		FavoriteCaseVO favoriteCaseVO1 = new FavoriteCaseVO();
		favoriteCaseVO1.setCsNo("CS0000008");
		favoriteCaseVO1.setMbNo("MB0000009");

		dao.insert(favoriteCaseVO1);

		// 刪除
		// dao.delete("CS0000016","MB0000044");

		// 查詢
		// FavoriteCaseVO milestoneVO3 =
		// dao.findByPrimaryKey("CS0000016","MB0000044");
		// System.out.print(milestoneVO3.getCsNo() + ",");
		// System.out.print(milestoneVO3.getMbNo() + ",");
		// System.out.println();
		// System.out.println("---------------------");

		// 查詢
		// List<FavoriteCaseVO> list = dao.getAll();
		// for (FavoriteCaseVO afavoriteCase : list) {
		// System.out.print(afavoriteCase.getCsNo() + ",");
		// System.out.print(afavoriteCase.getMbNo());
		//
		// System.out.println();
		// }

		//
	}

	@Override
	public void deleteAll(String csNo) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<String> getOneCaseFavoritMb(String csNo) {
		// TODO Auto-generated method stub
		return null;
	}

}
