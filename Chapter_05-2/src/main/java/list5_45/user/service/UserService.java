package list5_45.user.service;

import list5_45.user.dao.UserDao;
import list5_45.user.domain.Level;
import list5_45.user.domain.User;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.jta.JtaTransactionManager;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.List;

/**
 * @author wglee21g@gmail.com
 */
public class UserService {
	public static final int MIN_LOGCOUNT_FOR_SILVER = 50;
	public static final int MIN_RECOMMEND_FOR_GOLD = 30;
	private UserDao userDao;
	private DataSource dataSource;
	private PlatformTransactionManager transactionManager;

	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	public void setTransactionManager(PlatformTransactionManager transactionManager) {
		this.transactionManager = transactionManager;
	}

	public void upgradeLevels() throws SQLException {
//		PlatformTransactionManager transactionManager = new DataSourceTransactionManager(dataSource);
//		PlatformTransactionManager transactionManager = new JtaTransactionManager();
		TransactionStatus status = transactionManager.getTransaction(new DefaultTransactionDefinition());

		try {
			List<User> users = userDao.getAll();

			for (User user : users) {
				if (canUpgradeLevel(user)) {
					upgradeLevel(user);
				}
			}
			transactionManager.commit(status);
		} catch (RuntimeException e) {
			transactionManager.rollback(status);
			throw e;
		}
	}

	public void add(User user) {
		if (user.getLevel() == null) {
			user.setLevel(Level.BASIC);
		}

		userDao.add(user);
	}

	private boolean canUpgradeLevel(User user) {
		Level currentLevel = user.getLevel();

		switch (currentLevel) {
			case BASIC:
				return (user.getLogin() >= MIN_LOGCOUNT_FOR_SILVER);
			case SILVER:
				return (user.getLogin() >= MIN_RECOMMEND_FOR_GOLD);
			case GOLD:
				return false;
			default:
				throw new IllegalArgumentException("Unknown Level: " + currentLevel);
		}
	}

	protected void upgradeLevel(User user) {
		user.upgradeLevel();
		userDao.update(user);
	}

}
