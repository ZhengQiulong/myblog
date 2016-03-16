package com.zql.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;
import com.zql.base.BaseDao;
import com.zql.model.Article;
import com.zql.model.Note;
import com.zql.model.Person;
import com.zql.util.Page;

@Repository
public interface PersonDao extends BaseDao<Person>{
	public void saveNote(Note note);
	public List<Note> getNotes();
	public Page<Note> getNotesPage(Page<Note> page);
	public int login(String user,String password);

}
