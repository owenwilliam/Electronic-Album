package com.owen.domain;

import java.util.HashSet;
import java.util.Set;

public class User
{
	//标识属性值
	private Integer id;
	//该用户的用户名
	private String name;
	//该用户的密码
	private String pass;
	//使用Set保存该用户关联的相片
	private Set<Photo> photos = new HashSet<Photo>();
	public User()
	{
	}
	public User(Integer id, String name, String pass, Set<Photo> photos)
	{
		super();
		this.id = id;
		this.name = name;
		this.pass = pass;
		this.photos = photos;
	}
	public Integer getId()
	{
		return id;
	}
	public void setId(Integer id)
	{
		this.id = id;
	}
	public String getName()
	{
		return name;
	}
	public void setName(String name)
	{
		this.name = name;
	}
	public String getPass()
	{
		return pass;
	}
	public void setPass(String pass)
	{
		this.pass = pass;
	}
	public Set<Photo> getPhotos()
	{
		return photos;
	}
	public void setPhotos(Set<Photo> photos)
	{
		this.photos = photos;
	}
	
	
}
