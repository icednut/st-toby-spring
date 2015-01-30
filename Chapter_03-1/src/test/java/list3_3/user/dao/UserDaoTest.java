package list3_3.user.dao;

import list3_3.user.config.AppConfig;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 * @author wglee21g@gmail.com
 */
public class UserDaoTest {

	@Test
	public void testGetCount() throws Exception {
		AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppConfig.class);
		UserDao userDao = applicationContext.getBean("userDao", UserDao.class);

		int count = userDao.getCount();

		assertThat(count, is(0));
	}
}