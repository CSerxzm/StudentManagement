package dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;

import model.SC;
import utils.DBUtils;

public class SCDao {

	// 获取所有成绩记录的信息，用ArrayList返回
	public ArrayList<SC> query_all_sc() {
		Connection conn = DBUtils.getConnection();
		String sql = "select * from sc order by sno;";
		ArrayList<SC> results = new ArrayList<SC>();
		try {
			PreparedStatement ps = (PreparedStatement) conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				SC temp = new SC();
				temp.setSno(rs.getInt("sno"));
				temp.setCno(rs.getInt("cno"));
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
	// 插入成绩信息，返回一个int值表示状态,1：成功，0失败
	public int insert_sc(int sno,int cno,double taskgrade,double expgrade,double examgrade) {
		Connection conn = DBUtils.getConnection();
		String sql = "update sc set taskgrade=?,expgrade=?,examgrade=? " + 
				"where cno=? and sno=?;";
		int flag = 0;
		try {
			PreparedStatement ps = (PreparedStatement) conn.prepareStatement(sql);
			ps.setDouble(1, taskgrade);
			ps.setDouble(2, expgrade);
			ps.setDouble(3, examgrade);
			ps.setInt(4, cno);
			ps.setInt(5, sno);
			flag = ps.executeUpdate();
			ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtils.closeConnection(conn);
		}
		return flag;
	}
	// 删除成绩记录，返回一个int值表示状态,1：成功，0失败
	public int delete_sc(int Sno,int Cno) {
		Connection conn = DBUtils.getConnection();
		String sql = "delete from sc where sno = ? and cno = ?;";
		int flag = 0;
		try {
			PreparedStatement ps = (PreparedStatement) conn.prepareStatement(sql);
			ps.setInt(1, Sno);
			ps.setInt(2, Cno);
			flag = ps.executeUpdate();
			ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtils.closeConnection(conn);
		}
		return flag;
	}
	// 修改成绩信息，返回一个int值表示状态,1：成功，0失败
	public int insert_sc(int Sno,int Cno) {
		Connection conn = DBUtils.getConnection();
		String sql = "insert into sc(cno,sno) values(?,?);";
		int flag = 0;
		try {
			PreparedStatement ps = (PreparedStatement) conn.prepareStatement(sql);
			ps.setInt(1,Cno);
			ps.setInt(2,Sno);
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
