package dao;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;

import model.Course;
import model.SC;
import model.Student;
import model.StudentInform;
import utils.DBUtils;

public class ViewDao {
	public StudentInform query_all_student(int id) {
		Connection conn = DBUtils.getConnection();
		String sql = "select * from student_view where sno=?;";
		StudentInform result = new StudentInform();
		try {
			PreparedStatement ps = (PreparedStatement) conn.prepareStatement(sql);
			ps.setInt(1,id);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				result.setSno(rs.getInt("sno"));
				result.setSname(rs.getString("sname"));
				result.setDno(rs.getString("dno"));
				result.setDname(rs.getString("dname"));				
				result.setClno(rs.getInt("clno"));
				result.setClname(rs.getString("clname"));		
				result.setSsex(rs.getString("ssex"));
				result.setSage(rs.getInt("sage"));
			}
			// 关闭资源
			rs.close();
			ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtils.closeConnection(conn);
		}
		return result;
	}
	public ArrayList<Student> query_all_class(int id) {
		Connection conn = DBUtils.getConnection();
		String sql = "	select * from class_view where sno_temp=?;";
		ArrayList<Student> results = new ArrayList<Student>();
		try {
			PreparedStatement ps = (PreparedStatement) conn.prepareStatement(sql);
			ps.setInt(1,id);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Student student = new Student();
				student.setSno(rs.getInt("sno"));
				student.setSname(rs.getString("sname"));
				results.add(student);
			}
			// 关闭资源
			rs.close();
			ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtils.closeConnection(conn);
		}
		return results;
	}
	public ArrayList<Course> query_all_course(int id) {
		Connection conn = DBUtils.getConnection();
		String sql = "select * from course_view where sno=?;";
		ArrayList<Course> results = new ArrayList<Course>();
		try {
			PreparedStatement ps = (PreparedStatement) conn.prepareStatement(sql);
			ps.setInt(1,id);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Course course = new Course();
				course.setCno(rs.getInt("cno"));
				course.setCname(rs.getString("cname"));
				course.setCteacher(rs.getString("cteacher"));	
				course.setCcredit(rs.getInt("ccredit"));
				results.add(course);
			}
			// 关闭资源
			rs.close();
			ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtils.closeConnection(conn);
		}
		return results;
	}
	public ArrayList<Course> query_all_select(int id) {
		Connection conn = DBUtils.getConnection();
		String sql = "select c.cno cno,cname,cteacher,ccredit,maxnumofentrolled,nownumofentrolled from course c"
		 + " where c.cno  not in(select sc.cno from sc where sc.sno=?);";
		ArrayList<Course> results = new ArrayList<Course>();
		try {
			PreparedStatement ps = (PreparedStatement) conn.prepareStatement(sql);
			ps.setInt(1,id);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Course temp = new Course();
				temp.setCno(rs.getInt("cno"));
				temp.setCname(rs.getString("Cname"));
				temp.setCteacher(rs.getString("Cteacher"));
				temp.setCcredit(rs.getInt("Ccredit"));
				temp.setmaxnumofentrolled(rs.getInt("maxnumofentrolled"));
				temp.setnownumofentrolled(rs.getInt("nownumofentrolled"));
				results.add(temp);
			}
			// 关闭资源
				rs.close();
				ps.close();
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				DBUtils.closeConnection(conn);
			}
			return results;
	}
	// 获取某位同学成绩记录的信息，用ArrayList返回
	public ArrayList<SC> query_all_sc(int id) {
		Connection conn = DBUtils.getConnection();
		String sql = "select * from grade_view where sno=?;";
		ArrayList<SC> results = new ArrayList<SC>();
		try {
			PreparedStatement ps = (PreparedStatement) conn.prepareStatement(sql);
			ps.setInt(1,id);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				SC temp = new SC();
				temp.setCno(rs.getInt("cno"));
				temp.setCname(rs.getString("cname"));
				temp.settaskgrade(rs.getDouble("taskgrade"));
				temp.setexpgrade(rs.getDouble("expgrade"));
				temp.setexamgrade(rs.getDouble("examgrade"));
				temp.setgrade(rs.getDouble("grade"));
				results.add(temp);
			}
			// 关闭资源
			rs.close();
			ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtils.closeConnection(conn);
		}
		return results;
	}
}
