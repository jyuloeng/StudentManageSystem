package model;

import java.sql.Blob;

//ѧ����Ϣ
public class Student {
	private int id;					//ѧ�����
	private String studentid;		//ѧ��ѧ��
	private String passWord;		//ѧ������
	private String affpassWord;		//ȷ��ѧ������
	private String studentName;		//ѧ������
	private String studentSex;		//ѧ���Ա�
	private String studentCollege;	//����ѧԺ
	private String studentClass;	//�����༶
	private String entryYear;		//��ѧ���
	private Blob blob;				//ѧ����Ƭ
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
