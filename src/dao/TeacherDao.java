package dao;

import java.util.ArrayList;
import java.util.List;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.sql.*;
import model.CollegeType;
import model.Student;

import com.mysql.jdbc.PreparedStatement;
import model.StudentClass;
import model.Teacher;
import util.StringUtil;

//老师信息与数据库的操作

public class TeacherDao extends BaseDao{
	InputStream is = null; //定义一个io输入流
	
	//教师登陆
	public Teacher login(Teacher teacher) {
		String sql ="select * from s_teacher where teacherId=? and passWord=?";
		Teacher teacherReset = null;	//定义一个空的Teacher类返回类型
		try {
			java.sql.PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, teacher.getTeacherId());
			ps.setString(2, teacher.getPassWord());
			ResultSet eq = ps.executeQuery();
			if(eq.next()) {
				teacherReset = new Teacher();	//创建一个新的Teacher类接收从数据库获得的信息
				teacherReset.setTeacherId(eq.getString("teacherId"));
				teacherReset.setPassWord(eq.getString("passWord"));
				teacherReset.setId(eq.getInt("id"));
				teacherReset.setTeacherName(eq.getString("teacherName"));
			}
		} catch (SQLException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		return teacherReset;	//返回从数据库获得的相应的Teacher类的信息
	}
	
	//修改密码
	public String editPassWord(Teacher teacher,String newPassWord) {
		String sql = "select * from s_teacher where teacherId=? and passWord=?";
		java.sql.PreparedStatement ps = null;
		int id = 0;
		try {
			ps = con.prepareStatement(sql);	//验证原密码是否与数据库中数据一致
			ps.setString(1, teacher.getTeacherId());
			ps.setString(2, teacher.getPassWord());
			ResultSet eq = ps.executeQuery();
			if(!eq.next()) {
				String ret = "原密码错误！";
				return ret;
			}
			id = eq.getInt("id");	//如果一致就获得Teacher类型的教师id
		} catch (SQLException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		String ret2 = "密码修改失败！";
		String sql2 = "update s_teacher set passWord=? where id=?";
		try {
			ps = con.prepareStatement(sql2);
			ps.setString(1, newPassWord);
			ps.setInt(2, id);
			if(ps.executeUpdate()>0) {
				ret2 = "密码修改成功！";
			}
		} catch (SQLException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		return ret2;		//能到这里说明密码修改失败
	}
	
	//添加教师
	//address为获得本地文件的路径
	public boolean addTeacher(Teacher tc,String address) {
		String sql = "insert into s_teacher values(null,?,?,?,?,?,?,?)";
		try {
			java.sql.PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, tc.getTeacherId());
			ps.setString(2, tc.getPassWord());
			ps.setString(3, tc.getTeacherName());
			ps.setString(4, tc.getTeacherSex());
			ps.setString(5, tc.getTeacherCollege());
			ps.setString(6, tc.getEntryYear());
			//新建输入流将获得的本地文件地址信息转换为Blob类型写进数据库
			ps.setBlob(7, new FileInputStream(address));
			if(ps.executeUpdate()>0) {
				return true;
			}
		} catch (SQLException | FileNotFoundException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		return false;
	}
	
	//教师列表显示
	public List<Teacher> getTeacherList(Teacher teacher){
		List<Teacher> retList = new ArrayList<Teacher>();
		String sql = "select * from s_teacher";
		if(!StringUtil.isEmpty(teacher.getTeacherName())) {
			sql += " where teacherName like '%"+teacher.getTeacherName()+"%'";
		}
		if(!StringUtil.isEmpty(teacher.getTeacherCollege())) {
		sql +=" where teacherCollege like '%"+teacher.getTeacherCollege()+"%'";
	}
		try {
			java.sql.PreparedStatement ps = con.prepareStatement(sql);
			
			ResultSet eq = ps.executeQuery();
			while(eq.next()) {
				Teacher tc = new Teacher();
				tc.setId(eq.getInt("id"));
				tc.setTeacherId(eq.getString("teacherid"));
				tc.setPassWord(eq.getString("passWord"));
				tc.setTeacherName(eq.getString("teacherName"));
				tc.setTeacherSex(eq.getString("teacherSex"));
				tc.setTeacherCollege(eq.getString("teacherCollege"));
				tc.setEntryYear(eq.getString("entryYear"));
				tc.setBlob(eq.getBlob("photo"));
				retList.add(tc);
			}
		} catch (SQLException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		return retList;
	}
	
	public List<Teacher> getTeacherList(Teacher teacher,String collegeType){
		List<Teacher> retList = new ArrayList<Teacher>();
		String sql = "select teacherName FROM s_teacher WHERE teacherCollege=?";
		
		try {
			java.sql.PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, collegeType);
			ResultSet eq = ps.executeQuery();
			while(eq.next()) {
				Teacher tc = new Teacher();
				tc.setTeacherName(eq.getString("teacherName"));
				retList.add(tc);
			}
		} catch (SQLException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		return retList;
	}
	
	
	//删除教师
	public boolean delete(String id) {
		String sql = "delete from s_teacher where id=?";
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
	
	//更新教师信息
	public boolean update(Teacher tc,String address) {
		if(StringUtil.isEmpty(address)) {
			String sql = "update s_teacher set teacherId=?,passWord=?,teacherName=?,"
					+ "teacherSex=?,teacherCollege=?,entryYear=? where id=?";
			try {
				java.sql.PreparedStatement ps = con.prepareStatement(sql);
				ps.setString(1, tc.getTeacherId());
				ps.setString(2, tc.getAffpassWord());
				ps.setString(3, tc.getTeacherName());
				ps.setString(4, tc.getTeacherSex());
				ps.setString(5, tc.getTeacherCollege());
				ps.setString(6, tc.getEntryYear());
				ps.setInt(7, tc.getId());
				
				if(ps.executeUpdate()>0) {
					return true;
				}
			} catch (SQLException e) {
				// TODO 自动生成的 catch 块
				e.printStackTrace();
			} 
				return false;
			}
		if(!StringUtil.isEmpty(address)) {
		String sql = "update s_teacher set teacherId=?,passWord=?,teacherName=?,"
				+ "teacherSex=?,teacherCollege=?,entryYear=?,photo=? where id=?";
		try {
			java.sql.PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, tc.getTeacherId());
			ps.setString(2, tc.getAffpassWord());
			ps.setString(3, tc.getTeacherName());
			ps.setString(4, tc.getTeacherSex());
			ps.setString(5, tc.getTeacherCollege());
			ps.setString(6, tc.getEntryYear());
			ps.setBlob(7, new FileInputStream(address));
			ps.setInt(8, tc.getId());
			
			if(ps.executeUpdate()>0) {
				return true;
			}
		} catch (SQLException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
			return false;
		}
		return false;
	
	}
			//判断是否已经录入
			public boolean isAdd(Teacher teacher) {
				String sql = "select * from s_teacher where teacherId=?";
				try {
					java.sql.PreparedStatement ps = con.prepareStatement(sql);
					ps.setString(1, teacher.getTeacherId());
					ResultSet eq =ps.executeQuery();
					//原理为查询数据库是否有这个信息，有的话就返回true表示
					if(eq.next()) {
						return true;
					}
				} catch (SQLException e) {
					// TODO 自动生成的 catch 块
					e.printStackTrace();
			}
				return false;
			}
}
