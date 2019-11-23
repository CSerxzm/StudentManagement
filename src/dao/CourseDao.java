package dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;

import model.Course;
import model.Course_avg;
import model.Course_fail_rate;
import model.Course_ranking;
import model.SC;
import utils.DBUtils;

public class CourseDao {
	// 获取所有课程的信息，用ArrayList返回
	public ArrayList<Course> query_all_course() {
		Connection conn = DBUtils.getConnection();
		String sql = "select * from course order by cno;";
		ArrayList<Course> results = new ArrayList<Course>();
		try {
			PreparedStatement ps = (PreparedStatement) conn.prepareStatement(sql);
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
	// 插入课程信息，返回一个int值表示状态,1：成功，0失败
	public int insert_course(String Cname, String Cteacher,double Ccredit,double maxnumofentrolled) {
		Connection conn = DBUtils.getConnection();
		String sql = "insert into course(cname,cteacher,ccredit,maxnumofentrolled) values(?,?,?,?);";
		int flag = 0;
		try {
			PreparedStatement ps = (PreparedStatement) conn.prepareStatement(sql);
			ps.setString(1, Cname);
			ps.setString(2, Cteacher);
			ps.setDouble(3, Ccredit);
			ps.setDouble(4, maxnumofentrolled);
			flag = ps.executeUpdate();
			ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtils.closeConnection(conn);
		}
		return flag;
	}
	// 删除课程信息，返回一个int值表示状态,1：成功，0失败
	public int delete_course(int Cno) {
		Connection conn = DBUtils.getConnection();
		String sql = "delete from course where Cno = ?;";
		int flag = 0;
		try {
			PreparedStatement ps = (PreparedStatement) conn.prepareStatement(sql);
			ps.setInt(1, Cno);
			flag = ps.executeUpdate();
			ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtils.closeConnection(conn);
		}
		return flag;
	}
	//修改课程信息，返回一个int值表示状态,1：成功，0失败
	public int alter_course(int cno,String after_cname,String after_cteacher,double after_ccredit,int after_maxnumofentrolled) {
		Connection conn = DBUtils.getConnection();
		System.out.println(after_cname);
		String sql = "update course set cname = ?,cteacher = ?,ccredit = ?,maxnumofentrolled=? where cno = ?;";
		int flag = 0;
		try {
			PreparedStatement ps = (PreparedStatement) conn.prepareStatement(sql);
			ps.setString(1, after_cname);
			ps.setString(2, after_cteacher);
			ps.setDouble(3, after_ccredit);
			ps.setInt(4,after_maxnumofentrolled);
			ps.setInt(5, cno);
			flag = ps.executeUpdate();
			ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			DBUtils.closeConnection(conn);
		}
		return flag;
	}
	// 查询课程平均分信息，返回一个ArrayLst集合
	public ArrayList<Course_avg> course_avg() {
		Connection conn = DBUtils.getConnection();
		String sql = "select sc.cno cno,cname,avg(grade) avg from course,sc where course.cno = sc.cno group by cno order by cno;";
		ResultSet result = null;
		ArrayList<Course_avg> course_avg = new ArrayList<Course_avg>();
		try {
			PreparedStatement ps = (PreparedStatement) conn.prepareStatement(sql);
			result = ps.executeQuery();
			while(result.next()){
				Course_avg temp = new Course_avg();
				temp.setCno(result.getString("Cno"));
				temp.setCname(result.getString("Cname"));
				temp.setAvg(result.getDouble("avg"));
				course_avg.add(temp);
			}
			ps.close();
			result.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtils.closeConnection(conn);
		}
		return course_avg;
	}
	// 查询课程平均分信息，返回一个ArrayLst集合
	public ArrayList<Course_avg> course_avg(int sno) {
		Connection conn = DBUtils.getConnection();
		String sql = "select s2.cno cno,cname,avg(grade) avg from course,sc s2 "
				+ "where s2.cno in (select s1.cno from sc s1 where sno="+sno+" ) and course.cno = s2.cno group by s2.cno order by s2.cno;";
		ResultSet result = null;
		ArrayList<Course_avg> course_avg = new ArrayList<Course_avg>();
		try {
			PreparedStatement ps = (PreparedStatement) conn.prepareStatement(sql);
			result = ps.executeQuery();
			while(result.next()){
				Course_avg temp = new Course_avg();
				temp.setCno(result.getString("Cno"));
				temp.setCname(result.getString("Cname"));
				temp.setAvg(result.getDouble("avg"));
				course_avg.add(temp);
			}
			ps.close();
			result.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtils.closeConnection(conn);
		}
		return course_avg;
	}
	//查询课程不及格率，返回一个ArrayList集合
	public ArrayList<Course_fail_rate> fail_rate(){
		Connection conn = DBUtils.getConnection();
		String sql = "select cno,(select cname from course where cno = x.cno) cname,cast(100.0*(select count(sno) from sc where grade < 60 and cno = x.cno)/(select count(sno) from sc where cno = x.cno) as decimal(18,2)) rate from sc x group by cno order by cno;";
		ArrayList<Course_fail_rate> fail_rate = new ArrayList<Course_fail_rate>();
		try {
			PreparedStatement ps = (PreparedStatement) conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while(rs.next()){
				Course_fail_rate temp = new Course_fail_rate();
				temp.setCno(rs.getString("cno"));
				temp.setCname(rs.getString("cname"));
				temp.setFail_rate(rs.getDouble("rate"));
				fail_rate.add(temp);
			}
			rs.close();
			ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtils.closeConnection(conn);
		}
		return fail_rate;
	}
	//查询课程排名情况,返回一个ArrayList集合
	public ArrayList<Course_ranking> course_ranking(int cno){
		Connection conn = DBUtils.getConnection();
		String sql = "select student.Sno Sno,Sname,Grade " + 
				"from student,sc where student.sno = sc.sno and cno = '"+cno+"' order by grade desc;";
		ArrayList<Course_ranking> course_ranking = new ArrayList<Course_ranking>();
		try {
			PreparedStatement ps = (PreparedStatement) conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while(rs.next()){
				Course_ranking temp = new Course_ranking();
				temp.setSno(rs.getInt("Sno"));
				temp.setSname(rs.getString("Sname"));
				temp.setgrade(rs.getDouble("Grade"));
				course_ranking.add(temp);
			}
			rs.close();
			ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtils.closeConnection(conn);
		}
		return course_ranking;
	}
	//查询个人所有课程排名情况,返回一个ArrayList集合
	//cno 只是为了重载，没实际意义
	public ArrayList<SC> course_ranking(int sno,int cno){
		Connection conn = DBUtils.getConnection();
		String sql = "select s1.cno cno, cname,mysc.grade grade, count(*) ranking from sc mysc,sc s1,course " + 
				" where s1.cno=mysc.cno and s1.grade<=mysc.grade and s1.sno="+sno+" and s1.cno=course.cno group by cno;";
		ArrayList<SC> course_ranking = new ArrayList<SC>();
		try {
			PreparedStatement ps = (PreparedStatement) conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while(rs.next()){
				SC temp = new SC();
				temp.setCno(rs.getInt("cno"));
				temp.setCname(rs.getString("cname"));
				temp.setgrade(rs.getDouble("grade"));
				temp.setRanking(rs.getInt("ranking"));
				course_ranking.add(temp);
			}
			rs.close();
			ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtils.closeConnection(conn);
		}
		return course_ranking;
	}
}

