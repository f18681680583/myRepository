package cloudNote;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.List;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import cloudnote.dao.BookDao;
import cloudnote.dao.UserDao;
import cloudnote.entity.NoteBook;
import cloudnote.entity.User;
import cloudnote.service.UserService;
import cloudnote.util.NoteResult;
import cloudnote.util.NoteUtil;

public class test1 {
	@Test
	public void tese1(){
		String[] con={"conf/spring-mvc.xml","conf/spring-mybatis.xml"};
		ApplicationContext app=new ClassPathXmlApplicationContext(con);
		UserDao userdao=app.getBean("userDao",UserDao.class);
		UserService us=(UserService) app.getBean("userService");
		NoteResult nr=us.checkLogin("demo", "123456");
		System.out.println(nr);
		System.out.println(userdao.findByName("zhouj"));
}
	@Test
	public void tese4(){
		String[] con={"conf/spring-mvc.xml","conf/spring-mybatis.xml"};
		ApplicationContext app=new ClassPathXmlApplicationContext(con);
		BookDao dao=app.getBean("bookDao",BookDao.class);
		System.out.println(dao);
		List<NoteBook> books=dao.findNoteBook("333c6d0b-e4a2-4596-9902-a5d98c2f665a");
		System.out.println(books);
	}
	@Test
	public void test2(){
		String[] con={"conf/spring-mvc.xml","conf/spring-mybatis.xml"};
		ApplicationContext ac =
				new ClassPathXmlApplicationContext(con);
		UserDao userdao=(UserDao) ac.getBean("userDao");
		User user=new User();
		user.setCn_user_id("48595f52-b22c-4485-9244-f4004255b972");
		userdao.registUser(user);
	}
	@Test
	public void m() throws UnsupportedEncodingException, NoSuchAlgorithmException{
		
		System.out.println(new NoteResult().getStatus());
	}
}
