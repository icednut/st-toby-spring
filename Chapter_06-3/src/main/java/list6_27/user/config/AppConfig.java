package list6_27.user.config;

import list6_27.user.dao.UserDao;
import list6_27.user.dao.UserDaoJdbc;
import list6_27.user.service.DummyMailSender;
import list6_27.user.service.UserService;
import list6_27.user.service.UserServiceImpl;
import list6_27.user.service.UserServiceTx;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.SimpleDriverDataSource;
import org.springframework.transaction.PlatformTransactionManager;

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
	public UserService userService(UserDao userDao, DummyMailSender mailSender) {
		UserServiceImpl userService = new UserServiceImpl();

		userService.setUserDao(userDao);
		userService.setMailSender(mailSender);
		return userService;
	}

	@Bean
	public PlatformTransactionManager transactionManager(DataSource dataSource) {
		return new DataSourceTransactionManager(dataSource);
	}

	@Bean
	public DummyMailSender mailSender() {
		DummyMailSender mailSender = new DummyMailSender();
		return mailSender;
	}
}
