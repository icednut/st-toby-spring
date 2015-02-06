package list4_20.user.dao;

import common.user.domain.User;

import java.util.List;

/**
 * Created by 1002371 on 15. 2. 7..
 */
public interface UserDao {
	public void add(User user);

	public User get(String id);

	public void deleteAll();

	public int getCount();

	public List<User> getAll();
}
