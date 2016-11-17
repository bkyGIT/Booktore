package com.atguigu.bookstore.Dao;

import java.util.List;

import com.atguigu.bookstore.bean.Book;
import com.atguigu.bookstore.bean.Page;

public class BookDao extends BaseDao<Book>{

	public List<Book> getAll(){
		String sql = "SELECT id, title, author, price, sales, stock, img_path imgPath FROM bs_book";
		return getBeanList(sql);
	}
	
	public void save(Book book){
		String sql = null;
		
		if(book.getId() != null){
			sql = "UPDATE bs_book SET title = ?, author = ?, price = ?, sales = ?, stock = ? "
					+ " WHERE id = ?";
			update(sql, book.getTitle(), book.getAuthor(), book.getPrice(), book.getSales(), book.getStock(), book.getId());
		}else{
			sql = "INSERT INTO bs_book(title, author, price, sales, stock, img_path) "
					+ " VALUES(?,?,?,?,?,?)";
			update(sql, book.getTitle(), book.getAuthor(), book.getPrice(), book.getSales(), book.getStock(), "/static/img/default.jpg");
		}
	}
	
	public Book get(Integer id){
		
		String sql = "SELECT id , title , author , price , sales , stock , img_path imgPath FROM bs_book WHERE id = ?";
		
		return getBean(sql, id);
	}
	
	public void delete(Integer id){
		String sql = "DELETE FROM bs_book WHERE id=?";
		update(sql, id);
	}
	
	public Page<Book> findPage(Integer pageNo , Integer min , Integer max){
		if(min == null){
			min = 0;
		}
		if(max == null){
			max = Integer.MAX_VALUE;
		}
		
		//1. 创建 Page 对象
		Page<Book> page = new Page<>();
		
		//2. 为 Page 对象设置 pageNo
		page.setPageNo(pageNo);
		
		//3. 查询总的记录数
		String sql = "SELECT count(id) FROM bs_book WHERE price>=? AND price<=?";
		long totalElements = getSingleValue(sql, min, max);
		
		//4. 为 Page 对象设置总的记录数
		/*page.setTotalElements(totalElements);*/
		page.setTotalElements(totalElements);
		
		//5. 查询当前页面的 content
		sql = "SELECT id , title , author , price , sales , stock , img_path imgPath"
				+" FROM bs_book"
				+" WHERE price>=? AND price<=?"
				+" LIMIT ?,?";
		
		int fromIndex = (page.getPageNo() - 1) * page.getPageSize(); //0, 4, 8
		int maxResult = page.getPageSize();
		List<Book> content = getBeanList(sql, min, max, fromIndex, maxResult);
		
		//6. 设置 Page 对象的 content
		page.setContent(content);
		
		//7. 返回 Page
		return page;
	}
}
