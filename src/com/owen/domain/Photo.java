package com.owen.domain;

public class Photo
{
	private Integer id;
	private String title;
	private String fileName;
	private String uuidName;
	private User user;
	public Photo()
	{
	}
	public Photo(Integer id, String title, String fileName, String uuidName, User user)
	{
		super();
		this.id = id;
		this.title = title;
		this.fileName = fileName;
		this.uuidName = uuidName;
		this.user = user;
	}
	public Integer getId()
	{
		return id;
	}
	public void setId(Integer id)
	{
		this.id = id;
	}
	public String getTitle()
	{
		return title;
	}
	public void setTitle(String title)
	{
		this.title = title;
	}
	public String getFileName()
	{
		return fileName;
	}
	public void setFileName(String fileName)
	{
		this.fileName = fileName;
	}
	public User getUser()
	{
		return user;
	}
	public void setUser(User user)
	{
		this.user = user;
	}
	public String getUuidName()
	{
		return uuidName;
	}
	public void setUuidName(String uuidName)
	{
		this.uuidName = uuidName;
	}
	
	
}
