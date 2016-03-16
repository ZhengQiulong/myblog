package com.zql.serviceImpl;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sun.org.apache.regexp.internal.recompile;
import com.sun.org.apache.xml.internal.serializer.ElemDesc;
import com.zql.dao.ArticleDao;
import com.zql.dao.PersonDao;
import com.zql.model.Article;
import com.zql.model.Note;
import com.zql.service.ArticleService;
import com.zql.util.Page;

@Transactional @Service("articleService")
public class ArticleServiceImpl implements ArticleService{
	@Resource
	private ArticleDao articleDao;
	
	@Transactional(readOnly=true)
	public List<Article> getArticles() {
	
		return articleDao.getMany();
	}
	
	@Transactional(readOnly=true)
	public List getTypeNum() {
		
	  return articleDao.getTypeNum();
	}

	@Transactional(readOnly=true)
	public List getDateNum() {
		
		return articleDao.getDateNum();
	}

	@Transactional(readOnly=true)
	public List getTitles() {
		
		return articleDao.getTitles();
	}


	public List getContent(int id,int read) {
		
		return articleDao.getContent(id,read);
	}
	@Transactional(readOnly=true)
	public Page<Article> getPage(Page<Article> page)
	{
		return articleDao.getPage(page);
	}
	public Page<Article> getArticlePage(Page<Article> page,int searchId,String keywords)
	{
		Map<String,Object> map=new HashMap<String, Object>();
		
		if(searchId==0)
		{
			return articleDao.getArticlePage(page,map);
		}
		else if(searchId==1)
		{
			try {
				map.put("date",new String(keywords.getBytes("ISO8859-1"), "UTF-8"));
			} catch (UnsupportedEncodingException e) {
				
				e.printStackTrace();
			}
			return articleDao.getArticlePageByDate(page,map);
		}
		else {
			
			try {
				map.put("type",new String(keywords.getBytes("ISO8859-1"), "UTF-8"));
			} catch (UnsupportedEncodingException e) {
				
				e.printStackTrace();
			}
			return articleDao.getArticlePageByType(page,map);
		}
	}
	public void saveArticle(Article article)
	{
		if(article!=null)
		{
			if(article.getRead_num()==null)
			article.setRead_num(0);
			if(article.getDate()==null)
			article.setDate(new Date(System.currentTimeMillis()));
	    	articleDao.saveArticle(article);
		}
		else {
			return;
		}
	}

	@Override
	public Article getArticle(Integer id) {
		if(id!=null)
		return articleDao.getOne(id);
		else
			return null;
	}
}
