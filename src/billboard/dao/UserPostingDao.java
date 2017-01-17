package billboard.dao;

import billboard.beans.Bean;
import billboard.beans.UserPosting;

public class UserPostingDao extends Dao {

	public UserPostingDao() {
		super("users_postings");
	}

	@Override
	protected Bean makeNewBean() {
		return new UserPosting();
	}
}
