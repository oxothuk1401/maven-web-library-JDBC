package by.htp.library.dao.pool;

import java.util.ResourceBundle;

public class DBResourceManager {
	private static final DBResourceManager instance = new DBResourceManager();
	private static final String DB = "db";

	private ResourceBundle bundle = ResourceBundle.getBundle(DB);

	public static DBResourceManager getInstance() {
		return instance;
	}

	public String getValue(String key) {
		return bundle.getString(key);
	}
}