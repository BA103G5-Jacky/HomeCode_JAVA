package com.advertisement.model;

import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import oracle.sql.DATE;
//import tools.BLOB;

import java.sql.Timestamp;

public class AdvertisementJDBCDAO implements AdvertisementDAO_interface {

	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:XE";
	String userid = "BA103G5";
	String passwd = "iii";

	private static final String INSERT_STMT = "INSERT INTO advertisement (adno,adname,adcontent,adimg,aduploaddate) VALUES ('AD'||lpad(advertisement_sq.nextval,7,'0'), ?, ?, ?, sysdate)";
	private static final String GET_ALL_STMT = "SELECT adno,adname,adcontent,adimg,aduploaddate FROM advertisement order by adno";
	private static final String GET_ONE_STMT = "SELECT adno,adname,adcontent,adimg,aduploaddate FROM advertisement where adno = ?";
	private static final String GET_ONE_STMT2 = "SELECT adno,adname,adcontent,adimg,aduploaddate FROM advertisement where adname = ?";
	private static final String DELETE = "DELETE FROM advertisement where adno = ?";
	private static final String DELETE2 = "DELETE FROM advertisement where adname = ?";
	private static final String UPDATE = "UPDATE advertisement set adname=?, adcontent=?, adimg=?, aduploaddate=sysdate where adno = ?";
	private static final String UPDATE2 = "UPDATE advertisement set  adcontent=?, adimg=?, aduploaddate=sysdate where adname = ?";

	@Override
	public void insert(AdvertisementVO adVO) {
		// adno,adname,adcontent,adimg,aduploaddate
		// INSERT INTO advertisement (adno,adname,adcontent,adimg,aduploaddate)
		// VALUES ('AD'||lpad(advertisement_sq.nextval,7,'0'), ?, ?, ?, sysdate)
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setString(1, adVO.getAdname());
			pstmt.setString(2, adVO.getAdcontent());
			pstmt.setBytes(3, adVO.getAdimg());
			// pstmt.setDate(4, adVO.getAduploaddate());

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
	public void deleteByAdno(String adNo) {
		// adno,adname,adcontent,adimg,aduploaddate

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(DELETE);

			pstmt.setString(1, adNo);

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
	public void deleteByAdName(String adName) {
		// adno,adname,adcontent,adimg,aduploaddate
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(DELETE2);

			pstmt.setString(1, adName);

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
	public AdvertisementVO findAdByAdno(String adNo) {

		AdvertisementVO adVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
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

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
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

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
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
	public void updateByAdNo(AdvertisementVO adVO) {
		// TODO Auto-generated method stub

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE);

			// adno=?, adcontent=?, adimg=?, aduploaddate=? where adname = ?";

			pstmt.setString(1, adVO.getAdname());
			pstmt.setString(2, adVO.getAdcontent());
			pstmt.setBytes(3, adVO.getAdimg());

			pstmt.setString(4, adVO.getAdno());
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
	public void updateByAdName(AdvertisementVO adVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
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

	public static void main(String[] args) throws IOException {

		AdvertisementJDBCDAO dao = new AdvertisementJDBCDAO();

		// 新增 checked
		AdvertisementVO adVO1 = new AdvertisementVO();
		adVO1.setAdno("吳永志1");
		adVO1.setAdname("MANAGER");
		adVO1.setAdcontent("f");

		// adVO1.setAdimg(BLOB.getPictureByteArray("C:\\Users\\USER\\Dropbox\\pic\\IMG_4611.JPG"));
		dao.insert(adVO1);

		// // 修改 by findadname checked

		AdvertisementVO adVO2 = new AdvertisementVO();

		adVO2.setAdname("廣告29");
		adVO2.setAdcontent("MANAGER2");
//		adVO2.setAdimg(BLOB.getPictureByteArray("C:\\Users\\USER\\Dropbox\\pic\\IMG_4611.JPG"));

		// adVO2.setAdno("AD0000028");
		dao.updateByAdName(adVO2);

		// 修改 by findadno checked

		AdvertisementVO adVO3 = new AdvertisementVO();

		adVO3.setAdname("廣ㄋㄎㄎㄎㄎㄎ告29");
		adVO3.setAdcontent("MANAGER2");
//		adVO3.setAdimg(BLOB.getPictureByteArray("C:\\Users\\USER\\Dropbox\\pic\\IMG_4611.JPG"));

		adVO3.setAdno("AD0000029");
		dao.updateByAdNo(adVO3);

		//
		// 刪除
		dao.deleteByAdno("廣告30");
		dao.deleteByAdName("廣告30");
		//
		// // 查詢by adno checked
		//
		AdvertisementVO adVO4 = dao.findAdByAdno("AD0000001");
		System.out.print(adVO4.getAdno() + ",");
		System.out.print(adVO4.getAdname() + ",");
		System.out.print(adVO4.getAdcontent() + ",");
		System.out.print(adVO4.getAdimg() + ",");
		System.out.print(adVO4.getAduploaddate().toString());

		System.out.println("---------------------");

		// 查詢by adname checked
		AdvertisementVO adVO5 = dao.findAdByAdname("廣告");
		System.out.print(adVO5.getAdno() + ",");
		System.out.print(adVO5.getAdname() + ",");
		System.out.print(adVO5.getAdcontent() + ",");
		System.out.print(adVO5.getAdimg() + ",");
		System.out.print(adVO5.getAduploaddate().toString());

		System.out.println("---------------------");
		//
		// // 查詢
		List<AdvertisementVO> list = dao.getAll();
		for (AdvertisementVO aEmp : list) {
			System.out.print(aEmp.getAdno() + ",");
			System.out.print(aEmp.getAdname() + ",");
			System.out.print(aEmp.getAdcontent() + ",");
			System.out.print(aEmp.getAdimg() + ",");
			System.out.print(aEmp.getAduploaddate() + ",");

			System.out.println();
		}
	}

}
