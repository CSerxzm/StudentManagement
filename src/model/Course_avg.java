package model;
import java.io.Serializable;
/*
 * 
 */
public class Course_avg implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private String Cno;//课程号
	private String Cname;//课程名称
	private double avg;//平均分
	
	public String getCno() {
		return Cno;
	}
	public void setCno(String cno) {
		Cno = cno;
	}
	public String getCname() {
		return Cname;
	}
	public void setCname(String cname) {
		Cname = cname;
	}
	public double getAvg() {
		return avg;
	}
	public void setAvg(double avg) {
		this.avg = avg;
	}
	
}
