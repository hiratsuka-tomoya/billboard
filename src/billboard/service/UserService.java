package billboard.service;

import static billboard.utils.CloseableUtil.*;
import static billboard.utils.DBUtil.*;

import java.sql.Connection;
import java.util.List;

import billboard.beans.Bean;
import billboard.beans.User;
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

//	private void setDefaultIcon(User user) {
//
//		InputStream is = null;
//		try {
//			long randomNum = System.currentTimeMillis() % 5;
//			String filePath = "/duke_" + randomNum + ".jpg";
//			is = UserService.class.getResourceAsStream(filePath);
//			ByteArrayOutputStream baos = new ByteArrayOutputStream();
//			StreamUtil.copy(is, baos);
//			user.setIcon(baos.toByteArray());
//		} finally {
//			close(is);
//		}
//	}

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
//
//	public void update(User user) {
//
//		Connection connection = null;
//		try {
//			connection = getConnection();
//
//			String encPassword = CipherUtil.encrypt(user.getPassword());
//			user.setPassword(encPassword);
//
//			UserDao userDao = new UserDao();
//			userDao.update(connection, user);
//
//			commit(connection);
//		} catch (RuntimeException e) {
//			rollback(connection);
//			throw e;
//		} catch (Error e) {
//			rollback(connection);
//			throw e;
//		} finally {
//			close(connection);
//		}
//	}
//
//	public User getUser(int userId) {
//
//		Connection connection = null;
//		try {
//			connection = getConnection();
//
//			UserDao userDao = new UserDao();
//			User user = userDao.getUser(connection, userId);
//
//			commit(connection);
//
//			return user;
//		} catch (RuntimeException e) {
//			rollback(connection);
//			throw e;
//		} catch (Error e) {
//			rollback(connection);
//			throw e;
//		} finally {
//			close(connection);
//		}
//	}
//
//	public User getUser(String accountOrEmail) {
//
//		Connection connection = null;
//		try {
//			connection = getConnection();
//
//			UserDao userDao = new UserDao();
//			User user = userDao.getUser(connection, accountOrEmail);
//
//			commit(connection);
//
//			return user;
//		} catch (RuntimeException e) {
//			rollback(connection);
//			throw e;
//		} catch (Error e) {
//			rollback(connection);
//			throw e;
//		} finally {
//			close(connection);
//		}
//	}

}
