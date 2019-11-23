package model;

import java.io.Serializable;
/*
 * 学生信息类
 */
public class Student implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private int Sno;//学号
	private String Sname;//姓名
	private int clno;//所在班级
	private String Ssex;//性别
	private int Sage;//年龄
	
	
	public int getSno() {
		return Sno;
	}
	public void setSno(int sno) {
		Sno = sno;
	}
	public String getSname() {
		return Sname;
	}
	public void setSname(String sname) {
		Sname = sname;
	}
	public String getSsex() {
		return Ssex;
	}
	public void setSsex(String ssex) {
		Ssex = ssex;
	}
	public int getSage() {
		return Sage;
	}
	public void setSage(int sage) {
		Sage = sage;
	}
	public int getClno() {
		return clno;
	}
	public void setClno(int clno) {
		this.clno = clno;
	}
	
	
}
