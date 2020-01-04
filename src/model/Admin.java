package model;

//管理员表
public class Admin {
	private int id;			//系统管理员编号
	private String name;	//系统管理员账号
	private String password;//系统管理员密码
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	 
	
}
