package model;

//性别信息
public enum SexType {
	MAN("男",0),WOMAN("女",1);
	String name;   //性别名称
	int value;	   //性别索引
	
	private SexType(String name,int value) {
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
