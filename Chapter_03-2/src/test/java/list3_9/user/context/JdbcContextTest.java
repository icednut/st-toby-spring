package list3_9.user.context;

import list3_9.user.config.AppConfig;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @author wglee21g@gmail.com
 */
public class JdbcContextTest {

	@Test
	public void testDeleteAll() throws Exception {
		AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppConfig.class);
		JdbcContext jdbcContext = applicationContext.getBean("jdbcContext", JdbcContext.class);

		jdbcContext.deleteAll();
	}
}