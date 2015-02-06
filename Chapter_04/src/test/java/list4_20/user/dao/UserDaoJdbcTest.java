package list4_20.user.dao;

import common.user.domain.User;
import list4_20.user.config.AppConfig;
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
	@Autowired
	private UserDao userDao;

	@Test
	public void testDeleteAll() throws Exception {
		userDao.deleteAll();
	}

	@Test
	public void testGetAll() throws Exception {
		// given
		User givenUser1 = getUser1();
		User givenUser2 = getUser2();
		User givenUser3 = getUser3();

		userDao.deleteAll();

		userDao.add(givenUser1);
		List<User> userList1 = userDao.getAll();
		assertThat(userList1.size(), is(1));
		checkSameUser(givenUser1, userList1.get(0));

		userDao.add(givenUser2);
		List<User> userList2 = userDao.getAll();
		assertThat(userList2.size(), is(2));
		checkSameUser(givenUser1, userList2.get(0));
		checkSameUser(givenUser2, userList2.get(1));

		userDao.add(givenUser3);
		List<User> userList3 = userDao.getAll();
		assertThat(userList3.size(), is(3));
		checkSameUser(givenUser1, userList3.get(0));
		checkSameUser(givenUser2, userList3.get(1));
		checkSameUser(givenUser3, userList3.get(2));
	}

	private void checkSameUser(User givenUser, User actualUser) {
		assertThat(givenUser.getId(), is(actualUser.getId()));
		assertThat(givenUser.getName(), is(actualUser.getName()));
		assertThat(givenUser.getPassword(), is(actualUser.getPassword()));
	}

	public User getUser1() {
		User user = new User();
		user.setId("aaa");
		user.setName("이완근");
		user.setPassword("1234");
		return user;
	}

	public User getUser2() {
		User user = new User();
		user.setId("bbb");
		user.setName("철수");
		user.setPassword("1234");
		return user;
	}

	public User getUser3() {
		User user = new User();
		user.setId("ccc");
		user.setName("영희");
		user.setPassword("1234");
		return user;
	}
}