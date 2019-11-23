package model;

public class StudentInform {
	private int sno;
	private String sname;
	private String dno;
	private String dname;
	private int clno;
	private String clname;
	private String ssex;
	private int sage;
	
	public void setSno(int sno) {
		this.sno=sno;
	}
	public int getSno() {
		return sno;
	}
	public void setSname(String sname) {
		this.sname=sname;
	}
	public String getSname() {
		return sname;
	}
	
	public void setDno(String dno) {
		this.dno=dno;
	}
	public String getDno() {
		return dno;
	}
	public void setDname(String dname) {
		this.dname=dname;
	}
	public String getDname() {
		return dname;
	}
	
	public void setClno(int clno) {
		this.clno=clno;
	}
	public int getClno() {
		return clno;
	}
	public void setClname(String clname) {
		this.clname=clname;
	}
	public String getClname() {
		return clname;
	}
	public String getSsex() {
		return ssex;
	}
	public void setSsex(String ssex) {
		this.ssex = ssex;
	}
	public int getSage() {
		return sage;
	}
	public void setSage(int sage) {
		this.sage = sage;
	}
}
