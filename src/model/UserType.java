package model;

//�û���Ϣ
public enum UserType {
	ADMIN("ϵͳ����Ա",0),TEACHER("��ʦ",1),STUDENT("ѧ��",2);
	private String name;	//�û�����
	private int value;		//�û�����
	
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
	
	//��дtoString�����ص�ʱ����ȷ��������
	@Override
	public String toString() {
		return this.name;
	}
}
