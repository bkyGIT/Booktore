<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>书城首页</title>
<%@ include file="/WEB-INF/include/base.jsp" %>

<script type="text/javascript">
	$(function(){
		$("#cartInfo").hide();
		
		$(".book_add button").click(function(){
			var bookId = $(this).next(":hidden").val();
			var bookTitle = $(this).prev(":hidden").val();
			alert("1");
			
			var url = "${pageContext.request.contextPath}/manager/BookServlet";
			var args = {"method":"add2Cart" , "id":bookId , "time":new Date()};
			
			$.post(url , args , function(data){
				$("#cartInfo").show();
				$("#bookCount").text(data.totalCount);
				$("#bookTitle").text(bookTitle);
				//更新 "购物车" 字体的大小
				$("#shoppingCartA").html("<font color='red' size='30'>购物车</font>");
			},"json");
		});
		
	
		
		$("#query").click(function(){
			var min = $("#min").val();
			var max = $("#max").val();
			
			var url = "${pageContext.request.contextPath}/manager/BookServlet?method=listBooks&min=" + min + "&max=" + max;
			//浏览器的地址栏
			window.location.href = url;
		});
		
		$("#page_nav a").each(function(){
			this.href = this.href + "&min=${param.min}&max=${param.max}";
		});
	})
</script>

</head>
<body>
	
	<div id="header">
			<img class="logo_img" alt="" src="static/img/logo.gif" >
			<span class="wel_word">网上书城</span>
			<%@ include file="/WEB-INF/include/user-info.jsp" %>
	</div>
	
	<div id="main">
		<div id="book">
			<div class="book_cond">
				价格：<input type="text" name="min" id="min"> 元 - <input type="text" name="max" id="max"> 元 <button id="query">查询</button>
			</div>
			<div style="text-align: center" id="cartInfo">
				<span>
					您的购物车中有<span style="color: red" id="bookCount"></span>件商品
				</span>
				<div>
					您刚刚将<span style="color: red" id="bookTitle"></span>加入到了购物车中
				</div>
			</div>
		<c:forEach items="${page.content }" var="book">
			<div class="b_list">
				<div class="img_div">
					<img class="book_img" alt="" src="${pageContext.request.contextPath }${book.imgPath }" />
				</div>
				<div class="book_info">
					<div class="book_name">
						<span class="sp1">书名:</span>
						<span class="sp2">${book.title }</span>
					</div>
					<div class="book_author">
						<span class="sp1">作者:</span>
						<span class="sp2">${book.author }</span>
					</div>
					<div class="book_price">
						<span class="sp1">价格:</span>
						<span class="sp2">￥${book.price }</span>
					</div>
					<div class="book_sales">
						<span class="sp1">销量:</span>
						<span class="sp2">${book.sales }</span>
					</div>
					<div class="book_amount">
						<span class="sp1">库存:</span>
						<span class="sp2">${book.stock }</span>
					</div>
					<div class="book_add">
						<button>加入购物车</button>
					</div>
				</div>
			</div>
		</c:forEach>
		
			<div id="page_nav">
				当前第 ${page.pageNo } 页 / 共 ${page.totalPages } 页.
				&nbsp;&nbsp;
				共 ${page.totalElements } 条记录
				&nbsp;&nbsp;
				
				<c:if test="${page.hasPrev }">
				<a href="manager/BookServlet?method=listBooks&pageNo=1">首页</a>
				<a href="manager/BookServlet?method=listBooks&pageNo=${page.pageNo - 1 }">上一页</a>
				</c:if>
				
				<c:if test="${page.hasNext }">
				<a href="manager/BookServlet?method=listBooks&pageNo=${page.pageNo + 1 }">下一页</a>
				<a href="manager/BookServlet?method=listBooks&pageNo=${page.totalPages }">末页</a>
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