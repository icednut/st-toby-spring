package list5_55.user.config;

import list5_55.user.dao.UserDao;
import list5_55.user.dao.UserDaoJdbc;
import list5_55.user.service.DummyMailSender;
import list5_55.user.service.UserService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.SimpleDriverDataSource;
import org.springframework.mail.MailSender;
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
	public UserService userService(UserDao userDao, DataSource dataSource, PlatformTransactionManager transactionManager, MailSender mailSender) {
		UserService userService = new UserService();

		userService.setUserDao(userDao);
		userService.setDataSource(dataSource);
		userService.setTransactionManager(transactionManager);
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
