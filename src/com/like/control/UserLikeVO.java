package com.like.control;

public class UserLikeVO {
	private int PostNum;
	private String writer;
	private int LikeSet;
	
	public int getPostNum() {
		return PostNum;
	}
	public void setPostNum(int postNum) {
		PostNum = postNum;
	}
	public String getWriter() {
		return writer;
	}
	public void setWriter(String writer) {
		this.writer = writer;
	}
	public int getLikeSet() {
		return LikeSet;
	}
	public void setLikeSet(int likeSet) {
		LikeSet = likeSet;
	}
}
