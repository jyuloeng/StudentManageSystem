package dao;
import java.sql.*;
import util.DbUtil;

//�ṩ���ݹ���Ĺ�ͬ����
public class BaseDao {
	//���������ݿ������
	public Connection con = new DbUtil().getCon(); 
	
	//�ͷ���Դ
	public void closeDao() {
		try {
			con.close();
		} catch (SQLException e) {
			// TODO �Զ����ɵ� catch ��
			e.printStackTrace();
		}
	}
}
