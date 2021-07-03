package thrillio.dao;

import thrillio.DataStore;
import thrillio.entities.User;

public class UserDao {
	public User[] getUsers() {
		return DataStore.getUsers();
	}
}
