package model;

//�༶��Ϣ
public class StudentClass {
	private int id;				//�༶���
	private String classNumber;	//�༶����
	private String className;	//�༶����
	private String collegeType;	//����ѧԺ
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
