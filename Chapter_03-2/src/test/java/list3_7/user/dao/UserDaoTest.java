package list3_7.user.dao;

import list3_7.user.config.AppConfig;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @author wglee21g@gmail.com
 */
public class UserDaoTest {
	@Test
	public void testUserDaoAddExecute() throws Exception {
		AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppConfig.class);
		UserDao userDaoAdd = applicationContext.getBean("userDaoAdd", UserDaoAdd.class);

		userDaoAdd.execute();
	}

	@Test
	public void testUserDaoDeleteAllExecute() throws Exception {
		AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppConfig.class);
		UserDao userDaoDeleteAll = applicationContext.getBean("userDaoDeleteAll", UserDaoDeleteAll.class);

		userDaoDeleteAll.execute();
	}
}