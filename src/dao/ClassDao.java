package dao;
import model.StudentClass;
import util.StringUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import model.CollegeType;
import com.mysql.jdbc.PreparedStatement;
	
//�༶��Ϣ�����ݿ�Ĳ���

public class ClassDao extends BaseDao{
	
	//��Ӱ༶��Ϣ
	public boolean addClass(StudentClass cl) {
		String sql = "insert into s_class values(null,?,?,?)";
		try {
			java.sql.PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, cl.getClassNumber());
			ps.setString(2, cl.getClassName());
			ps.setString(3, cl.getCollegeType());
			if(ps.executeUpdate()>0) {
				return true;
			}
		} catch (SQLException e) {
			// TODO �Զ����ɵ� catch ��
			e.printStackTrace();
		}
		return false;
	}
	
	
	//�༶�б���ʾ
	public List<StudentClass> getClassList(StudentClass studentClass){
		List<StudentClass> retList = new ArrayList<StudentClass>();
		String sql = "select * from s_class";
		//����StringUtil���߰��е�isEmpty�жϴ������İ༶�����Ƿ�Ϊ��
		if(!StringUtil.isEmpty(studentClass.getClassName())) {
			sql +=" where className like '%"+studentClass.getClassName()+"%'";
		}
		//����StringUtil���߰��е�isEmpty�жϴ������İ༶����ѧԺ�Ƿ�Ϊ��
		if(!StringUtil.isEmpty(studentClass.getCollegeType())) {
			sql +=" where collegeType like '%"+studentClass.getCollegeType()+"%'";
		}
		try {
			java.sql.PreparedStatement ps = con.prepareStatement(sql);
			
			ResultSet executeQuery = ps.executeQuery();
			while(executeQuery.next()) {
				StudentClass sc = new StudentClass();
				sc.setId(executeQuery.getInt("id"));
				sc.setClassNumber(executeQuery.getString("classNumber"));
				sc.setClassName(executeQuery.getString("className"));
				sc.setCollegeType(executeQuery.getString("collegeType"));
				retList.add(sc);
			}
		} catch (SQLException e) {
			// TODO �Զ����ɵ� catch ��
			e.printStackTrace();
		}
		return retList;
	}
	
		//����ѧԺ������ʾ�༶�б�
		public List<StudentClass> getClassList(StudentClass studentClass,String collegeType){
			List<StudentClass> retList = new ArrayList<StudentClass>();
			String sql = "select className from s_class where collegeType=?";
			try {
				java.sql.PreparedStatement ps = con.prepareStatement(sql);
				ps.setString(1, collegeType);
				ResultSet executeQuery = ps.executeQuery();
				while(executeQuery.next()) {
					StudentClass sc = new StudentClass();
					sc.setClassName(executeQuery.getString("className"));
					retList.add(sc);
				}
			} catch (SQLException e) {
				// TODO �Զ����ɵ� catch ��
				e.printStackTrace();
			}
			return retList;
		}
		
	//ɾ���༶
	public boolean delete(String id) {
		String sql = "delete from s_class where id=?";
		try {
			java.sql.PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, id);
			if(ps.executeUpdate()>0) {
				return true;
			}
		} catch (SQLException e) {
			// TODO �Զ����ɵ� catch ��
			e.printStackTrace();
		}
		return false;
	}
	
	//���°༶��Ϣ
	public boolean update(StudentClass sc) {
		String sql = "update s_class set classNumber=?,"
				+ "className=?,collegeType=? where id=?";
		try {
			java.sql.PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, sc.getClassNumber());
			ps.setString(2, sc.getClassName());
			ps.setString(3, sc.getCollegeType());
			ps.setInt(4, sc.getId());
			if(ps.executeUpdate()>0) {
				return true;
			}
		} catch (SQLException e) {
			// TODO �Զ����ɵ� catch ��
			e.printStackTrace();
		}
		return false;
	}

}
