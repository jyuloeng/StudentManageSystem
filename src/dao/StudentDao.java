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

//ѧ����Ϣ�����ݿ�Ĳ���

public class StudentDao extends BaseDao {
	
	//ѧ����½
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
			// TODO �Զ����ɵ� catch ��
			e.printStackTrace();
		}
		return studentReset;
	}
	
	//�޸�ѧ������
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
				String ret = "ԭ�������";
				return ret;
			}
			id=eq.getInt("id");
		} catch (SQLException e) {
			// TODO �Զ����ɵ� catch ��
			e.printStackTrace();
		}
		String ret2 = "�����޸�ʧ�ܣ�";
		String sql2 = "update s_student set passWord=? where id =?";
		try {
			ps = con.prepareStatement(sql2);
			ps.setString(1, newPassWord);
			ps.setInt(2, id);
			if(ps.executeUpdate()>0) {
				ret2 = "�����޸ĳɹ���";
			}
		} catch (SQLException e) {
			// TODO �Զ����ɵ� catch ��
			e.printStackTrace();
		}
		return ret2;
	}
	
	//���ѧ��
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
			// TODO �Զ����ɵ� catch ��
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			// TODO �Զ����ɵ� catch ��
			e.printStackTrace();
		}
		return false;
	}
	
	//ѧ���б����ʾ
	public List<Student> getStudentList(Student student){
		List<Student> retList = new ArrayList<Student>();
		String sql = "select * from s_student";
		//����StringUtil���߰��е�isEmpty�жϴ�������ѧ�������Ƿ�Ϊ��	
		if(!StringUtil.isEmpty(student.getStudentName())){
			sql += " where studentName like '%" + student.getStudentName()+"%'";
		}
		//����StringUtil���߰��е�isEmpty�жϴ�������ѧ��ban'j�Ƿ�Ϊ��	
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
			// TODO �Զ����ɵ� catch ��
			e.printStackTrace();
		}
		return retList;
	}
	
		//���ݰ༶��ʾѧ��������
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
				// TODO �Զ����ɵ� catch ��
				e.printStackTrace();
			}
			return retList;
		}
		
	//ɾ��ѧ��
	public boolean delete(String id) {
		String sql = "delete from s_student where id=?";
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
	//����ѧ����Ϣ
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
			// TODO �Զ����ɵ� catch ��
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			// TODO �Զ����ɵ� catch ��
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
				// TODO �Զ����ɵ� catch ��
				e.printStackTrace();
			} 
			return false;
		}
		return false;
	}
	//�ж��Ƿ��Ѿ�¼��
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
				// TODO �Զ����ɵ� catch ��
				e.printStackTrace();
		}
			return false;
		}
}
