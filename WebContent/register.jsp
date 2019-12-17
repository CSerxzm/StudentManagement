<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE unspecified PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<title>学生信息管理系统</title>
		<link rel="stylesheet" type="text/css" href="css/login&register.css">
		<link rel="icon" type="image/x-ico" href="images/stu.ico">
	</head>
	<body>
		<!--Header-->
		<header>
			<nav>
				<ul>
					<a href="index.jsp"><li>首页</li></a>
					<a href="index.jsp"><li>登陆</li></a>
					<a href="register.jsp"><li>注册</li></a>
				</ul>
			</nav>
		</header>
			
		<!--Main-->
		<main>
			<div class="container">
				<form class="register_form" action="RegisterServlet" method="post">
					<h3>学生信息管理系统</h3>
					<p>注册</p>
					<input type="text" name="sno" value placeholder="学号" required="required">
					<input type="text" autofocus="autofocus" name="username" value placeholder="用户名" required="required">
					<input type="password" name="password" value placeholder="密码" required="required">
					<p>是否为教师</p>
					<span class="radio">
						<input type="radio" name="level" value="否" checked="checked"><span>否</span>
						<input type="radio" name="level" value="是"><span>是</span>
					</span>
					<input id="submit" type="submit" name="submit" value="提交">
				</form>
			</div>
		</main>

		<!--Footer-->
		<footer>
			<div class="copyright">
				&copy; Copyright. All rights reserved. Design by <a href="http://47.102.155.48:8080">xzm</a>
			</div>
		</footer>
	</body>
</html>