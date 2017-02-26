package com.owen.dao.impl;


import java.util.List;

import com.owen.dao.UserDao;
import com.owen.domain.User;

public class UserDaoImpl extends BaseDaoImpl<User> implements UserDao
{

	

	@Override
	public User findByName(String name)
	{
		List<User> users = find("select u from User u where u.name = ?0", name);
		if (users != null && users.size() == 1)
		{
			return users.get(0);
		}
		return null;
	}
	
}
