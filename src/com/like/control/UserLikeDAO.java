package com.like.control;

public interface UserLikeDAO {
	public int ClickLike(UserLikeVO vo);
	
	public int LikeUpdate(UserLikeVO vo);
}
