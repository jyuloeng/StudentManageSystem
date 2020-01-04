package model;

//入职/入学年份
public enum EntryYear {
	A1("1995年",0),A2("1996年",1),A3("1997年",2),A4("1998年",3),A5("1999年",4),B1("2000年",5),
	B2("2001年",6),B3("2002年",7),B4("2003年",8),B5("2004年",9),B6("2005年",10),B7("2006年",11),
	B8("2007年",12),B9("2008年",13),B10("2009年",14),C1("2010年",15),C2("2011年",16),C3("2012年",17),
	C4("2013年",18),C5("2014年",19),C6("2015年",20),C7("2016年",21),C8("2017年",22),C9("2018年",23),C10("2019年",24);
	
	private String name;	//年份名称
	private int value;		//年份索引
	private EntryYear(String name,int value) {
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
