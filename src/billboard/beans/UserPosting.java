package billboard.beans;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

public class UserPosting extends Bean {

	private int id;
	private String title;
	private String text;
	private String[] textLines;
	private String category;
	private int userId;
	private String userName;
	private int userDepartmentId;
	private int userBranchId;
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

	private Date createdDate;
	private Date updatedDate;

	@Override
	public void readResultSet(ResultSet rs) {
		try {
			this.setId(rs.getInt("id"));
			this.setTitle(rs.getString("title"));
			this.setText(rs.getString("text"));
			setTextLines(text.split(System.getProperty("line.separator")));
			this.setCategory(rs.getString("category"));
			this.setUserId(rs.getInt("user_id"));
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
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
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
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String[] getTextLines() {
		return textLines;
	}

	public void setTextLines(String[] textLines) {
		this.textLines = textLines;
	}
}
