package com.cus_service_msg.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class Cus_Service_MsgDAO implements Cus_Service_MsgDAO_interface {

	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/BA103G5");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}

	public final static String INSERT_STMT = "INSERT INTO CUS_SERVICE_MSG (cusMesgNo, mbNo, mesgTitle, mesgContent) "
			+ "Values ('CSMSG'||lpad(CSMSG_sq.nextval, 7, '0'), ?, ?, ?)";
	public final static String UPDATE_STMT = "UPDATE CUS_SERVICE_MSG SET RESPONSESTATE = ? WHERE CUSMESGNO = ?";
	public final static String DELETE_STMT = "DELETE CUS_SERVICE_MSG WHERE CUSMESGNO = ?";
	public final static String GET_ALL_STMT = "SELECT * FROM CUS_SERVICE_MSG ORDER BY CUSMESGNO";
	public final static String FIND_BY_STATE_STMT = "SELECT * FROM CUS_SERVICE_MSG WHERE RESPONSESTATE = ? ORDER BY CUSMESGNO";
	public final static String FIND_BY_MBNO_STMT = "SELECT * FROM CUS_SERVICE_MSG WHERE MBNO = ? ORDER BY CUSMESGNO";
	public final static String FIND_BYPK_STMT = "SELECT * FROM CUS_SERVICE_MSG WHERE CUSMESGNO = ? ORDER BY CUSMESGNO";

	@Override
	public Integer insert(Cus_Service_MsgVO cus_Service_MsgVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		int a = 0;
		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setString(1, cus_Service_MsgVO.getMbNo());
			pstmt.setString(2, cus_Service_MsgVO.getMesgTitle());
			pstmt.setString(3, cus_Service_MsgVO.getMesgContent());

			a = pstmt.executeUpdate();
			System.out.println("insert " + a + " cus_mesg");
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
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
		return a;

	}

	@Override
	public void update(Cus_Service_MsgVO cus_Service_MsgVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE_STMT);

			pstmt.setString(1, cus_Service_MsgVO.getResponseState());
			pstmt.setString(2, cus_Service_MsgVO.getCusmesgNo());

			int a = pstmt.executeUpdate();
			System.out.println("update " + a + " date");
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
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

	}

	@Override
	public void delete(String cusmesgNo) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE_STMT);

			pstmt.setString(1, cusmesgNo);

			int a = pstmt.executeUpdate();
			System.out.println("update " + a + " date");
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
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

	}

	@Override
	public Cus_Service_MsgVO findByPrimaryKey(String cusmesgNo) {
		Cus_Service_MsgVO cusServiceMsgVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(FIND_BYPK_STMT);
			pstmt.setString(1, cusmesgNo);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				cusServiceMsgVO = new Cus_Service_MsgVO();
				cusServiceMsgVO.setCusmesgNo(rs.getString(1));
				cusServiceMsgVO.setMbNo(rs.getString(2));
				cusServiceMsgVO.setMesgTitle(rs.getString(3));
				cusServiceMsgVO.setMesgContent(rs.getString(4));
				cusServiceMsgVO.setResponseState(rs.getString(5));
				cusServiceMsgVO.setMailTime(rs.getDate(6));
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
		return cusServiceMsgVO;
	}

	@Override
	public List<Cus_Service_MsgVO> findByMbNo(String mbNo) {
		List<Cus_Service_MsgVO> list = new ArrayList<Cus_Service_MsgVO>();
		Cus_Service_MsgVO cusServiceMsgVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(FIND_BY_MBNO_STMT);
			pstmt.setString(1, mbNo);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				cusServiceMsgVO = new Cus_Service_MsgVO();
				cusServiceMsgVO.setCusmesgNo(rs.getString(1));
				cusServiceMsgVO.setMbNo(rs.getString(2));
				cusServiceMsgVO.setMesgTitle(rs.getString(3));
				cusServiceMsgVO.setMesgContent(rs.getString(4));
				cusServiceMsgVO.setResponseState(rs.getString(5));
				cusServiceMsgVO.setMailTime(rs.getDate(6));
				list.add(cusServiceMsgVO);
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
	public List<Cus_Service_MsgVO> findByResponseState(String responseState) {
		List<Cus_Service_MsgVO> list = new ArrayList<Cus_Service_MsgVO>();
		Cus_Service_MsgVO cusServiceMsgVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(FIND_BY_STATE_STMT);
			pstmt.setString(1, responseState);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				cusServiceMsgVO = new Cus_Service_MsgVO();
				cusServiceMsgVO.setCusmesgNo(rs.getString(1));
				cusServiceMsgVO.setMbNo(rs.getString(2));
				cusServiceMsgVO.setMesgTitle(rs.getString(3));
				cusServiceMsgVO.setMesgContent(rs.getString(4));
				cusServiceMsgVO.setResponseState(rs.getString(5));
				cusServiceMsgVO.setMailTime(rs.getDate(6));
				list.add(cusServiceMsgVO);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
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
	public List<Cus_Service_MsgVO> getAll() {
		List<Cus_Service_MsgVO> list = new ArrayList<Cus_Service_MsgVO>();
		Cus_Service_MsgVO cusServiceMsgVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				cusServiceMsgVO = new Cus_Service_MsgVO();
				cusServiceMsgVO.setCusmesgNo(rs.getString(1));
				cusServiceMsgVO.setMbNo(rs.getString(2));
				cusServiceMsgVO.setMesgTitle(rs.getString(3));
				cusServiceMsgVO.setMesgContent(rs.getString(4));
				cusServiceMsgVO.setResponseState(rs.getString(5));
				cusServiceMsgVO.setMailTime(rs.getDate(6));
				list.add(cusServiceMsgVO);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
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

	public static void main(String[] args) {
		Cus_Service_MsgJDBCDAO dao = new Cus_Service_MsgJDBCDAO();

		// insert cus message
		// Cus_Service_MsgVO cusServiceMsgVO = new Cus_Service_MsgVO();
		// cusServiceMsgVO.setMbNo("MB0000019");
		// cusServiceMsgVO.setMesgTitle("你好啊");
		// cusServiceMsgVO.setMesgContent("請問你們有在徵人嗎");
		// dao.insert(cusServiceMsgVO);

		// update response state
		// Cus_Service_MsgVO cusServiceMsgVO2 = new Cus_Service_MsgVO();
		// cusServiceMsgVO2.setResponseState("已回覆");
		// cusServiceMsgVO2.setCusmesgNo("CSMSG0000002");;
		// dao.update(cusServiceMsgVO2);

		// delete cus message
		// dao.delete("CSMSG0000003");

		// get all cus message
		// List<Cus_Service_MsgVO> list = dao.getAll();
		// for(Cus_Service_MsgVO cusServiceMsgVO : list){
		// System.out.print(cusServiceMsgVO.getCusmesgNo() + ", ");
		// System.out.print(cusServiceMsgVO.getMbNo() + ", ");
		// System.out.print(cusServiceMsgVO.getMesgTitle() + ", ");
		// System.out.print(cusServiceMsgVO.getMesgContent() + ", ");
		// System.out.print(cusServiceMsgVO.getResponseState() + ", ");
		// System.out.print(cusServiceMsgVO.getMailTime() + ", ");
		// System.out.println();
		// }

		// get by responseState
		// List<Cus_Service_MsgVO> list2 = dao.findByResponseState("已回覆");
		// List<Cus_Service_MsgVO> list2 = dao.findByResponseState("未回覆");
		// for(Cus_Service_MsgVO cusServiceMsgVO : list2){
		// System.out.print(cusServiceMsgVO.getCusmesgNo() + ", ");
		// System.out.print(cusServiceMsgVO.getMbNo() + ", ");
		// System.out.print(cusServiceMsgVO.getMesgTitle() + ", ");
		// System.out.print(cusServiceMsgVO.getMesgContent() + ", ");
		// System.out.print(cusServiceMsgVO.getResponseState() + ", ");
		// System.out.print(cusServiceMsgVO.getMailTime() + ", ");
		// System.out.println();
		// }

		// get by mbNo
		// List<Cus_Service_MsgVO> list3 = dao.findByMbNo("MB0000002");
		// for(Cus_Service_MsgVO cusServiceMsgVO : list3){
		// System.out.print(cusServiceMsgVO.getCusmesgNo() + ", ");
		// System.out.print(cusServiceMsgVO.getMbNo() + ", ");
		// System.out.print(cusServiceMsgVO.getMesgTitle() + ", ");
		// System.out.print(cusServiceMsgVO.getMesgContent() + ", ");
		// System.out.print(cusServiceMsgVO.getResponseState() + ", ");
		// System.out.print(cusServiceMsgVO.getMailTime() + ", ");
		// System.out.println();
		// }

		// get by pk
		Cus_Service_MsgVO cusServiceMsgVO3 = dao.findByPrimaryKey("CSMSG0000008");
		System.out.print(cusServiceMsgVO3.getCusmesgNo() + ", ");
		System.out.print(cusServiceMsgVO3.getMbNo() + ", ");
		System.out.print(cusServiceMsgVO3.getMesgTitle() + ", ");
		System.out.print(cusServiceMsgVO3.getMesgContent() + ", ");
		System.out.print(cusServiceMsgVO3.getResponseState() + ", ");
		System.out.print(cusServiceMsgVO3.getMailTime() + ", ");
		System.out.println();

	}

}
