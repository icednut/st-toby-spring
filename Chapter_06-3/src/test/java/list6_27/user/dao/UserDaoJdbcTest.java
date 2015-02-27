package list6_27.user.dao;

import list6_27.user.config.AppConfig;
import list6_27.user.domain.Level;
import list6_27.user.domain.User;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 * @author wglee21g@gmail.com
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = AppConfig.class)
public class UserDaoJdbcTest {
	private User user1;
	private User user2;
	private User user3;

	@Autowired
	private UserDao userDao;

	@Before
	public void setUp() {
		this.user1 = new User("gyumee", "박성철", "springno1", Level.BASIC, 1, 0, "gyumee@test.com");
		this.user2 = new User("leegw700", "이길원", "springno2", Level.SILVER, 55, 10, "leegw700@test.com");
		this.user3 = new User("bumjin", "박범진", "springno3", Level.GOLD, 100, 40, "bumjin@test.com");
	}

	@Test
	public void testDeleteAll() {
		userDao.deleteAll();
	}

	@Test
	public void testGetAll() {
		userDao.deleteAll();

		userDao.add(user1);
		List<User> userList1 = userDao.getAll();
		assertThat(userList1.size(), is(1));
		checkSameUser(user1, userList1.get(0));

		userDao.add(user2);
		List<User> userList2 = userDao.getAll();
		assertThat(userList2.size(), is(2));
		checkSameUser(user1, userList2.get(0));
		checkSameUser(user2, userList2.get(1));

		userDao.add(user3);
		List<User> userList3 = userDao.getAll();
		assertThat(userList3.size(), is(3));
		checkSameUser(user3, userList3.get(0));
		checkSameUser(user1, userList3.get(1));
		checkSameUser(user2, userList3.get(2));
	}

	@Test
	public void update() throws Exception {
		userDao.deleteAll();

		userDao.add(user1); // 수정할 사용자
		userDao.add(user2); // 수정하지 않을 사용자

		user1.setName("오민규");
		user1.setPassword("springno6");
		user1.setLevel(Level.GOLD);
		user1.setLogin(1000);
		user1.setRecommend(999);
		userDao.update(user1);

		checkSameUser(user1, userDao.get(user1.getId()));
		checkSameUser(user2, userDao.get(user2.getId()));
	}

	private void checkSameUser(User givenUser, User actualUser) {
		assertThat(givenUser.getId(), is(actualUser.getId()));
		assertThat(givenUser.getName(), is(actualUser.getName()));
		assertThat(givenUser.getPassword(), is(actualUser.getPassword()));
		assertThat(givenUser.getLevel(), is(actualUser.getLevel()));
		assertThat(givenUser.getLogin(), is(actualUser.getLogin()));
		assertThat(givenUser.getRecommend(), is(actualUser.getRecommend()));
	}
}