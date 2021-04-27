package com.nagarro.dao;

import com.nagarro.model.User;

public interface UserDao {
	public void saveUser(User user);
	public User getUser(String uId);
}
