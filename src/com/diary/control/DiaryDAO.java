package com.diary.control;

import java.util.List;

public interface DiaryDAO {
	public int InsertDiary(DiaryVO vo);

	List<DiaryVO> select(String writer, int year, int month, int day);
	
	public int delete(DiaryVO vo);
}
