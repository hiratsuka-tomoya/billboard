package billboard.service;

import static billboard.utils.CloseableUtil.*;
import static billboard.utils.DBUtil.*;

import java.sql.Connection;
import java.util.List;

import billboard.beans.UserPostiong;
import billboard.dao.UserPostingDao;

public class UserPostingService {

//	public void register(Posting posting) {
//
//		Connection connection = null;
//		try {
//			connection = getConnection();
//
//			MessageDao messageDao = new MessageDao();
//			messageDao.insert(connection, posting);
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

	private static final int LIMIT_NUM = 1000;

	public List<UserPostiong> getUserPostings() {

		Connection connection = null;
		try {
			connection = getConnection();

			List<UserPostiong> ret = new UserPostingDao().getUserPostings(connection, LIMIT_NUM);

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

//	public List<UserMessage> getMessage(String whereColumn, String whereValue) {
//
//		Connection connection = null;
//		try {
//			connection = getConnection();
//
//			UserMessageDao messageDao = new UserMessageDao();
//			List<UserMessage> ret = messageDao.getUserMessages(connection, LIMIT_NUM, whereColumn, whereValue);
//
//			commit(connection);
//
//			return ret;
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
