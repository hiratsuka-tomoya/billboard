package billboard.dao;

import static billboard.utils.CloseableUtil.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import billboard.beans.Bean;
import billboard.exception.SQLRuntimeException;

public abstract class Dao {

	private String tableName;

	Dao(String tableName) {
		this.tableName = tableName;
	}

	protected abstract Bean makeNewBean();

	public List<Bean> getBeans(Connection connection, int num) {

		PreparedStatement ps = null;
		try {
			StringBuilder sql = new StringBuilder();
			sql.append("SELECT * FROM " + tableName + " ");
			sql.append("ORDER BY created_at DESC limit " + num);

			ps = connection.prepareStatement(sql.toString());

			ResultSet rs = ps.executeQuery();
			List<Bean> ret = toBeanList(rs);
			return ret;
		} catch (SQLException e) {
			throw new SQLRuntimeException(e);
		} finally {
			close(ps);
		}
	}

	public Bean getBean(Connection connection, int id) {

		PreparedStatement ps = null;
		try {
			String sql = "SELECT * FROM" + tableName + "WHERE id = ?";

			ps = connection.prepareStatement(sql);
			ps.setInt(1, id);

			ResultSet rs = ps.executeQuery();
			List<Bean> beanList = toBeanList(rs);
			if (beanList.isEmpty() == true) {
				return null;
			} else if (2 <= beanList.size()) {
				throw new IllegalStateException("2 <= beanList.size()");
			} else {
				return beanList.get(0);
			}
		} catch (SQLException e) {
			throw new SQLRuntimeException(e);
		} finally {
			close(ps);
		}
	}

	protected List<Bean> toBeanList(ResultSet rs) throws SQLException {

		List<Bean> ret = new ArrayList<Bean>();
		try {
			while (rs.next()) {

				Bean bean = makeNewBean();
				bean.readResultSet(rs);

				ret.add(bean);
			}
			return ret;
		} finally {
			close(rs);
		}
	}

}
