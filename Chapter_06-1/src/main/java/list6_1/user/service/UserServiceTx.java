package list6_1.user.service;

import list6_1.user.domain.User;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

/**
 * @author wglee21g@gmail.com
 */
public class UserServiceTx implements UserService {
	private UserService userService;
	private PlatformTransactionManager transactionManager;

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	public void setTransactionManager(PlatformTransactionManager transactionManager) {
		this.transactionManager = transactionManager;
	}

	@Override
	public void upgradeLevels() {
		TransactionStatus status = transactionManager.getTransaction(new DefaultTransactionDefinition());

		try {
			userService.upgradeLevels();
			transactionManager.commit(status);
		} catch (RuntimeException e) {
			transactionManager.rollback(status);
			throw e;
		}
	}

	@Override
	public void add(User user) {
		userService.add(user);
	}
}
