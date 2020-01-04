package model;

import java.sql.Blob;

//教师信息
public class Teacher {
	private int id;					//教师编号
	private String teacherid;		//教师工号
	private String passWord;		//教师密码
	private String affpassWord;		//确认教师密码
	private String teacherName;		//教师姓名
	private String teacherSex;		//教师性别
	private String teacherCollege;	//教师所属学院
	private String entryYear;		//教师入职年份
	private Blob blob;				//教师照片
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTeacherId() {
		return teacherid;
	}
	public void setTeacherId(String id) {
		this.teacherid = id;
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
	public String getTeacherName() {
		return teacherName;
	}
	public void setTeacherName(String teacherName) {
		this.teacherName = teacherName;
	}
	public String getTeacherSex() {
		return teacherSex;
	}
	public void setTeacherSex(String teacherSex) {
		this.teacherSex = teacherSex;
	}
	public String getTeacherCollege() {
		return teacherCollege;
	}
	public void setTeacherCollege(String teacherCollege) {
		this.teacherCollege = teacherCollege;
	}
	public String getEntryYear() {
		return entryYear;
	}
	public void setEntryYear(String entryYear) {
		this.entryYear = entryYear;
	}
	public Blob getBlob() {
		return blob;
	}
	public void setBlob(Blob blob) {
		this.blob = blob;
	}
	
	@Override
	public String toString() {
		return this.teacherName;
	}
	

}
