package model;

//�ɼ���Ϣ
public class Score {
	private int id;			//�ɼ����
	private int studentId;	//ѧ�����
	private int courseId;	//�γ̱��
	private int score;		//ѧ���ɼ�
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getStudentId() {
		return studentId;
	}
	public void setStudentId(int studentId) {
		this.studentId = studentId;
	}
	public int getCourseId() {
		return courseId;
	}
	public void setCourseId(int courseId) {
		this.courseId = courseId;
	}
	public int getScore() {
		return score;
	}
	public void setScore(int score) {
		this.score = score;
	}
}
