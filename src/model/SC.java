package model;

import java.io.Serializable;
/*
 * 课程成绩
 */
public class SC implements Serializable {
			
	private static final long serialVersionUID = 1L;

	private int Sno;//学号
	private int Cno;//课程号
	private String Cname;//课程名称
	private double taskgrade;//作业成绩
	private double expgrade;//实验成绩
	private double examgrade;//考试成绩
	private double Grade;//成绩
	private int ranking;
	
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public int getSno() {
		return Sno;
	}
	public void setSno(int sno) {
		Sno = sno;
	}
	public int getCno() {
		return Cno;
	}
	public void setCno(int cno) {
		Cno = cno;
	}
	public double gettaskgrade() {
		return taskgrade;
	}
	public void settaskgrade(double grade) {
		taskgrade = grade;
	}
	public double getexpgrade() {
		return expgrade;
	}
	public void setexpgrade(double grade) {
		expgrade = grade;
	}
	public double getexamgrade() {
		return examgrade;
	}
	public void setexamgrade(double grade) {
		examgrade = grade;
	}
	public double getgrade() {
		return Grade;
	}
	public void setgrade(double grade) {
		Grade = grade;
	}
	
	public String getCname() {
		return Cname;
	}
	public void setCname(String cname) {
		Cname = cname;
	}
	public int getRanking() {
		return ranking;
	}
	public void setRanking(int ranking) {
		this.ranking= ranking;
	}
}
