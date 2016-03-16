package com.zql.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.zql.base.BaseDao;
import com.zql.model.Article;
import com.zql.util.Page;

@Repository
public interface ArticleDao extends BaseDao<Article>{
	public List getTypeNum();
	public List getDateNum();
	public List getTitles();
	public List getContent(int id,int read);
	public Page<Article> getArticlePage(Page<Article> page,Map<String, Object> map);
	public Page<Article> getArticlePageByDate(Page<Article> page,
			Map<String, Object> map);
	public Page<Article> getArticlePageByType(Page<Article> page,
			Map<String, Object> map);
	public void saveArticle(Article article);
}
