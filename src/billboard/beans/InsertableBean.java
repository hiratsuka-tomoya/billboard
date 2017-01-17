package billboard.beans;

import java.sql.PreparedStatement;

public abstract class InsertableBean extends Bean {

	public abstract void setPrepareStatement(PreparedStatement ps);

}
