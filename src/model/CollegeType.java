package model;

//ѧԺ��Ϣ
public enum CollegeType {
	COMPUTING("�����ѧԺ",0),ELECTRONIC_IM("������ϢѧԺ",1),ELECTROMECHANICAL_EG("���繤��ѧԺ",2),
	MANAGEMENT("����ѧԺ",3),FACULTY_HM("����ѧԺ",4),FOREIGN_LG("�����ѧԺ",5),
	ART_DESIGN("�������ѧԺ",6),ECONOMIC_TD("��óѧԺ",7),MATERICAL_FD("������ʳƷѧԺ",8);
	
	private String name;	//ѧԺ����
	private int value;		//ѧԺ����
	
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
	
	//��дtoString�����ص�ʱ����ȷ��������
	@Override
	public String toString() {
		return this.name;
	}
}
