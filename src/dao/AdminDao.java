package dao;
import model.Admin;

import java.sql.*;

public class AdminDao extends BaseDao {

	//管理员登陆
	public Admin login(Admin admin) {
		String sql = "select * from s_admin where name=? and password=?";
		Admin adminReset = null;		//定义一个空的Admin类返回类型
		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, admin.getName());
			ps.setString(2, admin.getPassword());
			ResultSet executeQuery = ps.executeQuery();
			if(executeQuery.next()) {	
				adminReset = new Admin();	//创建一个新的Admin类接收数据库获得的信息
				adminReset.setId(executeQuery.getInt("id"));	
				adminReset.setName(executeQuery.getString("name"));
				adminReset.setPassword(executeQuery.getString("password"));
			}
		} catch (SQLException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		return adminReset;		//返回从数据库获得的相应Admin类的信息
	}
	
	//修改密码
	public String editPassWord(Admin admin,String newPassword) {
		String sql = "select * from s_admin where name=? and password=?";
		PreparedStatement ps = null;
		String ret2 = "密码修改失败！";
		String sql2 = "update s_admin set password = ? where id = ?";
		int id = 0;
		try {
			ps = con.prepareStatement(sql);	//验证原密码是否与数据库中数据一致
			ps.setString(1, admin.getName());	
			ps.setString(2, admin.getPassword());
			ResultSet executeQuery = ps.executeQuery();
			if(!executeQuery.next()) {		
				String ret = "原密码错误！";
				return ret;
			}
			id = executeQuery.getInt("id");	//如果一致就获得Admin类型的系统管理员id
		} catch (SQLException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		try {
			ps = con.prepareStatement(sql2);
			ps.setString(1,newPassword);	
			ps.setInt(2, id);
			if(ps.executeUpdate()>0) {
				ret2 = "密码修改成功！";
			}
		} catch (SQLException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		return ret2;	//能到这里说明密码修改失败
	}
}
