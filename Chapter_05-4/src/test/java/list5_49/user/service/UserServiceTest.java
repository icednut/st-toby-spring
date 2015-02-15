package list5_49.user.service;

import list5_49.user.config.AppConfig;
import list5_49.user.dao.UserDao;
import list5_49.user.domain.Level;
import list5_49.user.domain.User;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Arrays;
import java.util.List;

import static list5_49.user.service.UserService.MIN_LOGCOUNT_FOR_SILVER;
import static list5_49.user.service.UserService.MIN_RECOMMEND_FOR_GOLD;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

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
				new User("bumjin", "박범진", "p1", Level.BASIC, MIN_LOGCOUNT_FOR_SILVER - 1, 0, "bumjin@test.com"),
				new User("joytouch", "강명성", "p1", Level.BASIC, MIN_LOGCOUNT_FOR_SILVER, 0, "joytouch@test.com"),
				new User("erwins", "신승한", "p1", Level.SILVER, 60, MIN_RECOMMEND_FOR_GOLD - 1, "erwins@test.com"),
				new User("madnite1", "이상호", "p1", Level.SILVER, 60, MIN_RECOMMEND_FOR_GOLD, "madnite1@test.com"),
				new User("green", "오민규", "p1", Level.GOLD, 100, Integer.MAX_VALUE, "green@test.com")
		);
	}

	@Test
	public void upgradeAllOrNothing() throws Exception {
		userDao.deleteAll();

		for (User user : users) {
			userDao.add(user);
		}

		userService.upgradeLevels();

//		checkLevel(users.get(1), false);
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