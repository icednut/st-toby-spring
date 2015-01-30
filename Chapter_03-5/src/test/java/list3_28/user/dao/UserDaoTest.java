package list3_28.user.dao;

import list3_28.user.config.AppConfig;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @author wglee21g@gmail.com
 */
public class UserDaoTest {

	@Test
	public void testDeleteAll() throws Exception {
		AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppConfig.class);
		UserDao userDao = applicationContext.getBean("userDao", UserDao.class);

		userDao.deleteAll();
	}
}