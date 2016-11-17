package com.atguigu.bookstore.Test;

import java.sql.Connection;

import org.junit.Test;

import com.atguigu.bookstore.Utils.JDBCUtils;

public class TestJDBCUtils {
	
	@Test
	public void TestJDBC(){
		Connection conn = JDBCUtils.getConnection();
		
		System.out.println(conn);
		
		JDBCUtils.closeConnection(conn);
	}
}