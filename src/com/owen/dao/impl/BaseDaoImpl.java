package com.owen.dao.impl;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.SessionFactory;

import com.owen.dao.BaseDao;

public class BaseDaoImpl<T> implements BaseDao<T>
{
	//DAO组件进行持久操作底层依赖的SessionFactory组件
	private SessionFactory sessionFactory;
	

	public SessionFactory getSessionFactory()
	{
		return this.sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory)
	{
		this.sessionFactory = sessionFactory;
	}

	@SuppressWarnings("unchecked")
	@Override
	public T get(Class<T> entityClazz, Serializable id)
	{
		return (T)getSessionFactory().getCurrentSession().get(entityClazz, id);
	}

	@Override
	public Serializable save(T entity)
	{
		return getSessionFactory().getCurrentSession().save(entity);
	}

	@Override
	public void update(T entity)
	{
		getSessionFactory().getCurrentSession().update(entity);
	}

	@Override
	public void delete(T entity)
	{
		getSessionFactory().getCurrentSession().delete(entity);
	}

	@Override
	public void delete(Class<T> entity, Serializable id)
	{
		delete(get(entity, id));
	}

	@Override
	public List<T> findAll(Class<T> entityClazz)
	{
		return find("select en from " + entityClazz.getSimpleName() + " en");
	}

	

	@Override
	public long findCount(Class<T> entityClazz)
	{
		List list = find("select count(*) from " + entityClazz.getSimpleName());
		if (list != null && list.size() == 1)
		{
			return (Long) list.get(0);
		}
		return 0; 
	}

	
	/**
	 * 根据HQL语句查询实体
	 * @param hql
	 * @return
	 */
	@SuppressWarnings("unchecked")
	protected List<T> find(String hql)
	{
		return (List<T>)getSessionFactory().getCurrentSession().createQuery(hql).list();
	}
	

	/**
	 * 根据带占位符参数HQL语句查实体
	 * @param hql
	 * @param params
	 * @return
	 */
	@SuppressWarnings({ "unchecked", "unused" })
	protected List<T> find(String hql, Object...params)
	{
		Query query = getSessionFactory().getCurrentSession().createQuery(hql);
		for (int i=0, len = params.length; i < len; i++)
		{
			query.setParameter(i + "", params[i]);
		}
		return (List<T>)query.list();
	}
	
	
	/**
	 * 分页查询
	 * @param hql
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	@SuppressWarnings("unchecked")
	protected List<T> findByPage(String hql, int pageNo, int pageSize)
	{
		return getSessionFactory().getCurrentSession().createQuery(hql)
				.setFirstResult((pageNo - 1) * pageSize)
				.setMaxResults(pageSize)
				.list();
	}
	
	
	/**
	 * 分页查询，多个参数
	 * @param hql
	 * @param pageNo
	 * @param pageSize
	 * @param params
	 * @return
	 */
	@SuppressWarnings("unchecked")
	protected List<T> findByPage(String hql, int pageNo, int pageSize, Object...params)
	{
		Query query = getSessionFactory().getCurrentSession().createQuery(hql);
		for (int i = 0, len = params.length; i < len; i++)
		{
			query.setParameter(i + "",  params[i]);
		}
		return query.setFirstResult((pageNo - 1) * pageSize)
				.setMaxResults(pageSize)
				.list();
	}
}
