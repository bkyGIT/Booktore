package com.atguigu.bookstore.service;

import java.util.List;

import com.atguigu.bookstore.Dao.BookDao;
import com.atguigu.bookstore.bean.Book;
import com.atguigu.bookstore.bean.Page;

public class BookService {
	
	private BookDao bookDao = new BookDao();
	
	public List<Book> getAll(){
		return bookDao.getAll();
	}
	
	public void saveBook(Book book){
		bookDao.save(book);
	}
	
	public Book get(Integer id){
		return bookDao.get(id);
	}
	
	public void delete(Integer id){
		bookDao.delete(id);
	}

	public Page<Book> findPage(Integer pageNo, Integer min, Integer max) {
		return bookDao.findPage(pageNo,min, max); 
	}
}
