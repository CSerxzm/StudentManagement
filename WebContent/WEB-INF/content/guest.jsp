<%@page import="model.User"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE HTML>
<html lang="zh">
<head>
	<meta charset="UTF-8">
	<title>学生操作界面</title>
	<link rel="stylesheet" type="text/css" href="css/user&admin.css">
	<link rel="icon" type="image/x-ico" href="images/stu.ico">
</head>
	
<body>
	<%
		//获取登录成功的用户信息
		User user = (User) session.getAttribute("student");
		//判断用户是否登录
		if(user != null){
	%>
	<header>
		<div class="title">
			<span>学生操作界面</span>
		</div>
		<nav>
			<div class="userinfo">
				<ul>
					<li><%=user.getUsername() %></li>
					<li><%=user.getLevel() %></li>
                    <li><%=user.getId() %></li>
					<li><a href="UserExitServlet">退出登录</a></li>
					<li><a href="index.jsp">返回首页</a></li>
				</ul>
			</div>
		</nav>
	</header>
	
	<main>
		<%
		}else{
			response.sendRedirect("login.html");
		}
		%>
		<div class="container">
			<div class="select">
				<h3>请选择操作</h3>
				<ul id="accordion" class="accordion">
					<li>
						<div class="link"></i>个人信息</div>
						<ul class="submenu">
							<li><a onclick="query_all('user')">查看个人信息</a></li>		
							<li><a onclick="show_alter('user')">更新个人信息</a></li>
							<li><a onclick="show_alter('password')">修  改  密  码</a></li>	
						</ul>
					</li>
					<li>
						<div class="link">教学班管理</div>
						<ul class="submenu">
							<li><a onclick="query_all('class')">查看班级</a></li>
							<li><a onclick="query_all('course')">查看课程 </a></li>
							<li><a onclick="query_all('select')">  选    课     </a></li>	
							<li><a onclick="show_alter('course')">  退    课     </a></li>
						</ul>
					</li>
					<li>
						<div class="link">学生成绩管理</div>
						<ul class="submenu">
							<li><a onclick="query_all('sc')">查看全部成绩</a></li>
							<li><a onclick="query_all('ranking')">查看成绩排名</a></li>
							<li><a onclick="query_all('average')">查看课程平均分</a></li>
						</ul>
					</li>
				</ul>
				</div>
	
				<div id="result" class="result">
					<p class="welcome">欢迎使用学生信息管理系统！</p>
				</div>
			</div>
		</div>
	</main>
	
	<footer>
		<div class="copyright">
			&copy; Copyright. All rights reserved. Design by <a href="http://47.102.155.48:8080">xzm</a>
		</div>
	</footer>

	<script src="js/jquery-3.1.1.min.js"></script>
	<script src="js/guest.js"></script>
</body>
</html>