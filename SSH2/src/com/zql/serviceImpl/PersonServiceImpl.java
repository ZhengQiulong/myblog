package com.zql.serviceImpl;

import java.util.Date;
import java.util.List;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zql.dao.PersonDao;
import com.zql.model.Note;
import com.zql.model.Person;
import com.zql.service.PersonService;
import com.zql.util.Page;

@Transactional @Service("personService")
public class PersonServiceImpl implements PersonService {
    @Resource
	private PersonDao personDao;
	@Override
	public void save(Person person) {
		personDao.save(person);
	}

	@Override
	public void update(Person person) {
		personDao.update(person);
	}

	@Override 
	@Transactional(readOnly=true)
	public Person getPerson(Integer id) {
		return (Person)personDao.getOne(id);
		
	}

	@Override 
	@Transactional(readOnly=true)
	public List<Person> getPersons() {
		return personDao.getMany();
	}

	@Override
	public void delete(Integer personId) throws Exception {
		personDao.delete(personId);
	}

	@Override
	public void saveNote(String noteString,String writer) {
		Note note=new Note();
		note.setContent(noteString);
		note.setWriter(writer);
		note.setDate(new Date());
		personDao.saveNote(note);
		
	}

	@Transactional(readOnly=true)
	public List<Note> getNotes() {
		
		return personDao.getNotes();
	}
	public Page<Note> getNotesPage(Page<Note> page)
	{
		return personDao.getNotesPage(page);
				
	}

	@Override
	public int login(String user, String password) {
		
		return personDao.login(user, password);
	}
}
