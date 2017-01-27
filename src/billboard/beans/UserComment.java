package billboard.beans;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

public class UserComment extends Bean {

	private int id;
	private String text;
	private String[] textLines;
	private int userId;
	private int postingId;
	private String userName;
	private int userDepartmentId;
	private int userBranchId;
	private Date createdDate;
	public int getUserDepartmentId() {
		return userDepartmentId;
	}

	public void setUserDepartmentId(int userDepartmentId) {
		this.userDepartmentId = userDepartmentId;
	}

	public int getUserBranchId() {
		return userBranchId;
	}

	public void setUserBranchId(int userBranchId) {
		this.userBranchId = userBranchId;
	}

	private Date updatedDate;

	@Override
	public void readResultSet(ResultSet rs) {
		try {
			this.setId(rs.getInt("id"));
			this.setText(rs.getString("text"));
			setTextLines(text.split(System.getProperty("line.separator")));
			this.setUserId(rs.getInt("user_id"));
			this.setPostingId(rs.getInt("posting_id"));
			this.setUserName(rs.getString("user_name"));
			this.setUserBranchId(rs.getInt("user_branch_id"));
			this.setUserDepartmentId(rs.getInt("user_department_id"));
			this.setCreatedDate(rs.getTimestamp ("created_at"));
			this.setUpdatedDate(rs.getTimestamp ("updated_at"));
		} catch (SQLException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public int getPostingId() {
		return postingId;
	}
	public void setPostingId(int postingId) {
		this.postingId = postingId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public Date getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}
	public Date getUpdatedDate() {
		return updatedDate;
	}
	public void setUpdatedDate(Date updatedDate) {
		this.updatedDate = updatedDate;
	}

	public String[] getTextLines() {
		return textLines;
	}

	public void setTextLines(String[] textLines) {
		this.textLines = textLines;
	}

}
