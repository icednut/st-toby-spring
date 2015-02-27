package list5_18.user.dao;

import list5_18.user.domain.User;

import java.util.List;

/**
 * @author wglee21g@gmail.com
 */
public interface UserDao {
	public void add(User user);

	public User get(String id);

	public void deleteAll();

	public int getCount();

	public List<User> getAll();

	void update(User user);
}
