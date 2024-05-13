package com.like.control;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserLikeDAOImpl implements UserLikeDAO {
	
	Connection conn;
	PreparedStatement pstmt;
	ResultSet res;

	public static final String dirver = "oracle.jdbc.OracleDriver";
	public static final String url = "jdbc:oracle:thin:@localhost:1521/xe";
	public static final String user = "green";
	public static final String password = "1234";

	public UserLikeDAOImpl() {
		try {
			Class.forName(dirver);
		} catch (ClassNotFoundException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	@Override
	public int ClickLike(UserLikeVO vo) {
		// TODO Auto-generated method stub
		try {
			conn = DriverManager.getConnection(url, user, password);
			 String selectSql = "SELECT COUNT(*) AS COUNT FROM USERLIKES WHERE POSTNUM = ? AND WRITER = ?";
		        pstmt = conn.prepareStatement(selectSql);
		        pstmt.setInt(1, vo.getPostNum());
		        pstmt.setString(2, vo.getWriter());
		        res = pstmt.executeQuery();
		        if(res.next()) {
		        	int count = res.getInt("COUNT");
		        	if(count == 0) {
		        		String insertSql = "INSERT INTO USERLIKES (POSTNUM, WRITER, LIKESET) VALUES (?, ?, 1)";
		                pstmt = conn.prepareStatement(insertSql);
		                pstmt.setInt(1, vo.getPostNum());
		                pstmt.setString(2, vo.getWriter());
		                pstmt.executeUpdate();
		        	}
		        }
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		}
		return 0;
	}
	
	@Override
	public int LikeUpdate(UserLikeVO vo) {
		// TODO Auto-generated method stub
		int LikeSet = 0;
		try {
			conn = DriverManager.getConnection(url, user, password);
			String sql = "SELECT LIKESET FROM USERLIKES WHERE WRITER = ? AND POSTNUM = ?";

			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, vo.getWriter());
			pstmt.setInt(2, vo.getPostNum());
			res = pstmt.executeQuery();
			while(res.next()) {
				LikeSet = res.getInt("LIKESET");
				vo.setLikeSet(LikeSet);
			}
			

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		}
		return LikeSet;
	}
}
