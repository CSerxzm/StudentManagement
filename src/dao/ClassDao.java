package dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;

import utils.DBUtils;
import model.Class;

public class ClassDao {
	// 获取所有班级的信息，用ArrayList返回
	public ArrayList<Class> query_all_class() {
		Connection conn = DBUtils.getConnection();
		String sql = "select * from class order by clno;";
		ArrayList<Class> results = new ArrayList<Class>();
		try {
			PreparedStatement ps = (PreparedStatement) conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Class temp = new Class();
				temp.setClno(rs.getInt("clno"));
				temp.setClname(rs.getString("clname"));
				temp.setDno(rs.getString("dno"));
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
	// 插入班级信息，返回一个int值表示状态,1：成功，0失败
	public int insert_class(String clname, String dno) {
		Connection conn = DBUtils.getConnection();
		String sql = "insert into class(clname,dno) values(?,?);";
		int flag = 0;
		try {
			PreparedStatement ps = (PreparedStatement) conn.prepareStatement(sql);
			ps.setString(1, clname);
			ps.setString(2, dno);
			flag = ps.executeUpdate();
			ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtils.closeConnection(conn);
		}
		return flag;
	}
	// 删除班级信息，返回一个int值表示状态,1：成功，0失败
	public int delete_class(String clno) {
		Connection conn = DBUtils.getConnection();
		String sql = "delete from class where clno = ?;";
		int flag = 0;
		try {
			PreparedStatement ps = (PreparedStatement) conn.prepareStatement(sql);
			ps.setString(1, clno);
			flag = ps.executeUpdate();
			ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtils.closeConnection(conn);
		}
		return flag;
	}
	// 修改班级信息，返回一个int值表示状态,1：成功，0失败
	public int alter_class(String clno, String after_clname, String after_dno) {
		Connection conn = DBUtils.getConnection();
		String sql = "update class set clname = ?,dno = ? where clno = ?;";
		int flag = 0;
		try {
			PreparedStatement ps = (PreparedStatement) conn.prepareStatement(sql);
			ps.setString(1, after_clname);
			ps.setString(2, after_dno);
			ps.setString(3, clno);
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
