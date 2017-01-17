package billboard.beans;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

public class User extends Bean {
	private static final long serialVersionUID = 1L;

	private int id;
	private String loginID;
	private String password;
	private String name;
	private int branchID;
	private int departmentID;
	private boolean isStopped;
	private Date createdDate;
	private Date updatedDate;

	@Override
	public void readResultSet(ResultSet rs) {
		try {
			this.setId(rs.getInt("id"));
			this.setLoginID(rs.getString("login_ID"));
			this.setPassword(rs.getString("password"));
			this.setName(rs.getString("name"));
			this.setBranchID(rs.getInt("branch_id"));
			this.setDepartmentID(rs.getInt("department_id"));
			this.setStopped(rs.getBoolean("is_Stopped"));
			this.setCreatedDate(rs.getTimestamp("created_at"));
			this.setUpdatedDate(rs.getTimestamp("updated_at"));
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
	public String getLoginID() {
		return loginID;
	}
	public void setLoginID(String loginID) {
		this.loginID = loginID;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getBranchID() {
		return branchID;
	}
	public void setBranchID(int branchID) {
		this.branchID = branchID;
	}
	public int getDepartmentID() {
		return departmentID;
	}
	public void setDepartmentID(int departmentID) {
		this.departmentID = departmentID;
	}
	public boolean isStopped() {
		return isStopped;
	}
	public void setStopped(boolean isStopped) {
		this.isStopped = isStopped;
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

}
