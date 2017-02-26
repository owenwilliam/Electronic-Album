package com.owen.dao;

import java.util.List;

import com.owen.domain.Photo;
import com.owen.domain.User;

public interface PhotoDao extends BaseDao<Photo>
{
	//用以控制每页显示的相片数
	final int PAGE_SIZE = 3;
	/**
	 * 查询属性指定用户的相片，且进行分页控制
	 * @param user 查询相片所属的用户
	 * @return 查询到相片
	 */
	List<Photo> findByUser(User user, int pageNo);
}
