package com.nagarro.daoImp;

import org.springframework.beans.factory.annotation.Autowired;

import com.nagarro.dao.Dao;
import com.nagarro.dao.UserDao;
import com.nagarro.model.User;

public class UserDaoImp implements UserDao {

	@Autowired
	private Dao dao;

	public void saveUser(User user) {
		
		dao.begin();
		dao.getSession().save(user);
		dao.commit();
		dao.close();

	}

	public User getUser(String uId) {

		dao.begin();
		User user = (User) dao.getSession().get(User.class, uId);
		dao.commit();
		dao.close();

		return user;
	}
	
}