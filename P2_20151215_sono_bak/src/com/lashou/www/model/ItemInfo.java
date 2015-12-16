package com.lashou.www.model;

public class ItemInfo {
	String imgUrl;
	String title;
	String subtitle;
	public int type;
	
	public ItemInfo() {
	}
	
	public ItemInfo(String imgUrl, String title, String subtitle, int type) {
		super();
		this.imgUrl = imgUrl;
		this.title = title;
		this.subtitle = subtitle;
		this.type = type;
	}
	
	
}
