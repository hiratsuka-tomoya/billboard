package billboard.service;

import static billboard.utils.CloseableUtil.*;
import static billboard.utils.DBUtil.*;

import java.sql.Connection;
import java.util.List;

import billboard.beans.Bean;
import billboard.dao.UserCommentDao;

public class UserCommentService {

	private static final int LIMIT_NUM = 1000;

	public List<Bean> getUserComments() {

		Connection connection = null;
		try {
			connection = getConnection();

			List<Bean> ret = new UserCommentDao().getBeans(connection, LIMIT_NUM);

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
