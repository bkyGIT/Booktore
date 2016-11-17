package com.atguigu.bookstore.servlet;

import java.io.IOException;
import java.lang.reflect.Method;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class BaseServlet extends HttpServlet {
	
	protected void doGet(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		
		String methodName = request.getParameter("method");
		
		/*if ("regist".equals(methodName)) {
			regist(request, response);
		} else if ("login".equals(methodName)) {
			login(request, response);
		}*/
		
		
		try {
			/**
			 * getDeclaredMethod()用来获取指定类的方法的对象
			 * 需要两个参数：第一个是 String name 是方法的名字
			 * 		第二个参数Class parameterTypes 是方法的参数列表
			 */
			Method method = this.getClass().getDeclaredMethod(methodName, HttpServletRequest.class , HttpServletResponse.class);
			
			//设置一个访问权限
			method.setAccessible(true);
			
			//执行方法
			/**
			 * invoke方法用于执行一个方法
			 * 需要两个参数：
			 * 	第一个参数：Object obj 要调用方法的对象
			 * 第二个参数：Object ... args 调用方法时需要传递的参数
			 */
			method.invoke(this, request , response);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	protected void doPost(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}
