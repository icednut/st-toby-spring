package list6_4.user.service;

import list6_4.user.config.AppConfig;
import list6_4.user.dao.UserDao;
import list6_4.user.domain.Level;
import list6_4.user.domain.User;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.PlatformTransactionManager;

import java.util.Arrays;
import java.util.List;

import static list6_4.user.service.UserServiceImpl.MIN_LOGCOUNT_FOR_SILVER;
import static list6_4.user.service.UserServiceImpl.MIN_RECOMMEND_FOR_GOLD;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;

/**
 * @author wglee21g@gmail.com
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {AppConfig.class})
public class UserServiceTest {
	private List<User> users;

	@Autowired
	private UserServiceTx userService;

	@Autowired
	private UserDao userDao;

	@Autowired
	private PlatformTransactionManager transactionManager;

	@Before
	public void setUp() throws Exception {
		users = Arrays.asList(
				new User("bumjin", "박범진", "p1", Level.BASIC, MIN_LOGCOUNT_FOR_SILVER - 1, 0, "bumjin@test.com"),
				new User("joytouch", "강명성", "p1", Level.BASIC, MIN_LOGCOUNT_FOR_SILVER, 0, "joytouch@test.com"),
				new User("erwins", "신승한", "p1", Level.SILVER, 60, MIN_RECOMMEND_FOR_GOLD - 1, "erwins@test.com"),
				new User("madnite1", "이상호", "p1", Level.SILVER, 60, MIN_RECOMMEND_FOR_GOLD, "madnite1@test.com"),
				new User("green", "오민규", "p1", Level.GOLD, 100, Integer.MAX_VALUE, "green@test.com")
		);
	}

	@Test
	public void bean() throws Exception {
		assertThat(this.userService, is(notNullValue()));
	}

	@Test
	public void upgradeLevels() throws Exception {
		userDao.deleteAll();

		for (User user : users) {
			userDao.add(user);

		}

		userService.upgradeLevels();

		checkLevelUpgraded(users.get(0), false);
		checkLevelUpgraded(users.get(1), true);
		checkLevelUpgraded(users.get(2), false);
		checkLevelUpgraded(users.get(3), true);
		checkLevelUpgraded(users.get(4), false);
	}

	@Test
	public void add() throws Exception {
		userDao.deleteAll();

		User userWithLevel = users.get(4);
		User userWithoutLevel = users.get(0);
		userWithoutLevel.setLevel(null);

		userService.add(userWithLevel);
		userService.add(userWithoutLevel);

		User userWithLevelRead = userDao.get(userWithLevel.getId());
		User userWithoutLevelRead = userDao.get(userWithoutLevel.getId());

		assertThat(userWithLevelRead.getLevel(), is(userWithLevel.getLevel()));
		assertThat(userWithoutLevelRead.getLevel(), is(Level.BASIC));
	}

	@Test
	public void upgradeAllOrNothing() throws Exception {
		UserServiceImpl testUserService = new TestUserService(users.get(3).getId());
		testUserService.setUserDao(this.userDao);
		testUserService.setMailSender(new DummyMailSender());

		UserServiceTx userServiceTx = new UserServiceTx();
		userServiceTx.setTransactionManager(transactionManager);
		userServiceTx.setUserService(testUserService);

		userDao.deleteAll();
		for (User user : users) {
			userDao.add(user);
		}

		try {
			userServiceTx.upgradeLevels();
			fail("TestUserService Exception expected");
		} catch (TestUserServiceException e) {
		}

		checkLevelUpgraded(users.get(0), false);
		checkLevelUpgraded(users.get(1), false);
		checkLevelUpgraded(users.get(2), false);
		checkLevelUpgraded(users.get(3), false);
		checkLevelUpgraded(users.get(4), false);
	}

	private void checkLevelUpgraded(User user, boolean upgraded) {
		User userUpdate = userDao.get(user.getId());

		if (upgraded) {
			assertThat(userUpdate.getLevel(), is(user.getLevel().nextLevel()));
		} else {
			assertThat(userUpdate.getLevel(), is(user.getLevel()));
		}
	}

	static class TestUserService extends UserServiceImpl {
		private String id;

		private TestUserService(String id) {
			this.id = id;
		}

		protected void upgradeLevel(User user) {
			if (user.getId().equals(this.id)) {
				throw new TestUserServiceException();
			}
			super.upgradeLevel(user);
		}

	}

	static class TestUserServiceException extends RuntimeException {
	}
}