package list3_24.user.dao;

import common.user.domain.User;
import list3_24.user.config.AppConfig;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @author wglee21g@gmail.com
 */
public class UserDaoTest {

	@Test
	public void testAdd() throws Exception {
		AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppConfig.class);
		UserDao userDao = applicationContext.getBean("userDao", UserDao.class);

		userDao.add(getUser());
	}

	@Test
	public void testDeleteAll() throws Exception {
		AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppConfig.class);
		UserDao userDao = applicationContext.getBean("userDao", UserDao.class);

		userDao.deleteAll();
	}

	private User getUser() {
		User user = new User();
		user.setId("crazybnn");
		user.setName("이완근");
		user.setPassword("1234");
		return user;
	}
}