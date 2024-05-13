package com.diary.control;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;



public class DiaryDAOImpl implements DiaryDAO {
	Connection conn;
	PreparedStatement pstmt;
	ResultSet res;

	public static final String dirver = "oracle.jdbc.OracleDriver";
	public static final String url = "jdbc:oracle:thin:@localhost:1521/xe";
	public static final String user = "green";
	public static final String password = "1234";

	public DiaryDAOImpl() {
		try {
			Class.forName(dirver);
		} catch (ClassNotFoundException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

	@Override
	public int InsertDiary(DiaryVO vo) {
		// TODO Auto-generated method stub
		try {
			conn = DriverManager.getConnection(url, user, password);
			String sql = "INSERT INTO diary (num, writer, year, month, day, content, regdate) VALUES (SEQ_diary_NUM.NEXTVAL, ?, ?, ?,?,?, SYSDATE)";

			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, vo.getWriter());
			pstmt.setInt(2, vo.getYear());
			pstmt.setInt(3, vo.getMonth());
			pstmt.setInt(4, vo.getDay());
			pstmt.setString(5, vo.getContent());

			pstmt.executeUpdate();

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
	public List<DiaryVO> select(String writer, int year, int month, int day){
		List<DiaryVO> list = new ArrayList<DiaryVO>();
		try {
			conn = DriverManager.getConnection(url, user, password);
			String sql = "SELECT * FROM diary WHERE writer = ? AND year =? and month = ? and day = ? order by regdate desc";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, writer);
			pstmt.setInt(2, year);
			pstmt.setInt(3, month);
			pstmt.setInt(4, day);
			
			res = pstmt.executeQuery();
			while(res.next()) {
				DiaryVO vo = new DiaryVO();
				vo.setWriter(res.getString("WRITER"));
				vo.setContent(res.getString("CONTENT"));
				vo.setNum(res.getInt("num"));
				list.add(vo);
			}
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			try {
				res.close();
			} catch (SQLException e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		}try {
			pstmt.close();
		}catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		
		return list;
		
	}

	@Override
	public int delete(DiaryVO vo) {
		try {
			conn = DriverManager.getConnection(url, user, password);
			String sql = "DELETE FROM DIARY WHERE NUM =? AND YEAR = ? AND MONTH = ? AND DAY = ? AND WRITER = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, vo.getNum());
			pstmt.setInt(2, vo.getYear());
			pstmt.setInt(3, vo.getMonth());
			pstmt.setInt(4, vo.getDay());
			pstmt.setString(5, vo.getWriter());
			pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}
	
	}


