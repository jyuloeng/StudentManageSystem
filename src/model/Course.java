package model;

//课程信息
public class Course {
	private int id;				//课程编号
	private String courseName;	//课程名称
	private String teacherName;	//授课教师名称
	private int MaxStudentNum;	//最大可选人数
	private int selectNum = 0;	//已选课程人数
	
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
