package model;

//�Ա���Ϣ
public enum SexType {
	MAN("��",0),WOMAN("Ů",1);
	String name;   //�Ա�����
	int value;	   //�Ա�����
	
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
	
	//��дtoString�����ص�ʱ����ȷ��������
	@Override
	public String toString() {
		return this.name;
	}
}
