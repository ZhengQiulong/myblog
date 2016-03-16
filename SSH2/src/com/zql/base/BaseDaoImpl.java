package com.zql.base;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import com.zql.util.Page;

@Repository
public class BaseDaoImpl<T> implements BaseDao<T>{
	 protected Class<T> clazz;
     @Resource
     protected SessionFactory sessionFactory;
	    /**
	     * 通过反射泛型获取Class类型,getGenericSuperclass()方法获取对象的泛型的父类类型信息，
	     * getActualTypeArguments()[0]方法得到T的真实类型
	     * 
	     */
		public BaseDaoImpl() {
          Type type = getClass().getGenericSuperclass();
          if(!(type instanceof ParameterizedType)){
	      type = getClass().getSuperclass().getGenericSuperclass();
            }
          try {
        	  this.clazz = (Class<T>)((ParameterizedType)type).getActualTypeArguments()[0];
			
		} catch (Exception e) {
			// TODO: handle exception
		}
          

	    }
	   
		public void save(T entity) {
			sessionFactory.getCurrentSession().saveOrUpdate(entity);
		}

	
		public void update(T entity) {
			sessionFactory.getCurrentSession().merge(entity);
		}

		public T getOne(Integer id) {
			T entity=null;
			entity=(T)sessionFactory.getCurrentSession().get(clazz, id);
			return entity;
			
		}

		
		public List<T> getMany() {
			return sessionFactory.getCurrentSession().createQuery("from "+clazz.getName()).list();
		}

		
		public void delete(Integer id) {
			sessionFactory.getCurrentSession().delete(sessionFactory.getCurrentSession().load(clazz, id));
		}

	
		public Page<T> getPage(Page<T> page) {
			
			
			String hql="from "+clazz.getName()+" order by date desc";
			if(page.getNumber()==null)
			{
			   int size=sessionFactory.getCurrentSession().createQuery(hql).list().size();
			   int pageSize=page.getPagsize();
			   if(size%pageSize==0)
				size=size/pageSize;
			   else {
				      size=size/pageSize+1;
			   }
			   page.setNumber(size);
			}
			page.setValues(sessionFactory.getCurrentSession().createQuery(hql).setFirstResult((page.getCurrentPage()-1)*page.getPagsize()).setMaxResults(page.getPagsize()).list());
			return page;
		}

		@Override
		public Page<T> getPage(Page<T> page, String hqlwhere, Map<String,Object> map) {
			String hql="from "+clazz.getName()+" ";
			if(hqlwhere!=null)
				hql+=hqlwhere;
			Query query=sessionFactory.getCurrentSession().createQuery(hql);
			if(map!=null)
				query.setProperties(map);
			if(page.getNumber()==null)
			{
			   int size=query.list().size();
			   int pageSize=page.getPagsize();
			   if(size%pageSize==0)
				size=size/pageSize;
			   else {
				      size=size/pageSize+1;
			   }
			   page.setNumber(size);
			}
			page.setValues(query.setFirstResult((page.getCurrentPage()-1)*page.getPagsize()).setMaxResults(page.getPagsize()).list());
			return page;
		}
	    
}
