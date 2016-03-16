package com.zql.daoImpl;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.enterprise.deploy.model.J2eeApplicationObject;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.stereotype.Repository;

import sun.tools.jar.resources.jar;

import com.zql.base.BaseDaoImpl;
import com.zql.dao.ArticleDao;
import com.zql.model.Article;
import com.zql.util.Page;
@Repository
public class ArticleDaoImpl extends BaseDaoImpl<Article> implements ArticleDao{

	public List getTypeNum() {
		String hql="select distinct type,count(id) from Article article group by type";
	    return sessionFactory.getCurrentSession().createQuery(hql).list();	
	    }

	public List getDateNum() {
		String hql="select distinct DATE_FORMAT(date,'%Y年%m月') ,count(id) from Article article group by DATE_FORMAT(date,'%Y年%m月')";
		return sessionFactory.getCurrentSession().createQuery(hql).list();
	}
   
	public List getTitles() {
			String hql="select id,title,read_num from Article article order by read_num desc";
			return sessionFactory.getCurrentSession().createQuery(hql).setFirstResult(0).setMaxResults(10).list();
		
	}
	public List getContent(int id,int read) {
		String hql="update Article set read_num=:read where id=:id";
		sessionFactory.getCurrentSession().createQuery(hql).setInteger("read", read).setInteger("id", id).executeUpdate();
		hql="select content from Article article where id=:id";
		return sessionFactory.getCurrentSession().createQuery(hql).setInteger("id", id).list();
	}

	@Override
	public Page<Article> getArticlePage(Page<Article> page,
			Map<String, Object> map) {
		
		return this.getPage(page,"order by date desc",map);
	}
	public Page<Article> getArticlePageByDate(Page<Article> page,
			Map<String, Object> map) {
		
		return this.getPage(page,"where DATE_FORMAT(date,'%Y年%m月')=:date order by date desc",map);
	}
	public Page<Article> getArticlePageByType(Page<Article> page,
			Map<String, Object> map) {
		
		return this.getPage(page,"where type=:type order by date desc",map);
	}

	public void saveArticle(Article article) {
		this.save(article);
	}
}
