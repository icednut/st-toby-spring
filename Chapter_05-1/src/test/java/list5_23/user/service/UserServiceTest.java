package list5_23.user.service;

import list5_23.user.config.AppConfig;
import list5_23.user.dao.UserDao;
import list5_23.user.domain.Level;
import list5_23.user.domain.User;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;

import static list5_23.user.service.UserService.MIN_LOGCOUNT_FOR_SILVER;
import static list5_23.user.service.UserService.MIN_RECOMMEND_FOR_GOLD;

/**
 * @author wglee21g@gmail.com
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {AppConfig.class})
public class UserServiceTest {
	private List<User> users;

	@Autowired
	private UserService userService;

	@Autowired
	private UserDao userDao;

	@Before
	public void setUp() throws Exception {
		users = Arrays.asList(
				new User("bumjin", "박범진", "p1", Level.BASIC, MIN_LOGCOUNT_FOR_SILVER - 1, 0),
				new User("joytouch", "강명성", "p1", Level.BASIC, MIN_LOGCOUNT_FOR_SILVER, 0),
				new User("erwins", "신승한", "p1", Level.SILVER, 60, MIN_RECOMMEND_FOR_GOLD - 1),
				new User("madnite1", "이상호", "p1", Level.SILVER, 60, MIN_RECOMMEND_FOR_GOLD),
				new User("green", "오민규", "p1", Level.GOLD, 100, Integer.MAX_VALUE)
		);
	}

	@Test
	public void bean() throws Exception {
		assertThat(this.userService, is(notNullValue()));
	}

	@Test
	public void testUpgradeLevels() throws Exception {
		userDao.deleteAll();

		for (User user : users) {
			userDao.add(user);
			
		}

		userService.upgradeLevels();

		checkLevel(users.get(0), false);
		checkLevel(users.get(1), true);
		checkLevel(users.get(2), true);
		checkLevel(users.get(3), true);
		checkLevel(users.get(4), false);
	}

	@Test
	public void testAdd() throws Exception {
//		userDao.deleteAll();
//
//		User userWithLevel = users.get(4);
//		User userWithoutLevel = users.get(0);
//		userWithoutLevel.setLevel(null);
//
//		userService.add(userWithLevel);
//		userService.add(userWithoutLevel);
//
//		User userWithLevelRead = userDao.get(userWithLevel.getId());
//		User userWithoutLevelRead = userDao.get(userWithoutLevel.getId());
//
//		assertThat(userWithLevelRead.getLevel(), is(userWithLevel.getLevel()));
//		assertThat(userWithoutLevelRead.getLevel(), is(Level.BASIC));
	}

	private void checkLevel(User user, boolean upgraded) {
		User userUpdate = userDao.get(user.getId());
		
		if (upgraded) {
			assertThat(userUpdate.getLevel(), is(user.getLevel().nextLevel()));
		} else {
			assertThat(userUpdate.getLevel(), is(user.getLevel()));
		}
	}
}