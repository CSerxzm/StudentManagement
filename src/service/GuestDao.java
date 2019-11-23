package service;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLDecoder;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.ClassDao;
import dao.CourseDao;
import dao.DepartmentDao;
import dao.SCDao;
import dao.StudentDao;
import dao.UserDao;
import dao.ViewDao;
import model.Class;
import model.Course;
import model.Course_avg;
import model.Course_fail_rate;
import model.Course_ranking;
import model.Department;
import model.SC;
import model.Student;
import model.StudentInform;
import model.User;


public class GuestDao extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	private String action;//存储操作描述
	//接收请求
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		action = request.getParameter("action");
		//判断所执行操作
		switch (action) {
		//用户操作
		case "query_all_user":
			query_all_user(request, response);break;
		case "query_all_class":
			query_all_class(request,response);break;
		case "query_all_course":
			query_all_course(request, response);break;
		case "query_all_select":
			query_all_select(request, response);break;
		case "query_all_sc":
			query_all_sc(request, response);break;
		case "query_all_ranking":
			query_all_ranking(request, response);break;
		case "query_all_average":
			query_all_average(request, response);break;
		case "alter_user":
			alter_user(request, response);break;
		//课程操作
		case "alter_password":
			alter_password(request, response);break;
		case "alter_course":
			alter_course(request, response);break;
		case "alter_select":
			alter_select(request, response);break;
		default:
			break;
		}
	}

	private void alter_course(HttpServletRequest request, HttpServletResponse response)  throws ServletException, IOException{
		// TODO Auto-generated method stub
		response.setContentType("text/html;charset=utf-8");
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("student");
		int sno = user.getId();
		System.out.println("cno="+request.getParameter("cno"));
		int cno = Integer.valueOf(request.getParameter("cno"));
		int flag= new SCDao().delete_sc(sno,cno);
		PrintWriter out = response.getWriter();
		String info=null;
		if (flag == 1) {
			info = "退课成功";
		} else {
			info = "错误：退课失败！";
		}
		out.write("<div class='error'>");
		out.write("<div>" + info + "</div>");
		out.write("</div>");
		out.flush();
		out.close();
	}

	private void alter_password(HttpServletRequest request, HttpServletResponse response)  throws ServletException, IOException{
		// TODO Auto-generated method stub
		response.setContentType("text/html;charset=utf-8");
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("student");
		String username = user.getUsername();
		String old_password = request.getParameter("old_password");
		String new_password = request.getParameter("new_password");
		int flag= new UserDao().alter_password(username,old_password,new_password);
		PrintWriter out = response.getWriter();
		String info=null;
		if (flag == 1) {
			info = "修改密码成功";
		} else {
			info = "错误：修改密码失败！";
		}
		out.write("<div class='error'>");
		out.write("<div>" + info + "</div>");
		out.write("</div>");
		out.flush();
		out.close();
	}
//查看平均分
	private void query_all_average(HttpServletRequest request, HttpServletResponse response)  throws ServletException, IOException{
		// TODO Auto-generated method stub
		response.setContentType("text/html;charset=utf-8");
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("student");
		int sno = user.getId();
		ArrayList<Course_avg> results = new CourseDao().course_avg(sno);
		PrintWriter out = response.getWriter();
		if(results != null){
			//输出结果
			if(results != null){
				out.write("<div class='all'>");
				out.write("<table>");
				out.write("<thead>");
				out.write("<tr><th>课程号</th><th>课程名称</th><th>平均分</th></tr>");
				out.write("</thead>");
				for(Course_avg i:results) {
					out.write("<tr>");
					out.write("<td>"+i.getCno()+"</td>");
					out.write("<td>"+i.getCname()+"</td>");
					out.write("<td>"+i.getAvg()+"</td>");
					out.write("</tr>");
				}
				out.write("</table>");
				out.write("</div>");
			}
		}
		out.flush();
		out.close();
	}
	private void alter_user(HttpServletRequest request, HttpServletResponse response)  throws ServletException, IOException{
		// TODO Auto-generated method stub
		response.setContentType("text/html;charset=utf-8");
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("student");
		int id = user.getId();
		String after_sname = URLDecoder.decode(request.getParameter("after_sname"),"UTF-8");
		String after_ssex = URLDecoder.decode(request.getParameter("after_ssex"),"UTF-8");
		int after_ssage = Integer.valueOf(request.getParameter("after_sage"));
		int flag = new StudentDao().alter_student(id,after_sname,after_ssex,after_ssage);
		PrintWriter out = response.getWriter();
		String info=null;
		if (flag == 1) {
			info = "修改信息成功";
		} else {
			info = "错误：修改信息失败！";
		}
		out.write("<div class='error'>");
		out.write("<div>" + info + "</div>");
		out.write("</div>");
		out.flush();
		out.close();
	}
//查看所有课程排名
	private void query_all_ranking(HttpServletRequest request, HttpServletResponse response)  throws ServletException, IOException{
		// TODO Auto-generated method stub
		response.setContentType("text/html;charset=utf-8");
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("student");
		int id = user.getId();
		ArrayList<SC> results = new CourseDao().course_ranking(id,0);
		PrintWriter out = response.getWriter();
		// 输出结果
		if (results != null) {
			out.write("<div class='all'>");
			out.write("<table>");			
			out.write("<thead>");
			out.write("<tr><th>课程号</th><th>课程名</th><th>成绩</th><th>排名</th></tr>");
			out.write("</thead>");
			int no = 1;
			for (SC i : results) {
				out.write("<tr>");
				out.write("<td>" + i.getCno() + "</td>");
				out.write("<td>" + i.getCname() + "</td>");
				out.write("<td>" + i.getgrade() + "</td>");
				out.write("<td>"+i.getRanking()+"</td>");
				out.write("</tr>");
			}
			out.write("</table>");
			out.write("</div>");
		}
		out.flush();
		out.close();
	}
//查看我的所有成绩
	private void query_all_sc(HttpServletRequest request, HttpServletResponse response)  throws ServletException, IOException{
		// TODO Auto-generated method stub
		response.setContentType("text/html;charset=utf-8");
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("student");
		int id = user.getId();
		ArrayList<SC> results = new ViewDao().query_all_sc(id);
		PrintWriter out = response.getWriter();
		// 输出结果
		if (results != null) {
			out.write("<div id='all_sc' class='all'>");
			out.write("<table>");
			out.write("<thead>");
			out.write("<tr><th>课程号</th><th>课程名称</th><th>作业成绩</th><th>考试成绩</th><th>卷面成绩</th><th>总成绩</th></tr>");
			out.write("</thead>");			
			for (SC i : results) {
				out.write("<tr>");
				out.write("<td>" + i.getCno()  + "</td>");
				out.write("<td>" + i.getCname() + "</td>");
				out.write("<td>" + i.gettaskgrade() + "</td>");
				out.write("<td>" + i.getexpgrade() + "</td>");
				out.write("<td>" + i.getexamgrade() + "</td>");
				out.write("<td>" + i.getgrade() + "</td>");
				out.write("</tr>");
			}
			out.write("</table>");
			out.write("</div>");
		}
		out.flush();
		out.close();
	}
//查看我未选修课程
	private void query_all_select(HttpServletRequest request, HttpServletResponse response)  throws ServletException, IOException{
		// TODO Auto-generated method stub
		response.setContentType("text/html;charset=utf-8");
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("student");
		int id = user.getId();
		ArrayList<Course> results = new ViewDao().query_all_select(id);
		PrintWriter out = response.getWriter();
		if(results != null){
			//输出结果
			if(results != null){
				out.write("<div class='all'>");
				out.write("<table>");
				out.write("<thead>");
				out.write("<tr><th>课程号</th><th>课程名称</th><th>执教老师</th><th>学分</th><th>课程容量</th><th>已选人数</th><th>选择</th></tr>");
				out.write("</thead>");
				for(Course i:results) {
					out.write("<tr>");
					out.write("<td>"+i.getCno()+"</td>");
					out.write("<td>"+i.getCname()+"</td>");
					out.write("<td>"+i.getCteacher()+"</td>");
					out.write("<td>"+i.getCcredit()+"</td>");
					out.write("<td>"+i.getmaxnumofentrolled()+"</td>");
					out.write("<td>"+i.getnownumofentrolled()+"</td>");
					out.write("<td><input type='radio' name='select' value="+i.getCno()+" /></td>");	
					out.write("</tr>");
				}
				out.write("</table>");
				out.write("<input id='submit' onclick='select_course()' type='button' name='submit' value='提交'>");
				out.write("</div>");
			}
		}
		out.flush();
		out.close();
	}
	//查看我选修的课程
	private void query_all_course(HttpServletRequest request, HttpServletResponse response)  throws ServletException, IOException{
		// TODO Auto-generated method stub
		response.setContentType("text/html;charset=utf-8");
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("student");
		int id = user.getId();
		ArrayList<Course> results = new ViewDao().query_all_course(id);
		PrintWriter out = response.getWriter();
		// 输出结果
		if (results != null) {
			out.write("<div id='all_sc' class='all'>");
			out.write("<table>");
			out.write("<thead>");
			out.write("<tr><th>序号</th><th>课程号</th><th>课程名称</th><th>任课教师</th><th>学分</th></tr>");
			out.write("</thead>");
			int i=0;
			for (Course course : results) {
				out.write("<tr>");
				out.write("<td>" + (++i)+ "</td>");
				out.write("<td>" + course.getCno() + "</td>");
				out.write("<td>" + course.getCname() + "</td>");
				out.write("<td>" + course.getCteacher() + "</td>");
				out.write("<td>" + course.getCcredit() + "</td>");			
				out.write("</tr>");
			}
			out.write("</table>");
			out.write("</div>");
		}
		out.flush();
		out.close();	
	}
	//查询同班同学
	private void query_all_class(HttpServletRequest request, HttpServletResponse response)  throws ServletException, IOException{
		// TODO Auto-generated method stub
		response.setContentType("text/html;charset=utf-8");
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("student");
		int id = user.getId();
		ArrayList<Student> results = new ViewDao().query_all_class(id);
		PrintWriter out = response.getWriter();
		// 输出结果
		if (results != null) {
			out.write("<div id='all_sc' class='all'>");
			out.write("<table>");
			out.write("<thead>");
			out.write("<tr><th>序号</th><th>学号</th><th>姓名</th></tr>");
			out.write("</thead>");
			int i=0;
			for (Student student : results) {
				out.write("<tr>");
				out.write("<td>" + (++i)+ "</td>");
				out.write("<td>" + student.getSno() + "</td>");
				out.write("<td>" + student.getSname() + "</td>");
				out.write("</tr>");
			}
			out.write("</table>");
			out.write("</div>");
		}
		out.flush();
		out.close();
	}

	//查询用户信息
	protected void query_all_user(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("student");
		int id = user.getId();
		ViewDao viewDao = new ViewDao();	
		StudentInform result = viewDao.query_all_student(id);
		PrintWriter out = response.getWriter();
		//输出结果
		if(result != null){
			out.write("<div class='all'>");
			out.write("<table>");
			out.write("<thead>");
			out.write("<tr><th>学号</th><th>姓名</th><th>班级号</th><th>班级</th></tr>");
			out.write("</thead>");
			out.write("<tr>");
			out.write("<td>"+result.getSno()+"</td>");
			out.write("<td>"+result.getSname()+"</td>");
			out.write("<td>"+result.getClno()+"</td>");
			out.write("<td>"+result.getClname()+"</td>");
			out.write("</tr>");
			out.write("<tr><th>系号</th><th>系名</th><th>性别</th><th>年龄</th></tr>");
			out.write("</thead>");
			out.write("<tr>");
			out.write("<td>"+result.getDno()+"</td>");
			out.write("<td>"+result.getDname()+"</td>");
			out.write("<td>"+result.getSsex()+"</td>");
			out.write("<td>"+result.getSage()+"</td>");
			out.write("</tr>");
			out.write("</table>");
			out.write("</div>");
		}	
		out.flush();
		out.close();
	}
	//选课
	private void alter_select(HttpServletRequest request, HttpServletResponse response)  throws ServletException, IOException{
		// TODO Auto-generated method stub
		response.setContentType("text/html;charset=utf-8");
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("student");
		int id = user.getId();
		int select =Integer.valueOf(request.getParameter("select"));
		int flag = new SCDao().insert_sc(id,select);
		PrintWriter out = response.getWriter();
		String info=null;
		if (flag == 1) {
			info = "选课成功";
		} else {
			info = "错误：选课失败！";
		}
		out.write("<div class='error'>");
		out.write("<div>" + info + "</div>");
		out.write("</div>");
		out.flush();
		out.close();	
	}

}
