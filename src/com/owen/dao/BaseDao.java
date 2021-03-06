package com.owen.dao;

import java.io.Serializable;
import java.util.List;

public interface BaseDao<T>
{
	
	/**
	 * 根据ID加载实体
	 * @param entityClazz
	 * @param id
	 * @return
	 */
	T get(Class<T> entityClazz, Serializable id);
	
	/**
	 * 保存实体
	 * @param entity
	 * @return
	 */
	Serializable save(T entity);
	
	/**
	 * 更新实体
	 * @param entity
	 */
	void update(T entity);
	
	/**
	 * 删除实体
	 * @param entity
	 */
	void delete(T entity);
	
	/**
	 * 根据ID删除实体
	 * @param entity
	 * @param id
	 */
	void delete(Class<T> entity, Serializable id);
	
	/**
	 * 获取所有实体
	 * @param entityClazz
	 * @return
	 */
	List<T> findAll(Class<T> entityClazz);

	/**
	 * 获取实体总数
	 * @param entityClazz
	 * @return
	 */
	long findCount(Class<T> entityClazz);
}
