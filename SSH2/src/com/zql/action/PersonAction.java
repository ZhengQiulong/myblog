package com.zql.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionContext;
import com.sun.org.apache.bcel.internal.generic.NEW;
import com.zql.model.Article;
import com.zql.model.Note;
import com.zql.model.Person;
import com.zql.service.ArticleService;
import com.zql.service.PersonService;
import com.zql.util.Page;
@Controller
public class PersonAction {
	@Resource
	private PersonService personService;
	@Resource
	private ArticleService articleService;
	private Person person;
	private List<Article> articles;
	private List types;
	private List dates;
	private List titles;
	private String note;
	private List<Note> notes;
	private int user_id=1;
	private int article_id;
	private int read_num;
	private List content;
	private Page<Note> page=new Page<Note>(10,1); 
	private Page<Article> articlePage=new Page<Article>(7,1); 
	private int searchId=0;
	private String keyword;
	private Article article;
	public String index()
	{   
		init();
		articlePage=articleService.getArticlePage(articlePage,searchId,keyword);
		return "index";
	}
	public String write()
	{   
		init();
		 articleService.saveArticle(article);
		return "write";
	}
	public String edite()
	{   
		 init();
		  articleService.saveArticle(article);
		  article=articleService.getArticle(article_id);
		return "edite";
	}
	public void leaveNote()
	{
		personService.saveNote(note,"网友");
	}
    public String notes()
    {
    	init();
    	page=personService.getNotesPage(page);
    	return "notes";
    }
    public String content()
    {
    	init();
    	read_num++;
    	content=articleService.getContent(article_id,read_num);
    	return "content";
    }
    public void init()
    {
    	person=personService.getPerson(user_id);
		types=articleService.getTypeNum();
		dates=articleService.getDateNum();
		titles=articleService.getTitles();
    }
	public PersonService getPersonService() {
		return personService;
	}
    
	public void setPersonService(PersonService personService) {
		this.personService = personService;
	}

	public Person getPerson() {
		return person;
	}

	public void setPerson(Person person) {
		this.person = person;
	}

	public List<Article> getArticles() {
		return articles;
	}

	public void setArticles(List<Article> articles) {
		this.articles = articles;
	}

	public List getTypes() {
		return types;
	}

	public void setTypes(List types) {
		this.types = types;
	}

	public List getDates() {
		return dates;
	}

	public void setDates(List dates) {
		this.dates = dates;
	}

	public List getTitles() {
		return titles;
	}

	public void setTitles(List titles) {
		this.titles = titles;
	}
	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
	}
	public List<Note> getNotes() {
		return notes;
	}
	public void setNotes(List<Note> notes) {
		this.notes = notes;
	}
	public int getArticle_id() {
		return article_id;
	}
	public void setArticle_id(int article_id) {
		this.article_id = article_id;
	}
	public List getContent() {
		return content;
	}
	public void setContent(List content) {
		this.content = content;
	}
	public Page<Note> getPage() {
		return page;
	}
	public void setPage(Page<Note> page) {
		this.page = page;
	}
	public Page<Article> getArticlePage() {
		return articlePage;
	}
	public void setArticlePage(Page<Article> articlePage) {
		this.articlePage = articlePage;
	}
	
	public int getSearchId() {
		return searchId;
	}
	public void setSearchId(int searchId) {
		this.searchId = searchId;
	}
	
	public String getKeyword() {
		return keyword;
	}
	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}
	public Article getArticle() {
		return article;
	}
	public void setArticle(Article article) {
		this.article = article;
	}
	public int getRead_num() {
		return read_num;
	}
	public void setRead_num(int read_num) {
		this.read_num = read_num;
	}
	
	
	
}
