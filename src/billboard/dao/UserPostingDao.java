package billboard.dao;

import static billboard.utils.CloseableUtil.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import billboard.beans.UserPostiong;
import billboard.exception.SQLRuntimeException;

public class UserPostingDao {
	public List<UserPostiong> getUserPostings(Connection connection, int num) {

		PreparedStatement ps = null;
		try {
			StringBuilder sql = new StringBuilder();
			sql.append("SELECT * FROM users_postings ");
			sql.append("ORDER BY created_at DESC limit " + num);

			ps = connection.prepareStatement(sql.toString());

			ResultSet rs = ps.executeQuery();
			List<UserPostiong> ret = toUserPostingList(rs);
			return ret;
		} catch (SQLException e) {
			throw new SQLRuntimeException(e);
		} finally {
			close(ps);
		}
	}

	private List<UserPostiong> toUserPostingList(ResultSet rs)
			throws SQLException {

		List<UserPostiong> ret = new ArrayList<UserPostiong>();
		try {
			while (rs.next()) {

				UserPostiong userPostiong = new UserPostiong();
				userPostiong.setId(rs.getInt("id"));
				userPostiong.setTitle(rs.getString("title"));
				userPostiong.setText(rs.getString("text"));
				userPostiong.setCategory(rs.getString("category"));
				userPostiong.setUserId(rs.getInt("user_id"));
				userPostiong.setUserName(rs.getString("user_name"));
				userPostiong.setCreatedDate(rs.getDate("created_at"));
				userPostiong.setUpdatedDate(rs.getDate("updated_at"));

				ret.add(userPostiong);
			}
			return ret;
		} finally {
			close(rs);
		}
	}
}
