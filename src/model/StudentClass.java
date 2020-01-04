package model;

//班级信息
public class StudentClass {
	private int id;				//班级编号
	private String classNumber;	//班级代码
	private String className;	//班级名称
	private String collegeType;	//所属学院
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getClassName() {
		return className;
	}
	public void setClassName(String className) {
		this.className = className;
	}
	public String getClassNumber() {
		return classNumber;
	}
	public void setClassNumber(String classNumber2) {
		this.classNumber = classNumber2;
	}
	public String getCollegeType() {
		return collegeType;
	}
	public void setCollegeType(String collegeType) {
		this.collegeType = collegeType;
	}
	@Override
	public String toString() {
		return this.className;
	}
}
