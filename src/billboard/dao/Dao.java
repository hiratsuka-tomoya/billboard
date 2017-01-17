package billboard.dao;

import static billboard.utils.CloseableUtil.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import billboard.beans.Bean;
import billboard.exception.SQLRuntimeException;

public abstract class Dao {

	public List<Bean> getBeans(Connection connection, int num, String tableName) {

		PreparedStatement ps = null;
		try {
			StringBuilder sql = new StringBuilder();
			sql.append("SELECT * FROM" + tableName + " ");
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

	protected abstract List<Bean> toBeanList(ResultSet rs) throws SQLException;
//			throws SQLException {
//
//		List<Bean> ret = new ArrayList<Bean>();
//		try {
//			while (rs.next()) {
//
//				Bean bean = new UserPostiong();
//				bean.setId(rs.getInt("id"));
//				bean.setTitle(rs.getString("title"));
//				bean.setText(rs.getString("text"));
//				bean.setCategory(rs.getString("category"));
//				bean.setUserId(rs.getInt("user_id"));
//				bean.setUserName(rs.getString("user_name"));
//				bean.setCreatedDate(rs.getDate("created_at"));
//				bean.setUpdatedDate(rs.getDate("updated_at"));
//
//				ret.add(Bean);
//			}
//			return ret;
//		} finally {
//			close(rs);
//		}
//	}
}
