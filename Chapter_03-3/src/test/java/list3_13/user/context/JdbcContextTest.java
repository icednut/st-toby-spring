package list3_13.user.context;

import common.user.domain.User;
import list3_13.user.config.AppConfig;
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

	private User getUser() {
		User user = new User();
		user.setId("crazybnn");
		user.setName("이완근");
		user.setPassword("1234");
		return user;
	}
}