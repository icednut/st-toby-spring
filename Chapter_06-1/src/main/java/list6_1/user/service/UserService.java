package list6_1.user.service;

import list6_1.user.domain.User;

/**
 * @author wglee21g@gmail.com
 */
public interface UserService {
	void upgradeLevels();

	void add(User user);
}
