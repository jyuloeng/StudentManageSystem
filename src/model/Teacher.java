package model;

import java.sql.Blob;

//��ʦ��Ϣ
public class Teacher {
	private int id;					//��ʦ���
	private String teacherid;		//��ʦ����
	private String passWord;		//��ʦ����
	private String affpassWord;		//ȷ�Ͻ�ʦ����
	private String teacherName;		//��ʦ����
	private String teacherSex;		//��ʦ�Ա�
	private String teacherCollege;	//��ʦ����ѧԺ
	private String entryYear;		//��ʦ��ְ���
	private Blob blob;				//��ʦ��Ƭ
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
