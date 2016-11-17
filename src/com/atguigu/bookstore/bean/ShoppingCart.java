package com.atguigu.bookstore.bean;

import java.util.HashMap;
import java.util.Map;

public class ShoppingCart {
	
	//键: Book 的 Id， 值：ShoppingCartItem 对象
	//使用 Map 是为了更方便的定位到对应的商品. 
	private Map<Integer , ShoppingCartItem> items = new HashMap<>();
	
	public Map<Integer , ShoppingCartItem> getItems(){
		return items;
	}
	
	//返回总的金额
	public double getTotalMoney(){
		double totalMoney = 0d;
		for(ShoppingCartItem item : items.values()){
			totalMoney += item.getItemMoney();
		}
		return Math.round(totalMoney * 100)/100d ;
	}
	
	//返回总的数量
	public int getTotalCount(){
		int totalCount = 0;
		for(ShoppingCartItem item : items.values()){
			totalCount += item.getBookcount();
		}
		return totalCount;
	}
	
	//删除指定购物项
	public void removeItem(Integer bookId){
		this.items.remove(bookId);
	}
	
	//修改一个购物项的数量
	public void setItemCount(Integer bookId , int itemCount){
		items.get(bookId).setBookcount(itemCount);
	}
	
	//添加一个购物项
	public void add2Cart(Book book){
		//若购物车中已经有了对应的购物项, 则其数量 + 1
		if(items.containsKey(book.getId())){
			items.get(book.getId()).bookCountIncrement();
		} else {
			ShoppingCartItem item = new ShoppingCartItem();
			item.setBook(book);
			item.setBookcount(1);
			
			items.put(book.getId(), item);
		}
	}
	
	//判断当前购物车是否为空
	public boolean isEmpty(){
		return this.items.isEmpty();
	}
	
}
