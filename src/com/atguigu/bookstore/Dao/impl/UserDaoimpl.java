package com.atguigu.bookstore.Dao.impl;

import com.atguigu.bookstore.Dao.BaseDao;
import com.atguigu.bookstore.Dao.UserDao;
import com.atguigu.bookstore.bean.User;

public class UserDaoimpl extends BaseDao<User> implements UserDao {
	
	public User getByUsername(String username){
		String sql = "SELECT id , username , password ,email FROM bs_user WHERE username=?";
		return this.getBean(sql, username);
	}

	@Override
	public User getUserByUsernameAndPassword(User user) {
		
		// 创建一个SQL模板
		String sql = "SELECT id , username , password ,email FROM bs_user WHERE username=? AND password=?";

		return this.getBean(sql, user.getUsername(), user.getPassword());
	}

	@Override
	public int saveUser(User user) {
		
		// 创建一个SQL模板
		String sql = "INSERT INTO bs_user(username,password,email) VALUES(?,?,?)";
		
		return this.update(sql, user.getUsername(), user.getPassword(), user.getEmail());
	}

}
