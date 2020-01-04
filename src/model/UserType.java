package model;

//用户信息
public enum UserType {
	ADMIN("系统管理员",0),TEACHER("教师",1),STUDENT("学生",2);
	private String name;	//用户名称
	private int value;		//用户索引
	
	private UserType(String name,int value) {
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
