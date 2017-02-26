package com.owen.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.owen.dao.PhotoDao;
import com.owen.dao.UserDao;
import com.owen.domain.Photo;
import com.owen.domain.User;
import com.owen.exception.AlbumException;
import com.owen.service.AlbumService;
import com.owen.vo.PhotoHolder;

public class AlbumServiceImpl implements AlbumService
{
	//业务逻辑组件所依赖的2个DAO组件
	private UserDao userDao = null;
	private PhotoDao photoDao = null;
	
	/**
	 * 依赖注入2个DAO组件所需要的setter方法
	 * @param userDao
	 */
	public void setUserDao(UserDao userDao)
	{
		this.userDao = userDao;
	}
	
	public void setPhotoDao(PhotoDao photoDao)
	{
		this.photoDao = photoDao;
	}

	@Override
	public boolean userLogin(String name, String pass)
	{
		try
		{
			//使用UserDao根据用户名查询用户
				User user = userDao.findByName(name);
				if (user != null && user.getPass().equals(pass))
				{
					return true;
				}
				return false;
		}
		catch ( Exception e)
		{
			e.printStackTrace();
			throw new AlbumException("处理用户登录出现异常！");
		}
	}

	@Override
	public int registUser(String name, String pass)
	{
		try
		{
			//创建一个新的User实例
			User user = new User();
			user.setName(name);
			user.setPass(pass);
			userDao.save(user);
			return user.getId();
		}
		catch (Exception e)
		{
			e.printStackTrace();
			throw new AlbumException("新用户注册出现异常！");
		}
	}

	@Override
	public int addPhoto(String user, String title, String fileName, String uuidName)
	{
		try
		{
			//创建一个新的Photo实例
			Photo photo = new Photo();
			photo.setTitle(title);
			photo.setFileName(fileName);
			photo.setUuidName(uuidName);
			photo.setUser(userDao.findByName(user));
			//持久化Photo实例
			photoDao.save(photo);
			return photo.getId();
		}
		catch (Exception e)
		{
			e.printStackTrace();
			throw new AlbumException("添加相片过程出现异常！");
		}
	}

	@Override
	public boolean validateName(String name)
	{
		try
		{
			//根据用户名查询对应的User实例
			User user = userDao.findByName(name);
			if (user != null)
			{
				return false;
			}
			return true;
		}
		catch (Exception e)
		{
			e.printStackTrace();
			throw new AlbumException("验证用户名是否存在的过程中出现异常！");
		}
	}

	@Override
	public List<PhotoHolder> getPhotoByUser(String user, int pageNo)
	{
		try
		{
			List<Photo> pl = photoDao.findByUser(userDao.findByName(user), pageNo);
			List<PhotoHolder> result = new ArrayList<PhotoHolder>();
			for (Photo p : pl)
			{
				result.add(new PhotoHolder(p.getTitle(), p.getFileName(), p.getUuidName()));
			}
			return result;
		}
		catch (Exception e)
		{
			e.printStackTrace();
			throw new AlbumException("查询相片列表过程中出现异常！");
		}
	}

}
