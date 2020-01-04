package dao;
import model.StudentClass;
import util.StringUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import model.CollegeType;
import com.mysql.jdbc.PreparedStatement;
	
//班级信息与数据库的操作

public class ClassDao extends BaseDao{
	
	//添加班级信息
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
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		return false;
	}
	
	
	//班级列表显示
	public List<StudentClass> getClassList(StudentClass studentClass){
		List<StudentClass> retList = new ArrayList<StudentClass>();
		String sql = "select * from s_class";
		//调用StringUtil工具包中的isEmpty判断传进来的班级名称是否为空
		if(!StringUtil.isEmpty(studentClass.getClassName())) {
			sql +=" where className like '%"+studentClass.getClassName()+"%'";
		}
		//调用StringUtil工具包中的isEmpty判断传进来的班级所属学院是否为空
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
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		return retList;
	}
	
		//根据学院名称显示班级列表
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
				// TODO 自动生成的 catch 块
				e.printStackTrace();
			}
			return retList;
		}
		
	//删除班级
	public boolean delete(String id) {
		String sql = "delete from s_class where id=?";
		try {
			java.sql.PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, id);
			if(ps.executeUpdate()>0) {
				return true;
			}
		} catch (SQLException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		return false;
	}
	
	//更新班级信息
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
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		return false;
	}

}
