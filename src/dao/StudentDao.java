package dao;

import java.util.ArrayList;
import java.util.List;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.sql.*;
import model.CollegeType;
import model.Score;
import model.Student;

import com.mysql.jdbc.PreparedStatement;
import model.StudentClass;
import model.Teacher;
import util.StringUtil;

//学生信息与数据库的操作

public class StudentDao extends BaseDao {
	
	//学生登陆
	public Student login(Student student) {
		String sql = "select * from s_student where studentId=? and passWord=?";
		Student studentReset = null;
		try {
			java.sql.PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, student.getStudentId());
			ps.setString(2, student.getPassWord());
			ResultSet eq = ps.executeQuery();
			if(eq.next()) {
				studentReset = new Student();
				studentReset.setId(eq.getInt("id"));
				studentReset.setStudentId(eq.getString("studentId"));
				studentReset.setPassWord(eq.getString("passWord"));
				studentReset.setStudentName(eq.getString("studentName"));
			}
		} catch (SQLException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		return studentReset;
	}
	
	//修改学生密码
	public String editPassWord(Student student,String newPassWord) {
		String sql = "select * from s_student where studentId=? and passWord=?";
		java.sql.PreparedStatement ps = null;
		int id = 0;
		try {
			ps = con.prepareStatement(sql);
			ps.setString(1, student.getStudentId());
			ps.setString(2, student.getPassWord());
			ResultSet eq = ps.executeQuery();
			if(!eq.next()) {
				String ret = "原密码错误";
				return ret;
			}
			id=eq.getInt("id");
		} catch (SQLException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		String ret2 = "密码修改失败！";
		String sql2 = "update s_student set passWord=? where id =?";
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
		return ret2;
	}
	
	//添加学生
	public boolean addStudent(Student sd,String address) {
		String sql = "insert into s_student values(null,?,?,?,?,?,?,?,?)";
		try {
			java.sql.PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, sd.getStudentId());
			ps.setString(2, sd.getPassWord());
			ps.setString(3, sd.getStudentName());
			ps.setString(4, sd.getStudentSex());
			ps.setString(5, sd.getStudentCollege());
			ps.setString(6, sd.getStudentClass());
			ps.setString(7, sd.getEntryYear());
			ps.setBlob(8, new FileInputStream(address));
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
	
	//学生列表的显示
	public List<Student> getStudentList(Student student){
		List<Student> retList = new ArrayList<Student>();
		String sql = "select * from s_student";
		//调用StringUtil工具包中的isEmpty判断传进来的学生姓名是否为空	
		if(!StringUtil.isEmpty(student.getStudentName())){
			sql += " where studentName like '%" + student.getStudentName()+"%'";
		}
		//调用StringUtil工具包中的isEmpty判断传进来的学生ban'j是否为空	
		if(!StringUtil.isEmpty(student.getStudentClass())) {
			sql += " where studentClass like '%" + student.getStudentClass()+"%'";
		}
	
		try {
			java.sql.PreparedStatement ps = con.prepareStatement(sql);
			ResultSet eq = ps.executeQuery();
			while(eq.next()) {
				Student sd =new Student();
				sd.setId(eq.getInt("id"));
				sd.setStudentId(eq.getString("studentId"));
				sd.setPassWord(eq.getString("passWord"));
				sd.setStudentName(eq.getString("studentName"));
				sd.setStudentSex(eq.getString("studentSex"));
				sd.setStudentCollege(eq.getString("studentCollege"));
				sd.setStudentClass(eq.getString("studentClass")); 
				sd.setEntryYear(eq.getString("entryYear"));
				sd.setBlob(eq.getBlob("photo"));
				retList.add(sd);
			}
		} catch (SQLException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		return retList;
	}
	
		//根据班级显示学生的姓名
		public List<Student> getStudentList(Student student,String className){
			List<Student> retList = new ArrayList<Student>();
			String sql = "select studentName from s_student where studentClass=?";
			
			try {
				java.sql.PreparedStatement ps = con.prepareStatement(sql);
				ps.setString(1, className);
				ResultSet eq = ps.executeQuery();
				while(eq.next()) {
					Student sd =new Student();
					sd.setStudentName(eq.getString("studentName"));
					retList.add(sd);
				}
			} catch (SQLException e) {
				// TODO 自动生成的 catch 块
				e.printStackTrace();
			}
			return retList;
		}
		
	//删除学生
	public boolean delete(String id) {
		String sql = "delete from s_student where id=?";
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
	//更新学生信息
	public boolean update(Student sd,String address) {
		if(!StringUtil.isEmpty(address)) {
		String sql = "update s_student set studentId=?,passWord=?,studentName=?,studentSex=?,studentCollege=?,studentClass=?,entryYear=?,photo=? where id=?";
		try {
			java.sql.PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, sd.getStudentId());
			ps.setString(2, sd.getAffpassWord());
			ps.setString(3, sd.getStudentName());
			ps.setString(4, sd.getStudentSex());
			ps.setString(5, sd.getStudentCollege());
			ps.setString(6, sd.getStudentClass());
			ps.setString(7, sd.getEntryYear());
			ps.setBlob(8, new FileInputStream(address));
			ps.setInt(9, sd.getId());
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
		if(StringUtil.isEmpty(address)) {
			String sql = "update s_student set studentId=?,passWord=?,studentName=?,studentSex=?,studentCollege=?,studentClass=?,entryYear=? where id=?";
			try {
				java.sql.PreparedStatement ps = con.prepareStatement(sql);
				ps.setString(1, sd.getStudentId());
				ps.setString(2, sd.getAffpassWord());
				ps.setString(3, sd.getStudentName());
				ps.setString(4, sd.getStudentSex());
				ps.setString(5, sd.getStudentCollege());
				ps.setString(6, sd.getStudentClass());
				ps.setString(7, sd.getEntryYear());
			
				ps.setInt(8, sd.getId());
				if(ps.executeUpdate()>0) {
					return true;
				}
			} catch (SQLException e) {
				// TODO 自动生成的 catch 块
				e.printStackTrace();
			} 
			return false;
		}
		return false;
	}
	//判断是否已经录入
		public boolean isAdd(Student student) {
			String sql = "select * from s_student where studentId=?";
			try {
				java.sql.PreparedStatement ps = con.prepareStatement(sql);
				ps.setString(1, student.getStudentId());
				ResultSet eq =ps.executeQuery();
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
