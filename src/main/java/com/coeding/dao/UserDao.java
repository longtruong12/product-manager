package com.coeding.dao;

import java.util.HashMap;
import java.util.Map;

import com.coeding.config.SecurityConfig;
import com.coeding.model.User;


public class UserDao {
	private static final Map<String, User> mapUsers = new HashMap<String, User>();

	static {
		initUsers();
	}

	private static void initUsers() {

		// User này có 1 vai trò là EMPLOYEE.
		User emp = new User("employee1", "123", SecurityConfig.ROLE_EMPLOYEE);

		// User này có 2 vai trò EMPLOYEE và MANAGER.
		User mng = new User("manager1", "123", SecurityConfig.ROLE_EMPLOYEE, SecurityConfig.ROLE_MANAGER);

		mapUsers.put(emp.getEmail(), emp);
		mapUsers.put(mng.getEmail(), mng);
	}

	// Tìm kiếm người dùng theo userName và password.
	public static User findUser(String userName, String password) {
		User u = mapUsers.get(userName);
		if (u != null && u.getPassword().equals(password)) {
			return u;
		}
		return null;
	}
}
