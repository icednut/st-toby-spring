package list3_18.user.context;

import common.user.domain.User;
import list3_18.user.config.AppConfig;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @author wglee21g@gmail.com
 */
public class JdbcContextTest {

	@Test
	public void testAdd() throws Exception {
		AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppConfig.class);
		JdbcContext jdbcContext = applicationContext.getBean("jdbcContext", JdbcContext.class);

		jdbcContext.add(getUser());
	}

	@Test
	public void testDeleteAll() throws Exception {
		AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppConfig.class);
		JdbcContext jdbcContext = applicationContext.getBean("jdbcContext", JdbcContext.class);

		jdbcContext.deleteAll();
	}

	private User getUser() {
		User user = new User();
		user.setId("crazybnn");
		user.setName("이완근");
		user.setPassword("1234");
		return user;
	}
}