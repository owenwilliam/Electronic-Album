package com.owen.dao.impl;

import java.io.Serializable;
import java.util.List;

import com.owen.dao.PhotoDao;
import com.owen.domain.Photo;
import com.owen.domain.User;

public class PhotoDaoImpl extends BaseDaoImpl<Photo> implements PhotoDao
{

	
	@Override
	public List<Photo> findByUser(User user, int pageNo)
	{
		//返回分页查询的结果
		return (List<Photo>)findByPage("select b from Photo b where b.user=?0",
				pageNo, PAGE_SIZE, user);
	}

}
