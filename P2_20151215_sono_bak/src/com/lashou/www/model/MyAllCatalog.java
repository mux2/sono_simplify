package com.lashou.www.model;

public class MyAllCatalog
{
	int imgId;
	String catalog;
	int count;
	public MyAllCatalog(int imgId, String catalog, int count)
	{
		super();
		this.imgId = imgId;
		this.catalog = catalog;
		this.count = count;
	}
	public MyAllCatalog()
	{
		super();
	}
	public int getImgId()
	{
		return imgId;
	}
	public void setImgId(int imgId)
	{
		this.imgId = imgId;
	}
	public String getCatalog()
	{
		return catalog;
	}
	public void setCatalog(String catalog)
	{
		this.catalog = catalog;
	}
	public int getCount()
	{
		return count;
	}
	public void setCount(int count)
	{
		this.count = count;
	}
	
}
