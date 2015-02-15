package list5_41.user.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.SimpleDriverDataSource;
import list5_41.user.dao.UserDao;
import list5_41.user.dao.UserDaoJdbc;
import list5_41.user.service.UserService;

import javax.sql.DataSource;
import java.sql.Driver;

/**
 * @author wglee21g@gmail.com
 */
@Configuration
public class AppConfig {
	@Bean
	public DataSource dataSource() throws ClassNotFoundException {
		SimpleDriverDataSource dataSource = new SimpleDriverDataSource();

		dataSource.setDriverClass((Class<Driver>) Class.forName("org.h2.Driver"));
		dataSource.setUrl("jdbc:h2:tcp://localhost/~/springbook");
		dataSource.setUsername("sa");
		dataSource.setPassword("");
		return dataSource;
	}

	@Bean
	public UserDao userDao() throws ClassNotFoundException {
		UserDaoJdbc userDao = new UserDaoJdbc();

		userDao.setDataSource(dataSource());
		return userDao;
	}

	@Bean
	public UserService userService(UserDao userDao, DataSource dataSource) {
		UserService userService = new UserService();

		userService.setUserDao(userDao);
		userService.setDataSource(dataSource);
		return userService;
	}
}
