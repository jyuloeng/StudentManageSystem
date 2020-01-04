package dao;
import model.Admin;

import java.sql.*;

public class AdminDao extends BaseDao {

	//����Ա��½
	public Admin login(Admin admin) {
		String sql = "select * from s_admin where name=? and password=?";
		Admin adminReset = null;		//����һ���յ�Admin�෵������
		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, admin.getName());
			ps.setString(2, admin.getPassword());
			ResultSet executeQuery = ps.executeQuery();
			if(executeQuery.next()) {	
				adminReset = new Admin();	//����һ���µ�Admin��������ݿ��õ���Ϣ
				adminReset.setId(executeQuery.getInt("id"));	
				adminReset.setName(executeQuery.getString("name"));
				adminReset.setPassword(executeQuery.getString("password"));
			}
		} catch (SQLException e) {
			// TODO �Զ����ɵ� catch ��
			e.printStackTrace();
		}
		return adminReset;		//���ش����ݿ��õ���ӦAdmin�����Ϣ
	}
	
	//�޸�����
	public String editPassWord(Admin admin,String newPassword) {
		String sql = "select * from s_admin where name=? and password=?";
		PreparedStatement ps = null;
		String ret2 = "�����޸�ʧ�ܣ�";
		String sql2 = "update s_admin set password = ? where id = ?";
		int id = 0;
		try {
			ps = con.prepareStatement(sql);	//��֤ԭ�����Ƿ������ݿ�������һ��
			ps.setString(1, admin.getName());	
			ps.setString(2, admin.getPassword());
			ResultSet executeQuery = ps.executeQuery();
			if(!executeQuery.next()) {		
				String ret = "ԭ�������";
				return ret;
			}
			id = executeQuery.getInt("id");	//���һ�¾ͻ��Admin���͵�ϵͳ����Աid
		} catch (SQLException e) {
			// TODO �Զ����ɵ� catch ��
			e.printStackTrace();
		}
		try {
			ps = con.prepareStatement(sql2);
			ps.setString(1,newPassword);	
			ps.setInt(2, id);
			if(ps.executeUpdate()>0) {
				ret2 = "�����޸ĳɹ���";
			}
		} catch (SQLException e) {
			// TODO �Զ����ɵ� catch ��
			e.printStackTrace();
		}
		return ret2;	//�ܵ�����˵�������޸�ʧ��
	}
}
