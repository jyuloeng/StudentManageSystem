package model;

import java.sql.Blob;

//学生信息
public class Student {
	private int id;					//学生编号
	private String studentid;		//学生学号
	private String passWord;		//学生密码
	private String affpassWord;		//确认学生密码
	private String studentName;		//学生姓名
	private String studentSex;		//学生性别
	private String studentCollege;	//所属学院
	private String studentClass;	//所属班级
	private String entryYear;		//入学年份
	private Blob blob;				//学生照片
	 public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}    
	public String getStudentId() {
		return studentid;
	}
	public void setStudentId(String id) {
		this.studentid = id;
	}
	public String getPassWord() {
		return passWord;
	}
	public void setPassWord(String passWord) {
		this.passWord = passWord;
	}
	public String getAffpassWord() {
		return affpassWord;
	}
	public void setAffpassWord(String affpassWord) {
		this.affpassWord = affpassWord;
	}
	public String getStudentName() {
		return studentName;
	}
	public void setStudentName(String StudentName) {
		this.studentName = StudentName;
	}
	public String getStudentSex() {
		return studentSex;
	}
	public void setStudentSex(String StudentSex) {
		this.studentSex = StudentSex;
	}
	public String getStudentCollege() {
		return studentCollege;
	}
	public void setStudentCollege(String StudentCollege) {
		this.studentCollege = StudentCollege;
	}
	public String getEntryYear() {
		return entryYear;
	}
	public void setEntryYear(String entryYear) {
		this.entryYear = entryYear;
	}
	public String getStudentClass() {
		return studentClass;
	}
	public void setStudentClass(String studentClass) {
		this.studentClass = studentClass;
	}
	public Blob getBlob() {
		return blob;
	}
	public void setBlob(Blob blob) {
		this.blob = blob;
	}
	@Override
	public String toString() {
		return this.studentName;
	}
	
}
