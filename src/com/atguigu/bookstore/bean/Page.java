package com.atguigu.bookstore.bean;

import java.util.ArrayList;
import java.util.List;

public class Page<T> {
	//总的记录数
	private long totalElements;
	
	//每页显示多少条记录
	private int pageSize = 4;
	
	//当前页面的 List
	private List<T> content = new ArrayList<>();
	
	//当前页码. 
	private int pageNo;
	
	//判断是否有下一页
	public boolean isHasNext(){
		if(this.pageNo < getTotalPages()){
			return true;
		}
		
		return false;
	}
	
	//判断是否有上一页
	public boolean isHasPrev(){
		if(this.pageNo > 1){
			return true;
		}
		
		return false;
	}
	
	//总的页码：可以根据 总的记录数 和 每页显示的记录数 来计算. 
	public int getTotalPages(){
		int totalPages = (int)(totalElements / pageSize);
		
		if(totalElements % pageSize != 0){
			totalPages++;
		}
		
		return totalPages;
	}
	
	//需要从前台页面传进来。 
	public void setPageNo(int pageNo) {
		//传入的页码数，必须大于 0
		if(pageNo < 1){
			pageNo = 1;
		}
		this.pageNo = pageNo;
	}
	
	//调用 EL 时需要使用的 getter 方法.
	public int getPageNo() {
		return pageNo;
	}
	
	//设置总的记录数. 需要程序员完成查询，并设置总的记录数
	public void setTotalElements(long totalElements) {
		this.totalElements = totalElements;
		
		//检查 pageNo 的合法性
		if(pageNo > getTotalPages()){
			this.pageNo = getTotalPages();
		}
	}
	
	public long getTotalElements() {
		return totalElements;
	}
	
	//查询当前页码对应的 List，需要程序员完成查询，并进行设置
	public void setContent(List<T> content) {
		this.content = content;
	}
	
	public List<T> getContent() {
		return content;
	}
	
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	
	public int getPageSize() {
		return pageSize;
	}
}
