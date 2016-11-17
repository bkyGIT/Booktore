<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>图书管理</title>
<%@ include file="/WEB-INF/include/base.jsp" %>
<script type="text/javascript">
	$(function(){
		$(".delete").click(function(){
			var title = $(this).prev(":hidden").val();
			var flag = confirm("确定要删除<<" + title + ">>的信息吗");
			if(flag){
				return true;
			}
			
			//取消默认行为.
			return false;
		});
	})
</script>
</head>
<body>
	
	<div id="header">
			<img class="logo_img" alt="" src="static/img/logo.gif" >
			<span class="wel_word">图书管理系统</span>
			<%@ include file="/WEB-INF/include/manager-info.jsp" %>
	</div>
	
	<div id="main">
	
		<c:if test="${empty books }">
			没有任何图书
		</c:if>
		
		<c:if test="${!empty books }">
	
		<table>
			<tr>
				<td>名称</td>
				<td>价格</td>
				<td>作者</td>
				<td>销量</td>
				<td>库存</td>
				<td colspan="2">操作</td>
			</tr>	
			
			<c:forEach items="${books }" var="book">
			<tr>
				<td>${book.title }</td>
				<td>${book.price }</td>
				<td>${book.author }</td>
				<td>${book.sales }</td>
				<td>${book.stock }</td>
				<td><a href="manager/BookServlet?id=${book.id }&method=input">修改</a></td>
				<td>
					<input type="hidden" value="${book.title }"/>
					<a class="delete" href="manager/BookServlet?id=${book.id }&method=delete">删除</a>
				</td>
			</tr>	
			</c:forEach>
			<tr>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
				<td><a href="pages/manager/book_edit.jsp">添加图书</a></td>
			</tr>	
		</table>
		</c:if>	
	</div>
	
	<div id="bottom">
		<span>
			尚硅谷书城.Copyright &copy;2015
		</span>
	</div>
</body>
</html>