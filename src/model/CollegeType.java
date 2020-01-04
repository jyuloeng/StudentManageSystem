package model;

//学院信息
public enum CollegeType {
	COMPUTING("计算机学院",0),ELECTRONIC_IM("电子信息学院",1),ELECTROMECHANICAL_EG("机电工程学院",2),
	MANAGEMENT("管理学院",3),FACULTY_HM("人文学院",4),FOREIGN_LG("外国语学院",5),
	ART_DESIGN("艺术设计学院",6),ECONOMIC_TD("经贸学院",7),MATERICAL_FD("材料与食品学院",8);
	
	private String name;	//学院名称
	private int value;		//学院索引
	
	private CollegeType(String name,int value) {
		this.name=name;
		this.value=value;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getValue() {
		return value;
	}
	public void setValue(int value) {
		this.value = value;
	}
	
	//重写toString，返回的时候正确返回文字
	@Override
	public String toString() {
		return this.name;
	}
}
