package billboard.dao;

import static billboard.utils.CloseableUtil.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import billboard.beans.User;
import billboard.exception.NoRowsUpdatedRuntimeException;
import billboard.exception.SQLRuntimeException;

public class UserDao {

	public User getUser(Connection connection, String loginID,
			String password) {

		PreparedStatement ps = null;
		try {
			String sql = "SELECT * FROM users WHERE login_id = ? AND password = ?";

			ps = connection.prepareStatement(sql);
			ps.setString(1, loginID);
			ps.setString(2, password);

			ResultSet rs = ps.executeQuery();
			List<User> userList = toUserList(rs);
			if (userList.isEmpty() == true) {
				return null;
			} else if (2 <= userList.size()) {
				//ID重複
				throw new IllegalStateException("2 <= userList.size()");
			} else {
				return userList.get(0);
			}
		} catch (SQLException e) {
			throw new SQLRuntimeException(e);
		} finally {
			close(ps);
		}
	}

	private List<User> toUserList(ResultSet rs) throws SQLException {

		List<User> ret = new ArrayList<User>();
		try {
			while (rs.next()) {
				int id = rs.getInt("id");
				String loginID = rs.getString("login_ID");
				String password = rs.getString("password");
				String name = rs.getString("name");
				int branch_id = rs.getInt("branch_id");
				int department_id = rs.getInt("department_id");
				Boolean isStopped = rs.getBoolean("is_Stopped");
				Timestamp created_at = rs.getTimestamp("created_at");
				Timestamp updated_at = rs.getTimestamp("updated_at");

				User user = new User();
				user.setId(id);
				user.setLoginID(loginID);
				user.setPassword(password);
				user.setName(name);
				user.setBranchID(branch_id);
				user.setDepartmentID(department_id);
				user.setStopped(isStopped);
				user.setCreatedDate(created_at);
				user.setUpdatedDate(updated_at);

				ret.add(user);
			}
			return ret;
		} finally {
			close(rs);
		}
	}

//	private byte[] getIcon(ResultSet rs) throws SQLException {
//		byte[] ret = null;
//		InputStream binaryStream = rs.getBinaryStream("icon");
//		if (binaryStream != null) {
//			ByteArrayOutputStream baos = new ByteArrayOutputStream();
//			StreamUtil.copy(binaryStream, baos);
//			ret = baos.toByteArray();
//		}
//		return ret;
//	}

	public void insert(Connection connection, User user) {

		PreparedStatement ps = null;
		try {
			StringBuilder sql = new StringBuilder();
			sql.append("INSERT INTO users ( ");
			sql.append("login_id");
			sql.append(", password");
			sql.append(", name");
			sql.append(", branch_id");
			sql.append(", department_id");
			sql.append(", is_stopped");
			sql.append(") VALUES (");
			sql.append("?"); // login_id
			sql.append(", ?"); // password
			sql.append(", ?"); // name
			sql.append(", ?"); // branch_id
			sql.append(", ?"); // department_id
			sql.append(", ?"); // is_stopped
			sql.append(")");

			ps = connection.prepareStatement(sql.toString());

			ps.setString(1, user.getLoginID());
			ps.setString(2, user.getPassword());
			ps.setString(3, user.getName());
			ps.setInt(4, user.getBranchID());
			ps.setInt(5, user.getDepartmentID());
			ps.setBoolean(5, user.isStopped());
			ps.executeUpdate();
		} catch (SQLException e) {
			throw new SQLRuntimeException(e);
		} finally {
			close(ps);
		}
	}

	public void update(Connection connection, User user) {

		PreparedStatement ps = null;
		try {
			StringBuilder sql = new StringBuilder();
			sql.append("UPDATE users SET");
			sql.append("  login_id = ?");
			sql.append(", password = ?");
			sql.append(", name = ?");
			sql.append(", branch_id = ?");
			sql.append(", department_id = ?");
			sql.append(", is_stopped = ?");
			sql.append(" WHERE");
			sql.append(" id = ?");
			sql.append(" AND");
			sql.append(" update_date = ?");

			ps = connection.prepareStatement(sql.toString());

			ps.setString(1, user.getLoginID());
			ps.setString(2, user.getPassword());
			ps.setString(3, user.getName());
			ps.setInt(4, user.getBranchID());
			ps.setInt(5, user.getDepartmentID());
			ps.setBoolean(6, user.isStopped());
			ps.setInt(6, user.getId());
			ps.setTimestamp(7, new Timestamp(user.getUpdatedDate().getTime()));

			int count = ps.executeUpdate();
			if (count == 0) {
				throw new NoRowsUpdatedRuntimeException();
			}
		} catch (SQLException e) {
			throw new SQLRuntimeException(e);
		} finally {
			close(ps);
		}

	}

	public User getUser(Connection connection, int id) {

		PreparedStatement ps = null;
		try {
			String sql = "SELECT * FROM user WHERE id = ?";

			ps = connection.prepareStatement(sql);
			ps.setInt(1, id);

			ResultSet rs = ps.executeQuery();
			List<User> userList = toUserList(rs);
			if (userList.isEmpty() == true) {
				return null;
			} else if (2 <= userList.size()) {
				throw new IllegalStateException("2 <= userList.size()");
			} else {
				return userList.get(0);
			}
		} catch (SQLException e) {
			throw new SQLRuntimeException(e);
		} finally {
			close(ps);
		}
	}

	public User getUser(Connection connection, String accountOrEmail) {

		PreparedStatement ps = null;
		try {
			String sql = "SELECT * FROM user WHERE (account = ? OR email = ?)";

			ps = connection.prepareStatement(sql);
			ps.setString(1, accountOrEmail);
			ps.setString(2, accountOrEmail);

			ResultSet rs = ps.executeQuery();
			List<User> userList = toUserList(rs);
			if (userList.isEmpty() == true) {
				return null;
			} else if (2 <= userList.size()) {
				//ID重複
				throw new IllegalStateException("2 <= userList.size()");
			} else {
				return userList.get(0);
			}
		} catch (SQLException e) {
			throw new SQLRuntimeException(e);
		} finally {
			close(ps);
		}
	}

}
