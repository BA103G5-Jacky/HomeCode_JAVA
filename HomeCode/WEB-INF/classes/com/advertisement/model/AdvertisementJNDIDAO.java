package com.advertisement.model;

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

public class AdvertisementJNDIDAO implements AdvertisementDAO_interface {

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

	private static final String INSERT_STMT = "INSERT INTO advertisement (adno,adname,adcontent,adimg,aduploaddate) VALUES ('AD'||lpad(advertisement_sq.nextval,7,'0'), ?, ?, ?, sysdate)";
	private static final String GET_ALL_STMT = "SELECT adno,adname,adcontent,adimg,aduploaddate FROM advertisement order by adno";
	private static final String GET_ONE_STMT = "SELECT adno,adname,adcontent,adimg,aduploaddate FROM advertisement where adno = ?";
	private static final String GET_ONE_STMT2 = "SELECT adno,adname,adcontent,adimg,aduploaddate FROM advertisement where adname = ?";
	private static final String DELETE = "DELETE FROM advertisement where adno = ?";
	private static final String DELETE2 = "DELETE FROM advertisement where adname = ?";
	private static final String UPDATE = "UPDATE advertisement set adname=?, adcontent=?, adimg=?, aduploaddate=sysdate where adno = ?";
	private static final String UPDATE2 = "UPDATE advertisement set  adcontent=?, adimg=?, aduploaddate=sysdate where adname = ?";

	public void insert(AdvertisementVO adVO) {
		// adno,adname,adcontent,adimg,aduploaddate
		// INSERT INTO advertisement (adno,adname,adcontent,adimg,aduploaddate)
		// VALUES ('AD'||lpad(advertisement_sq.nextval,7,'0'), ?, ?, ?, sysdate)
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setString(1, adVO.getAdname());
			pstmt.setString(2, adVO.getAdcontent());
			pstmt.setBytes(3, adVO.getAdimg());
			// pstmt.setDate(4, adVO.getAduploaddate());

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
	public void deleteByAdno(String adNo) {
		// adno,adname,adcontent,adimg,aduploaddate

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);

			pstmt.setString(1, adNo);

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
	public void deleteByAdName(String adName) {
		// adno,adname,adcontent,adimg,aduploaddate
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE2);

			pstmt.setString(1, adName);

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
	public AdvertisementVO findAdByAdno(String adNo) {

		AdvertisementVO adVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setString(1, adNo);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVo 也稱為 Domain objects
				// adno,adname,adcontent,adimg,aduploaddate
				adVO = new AdvertisementVO();
				adVO.setAdno(rs.getString("adno"));
				adVO.setAdname(rs.getString("adname"));
				adVO.setAdcontent(rs.getString("adcontent"));
				adVO.setAdimg(rs.getBytes("adimg"));
				adVO.setAduploaddate(rs.getDate("aduploaddate"));

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
		return adVO;
	}

	@Override
	public AdvertisementVO findAdByAdname(String adName) {
		// adno,adname,adcontent,adimg,aduploaddate
		AdvertisementVO adVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT2);

			pstmt.setString(1, adName);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVo 也稱為 Domain objects
				// adno,adname,adcontent,adimg,aduploaddate
				adVO = new AdvertisementVO();
				adVO.setAdno(rs.getString("adno"));
				adVO.setAdname(rs.getString("adname"));
				adVO.setAdcontent(rs.getString("adcontent"));
				adVO.setAdimg(rs.getBytes("adimg"));
				adVO.setAduploaddate(rs.getDate("aduploaddate"));

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
		return adVO;
	}

	@Override
	public List<AdvertisementVO> getAll() {
		// adno,adname,adcontent,adimg,aduploaddate
		List<AdvertisementVO> list = new ArrayList<AdvertisementVO>();
		AdvertisementVO adVO;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVO 也稱為 Domain objects
				adVO = new AdvertisementVO();
				adVO.setAdno(rs.getString("adno"));
				adVO.setAdname(rs.getString("adname"));
				adVO.setAdcontent(rs.getString("adcontent"));
				adVO.setAdimg(rs.getBytes("adimg"));
				adVO.setAduploaddate(rs.getDate("aduploaddate"));
				list.add(adVO); // Store the row in the list
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
	public void updateByAdNo(AdvertisementVO adVO) {
		// TODO Auto-generated method stub

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);

			// adno=?, adcontent=?, adimg=?, aduploaddate=? where adname = ?";

			pstmt.setString(1, adVO.getAdname());
			pstmt.setString(2, adVO.getAdcontent());
			pstmt.setBytes(3, adVO.getAdimg());

			pstmt.setString(4, adVO.getAdno());
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
	public void updateByAdName(AdvertisementVO adVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE2);

			// adno=?, adcontent=?, adimg=?, aduploaddate=? where adname = ?";

			// "UPDATE advertisement set adno=?, adcontent=?, adimg=?,
			// aduploaddate=?
			// where adname = ?";
			// pstmt.setString(1, adVO.getAdno());
			pstmt.setString(1, adVO.getAdcontent());
			pstmt.setBytes(2, adVO.getAdimg());
			pstmt.setString(3, adVO.getAdname());
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

}
