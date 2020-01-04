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

//��ʦ��Ϣ�����ݿ�Ĳ���

public class TeacherDao extends BaseDao{
	InputStream is = null; //����һ��io������
	
	//��ʦ��½
	public Teacher login(Teacher teacher) {
		String sql ="select * from s_teacher where teacherId=? and passWord=?";
		Teacher teacherReset = null;	//����һ���յ�Teacher�෵������
		try {
			java.sql.PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, teacher.getTeacherId());
			ps.setString(2, teacher.getPassWord());
			ResultSet eq = ps.executeQuery();
			if(eq.next()) {
				teacherReset = new Teacher();	//����һ���µ�Teacher����մ����ݿ��õ���Ϣ
				teacherReset.setTeacherId(eq.getString("teacherId"));
				teacherReset.setPassWord(eq.getString("passWord"));
				teacherReset.setId(eq.getInt("id"));
				teacherReset.setTeacherName(eq.getString("teacherName"));
			}
		} catch (SQLException e) {
			// TODO �Զ����ɵ� catch ��
			e.printStackTrace();
		}
		return teacherReset;	//���ش����ݿ��õ���Ӧ��Teacher�����Ϣ
	}
	
	//�޸�����
	public String editPassWord(Teacher teacher,String newPassWord) {
		String sql = "select * from s_teacher where teacherId=? and passWord=?";
		java.sql.PreparedStatement ps = null;
		int id = 0;
		try {
			ps = con.prepareStatement(sql);	//��֤ԭ�����Ƿ������ݿ�������һ��
			ps.setString(1, teacher.getTeacherId());
			ps.setString(2, teacher.getPassWord());
			ResultSet eq = ps.executeQuery();
			if(!eq.next()) {
				String ret = "ԭ�������";
				return ret;
			}
			id = eq.getInt("id");	//���һ�¾ͻ��Teacher���͵Ľ�ʦid
		} catch (SQLException e) {
			// TODO �Զ����ɵ� catch ��
			e.printStackTrace();
		}
		String ret2 = "�����޸�ʧ�ܣ�";
		String sql2 = "update s_teacher set passWord=? where id=?";
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
		return ret2;		//�ܵ�����˵�������޸�ʧ��
	}
	
	//��ӽ�ʦ
	//addressΪ��ñ����ļ���·��
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
			//�½�����������õı����ļ���ַ��Ϣת��ΪBlob����д�����ݿ�
			ps.setBlob(7, new FileInputStream(address));
			if(ps.executeUpdate()>0) {
				return true;
			}
		} catch (SQLException | FileNotFoundException e) {
			// TODO �Զ����ɵ� catch ��
			e.printStackTrace();
		}
		return false;
	}
	
	//��ʦ�б���ʾ
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
			// TODO �Զ����ɵ� catch ��
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
			// TODO �Զ����ɵ� catch ��
			e.printStackTrace();
		}
		return retList;
	}
	
	
	//ɾ����ʦ
	public boolean delete(String id) {
		String sql = "delete from s_teacher where id=?";
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
	
	//���½�ʦ��Ϣ
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
				// TODO �Զ����ɵ� catch ��
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
			// TODO �Զ����ɵ� catch ��
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			// TODO �Զ����ɵ� catch ��
			e.printStackTrace();
		}
			return false;
		}
		return false;
	
	}
			//�ж��Ƿ��Ѿ�¼��
			public boolean isAdd(Teacher teacher) {
				String sql = "select * from s_teacher where teacherId=?";
				try {
					java.sql.PreparedStatement ps = con.prepareStatement(sql);
					ps.setString(1, teacher.getTeacherId());
					ResultSet eq =ps.executeQuery();
					//ԭ��Ϊ��ѯ���ݿ��Ƿ��������Ϣ���еĻ��ͷ���true��ʾ
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
