package list3_7.user.dao;

import common.user.domain.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * @author wglee21g@gmail.com
 */
public class UserDaoAdd extends UserDao {
	@Override
	protected PreparedStatement makeStatement(Connection c) throws SQLException {
		PreparedStatement ps = c.prepareStatement("insert into users(id, name, password) values (?, ?, ?)");
		User user = getUser();

		ps.setString(1, user.getId());
		ps.setString(2, user.getName());
		ps.setString(3, user.getPassword());

		ps.executeUpdate();
		return ps;

	}

	private User getUser() {
		User user = new User();
		user.setId("crazybnn");
		user.setName("이완근");
		user.setPassword("1234");
		return user;
	}
}
