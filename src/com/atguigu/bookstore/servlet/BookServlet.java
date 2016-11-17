package com.atguigu.bookstore.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import javax.servlet.http.HttpSession;

import com.atguigu.bookstore.Utils.WEBUtils;
import com.atguigu.bookstore.bean.Book;
import com.atguigu.bookstore.bean.Page;
import com.atguigu.bookstore.bean.ShoppingCart;
import com.atguigu.bookstore.service.BookService;


public class BookServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;
   
	private BookService bookService = new BookService();
	
	protected void add2Cart(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		//获取购物车
		ShoppingCart cart = getShoppingCart(request);
		
		//获取请求参数 id
		Integer bookId = Integer.parseInt(request.getParameter("id"));
		
		//把图书加入到购物车中
		Book book = bookService.get(bookId);
		cart.add2Cart(book);
		
		
		//返回 ShoppingCart 对应的 JSON 数据
		ObjectMapper mapper = new ObjectMapper();
		String result = mapper.writeValueAsString(cart);
		response.getWriter().print(result);
	}
	
	
	
	private ShoppingCart getShoppingCart(HttpServletRequest request) {
		HttpSession session = request.getSession();
		ShoppingCart cart = (ShoppingCart) session.getAttribute("SHOPPING_CART");
		if(cart == null){
			cart = new ShoppingCart();
			session.setAttribute("SHOPPING_CART" , cart);
		}
		return cart;
	}



	protected void list(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//Servlet 中调用 Service 方法得到 Book 的 List. 
		List<Book> books = bookService.getAll();
	
		//把上述的 List 加入到 request 中.
		request.setAttribute("books", books);
		
		//转发页面.
		request.getRequestDispatcher("/pages/manager/book_manager.jsp").forward(request, response);
	}
	
	protected void add(HttpServletRequest request , HttpServletResponse response) throws Exception{
		String pageNoStr = request.getParameter("pageNo");
		Integer pageNo = 1;
		try {
			pageNo = Integer.parseInt(pageNoStr);
		} catch (Exception e) {
			//若没有输入任何页码，则应该显示最后一页
			pageNo = Integer.MAX_VALUE;
		}
		
		Book book = new Book();
		WEBUtils.param2Bean(request, book);
		System.out.println(book);
		bookService.saveBook(book);
		String cotextPath = request.getContextPath();
		response.sendRedirect(cotextPath + "/manager/BookServlet?method=list");
	}
	
	protected void input(HttpServletRequest request , HttpServletResponse response) throws Exception, IOException{
		String idstr = request.getParameter("id");
		System.out.println(idstr);
		Book book = bookService.get(Integer.parseInt(idstr));
		System.out.println("input"+book);
		request.setAttribute("book", book);
		request.getRequestDispatcher("/pages/manager/book_edit.jsp").forward(request, response);
	}
	
	
	protected void delete(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
		String pageNoStr = request.getParameter("pageNo");
		Integer pageNo = 1;
		try {
			pageNo = Integer.parseInt(pageNoStr);
		} catch (Exception e) {}
		
		String idStr = request.getParameter("id");
		Integer id = Integer.parseInt(idStr);
		
		bookService.delete(id);
		
		String contextPath = request.getContextPath();
		response.sendRedirect(contextPath + "/manager/BookServlet?method=getPage&pageNo=" + pageNo);
	}
	
	
	protected void listBooks(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		//0. 获取 min 和 max
		Integer min = null; 
		Integer max = null;
		
		try {
			min = Integer.parseInt(request.getParameter("min"));
		} catch (Exception e) {}
		try {
			max = Integer.parseInt(request.getParameter("max"));
		} catch (Exception e) {}
		
		//1. 获取 pageNo
		String pageNoStr = request.getParameter("pageNo");
		Integer pageNo = 1;
		try {
			pageNo = Integer.parseInt(pageNoStr);
		} catch (Exception e) {}
		
		//2. 调用 Service 方法来获取 pageNo 对应的 Page 对象
		Page<Book> page = bookService.findPage(pageNo, min, max);
		
		//3. 把 page 放入到 request 中
		request.setAttribute("page", page);
		
		//4. 转发页面
		request.getRequestDispatcher("/pages/book/book.jsp").forward(request, response);
	}
	
	protected void getPage(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		//0. 获取 min 和 max
		Integer min = null; 
		Integer max = null;
		
		try {
			min = Integer.parseInt(request.getParameter("min"));
		} catch (Exception e) {}
		try {
			max = Integer.parseInt(request.getParameter("max"));
		} catch (Exception e) {}
		
		//1. 获取 pageNo
		String pageNoStr = request.getParameter("pageNo");
		Integer pageNo = 1;
		try {
			pageNo = Integer.parseInt(pageNoStr);
		} catch (Exception e) {}
		
		//2. 调用 Service 方法来获取 pageNo 对应的 Page 对象
		Page<Book> page = bookService.findPage(pageNo, min, max);
		
		//3. 把 page 放入到 request 中
		request.setAttribute("page", page);
		
		//4. 转发页面
		request.getRequestDispatcher("/pages/manager/book_page.jsp").forward(request, response);
	}
}
