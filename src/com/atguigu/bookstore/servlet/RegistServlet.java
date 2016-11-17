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
 * Servlet implementation class RegistServlet
 */
public class RegistServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private UserServce userService = new UserServiceImpl();
   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String name = request.getParameter("username");
		String password = request.getParameter("password");
		String email = request.getParameter("email");
		
		//将信息封装为对象
		User user = new User(null, name, password, email);
		
		//调用service注册用户
		boolean isRegist = userService.regist(user);
		
		//判断是否注册成功
		if(isRegist){
			//注册成功，重定向到regist_success.html
			response.sendRedirect(request.getContextPath()+"/pages/user/regist_success.jsp");
		}else{
			request.setAttribute("msg", "用户名已存在！");
			
			//注册失败，转发到regist.html
			request.getRequestDispatcher("/pages/user/regist.jsp").forward(request, response);
		}
	}

}
