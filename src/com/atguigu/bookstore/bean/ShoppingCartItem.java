package com.atguigu.bookstore.bean;

public class ShoppingCartItem {
	
	private Book book ;
	private int bookcount;
	
	public Book getBook() {
		return book;
	}
	public void setBook(Book book) {
		this.book = book;
	}
	public int getBookcount() {
		return bookcount;
	}
	public void setBookcount(int bookcount) {
		this.bookcount = bookcount;
	}
	
	//返回当前购物项的钱数
	public double getItemMoney(){
		double result = book.getPrice() * bookcount;
		result = Math.round(result*100);
		return result / 100d;
	}
	
	
	//数量 + 1
	public void bookCountIncrement(){
		this.bookcount++;
	}
}
