package com.zql.service;

import java.util.List;

import com.zql.model.Note;
import com.zql.model.Person;
import com.zql.util.Page;


public interface PersonService {
   public void save(Person person) ;
   public void update(Person person);
   public Person getPerson(Integer id);
   public List<Person> getPersons();
   public void delete(Integer personId) throws Exception;
   public void saveNote(String noteString,String writer);
   public List<Note> getNotes();
   public Page<Note> getNotesPage(Page<Note> page);
   public int login(String user,String password);
}
