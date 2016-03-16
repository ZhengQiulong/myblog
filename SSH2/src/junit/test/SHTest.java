package junit.test;
import static org.junit.Assert.*;

import java.util.List;


import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.zql.model.Person;
import com.zql.service.PersonService;


public class SHTest {
private static PersonService personService;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	   try {
		ApplicationContext ctx= new ClassPathXmlApplicationContext("beans.xml");
		   personService= (PersonService) ctx.getBean("personService");
	} catch (Exception e) {
		e.printStackTrace();
	}
	}

	@Test
	public void test() {
		fail("Not yet implemented");
	}
	
	@Test
	public void testsave() {
			personService.save(new Person("hhp123"));
	}
	
	
	
	@Test
	public void testdelete() throws Exception {
		personService.delete(6);
	}
	
	@Test
	public void testupdate() {
		personService.update(new Person(23,"jjj23"));
	}
	
	@Test
	public void getperson() {
		System.out.println(personService.getPerson(1).toString());
		try {
			System.out.println("qing guan bi");
			Thread.sleep(1000*15);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("di er ci kai shi");
		System.out.println(personService.getPerson(1).toString());
	}
	
	@Test
	public void testgetpersons() {
		List<Person> list= personService.getPersons();
		for (int i = 0; i < list.size(); i++) {
			System.out.println(list.get(i).toString());
		}
	}
	

}
