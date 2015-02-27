package list6_4.user.service;

import list6_4.user.domain.User;

/**
 * @author wglee21g@gmail.com
 */
public interface UserService {
	void add(User user);

	void upgradeLevels();
}
