package model;

//��ְ/��ѧ���
public enum EntryYear {
	A1("1995��",0),A2("1996��",1),A3("1997��",2),A4("1998��",3),A5("1999��",4),B1("2000��",5),
	B2("2001��",6),B3("2002��",7),B4("2003��",8),B5("2004��",9),B6("2005��",10),B7("2006��",11),
	B8("2007��",12),B9("2008��",13),B10("2009��",14),C1("2010��",15),C2("2011��",16),C3("2012��",17),
	C4("2013��",18),C5("2014��",19),C6("2015��",20),C7("2016��",21),C8("2017��",22),C9("2018��",23),C10("2019��",24);
	
	private String name;	//�������
	private int value;		//�������
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
	
	//��дtoString�����ص�ʱ����ȷ��������
	@Override
	public String toString() {
		return this.name;
	}
}
