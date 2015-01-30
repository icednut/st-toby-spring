package list3_28.user.dao;

import list3_28.user.context.JdbcContext;

import javax.sql.DataSource;
import java.sql.SQLException;

/**
 * @author wglee21g@gmail.com
 */
public class UserDao {
	private JdbcContext jdbcContext;
	private DataSource dataSource;

	public void setDataSource(DataSource dataSource) {
		this.jdbcContext = new JdbcContext();
		this.jdbcContext.setDataSource(dataSource);
		this.dataSource = dataSource;
	}

	public void deleteAll() throws SQLException {
		this.jdbcContext.executeSql("delete from users");
	}
}
