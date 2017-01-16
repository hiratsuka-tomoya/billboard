package billboard.service;

import static billboard.utils.CloseableUtil.*;
import static billboard.utils.DBUtil.*;

import java.sql.Connection;

import billboard.beans.User;
import billboard.dao.UserDao;

public class LoginService {

	public User login(String loginID, String password) {

		Connection connection = null;
		try {
			connection = getConnection();

			UserDao userDao = new UserDao();
			String encPassword = password; //▲テスト用に非暗号化 CipherUtil.encrypt(password);
			User user = userDao
					.getUser(connection, loginID, encPassword);

			commit(connection);

			return user;
		} catch (RuntimeException e) {
			rollback(connection);
			throw e;
		} catch (Error e) {
			rollback(connection);
			throw e;
		} finally {
			close(connection);
		}
	}

}
