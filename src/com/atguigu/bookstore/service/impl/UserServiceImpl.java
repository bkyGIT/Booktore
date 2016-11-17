package com.atguigu.bookstore.service.impl;


import com.atguigu.bookstore.Dao.impl.UserDaoimpl;
import com.atguigu.bookstore.bean.User;
import com.atguigu.bookstore.service.UserServce;


public class UserServiceImpl implements UserServce{
	
	private UserDaoimpl userDao = new UserDaoimpl();
	
	public User getByUsername(String username){
		return userDao.getByUsername(username);
	}
	
	public User login(User user){
		return userDao.getUserByUsernameAndPassword(user);
	}
	
	public boolean regist(User user){
		
		//调用dao存储用户
		int count = userDao.saveUser(user);
		
		return count == 1;
	}
}
