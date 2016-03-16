package com.zql.daoImpl;
import java.util.Iterator;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.zql.base.BaseDaoImpl;
import com.zql.dao.PersonDao;
import com.zql.model.Note;
import com.zql.model.Person;
import com.zql.util.Page;

@Repository
public class PersonDaoImpl extends BaseDaoImpl<Person> implements PersonDao {


	public void saveNote(Note note) {
		sessionFactory.getCurrentSession().save(note);
	}

	
	public List<Note> getNotes() {
		
		return sessionFactory.getCurrentSession().createQuery("from Note").list();
	}


	public Page<Note> getNotesPage(Page<Note> page) {
	
		String hql="from Note order by date desc";
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
	public int login(String user, String password) {
		String hql="select id from Person where account=:user and password=:password";
		List<Integer> list=sessionFactory.getCurrentSession().createQuery(hql).setString("user",user).setString("password",password).list();
		
		if(list.size()!=0)
		{
			return list.get(0);
			
		}
		return 0;
	}

}
