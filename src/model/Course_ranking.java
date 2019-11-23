package model;

import java.io.Serializable;

public class Course_ranking implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private int Sno;//学号
	private String Sname;//学生姓名
	private int Cno;//课程号
	private String Cname;
	private int ranking;
	
	private Double grade;//成绩

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
	public Double getgrade() {
		return grade;
	}
	public void setgrade(Double grade) {
		this.grade = grade;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public int getCno() {
		return Cno;
	}
	public void setCno(int cno) {
		Cno = cno;
	}
	public String getCname() {
		return Cname;
	}
	public void setCname(String cname) {
		Cname = cname;
	}
//	public int getRanking() {
//		return ranking;
//	}
//	public void setRanking(int ranking) {
//		this.ranking= ranking;
//	}
}
