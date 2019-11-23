package dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;

import model.Student;
import utils.DBUtils;

public class StudentDao {
	// 获取所有学生的信息，用ArrayList返回
	public ArrayList<Student> query_all_student() {
		Connection conn = DBUtils.getConnection();
		String sql = "select * from student order by sno;";
		ArrayList<Student> results = new ArrayList<Student>();
		try {
			PreparedStatement ps = (PreparedStatement) conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Student temp = new Student();
				temp.setSno(rs.getInt("sno"));
				temp.setSname(rs.getString("sname"));
				temp.setClno(rs.getInt("clno"));
				temp.setSsex(rs.getString("ssex"));
				temp.setSage(rs.getInt("sage"));
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
	// 插入学生信息，返回一个int值表示状态,1：成功，0失败
	public int insert_student(String Sname,String Ssex,int Sage,int Clno) {
		Connection conn = DBUtils.getConnection();
		String sql = "insert into student(sname,clno,ssex,sage) values(?,?,?,?);";
		int flag = 0;
		try {
			PreparedStatement ps = (PreparedStatement) conn.prepareStatement(sql);
			ps.setString(1, Sname.trim());
			ps.setInt(2, Clno);
			ps.setString(3, Ssex.trim());	
			ps.setInt(4, Sage);
			flag = ps.executeUpdate();
			ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtils.closeConnection(conn);
		}
		return flag;
	}
	// 删除学生信息，返回一个int值表示状态,1：成功，0失败
	public int delete_student(String sno) {
		Connection conn = DBUtils.getConnection();
		String sql = "delete from student where sno = ?;";
		int flag = 0;
		try {
			PreparedStatement ps = (PreparedStatement) conn.prepareStatement(sql);
			ps.setString(1, sno);
			flag = ps.executeUpdate();
			ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtils.closeConnection(conn);
		}
		return flag;
	}
	// 修改学生信息，返回一个int值表示状态,1：成功，0失败
	public int alter_class(String sno,String after_sname,String after_ssex,int after_sage,String after_clno) {
		Connection conn = DBUtils.getConnection();
		String sql = "update student set sname = ?,ssex = ?,sage = ?,clno = ? where sno = ?;";
		int flag = 0;
		try {
			PreparedStatement ps = (PreparedStatement) conn.prepareStatement(sql);
			ps.setString(1, after_sname);
			ps.setString(2, after_ssex);
			ps.setInt(3, after_sage);
			ps.setString(4, after_clno);
			ps.setString(5, sno);
			flag = ps.executeUpdate();
			ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtils.closeConnection(conn);
		}
		return flag;
	}
	// 修改学生信息,学生端操作
	public int alter_student(int sno,String after_sname,String after_ssex,int after_sage) {
		Connection conn = DBUtils.getConnection();
		String sql = "update student set sname = ?,ssex = ?,sage = ? where sno = ?;";
		int flag = 0;
		try {
			PreparedStatement ps = (PreparedStatement) conn.prepareStatement(sql);
			ps.setString(1, after_sname);
			ps.setString(2, after_ssex);
			ps.setInt(3, after_sage);
			ps.setInt(4, sno);
			flag = ps.executeUpdate();
			ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtils.closeConnection(conn);
		}
		return flag;
	}
}
