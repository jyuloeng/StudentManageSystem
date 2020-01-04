package dao;
import java.sql.*;
import util.DbUtil;

//提供数据管理的共同操作
public class BaseDao {
	//建立与数据库的连接
	public Connection con = new DbUtil().getCon(); 
	
	//释放资源
	public void closeDao() {
		try {
			con.close();
		} catch (SQLException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
	}
}
