<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>尚硅谷会员注册页面</title>
<%@ include file="/WEB-INF/include/base.jsp" %>
<style type="text/css">
	.login_form{
		height:420px;
		margin-top: 25px;
	}
	
</style>

<script type="text/javascript">
	$(function(){
		//为 name="username" 的 input 节点添加 change 响应函数
		$("input[name='username']").change(function(){
			var val = this.value;
			val = $.trim(val);
			
			if(val != ""){
				var url = "UserServlet?method=valiateUsername";
				var args = {"username":val};
				
				$.post(url , args , function(data){
					if(data == "1"){
						$(".errorMsg").empty();
					} else if(data == "0"){
						$(".errorMsg").text("用户名不可用!");
					}
				});
			}
		});
		
		$('#kaptchaImage').click(function () { $(this).attr('src', '${pageContext.request.contextPath}/Kaptcha.jpg?' + Math.floor(Math.random()*100) ); })
		//为注册按钮绑定单击响应函数
		$("#sub_btn").click(function(){
			//获取用户名等输入信息
			var name = $("[name=username]").val();
			var password = $("[name=password]").val();
			var repwd = $("[name=repwd]").val();
			var email = $("[name=email]").val();
			var code = $("[name=code]").val();
			
			//用正则表达式检测用户输入是否正确(jqueryAPI-->正则表达式)
			var nameReg = /^[A-Za-z0-9_-]{3,16}$/;
			if(!nameReg.test(name)){
				alert("用户名输入有误");
				return false;
			}
			
			var passwordReg = /^[a-z0-9_-]{6,18}$/;
			if(!passwordReg.test(password)){
				alert("密码输入有误");
				return false;
			}
			
		    if(repwd != password){
				alert("两次密码输入不一致");
				return false;
			}
			
			var emailReg = /^([a-z0-9_\.-]+)@([\da-z\.-]+)\.([a-z\.]{2,6})$/;
			if(!emailReg.test(email)){
				alert("邮箱输入不正确");
				return false;
			}
			
			if(code == ""){
				alert("验证码不能为空");
				return false;
			}
			
		});
	});
</script>
</head>
<body>
		<div id="login_header">
			<img class="logo_img" alt="" src="static/img/logo.gif" >
		</div>
		
			<div class="login_banner">
			
				<div id="l_content">
					<span class="login_word">欢迎注册</span>
				</div>
				
				<div id="content">
					<div class="login_form">
						<div class="login_box">
							<div class="tit">
								<h1>注册尚硅谷会员</h1>
								<span class="errorMsg">${msg }</span>
							</div>
							<div class="form">
								<form action="UserServlet?method=regist" method="post">
									<label>用户名称：</label>
									<input value="${param.username }" class="itxt" type="text" placeholder="请输入用户名" autocomplete="off" tabindex="1" name="username" />
									<br />
									<br />
									<label>用户密码：</label>
									<input class="itxt" type="password" placeholder="请输入密码" autocomplete="off" tabindex="1" name="password" />
									<br />
									<br />
									<label>确认密码：</label>
									<input class="itxt" type="password" placeholder="确认密码" autocomplete="off" tabindex="1" name="repwd" />
									<br />
									<br />
									<label>电子邮件：</label>
									<input value="${param.email }" class="itxt" type="text" placeholder="请输入邮箱地址" autocomplete="off" tabindex="1" name="email" />
									<br />
									<br />
									<label>验证码：</label>
									<input class="itxt" type="text" style="width: 150px;" name="kaptchafield"/>
									<img id="kaptchaImage" alt="" src="Kaptcha.jpg" style="float: right; margin-right: 40px">									
									<br />
									<br />
									<input type="submit" value="注册" id="sub_btn" />
									
								</form>
							</div>
							
						</div>
					</div>
				</div>
			</div>
		<div id="bottom">
			<span>
				尚硅谷书城.Copyright &copy;2015
			</span>
		</div>
</body>
</html>