package list6_1.user.dao;

import list6_1.user.domain.User;

import java.util.List;

/**
 * wglee21g@gmail.com
 */
public interface UserDao {
	public void add(User user);

	public User get(String id);

	public void deleteAll();

	public int getCount();

	public List<User> getAll();

	void update(User user);
}
