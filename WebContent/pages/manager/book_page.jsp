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
		
		$("#pageNo").change(function(){
			var val = this.value;
			val = $.trim(val);
			
			//利用正则表达式验证 val 的合法性. 
			var reg = /^\d+$/g;
			if(!reg.test(val)){
				alert("输入的页码不合法!");
				this.value = "";
				return;
			}
			
			//Javascript 中也有 parseInt 函数，可以把一个字符串转为数值型. 
			var pageNo = parseInt(val);
			if(pageNo < 1 || pageNo > parseInt("${page.totalPages}")){
				alert("输入的页码不合法!");
				this.value = "";
				return;
			}
			
			window.location.href = "manager/BookServlet?method=getPage&pageNo=" + pageNo;
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
	
		<c:if test="${empty page.content }">
			没有任何图书
		</c:if>
		
		<c:if test="${!empty page.content }">
	
		<table>
			<tr>
				<td>名称</td>
				<td>价格</td>
				<td>作者</td>
				<td>销量</td>
				<td>库存</td>
				<td colspan="2">操作</td>
			</tr>	
			
			<c:forEach items="${page.content }" var="book">
			<tr>
				<td>${book.title }</td>
				<td>${book.price }</td>
				<td>${book.author }</td>
				<td>${book.sales }</td>
				<td>${book.stock }</td>
				<td><a href="manager/BookServlet?id=${book.id }&method=input&pageNo=${page.pageNo}">修改</a></td>
				<td>
					<input type="hidden" value="${book.title }"/>
					<a class="delete" href="manager/BookServlet?id=${book.id }&method=delete&pageNo=${page.pageNo}">删除</a>
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
		<br>
		
		<div id="page_nav">
			当前第 ${page.pageNo } 页 / 共 ${page.totalPages } 页.
			&nbsp;&nbsp;
			共 ${page.totalElements } 条记录
			&nbsp;&nbsp;
			
			<c:if test="${page.hasPrev }">
			<a href="manager/BookServlet?method=getPage&pageNo=1">首页</a>
			<a href="manager/BookServlet?method=getPage&pageNo=${page.pageNo - 1 }">上一页</a>
			</c:if>
			
			<c:if test="${page.hasNext }">
			<a href="manager/BookServlet?method=getPage&pageNo=${page.pageNo + 1 }">下一页</a>
			<a href="manager/BookServlet?method=getPage&pageNo=${page.totalPages }">末页</a>
			</c:if>
			
			&nbsp;&nbsp;
			转到 <input type="text" size="2" id="pageNo"/> 页.
		</div>
	</div>
	
	
	<div id="bottom">
		<span>
			尚硅谷书城.Copyright &copy;2015
		</span>
	</div>
</body>
</html>