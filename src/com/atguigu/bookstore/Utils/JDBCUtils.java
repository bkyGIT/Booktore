package com.atguigu.bookstore.Utils;

import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

import com.mchange.v2.c3p0.ComboPooledDataSource;


//操作数据库连接的工具类
public class JDBCUtils {
	
	//获取数据源
	private static DataSource source = new ComboPooledDataSource();
	
	//获取数据库连接的方法
	public static Connection getConnection(){
		
		//创建一个数据连接
		Connection conn = null;
		
		try {
			conn = source.getConnection();
		} catch (SQLException e) {
			e.printStackTrace();
		} 
		return conn;
	}
	
	
	//释放数据库连接的方法
	public static void closeConnection(Connection conn){
		if(conn != null){
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}
