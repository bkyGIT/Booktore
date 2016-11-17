package com.atguigu.bookstore.Dao;

import com.atguigu.bookstore.bean.User;

//定义User相关数据库的操作的接口
public interface UserDao {
	
	//根据用户名和密码查询用户
	User getUserByUsernameAndPassword(User user);
	
	//将一个用户添加进数据库
	int saveUser(User user);

}
