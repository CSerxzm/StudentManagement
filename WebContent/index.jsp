<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE unspecified PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<title>学生信息管理系统</title>
		<link rel="stylesheet" type="text/css" href="css/login&register.css">
		<link rel="icon" type="image/x-ico" href="images/stu.ico">
	</head>
	<body>

		<header>
			<nav>
				<ul>
					<a href="#"><li>首页</li></a>
					<a href="index.jsp"><li>登陆</li></a>
					<a href="register.jsp"><li>注册</li></a>
				</ul>
			</nav>
		</header>
			
		<!--Main-->
		<main>
			<div class="container">
				<form  class="form" action="LoginServlet" method="post">
					<h3>学生信息管理系统</h3>
					<p>登陆</p>
					<input type="text" autofocus="autofocus" name="username" placeholder="输入用户名" required="required">
					<input type="password" name="password" placeholder="输入密码" required="required">
					<input id="submit" type="submit" name="submit" value="提交">
				</form>
			</div>
		</main>

		<footer>
			<div class="copyright">
				&copy; Copyright. All rights reserved. Design by <a href="http://47.102.155.48:8080">xzm</a>
			</div>
		</footer>
	</body>
</html>