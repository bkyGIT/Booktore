package com.atguigu.bookstore.Dao;

import java.lang.reflect.ParameterizedType;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import com.atguigu.bookstore.Utils.JDBCUtils;

public class BaseDao<T> {
	
	//获取QueryRunner
	private QueryRunner run = new QueryRunner();
	
	//定义泛型的类型
	private Class<T> type;
	
	
	public BaseDao(){
		ParameterizedType pt = (ParameterizedType) this.getClass().getGenericSuperclass();
		//获取泛型
		type = (Class<T>) pt.getActualTypeArguments()[0];
	}
	
	
	//查询一组对象
	public List<T> getBeanList(String sql , Object...params){
		List<T> list = null;
		Connection conn = null;
		
		conn = JDBCUtils.getConnection();
		try {
			list = run.query(conn, sql, new BeanListHandler<T>(type), params);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCUtils.closeConnection(conn);
		}
		return list;
	}
	
	
	
	//查询一个对象的方法
	public T getBean(String sql , Object...params){
		T t = null;
		
		Connection conn = JDBCUtils.getConnection();
		
		try {
			t = run.query(conn, sql, new BeanHandler<T>(type), params);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			JDBCUtils.closeConnection(conn);
		}
		return t;
	}
	
	
	
	//用于执行增删改的数据库操作，返回的影响的行数
	public int update(String sql , Object...params){
		int count = 0;
		Connection conn = null;
		
		conn = JDBCUtils.getConnection();
		try {
			count = run.update(conn, sql, params);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			JDBCUtils.closeConnection(conn);
		}
		return count;
	}
	
	
	//返回一个指定的字段值或统计函数的值
	public <E>E getSingleValue(String sql , Object...params){
		
		Connection conn = null;
		conn = JDBCUtils.getConnection();
		//执行查询
		try {
			ResultSetHandler rh = new ScalarHandler();
			return (E)run.query(conn, sql, rh, params);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			JDBCUtils.closeConnection(conn);
		}
		return null;
	} 
	
	
}
