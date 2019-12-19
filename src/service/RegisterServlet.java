package service;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.UserDao;
import model.User;

public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		int sno = Integer.valueOf(request.getParameter("sno"));
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String level = request.getParameter("level");
		//实例化UserDao对象
		UserDao userDao = new UserDao();
		if (level.equals("学生")) {
			User user = userDao.register(sno,username, password,level);
			if(user!=null) {
				request.getSession().setAttribute("student", user);//将用户对象放到session中
				//转发到user.jsp中
				request.getRequestDispatcher("/WEB-INF/content/guest.jsp").forward(request, response);				
			}else {
				request.setAttribute("info"," 错误:已存在该用户,不能重复注册！");
				request.getRequestDispatcher("/WEB-INF/content/message.jsp").forward(request, response);
			}
		}else {
			if(sno==999) {
				//邀请码
				User user = userDao.register(username, password,level);
				if(user!=null) {
					request.getSession().setAttribute("admin", user);//将用户对象放到session中
					//转发到user.jsp中
					request.getRequestDispatcher("/WEB-INF/content/admin.jsp").forward(request, response);				
				}else {
					request.setAttribute("info"," 错误:已存在该用户,不能重复注册！");
					request.getRequestDispatcher("/WEB-INF/content/message.jsp").forward(request, response);
				}

			}else {
				request.setAttribute("info"," 错误:邀请码错误！");
				request.getRequestDispatcher("/WEB-INF/content/message.jsp").forward(request, response);
			}
		}
	}

}
