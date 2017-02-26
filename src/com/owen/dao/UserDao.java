package com.owen.dao;

import com.owen.domain.User;

public interface UserDao extends BaseDao<User>
{
	/**
	 * 根据用户名查找的用户
	 * @param name 需要查找的用户的用户名
	 * @return 查找到的用户
	 */
	User findByName(String name);
}
