package com.atguigu.bookstore.service;

import com.atguigu.bookstore.bean.User;

public interface UserServce {
	
	/**
	 * 处理登录业务的方法
	 * 会返回一个User对象
	 * 如果对象不为空则登录成功，否则失败
	 */
	User login(User user);
	
	/**
	 * 处理注册业务的方法
	 * 如果成功，则返回true，否则返回false
	 */
	boolean regist(User user);
}
