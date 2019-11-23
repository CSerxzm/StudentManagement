package model;
import java.io.Serializable;
/*
 * 班级类
 */
public class Class implements Serializable {

	private static final long serialVersionUID = 1L;

	private int Clno;//班级编号
	private String Clname;//班级名称
	private String Dno;//所属院系
	public int getClno() {
		return Clno;
	}
	public void setClno(int clno) {
		Clno = clno;
	}
	public String getClname() {
		return Clname;
	}
	public void setClname(String clname) {
		Clname = clname;
	}
	public String getDno() {
		return Dno;
	}
	public void setDno(String dno) {
		Dno = dno;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}	
}
