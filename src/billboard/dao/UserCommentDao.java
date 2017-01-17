package billboard.dao;

import static billboard.utils.CloseableUtil.*;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import billboard.beans.Bean;
import billboard.beans.UserComment;

public class UserCommentDao extends Dao {

	public List<Bean> getBeans(Connection connection, int num) {
		return super.getBeans(connection, num, "users_comments");
	}

	protected List<Bean> toBeanList(ResultSet rs)
			throws SQLException {

		List<Bean> ret = new ArrayList<Bean>();
		try {
			while (rs.next()) {

				Bean bean = new UserComment();
//				bean.setId(rs.getInt("id"));
//				bean.setText(rs.getString("text"));
//				bean.setUserId(rs.getInt("user_id"));
//				bean.setPostingId(rs.getInt("posting_id"));
//				bean.setUserName(rs.getString("user_name"));
//				bean.setCreatedDate(rs.getDate("created_at"));
//				bean.setUpdatedDate(rs.getDate("updated_at"));

				ret.add(bean);
			}
			return ret;
		} finally {
			close(rs);
		}
	}
}
