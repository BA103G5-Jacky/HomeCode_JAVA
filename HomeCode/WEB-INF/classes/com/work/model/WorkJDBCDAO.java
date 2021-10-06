package com.work.model;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.tools.*;



import java.util.*;



public class WorkJDBCDAO implements WorkDAO_interface {

	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:XE";
	String userid = "G5mark";
	String passwd = "123456";
	
	
	private static final String INSERT_STMT = 
		"INSERT INTO work (wkno,mbno,wkpic,wkname,wkdecr) VALUES ('MB'||lpad(member_sq.nextval,7,'0'), ?, ?, ?, ?)";
		private static final String GET_ALL_STMT = 
			"SELECT wkno,mbno,wkpic,wkname,wkdecr FROM work order by wkno";
		private static final String GET_ONE_STMT = 
			"SELECT wkno,mbno,wkpic,wkname,wkdecr FROM work where wkno = ?";
		private static final String DELETE = 
			"DELETE FROM work where wkno = ?";
		private static final String UPDATE = 
			"UPDATE work set mbno=?, wkpic=?, wkname=?, wkdecr=? where wkno = ?";
	
	
	
	@Override
	public void insert(WorkVO workVO) {
		
		
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);

			
			pstmt.setString(1, workVO.getMbno());
			pstmt.setBytes(2, workVO.getWkpic());
			pstmt.setString(3, workVO.getWkname());
			pstmt.setString(4, workVO.getWkdecr());
			

			pstmt.executeUpdate();

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
			// Handle any SQL errors
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
	public void update(WorkVO workVO) {
		

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE);

			
			pstmt.setString(1, workVO.getMbno());
			pstmt.setBytes (2, workVO.getWkpic());
			pstmt.setString(3, workVO.getWkname());
			pstmt.setString(4, workVO.getWkdecr());
			pstmt.setString(5, workVO.getWkno());
			pstmt.executeUpdate();

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
			// Handle any SQL errors
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
	public void delete(String wkno) {
		
		
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(DELETE);

			pstmt.setString(1, wkno);

			pstmt.executeUpdate();

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
			// Handle any SQL errors
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
	public WorkVO findByPrimaryKey(String wkno) {
		
		WorkVO workVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);   //載入驅動
			con = DriverManager.getConnection(url, userid, passwd); //用驅動GET連線,建構子塞ORACLE的URL 還有帳密
			
			//這方法我塞ㄉSQL句子昰------> GET_ONE_STMT = "SELECT wkno,mbno,wkpic,wkname,wkdecr FROM work where wkno = ?"; 
			
			pstmt = con.prepareStatement(GET_ONE_STMT); //用連線的PrepareStatement方法查詢資料庫(SQL指令塞在建構子裡)
			pstmt.setString(1, wkno); //用PREPARESTATEMENT的SETxxx來填滿每一個SQL指令的問號, 建購子裡面昰(SQL的第幾個問號,你要塞ㄉ東西)

			rs = pstmt.executeQuery();  //在PREPARESTATEMENT塞完指令後,用它的executeQurery方法,開始對oracle執行我們的SQL指令! 然後把查詢出來的東西丟給RESULTSET物件

			
			
			//然而用SQL指令查詢出來之後 我們卻看不到顯示的結果,所以只能用ResultSet的游標在黑暗中
		    //移動,利用它在查詢出來的表格裡面漂移。
			//在查詢出來的表格中,我們必須利用ResultSet裡面附上的method來移動它自己的游標,去到達
			//我們下SQL指令所搜尋出來的每一個結果(ResultSet游標是指向一條橫列)。
			//就像剛剛說的,因為用JDBC查詢我們看不到表格ㄉ資料,
			//所以移動ResultSet到了我們想要的橫列位子後,我們NEW一個VO的物件,然後把
			//該橫列的資料全部打包起來(用VO的set方法把搜到的東西裝進VO),包完我們想包的資料後,
			//把它(VO物件)回傳給呼叫這個方法method的主人。之後如果想要看資料,把VO這個包裹打開
			//來看(用VO的GET方法拿出來,然後PRINT就好,看你要PRINT在哪都可。)
			
			
			//(游標一開始在表格得最上面,我們可以用ResultSet裡面附上的方法移動到我們想找的那條橫列(row),
			//記得如果想用index搜尋的話,ResultSet的index昰從1開始起跳!)
			
			
			while (rs.next()) {  
				// empVo 也稱為 Domain objects
				workVO = new WorkVO();
				workVO.setWkno(rs.getString("wkno"));
				workVO.setMbno(rs.getString("mbno")); 
				workVO.setWkpic(rs.getBytes("wkpic"));
				workVO.setWkname(rs.getString("wkname"));
				workVO.setWkdecr(rs.getString("wkdecr"));
				
			}

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
			// Handle any SQL errors
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
		return workVO;
	}
	

	@Override
	public List<WorkVO> getAll() {
		
		List<WorkVO> list = new ArrayList<WorkVO>();
		WorkVO workVO ;

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
				workVO = new WorkVO();
				
				workVO.setWkno(rs.getString("wkno"));
				workVO.setMbno(rs.getString("mbno"));
				workVO.setWkpic(rs.getBytes("wkpic"));
				workVO.setWkname(rs.getString("wkname"));
				workVO.setWkdecr(rs.getString("wkdecr"));
				
				list.add(workVO); // Store the row in the list
			}

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
			// Handle any SQL errors
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
	public List<WorkVO> findByMbNo(String mbNo) {
		// TODO Auto-generated method stub
		return null;
	}

	
	
//	public static void main(String[] args) throws IOException {
//
//		
//		WorkJDBCDAO dao = new WorkJDBCDAO();
//
//	// 新增
//		WorkVO work = new WorkVO();
//		work.setMbno("MB0000025");
//		work.setWkpic(BLOB.getPictureByteArray("C:\\Users\\USER\\Dropbox\\pic\\IMG_4611.JPG"));
//		work.setWkname("ㄏㄏㄏㄏ");
//		work.setWkdecr("幹ㄋ娘機掰");
//		dao.insert(work);
//
//		// 修改
//		WorkVO work2 = new WorkVO();
//		work2.setMbno("MB0000023");
//		work2.setWkpic(BLOB.getPictureByteArray("C:\\Users\\USER\\Dropbox\\pic\\IMG_4611.JPG"));
//		work2.setWkname("homecode作品2");
//		work2.setWkdecr("幹你娘機掰");
//		work2.setWkno("MB0000030");
//		dao.update(work2);
//
//		// 刪除
//		dao.delete("MB0000033");
//
//		// 查詢
//		WorkVO work3 = dao.findByPrimaryKey("WK0000001");
//		System.out.print(work3.getMbno() + ",");
//		System.out.print(work3.getWkpic() + ",");
//		System.out.print(work3.getWkname() + ",");
//		System.out.print(work3.getWkdecr() + ",");
//		
//		System.out.println("---------------------");
//
//	// 查詢
//		List<WorkVO> list = dao.getAll();
//		for (WorkVO awork : list) {
//			System.out.print(awork.getWkno() + ",");
//			System.out.print(awork.getMbno() + ",");
//			System.out.print(awork.getWkpic() + ",");
//			System.out.print(awork.getWkname() + ",");
//			System.out.print(awork.getWkdecr() + ",");
//			System.out.println();
//		}
//
//
//	}
}
	
	
	

	



