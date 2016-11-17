package com.atguigu.bookstore.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.atguigu.bookstore.bean.User;
import com.atguigu.bookstore.service.UserServce;
import com.atguigu.bookstore.service.impl.UserServiceImpl;

/**
 * Servlet implementation class loginServlet
 */
public class loginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private UserServce userService = new UserServiceImpl();
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//获取用户填写的用户名和密码
		String name = request.getParameter("username");
		String password = request.getParameter("password");
		
		//将信息封装进对象
		User user = new User(null , name , password , null);
		
		//调用servlet去处理登录业务
		User loginUser = userService.login(user);
		
		//判断loginUser是否为空
		if(loginUser == null){
			//设置一个错误信息
			request.setAttribute("msg", "用户名或密码错误");
			
			request.getRequestDispatcher("/pages/user/login.jsp").forward(request, response);
		}else{
			response.sendRedirect(request.getContextPath()+"/pages/user/login_success.jsp");
		}
	}

}
