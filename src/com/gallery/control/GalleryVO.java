package com.gallery.control;

public class GalleryVO {
	private String id;
	private int num;
	private String imagename;
	private String ipath;

	public String getIpath() {
		return ipath;
	}

	public void setIpath(String ipath) {
		this.ipath = ipath;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getImagename() {
		return imagename;
	}

	public void setImagename(String imagename) {
		this.imagename = imagename;
	}

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}
}
