package model;

//�γ���Ϣ
public class Course {
	private int id;				//�γ̱��
	private String courseName;	//�γ�����
	private String teacherName;	//�ڿν�ʦ����
	private int MaxStudentNum;	//����ѡ����
	private int selectNum = 0;	//��ѡ�γ�����
	
	public int getMaxStudentNum() {
		return MaxStudentNum;
	}
	public void setMaxStudentNum(int maxStudentNum) {
		MaxStudentNum = maxStudentNum;
	}
	public String getTeacherName() {
		return teacherName;
	}
	public void setTeacherName(String teacherName) {
		this.teacherName = teacherName;
	}
	public String getCourseName() {
		return courseName;
	}
	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getSelectNum() {
		return selectNum;
	}
	public void setSelectNum(int selectNum) {
		this.selectNum = selectNum;
	}
	
	@Override
	public String toString() {
		return this.courseName;
	}
	
}
