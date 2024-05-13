package com.join.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;



import com.join.VO.JoinVo;

public class JoinDAO {
	private static final String driver = "oracle.jdbc.driver.OracleDriver";
	private static final String url = "jdbc:oracle:thin:@localhost:1521/xe";
	private static final String user = "green";
	private static final String password = "1234";

	private Connection conn;
	private PreparedStatement pstmt;
	private ResultSet rs;

	public int insertData(JoinVo joinVo) {
		int result = 0;
		pstmt = null;
		try {
			Class.forName(driver);
			conn = DriverManager.getConnection(url, user, password);
			String sql = "INSERT INTO member(id, pwd, name, tel, dogname, dogsex, dogbirth)";
			sql += " values(?,?,?,?,?,?,?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, joinVo.getId());
			pstmt.setString(2, joinVo.getPwd());
			pstmt.setString(3, joinVo.getName());
			pstmt.setString(4, joinVo.getTel());
			pstmt.setString(5, joinVo.getDogname());
			pstmt.setString(6, joinVo.getDogsex());
			pstmt.setString(7, joinVo.getDogbirth());
			result = pstmt.executeUpdate();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			closeResources();
		}
		return result;
	}

	private void closeResources() {
		try {
			if (pstmt != null) {
				pstmt.close();
			}
			if (conn != null) {
				conn.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public boolean isIdExist(String id) {
		boolean result = false;
		ResultSet rs = null;
		try {
			Class.forName(driver);
			conn = DriverManager.getConnection(url, user, password);
			String sql = "SELECT ID FROM member WHERE ID = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				result = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeResources();
		}
		return result;
	}

	public boolean changeData(JoinVo joinVo) {
		boolean result = false;
		pstmt = null;
		try {
			Class.forName(driver);
			conn = DriverManager.getConnection(url, user, password);
			String sql = "UPDATE MEMBER SET pwd = ?, name = ?, tel = ?, dogname = ?, dogsex = ?, dogbirth = ? WHERE ID = ?";
			;

			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, joinVo.getPwd());
			pstmt.setString(2, joinVo.getName());
			pstmt.setString(3, joinVo.getTel());
			pstmt.setString(4, joinVo.getDogname());
			pstmt.setString(5, joinVo.getDogsex());
			pstmt.setString(6, joinVo.getDogbirth());
			pstmt.setString(7, joinVo.getId());
			pstmt.executeUpdate();

			result = true;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			closeResources();
		}
		return result;
	}
	public String SearchPassword(JoinVo joinVo) {
		String pwd = null;
		ResultSet rs = null;
		pstmt = null;
		try {
			Class.forName(driver);
			conn = DriverManager.getConnection(url, user, password);
			String sql = "SELECT PWD FROM MEMBER WHERE ID = ? AND DOGNAME = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, joinVo.getId());
			pstmt.setString(2, joinVo.getDogname());
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				pwd = rs.getString("PWD");
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			closeResources();
		}
		return pwd;
	}
	
	public int selectUser(JoinVo vo) {
		// TODO Auto-generated method stub
		try {
			conn = DriverManager.getConnection(url, user, password);
			String sql = "select NAME, TEL, DOGNAME, DOGSEX, DOGBIRTH from MEMBER WHERE id = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, vo.getId());
			rs = pstmt.executeQuery();
				while(rs.next()) {
				vo.setName(rs.getString("NAME"));
				vo.setTel(rs.getString("TEL"));
				vo.setDogname(rs.getString("DOGNAME"));
				vo.setDogsex(rs.getString("DOGSEX"));
				vo.setDogbirth(rs.getString("DOGBIRTH"));
				
				}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				rs.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		try {
			pstmt.close();
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return 0;
	}
	
	public int DeleteUser(JoinVo vo) {
		// TODO Auto-generated method stub
		try {
			conn = DriverManager.getConnection(url, user, password);
			String sql = "DELETE FROM member WHERE ID = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, vo.getId());
			pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		try {
			pstmt.close();
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return 0;
	}

}
