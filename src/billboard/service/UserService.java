package billboard.service;

import static billboard.utils.CloseableUtil.*;
import static billboard.utils.DBUtil.*;

import java.sql.Connection;
import java.util.List;

import billboard.beans.Bean;
import billboard.beans.User;
import billboard.dao.Dao;
import billboard.dao.UserDao;
import billboard.utils.CipherUtil;

public class UserService {

	public void register(User user) {

		Connection connection = null;
		try {
			connection = getConnection();

			String encPassword = CipherUtil.encrypt(user.getPassword());
			user.setPassword(encPassword);

			UserDao userDao = new UserDao();
			userDao.insert(connection, user);

			commit(connection);
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

	public boolean recoverUser(int id) {

		Connection connection = null;
		try {
			connection = getConnection();
			UserDao userDao = new UserDao();
			userDao.recoverUser(connection, id);
			commit(connection);
			return true;
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

	public boolean stopUser(int id) {

		Connection connection = null;
		try {
			connection = getConnection();
			UserDao userDao = new UserDao();
			userDao.stopUser(connection, id);
			commit(connection);
			return true;
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

	public List<Bean> getUsers() {

		int MAX_NUM = 1000;
		Connection connection = null;
		try {
			connection = getConnection();

			UserDao userDao = new UserDao();
			List<Bean> users = userDao.getBeans(connection, MAX_NUM);

			commit(connection);

			return users;
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

	public boolean update(User user, boolean isPasswordChanged) {

		Connection connection = null;
		try {
			connection = getConnection();

			if (isPasswordChanged) {
				user.setPassword(CipherUtil.encrypt(user.getPassword()));
			} else {
				user.setPassword(user.getPassword());
			}

			UserDao userDao = new UserDao();
			userDao.update(connection, user);

			commit(connection);
			return true;
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

	public Bean getUser(int userId) {

		Connection connection = null;
		try {
			connection = getConnection();

			Dao userDao = new UserDao();
			Bean user = userDao.getBean(connection, "id",  userId);

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

	public Bean getUserFromLoginId(String loginId) {

		Connection connection = null;
		try {
			connection = getConnection();

			Dao userDao = new UserDao();
			Bean user = userDao.getBean(connection, "login_id",  loginId);

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

	public Boolean deleteUser(int id) {

		Connection connection = null;
		try {
			connection = getConnection();

			Boolean ret = new UserDao().deleteBeans(connection, id);

			commit(connection);

			return ret;
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
