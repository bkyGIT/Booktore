package com.atguigu.bookstore.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.atguigu.bookstore.Utils.WEBUtils;
import com.atguigu.bookstore.bean.User;
import com.atguigu.bookstore.service.impl.UserServiceImpl;

public class UserServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;

	//创建一个UserService
	private UserServiceImpl userService = new UserServiceImpl();
	
	protected void valiateUsername(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String username = request.getParameter("username");
		User user = userService.getByUsername(username);
		
		if(user == null){
			response.getWriter().print("1");
		} else {
			response.getWriter().print("0");
		}
	}
	
	protected void logout(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		session.invalidate();
		response.sendRedirect(getServletContext().getContextPath() + "/index.jsp");
	}

	protected void login(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
	/*	Map map = request.getParameterMap();
		
		User user = new User();
		
		try {
			BeanUtils.populate(user, map);
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}*/
		
		User user = WEBUtils.param2Bean(request , new User());
		
		// 获取用户填写的用户名和密码
		/*String name = request.getParameter("username");
		String password = request.getParameter("password");
		// 将信息封装进对象
		User user = new User(null, name, password, null);*/
		// 调用servlet去处理登录业务
		User loginUser = userService.login(user);

		// 判断loginUser是否为空
		if (loginUser == null) {
			// 设置一个错误信息
			request.setAttribute("msg", "用户名或密码错误");

			request.getRequestDispatcher("/pages/user/login.jsp").forward(request, response);
		} else {
			//获取HttpSession对象
			HttpSession session = request.getSession();
			//将User存入HttpSession中
			session.setAttribute("user", loginUser);
			//登录成功，重定向到login_success.html
			response.sendRedirect(request.getContextPath()+"/pages/user/login_success.jsp");
		}
	}

	protected void regist(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		String c = (String)session.getAttribute(com.google.code.kaptcha.Constants.KAPTCHA_SESSION_KEY);
		String parm = (String) request.getParameter("kaptchafield");
		
		if (!c.equals(parm)) {
			//设置一个错误消息
			request.setAttribute("msg", "验证码错误");
			
			//注册失败，转发到regist.html
			request.getRequestDispatcher("/pages/user/regist.jsp").forward(request, response);
			return ;
			
		}

		/*String name = request.getParameter("username");
		String password = request.getParameter("password");
		String email = request.getParameter("email");

		// 将信息封装为对象
		User user = new User(null, name, password, email);*/
		
		User user = WEBUtils.param2Bean(request , new User());
		
		System.out.println(user);

		// 调用service注册用户
		boolean isRegist = userService.regist(user);

		// 判断是否注册成功
		if (isRegist) {
			// 注册成功，重定向到regist_success.html
			response.sendRedirect(request.getContextPath()+ "/pages/user/regist_success.jsp");
		} else {
			request.setAttribute("msg", "用户名已存在！");

			// 注册失败，转发到regist.html
			request.getRequestDispatcher("/pages/user/regist.jsp").forward(request, response);
		}
	}

}
