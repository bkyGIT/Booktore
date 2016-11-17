package com.atguigu.bookstore.Test;

import org.junit.Test;

import com.atguigu.bookstore.Dao.UserDao;
import com.atguigu.bookstore.Dao.impl.UserDaoimpl;
import com.atguigu.bookstore.bean.User;

public class TestUserDao {
	
	//创建一个UserDao
	UserDao userdao = new UserDaoimpl();
	
	@Test
	public void testSaveUser() {
		
		//创建一个User对象
		User user = new User(null, "小岳岳", "123123", "ad@atguig.com");
		
		//调用dao将用户插入进数据库
		int count = userdao.saveUser(user);
		
		System.out.println(count);
		
	}
	
	
	@Test
	public void TestgetUser(){
		
		User user = new User(null, "小岳岳", "123123", null);
		
		User us = userdao.getUserByUsernameAndPassword(user);
		
		System.out.println(us);
		
	}
}
