package com.favorite_case.model;

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

public class FavoriteCaseDAO implements FavoriteCaseDAO_interface {

	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/BA103G5");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}

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

			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setString(1, favoriteCaseVO.getCsNo());
			pstmt.setString(2, favoriteCaseVO.getMbNo());

			int a = pstmt.executeUpdate();

			System.out.println("FavoriteCaseDAO - Data has been insert " +a);

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
	public void delete(String csNo, String mbNo) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);

			pstmt.setString(1, csNo);
			pstmt.setString(2, mbNo);

			int a = pstmt.executeUpdate();
			
			System.out.println("FavoriteCaseDAO - Data has been delete " +a);
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
	public void deleteAll(String csNo) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETEALL_STMT);

			pstmt.setString(1, csNo);

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
	public List<FavoriteCaseVO> getAll() {
		List<FavoriteCaseVO> list = new ArrayList<FavoriteCaseVO>();
		FavoriteCaseVO favoriteCaseVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
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

			con = ds.getConnection();
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
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONEMBFC_STMT);
			pstmt.setString(1, mbNo);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				list.add(rs.getString("csNo"));
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
	public List<String> getOneCaseFavoritMb(String csNo) {
		List<String> list = new ArrayList<String>();
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONECS_STMT);
			pstmt.setString(1, csNo);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				list.add(rs.getString("mbNo"));
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

}
