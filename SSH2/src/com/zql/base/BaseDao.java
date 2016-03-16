package com.zql.base;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.zql.util.Page;
@Repository
public interface BaseDao<T> {
	public void save(T entity);
	public void update(T entity);
	public T getOne(Integer id);
    public List<T> getMany(); 
    public void delete(Integer id);
    public Page<T> getPage(Page<T> page);
    public Page<T> getPage(Page<T> page,final String hqlwhere,Map<String, Object> map);
}
