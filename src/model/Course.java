package model;

import java.io.Serializable;

public class Course implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private int Cno;//课程号
	private String Cname;//课程名称
	private String Cteacher;//任课老师
	private int Ccredit;//学分
	private int maxnumofentrolled;//最大选课人数
	private int nownumofentrolled;//现有选课人数
	
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
	public String getCteacher() {
		return Cteacher;
	}
	public void setCteacher(String cteacher) {
		Cteacher = cteacher;
	}
	public int getCcredit() {
		return Ccredit;
	}
	public void setCcredit(int ccredit) {
		Ccredit = ccredit;
	}
	public int getmaxnumofentrolled() {
		return maxnumofentrolled;
	}
	public void setmaxnumofentrolled(int maxnumofentrolled) {
		this.maxnumofentrolled = maxnumofentrolled;
	}
	public int getnownumofentrolled() {
		return nownumofentrolled;
	}
	public void setnownumofentrolled(int nownumofentrolled) {
		this.nownumofentrolled = nownumofentrolled;
	}
	
}
