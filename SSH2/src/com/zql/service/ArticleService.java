package com.zql.service;

import java.util.List;
import java.util.Map;

import com.zql.model.Article;
import com.zql.model.Note;
import com.zql.util.Page;

public interface ArticleService {
	public List<Article> getArticles();
    public List getTypeNum();
    public List getDateNum();
    public List getTitles();
    public List getContent(int id,int read);
    public Page<Article> getPage(Page<Article> page);
    public Page<Article> getArticlePage(Page<Article> page,int searchId,String keywords);
    public void saveArticle(Article article);
    public Article getArticle(Integer id);
}
